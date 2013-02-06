/*======================================================================
**
**  File: chimu/form/mapping/ForwardAssociationSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.mapping.description.SlotTypeConstants;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;


/*package*/ class ForwardAssociationSlotC extends SetterSlotC implements ForwardAssociationSlotPi {
    /*subsystem*/ public int slotType() {
        return SlotTypeConstants.FORWARD_SLOT;
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

    /*package*/ ForwardAssociationSlotC(
            String          name,
            ObjectMapper    mapper,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column               mappedColumn,
            ObjectRetrieverPi    partnerRetriever
            ) {
        super(name,mapper,
            getter,setter,
            mappedColumn,
            null, //partnerRetriever.newStubObjectFunction(),
            null //partnerRetriever.encodeObjectFunction();
            );
        this.partnerRetriever = partnerRetriever;
    };

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************
    //(P)********** Validation *************
    //**************************************

    /*package*/ public void crossValidate() {
        Column partnerColumn = partnerRetriever.identityKeyColumn();
        if (databaseColumn.sqlDataType() != partnerColumn.sqlDataType()) {
            throw new ConfigurationException("SQL Data Types don't match {"+databaseColumn.sqlDataType()+" vs "+partnerColumn.sqlDataType()+"} between the forward slot "+fullName()+" and the identity slot in "+partnerRetriever.name());
        };
        if (partnerColumn.javaDataType() != databaseColumn.javaDataType()) {
            if (databaseColumn instanceof BasicColumnPi) {
                ((BasicColumnPi) databaseColumn).setJavaDataType(partnerColumn.javaDataType());
            }
        }
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

        /**
         *I don't need to do anything for this
         */
    /*package*/ public void deleteCascadeFor(MappedObject object) {};

    public boolean isAssociation() {return true;};
    public boolean isForwardAssociation() {return true;};


    public ObjectRetriever partnerRetriever() {return partnerRetriever;};

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

        /**
         *@param slotValue(Object)
         *@return columnValue(Object)
         */
    /*package*/ public Object encode(Object slotValue) {
        if (slotValue == null) return null;
        try {
            MappedObject mo = (MappedObject) slotValue;
            return ((ObjectMapperPi) mo.form_objectMapper()).encodeObject(mo);
        } catch (ClassCastException e) {
            throw new MappingException("Tried to encode object "+slotValue+" but it is not a MappedObject",e);
        };
    }

        /**
         *@param columnValue(Object)
         *@return slotValue(Object)
         */
    /*package*/ public Object decode(Object columnValue) {
        return partnerRetriever.newStubObject(columnValue);
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected ObjectRetrieverPi  partnerRetriever;
};