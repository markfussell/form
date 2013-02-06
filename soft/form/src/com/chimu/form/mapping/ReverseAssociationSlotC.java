/*======================================================================
**
**  File: chimu/form/mapping/ReverseAssociationSlotC.java
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


/*package*/  class ReverseAssociationSlotC
                    extends SetterSlotC
                    implements ReverseAssociationSlotPi {

    /*subsystem*/ public int slotType() {
        return SlotTypeConstants.REVERSE_SLOT;
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

    /*package*/ ReverseAssociationSlotC(
            String          slotName,
            ObjectMapper    mapper,

            Function1Arg     getter,
            Procedure2Arg    setter,

            ObjectRetrieverPi  partnerRetriever,
            String             partnerSlotName
            ) {
        super(slotName,mapper,
            getter,setter,
            ((ObjectMapperPi) mapper).identityKeyColumn(),
            null,
            null);
        this.partnerSlotName    = partnerSlotName;
        this.partnerRetriever   = partnerRetriever;
    };

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public void setupIsOneToOne() {
        isOneToOne = true;
    }

    public void setupHolderConstructor(Function1Arg aFunctor) {
        holderConstructor = aFunctor;
    }

    //**************************************
    //(P)********** Validation *************
    //**************************************

    /*package*/ public void crossValidate() {
        Slot partnerSlot = partnerRetriever.slotNamed(partnerSlotName);
        if (partnerSlot == null) {
            throw new ConfigurationException(
                    "Reverse association slot "+fullName()+
                    " refers to nonexistent slot "+createFullSlotName(partnerRetriever, partnerSlotName)
                );
        };
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public boolean isAssociation() {return true;}
    public boolean isForwardAssociation() {return false;}
    public boolean hasColumnValue() {return false;}

    public boolean isOneToOne() {
        return isOneToOne;
    };

    public ObjectRetriever partnerRetriever() {return partnerRetriever;};

    /*subsystem*/ public ForwardAssociationSlotSi partnerSlot() {return (ForwardAssociationSlotSi) partnerRetriever.slotNamed(partnerSlotName);};

    //**********************************************************
    //(P)****************      PACKAGE      ********************
    //**********************************************************

    /*package*/ public void deleteCascadeFor(MappedObject object) {
        // throw new NotImplementedYetException();
    };

    /*package*/ public Object decode(final Object columnValue) {
        if (isOneToOne) {
            if (holderConstructor == null) {
                return partnerRetriever.findWhereSlotNamed_equalsColumnValue(partnerSlotName, columnValue);
            } else {
                return holderConstructor.valueWith(
                        new Function0Arg() {public Object value() {
                            try {
                                return partnerRetriever.findWhereSlotNamed_equalsColumnValue(partnerSlotName, columnValue);
                            } catch (Exception e2) {
                                throw new MappingException("Couldn't retrieve the slot value for "+partnerSlotName,e2);
                            };
                        }}
                    );
            }
        } else {
            return CollectionsPack.newListFuture(
                new Function0Arg() {public Object value() {
                    return partnerRetriever.selectWhereSlotNamed_equalsColumnValue(partnerSlotName, columnValue);
                }}
            );
        };
    };

    /*package*/ public Object encode(Object slotValue) {
        return null;       // Reverse associations are not part of writing
        //if (holderConstructor != null)
    };

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final ObjectRetrieverPi  partnerRetriever;
    protected final String             partnerSlotName;
    protected boolean                  isOneToOne = false;
    
};