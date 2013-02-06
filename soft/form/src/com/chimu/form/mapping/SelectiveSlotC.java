/*======================================================================
**
**  File: chimu/form/mapping/SelectiveSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

/*package*/ class SelectiveSlotC implements ForwardAssociationSlotPi, ReverseAssociationSlotPi, ExternalAssociationSlotPi {
    /*subsystem*/ public int slotType() {return realSlot.slotType();}
    public void deleteAssociationsFor(MappedObject object) {
        if (isExternalAssociation()) {
            ((ExternalAssociationSlotPi) realSlot).deleteAssociationsFor( object);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }
    public void insertAssociationsFor(MappedObject object, KeyedCollection slotValues) {
        if (isExternalAssociation()) {
            ((ExternalAssociationSlotPi) realSlot).insertAssociationsFor( object,slotValues);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }
    public void updateAssociationsFor(MappedObject object, KeyedCollection slotValues) {
        if (isExternalAssociation()) {
            ((ExternalAssociationSlotPi) realSlot).updateAssociationsFor( object,slotValues);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public AssociationConnector     associationConnector() {return ((ExternalAssociationSlotPi) realSlot).associationConnector();}
    public AssociationConnectorSlot associationSlot() {return ((ExternalAssociationSlotPi) realSlot).associationSlot();}

    //**********************************************************
    //(P)**************** Not Implemented Yet ******************
    //**********************************************************

    public void setupGenerateForInsertFunction(Function2Arg generator) {throw new ShouldNotImplementException();}
    public void setupGenerateForUpdateFunction(Function2Arg generator) {throw new ShouldNotImplementException();}

    public void setupUpdatingByDeltas() {throw new ShouldNotImplementException();}
    public void setupUpdatingByReplacing() {throw new ShouldNotImplementException();}
    public void setupContainsPartnerNot() {throw new ShouldNotImplementException();}
    public void setupContainsPartner() {throw new ShouldNotImplementException();}

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ SelectiveSlotC(
            ObjectRetriever          retriever,
            SlotPi                   realSlot
            ) {
        this.retriever         = retriever;
        this.realSlot       = realSlot;
    }

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************
    //(P)********** Validation *************
    //**************************************

    /*package*/ public void crossValidate() {
        return; //[1]

        //[1] Don't need to do anything for a SelectiveSlot (handled by the real slot's mapper)
    }

    //**********************************************************
    //(P)*********************** Asking ************************
    //**********************************************************


    public String name() {
        return realSlot.name();
    }

    public ObjectMapper mapper() {
        throw new ShouldNotImplementException();
    }

    public Column dbColumn() {
        return realSlot.dbColumn();
    }

    public Column column() {
        return realSlot.column();
    }

    public String columnName() {
        return realSlot.columnName();
    }

    public boolean isRowWriter() {throw new ShouldNotImplementException();}
    public boolean isObjectWriter() {throw new ShouldNotImplementException();}
    public boolean isObjectInitializer() {throw new ShouldNotImplementException();}
    public boolean isObjectReader() {throw new ShouldNotImplementException();}


    public boolean hasColumnValue() {return realSlot.hasColumnValue();}
    public boolean hasGetter() {return false;}
    public boolean hasSetter() {return false;}

    public boolean isAssociation() {return realSlot.isAssociation();}
    public boolean isForwardAssociation() {return realSlot.isForwardAssociation();}
    public boolean isReverseAssociation() {return realSlot.isAssociation() && !realSlot.isForwardAssociation() &&!realSlot.isExternalAssociation() ;}
    public boolean isExternalAssociation(){return realSlot.isExternalAssociation();}

    public boolean isValueNullable() {return realSlot.isValueNullable();}
    public int valueMaximumLength() {return realSlot.valueMaximumLength();}

    //**********************************************************
    //**********************************************************
    //**********************************************************
        //Added

    public ObjectRetriever partnerRetriever() {
        if (isAssociation()) {
            return ((AssociationSlotPi) realSlot).partnerRetriever();
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public boolean isOneToOne() {
        if (isReverseAssociation()) {
            return ((ReverseAssociationSlotPi) realSlot).isOneToOne();
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public boolean isToOne() {
        if (isExternalAssociation()) {
            return ((ExternalAssociationSlotPi) realSlot).isToOne();
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void setupIsOneToOne() {throw new ShouldNotImplementException();}
    public void setupIsToOne() {throw new ShouldNotImplementException();}
    public void setupHolderConstructor(Function1Arg aFunctor) {throw new ShouldNotImplementException();}

    public Object newColumnValueFromObject (Object value) {
        if (isAssociation()) {
            return ((SetterSlotPi) realSlot).newColumnValueFromObject(value);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public Object newSlotValueFromObject (Object value) {
        if (isAssociation()) {
            return ((SetterSlotPi) realSlot).newSlotValueFromObject(value);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void setObject_usingColumnValue (Object domainObject, Object value) {
        if (isAssociation()) {
            ((SetterSlotPi) realSlot).setObject_usingColumnValue(domainObject,value);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void setObject_usingSlotValue (Object domainObject, Object value) {
        if (isAssociation()) {
            ((SetterSlotPi) realSlot).setObject_usingSlotValue(domainObject,value);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void setObject_usingRow (Object domainObject, Row row) {
        if (isAssociation()) {
            ((SetterSlotPi) realSlot).setObject_usingRow(domainObject,row);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void setRow_usingObject (Row row, Object domainObject) {
        if (isAssociation()) {
            ((SetterSlotPi) realSlot).setRow_usingObject(row,domainObject);
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public ForwardAssociationSlotSi partnerSlot() {
        if (isReverseAssociation()) {
            return ((ReverseAssociationSlotPi) realSlot).partnerSlot();
        } else {
            throw new ShouldNotImplementException("Inappropriate for this type of slot: "+realSlot);
        }
    }

    public void deleteCascadeFor(MappedObject object) {throw new ShouldNotImplementException();}

    //**********************************************************
    //**********************************************************
    //**********************************************************


    public void setupDisableRowWriting() {throw new ShouldNotImplementException();}
    public void setupDisableObjectInitializing() {throw new ShouldNotImplementException();}
    public void setupRefreshAfterInsert() {throw new ShouldNotImplementException();}
    public void setupRefreshAfterInsertOrUpdate() {throw new ShouldNotImplementException();}
    public void setupRefreshAfterUpdate() {throw new ShouldNotImplementException();}
    public void setupOptimisticLock() {throw new ShouldNotImplementException();}
    public void setupOptimisticLockWithPredicate(Predicate2Arg predicate) {throw new ShouldNotImplementException();}
   /*package*/ public boolean needToRefreshAfterInsert() {throw new ShouldNotImplementException();}
    /*package*/ public boolean needToRefreshAfterUpdate() {throw new ShouldNotImplementException();}
    /*package*/ public boolean isOptimisticLock() {throw new ShouldNotImplementException();}
    public void setupGetterAndSetter(Function1Arg getter, Procedure2Arg setter) {throw new ShouldNotImplementException();}
    public void setupGetter(Function1Arg getter) {throw new ShouldNotImplementException();}
    public void setupSetter(Procedure2Arg setter) {throw new ShouldNotImplementException();}

    // **************************************************************
    // **************************************************************

    public Object newSlotValueFromRow (Row row) {
        return realSlot.newSlotValueFromRow(row);
    }

    public void setRow_usingSlotValue  (Row row, Object value){
        realSlot.setRow_usingSlotValue(row,value);
    }

    // --------------------------------

    public Object newColumnValueFromRow (Row row) {
        return realSlot.newColumnValueFromRow(row);
    }

    public void setRow_usingColumnValue  (Row row, Object value){
        realSlot.setRow_usingColumnValue(row,value);
    }

    // **************************************************************
    // **************************************************************

    public Object newSlotValueFromColumnValue (Object columnValue) {
        return realSlot.newSlotValueFromColumnValue(columnValue);
    }

    // **************************************************************
    // **************************************************************

        /**
         *@param columnValue(Object)
         *@return slotValue(Object)
         */
    /*package*/ public Object decode(Object columnValue) {
        return realSlot.decode(columnValue);
    }

        /**
         *@param slotValue(Object)
         *@return columnValue(Object)
         */
    /*package*/ public Object encode(Object slotValue) {
        return realSlot.encode(slotValue);
    }

    //**********************************************************
    //(P)************** Instance Variables *********************
    //**********************************************************

    protected ObjectRetriever   retriever;
    protected SlotPi            realSlot;
}