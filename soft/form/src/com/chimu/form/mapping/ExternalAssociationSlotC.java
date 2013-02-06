/*======================================================================
**
**  File: chimu/form/mapping/ExternalAssociationSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.mapping.description.SlotTypeConstants;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

/*package*/ class ExternalAssociationSlotC extends SetterSlotC implements ExternalAssociationSlotPi {
    /*subsystem*/ public int slotType() {
        return SlotTypeConstants.EXTERNAL_SLOT;
    }

    //**********************************************************
    //(P)**************** Not Implemented Yet ******************
    //**********************************************************
    public void    setupContainsPartner() {throw new NotImplementedYetException();};
    public void    setupContainsPartnerNot() {throw new NotImplementedYetException();};
    public void    setupUpdatingByDeltas() {throw new NotImplementedYetException();} ;
    public void    setupUpdatingByReplacing() {throw new NotImplementedYetException();};

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ ExternalAssociationSlotC(
            String          name,
            ObjectMapper    mapper,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column                     mappedColumn,

            AssociationConnectorPi     associationConnector,
            String                     myAssociationSlotName
            ) {
        super(name,mapper,
            getter,setter,
            mappedColumn,
            null,   //partnerMapper.newStubObjectFunction(),
            null);  //partnerMapper.encodeObjectFunction());
        this.myAssociationSlotName = myAssociationSlotName;
        this.associationConnector = associationConnector;

        associationConnector.setupRegisterExternalSlot(this,myAssociationSlotName);
    };

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public void setupIsToOne() {
        isToOne = true;
    };

    public boolean isToOne() {
        return isToOne;
    };

    public void setupHolderConstructor(Function1Arg aFunctor) {
        holderConstructor = aFunctor;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean isAssociation() {return true;};
    public boolean isExternalAssociation() {return true;};
    public boolean hasColumnValue() {return false;};

    /*package*/ public void deleteCascadeFor(MappedObject object) {
        deleteAssociationsFor(object);
    };

    /*package*/ public void deleteAssociationsFor(MappedObject object) {
        try {
            associationConnector.deleteWhereSlotNamed_equals(myAssociationSlotName,object);
        } catch (Exception e) {
            throw new MappingException("Could not delete the associations",e);
        };
    };

    /*package*/ public void insertAssociationsFor(MappedObject object, KeyedCollection slotValues) {
        try {
            if (isToOne) {
                Object slotValue = slotValues.atKey(name); 
                if (holderConstructor != null) slotValue = unwrapHolder(slotValue);
                
                MappedObject partnerObject = (MappedObject) slotValue; //newSlotValueFromObject(object);
                if (partnerObject != null) {
                    associationConnector.insertObject_for_fromSlotNamed(partnerObject,object,myAssociationSlotName);
                };
            } else {
                Collection partnerObjects = (Collection) slotValues.atKey(name); //newSlotValueFromObject(object);
                if (partnerObjects != null) {
                    associationConnector.insertCollection_for_fromSlotNamed(partnerObjects,object,myAssociationSlotName);
                };
            };
        } catch (Exception e) {
            throw new MappingException("Could not insert the associations",e);
        };
    };
    /*package*/ public void updateAssociationsFor(MappedObject object, KeyedCollection slotValues) {
        try {
            if (isToOne) {
                Object slotValue = slotValues.atKey(name); 
                if (holderConstructor != null) slotValue = unwrapHolder(slotValue);
                
                MappedObject partnerObject = (MappedObject) slotValue; //newSlotValueFromObject(object);
                if (partnerObject != null) {
                    associationConnector.updateObject_for_fromSlotNamed(partnerObject,object,myAssociationSlotName);
                };
            } else {
                Collection partnerObjects = (Collection) slotValues.atKey(name); //newSlotValueFromObject(object);
                if (partnerObjects != null) {
                    associationConnector.updateCollection_for_fromSlotNamed(partnerObjects,object,myAssociationSlotName);
                };
            };
        } catch (Exception e) {
            throw new MappingException("Could not update the associations",e);
        };
    };

    // **************************************************************
    // **************************************************************

    public Object newSlotValueFromRow (Row row) {
        Object ccValue = this.extractValue(row);
        return this.decode(ccValue);
    };

    // **************************************************************
    // **************************************************************

    public Object newSlotValueFromColumnValue (Object ccValue) {
        return this.decode(ccValue);
    };

    // **************************************************************
    // **************************************************************

    /*package*/ public Object decode(final Object columnValue) {
        //Can still do a one to one check
        //Also, associationConnector can either build a bunch of stubs, or build the full objects
        //Not sure what the difference in performance would be
        if (columnValue == null) return null;
        try {
            if (isToOne) {
                if (holderConstructor == null) {
                    return associationConnector.findWhereSlotNamed_equalsColumnValue(myAssociationSlotName, columnValue);
                } else {
                    return holderConstructor.valueWith(
                            new Function0Arg() {public Object value() {
                                try {
                                    return associationConnector.findWhereSlotNamed_equalsColumnValue(myAssociationSlotName,columnValue);
                                } catch (Exception e2) {
                                    throw new MappingException("Couldn't retrieve the slot value for "+myAssociationSlotName,e2);
                                };
                            }}
                        );
                }
            } else {
                return CollectionsPack.newListFuture(
                    new Function0Arg() {public Object value() {
                        try {
                            return associationConnector.selectWhereSlotNamed_equalsColumnValue(myAssociationSlotName,columnValue);
                        } catch (Exception e2) {
                            throw new MappingException("Couldn't retrieve the slot value for "+myAssociationSlotName,e2);
                        };
                    }}
                );
            }
        } catch (Exception e) {
            throw new MappingException("Couldn't retrieve the slot value for "+myAssociationSlotName,e);
        };
    };

    public ObjectRetriever partnerRetriever() {return associationSlot().pairedPartnerRetriever();};

    /*subsystem(Query)*/ public AssociationConnector associationConnector() {return associationConnector;};
    /*subsystem(Query)*/ public AssociationConnectorSlot associationSlot() {return (AssociationConnectorSlot) associationConnector.associationSlotNamed(myAssociationSlotName);};

    //(P) ************* Instance Variables **************

    protected AssociationConnectorPi     associationConnector;
    protected String                     myAssociationSlotName;

    protected boolean                    isToOne = false;


};