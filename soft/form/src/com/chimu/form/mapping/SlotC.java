/*======================================================================
**
**  File: chimu/form/mapping/SlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.mapping.description.SlotTypeConstants;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

/*package*/ class SlotC implements SlotPi {
    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public int slotType() {
        if ((encoder != null) || (decoder != null)) {
            return SlotTypeConstants.TRANSFORM_SLOT;
        } else {
            return SlotTypeConstants.DIRECT_SLOT;
        }
    }

    /*
    SlotDescription createSlotDescription() {
        SlotDescriptionC()
        slotD.setMapperName(mapper.name());
        slotD.setDatabaseColumnName(databaseColumn.name());


    }

    void buildSlotC(
            String          name,
            ObjectMapper    mapper,
            boolean         isObjectWriter,
            boolean         isRowWriter,
            Column          databaseColumn,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        this.name           = name;
        this.mapper         = mapper;
        this.isObjectWriter = isObjectWriter;
        this.isRowWriter    = isRowWriter;
        this.databaseColumn = databaseColumn;
        this.decoder        = decoder;
        this.encoder        = encoder;
    }

    */

    //**********************************************************
    //(P)**************** Not Implemented Yet ******************
    //**********************************************************

    public void setupGenerateForInsertFunction(Function2Arg generator) {throw new NotImplementedYetException();};
    public void setupGenerateForUpdateFunction(Function2Arg generator) {throw new NotImplementedYetException();};

       /**
         * Setup the getter and setter for the slot (this would actually cause a slot change)
         * but the client shouldn't have to know that.
         */
    public void setupGetterAndSetter(Function1Arg getter, Procedure2Arg setter) {
        throw new NotImplementedYetException();
        //mapper.replaceSlot_withSlot(this,newSlot);
    }

    public void setupGetter(Function1Arg getter) {
        throw new NotImplementedYetException();
    }

    public void setupSetter(Procedure2Arg setter) {
        throw new NotImplementedYetException();
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ SlotC(
            String          name,
            ObjectMapper    mapper,
            boolean         isObjectWriter,
            boolean         isRowWriter,
            Column          databaseColumn,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        this.name           = name;
        this.mapper         = mapper;
        this.isObjectWriter = isObjectWriter;
        this.isRowWriter    = isRowWriter;
        this.databaseColumn = databaseColumn;
        this.decoder        = decoder;
        this.encoder        = encoder;
    }

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public void setupDisableRowWriting() {
        isRowWriter = false;
    };

    public void setupDisableObjectInitializing() {
        isObjectWriter = false;
    };

    public void setupRefreshAfterInsert() {
	    needToRefreshAfterInsert = true;
    }

    public void setupRefreshAfterInsertOrUpdate() {
	    needToRefreshAfterInsert = true;
    	needToRefreshAfterUpdate = true;
   }

    public void setupRefreshAfterUpdate(){
    	needToRefreshAfterUpdate = true;
    }

    public void setupOptimisticLock() {
	    isOptimisticLock = true;
    }

    public void setupOptimisticLockWithPredicate(Predicate2Arg optimisticPredicate) {
	    isOptimisticLock = true;
	    this.optimisticPredicate = optimisticPredicate;
    }

        //IdentityC needs to override and throw an exception for this:
        //ShouldNotImplement("Identity slots can not be wrapped by a holder");
        
        //The main affect of the holderConstructor on program executing is that
        //the holder will need to respond to equality checks by checking its 
        //values.
        
        //Or could have an 'newSlotValueFromRow_unwrapped(...)' but
        //need to make sure that 'form_identityKey' also returns an unwrapped
        //slot value
    public void setupHolderConstructor(Function1Arg aFunctor) {
        this.holderConstructor = aFunctor;
    }
    


    //**************************************
    //(P)********** Validation *************
    //**************************************

    /*package*/ public void crossValidate() {
        return; //[1]

        //[1] Don't need to do anything for a basic Direct slot
    }


    //**********************************************************
    //(P)*********************** Asking ************************
    //**********************************************************

    public String name() {
        return name;
    }

    public ObjectMapper mapper() {
        return mapper;
    }

    public Column dbColumn() {
        return databaseColumn;
    }

    public Column column() {
        return databaseColumn;
    }

    public String columnName() {
        return databaseColumn.name();
    }

    public boolean isRowWriter() {
        return isRowWriter && hasColumnValue();
    }

    public boolean isObjectWriter() {
        return isObjectWriter;
    }

    public boolean isObjectInitializer() {
        return isObjectWriter;
    }

    public boolean isObjectReader() {
        return isObjectWriter();
    }

    public boolean hasColumnValue() {return true;}

    public boolean hasGetter() {return false;}
    public boolean hasSetter() {return false;}

    public boolean isAssociation() {return false;}
    public boolean isForwardAssociation() {return false;}
    public boolean isExternalAssociation(){return false;}

    public boolean isValueNullable() {return databaseColumn.isNullable();}
    public int valueMaximumLength() {return databaseColumn.maximumLength();}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public boolean needToRefreshAfterInsert() {
    	return needToRefreshAfterInsert;
   }

    /*package*/ public boolean needToRefreshAfterUpdate() {
    	return needToRefreshAfterUpdate;
    }

    /*package*/ public boolean isOptimisticLock() {
    	return isOptimisticLock;
    }

    /*package*/ public boolean doOptimisticValuesCheck(Object slotValue, Object rowSlotValue) {
        if (optimisticPredicate == null) {
            if (slotValue == null) return rowSlotValue == null;
            return slotValue.equals(rowSlotValue);
        };
        return optimisticPredicate.isTrueWith_with(slotValue, rowSlotValue);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object newSlotValueFromRow (Row row) {
        Object value = this.extractValue(row);
        return decode(value);
    }

    public Object newSlotValueFromRow_unwrapped (Row row) {
        Object value = this.extractValue(row);
        Object slotValue = decode(value);
        if (holderConstructor != null) slotValue = unwrapHolder(slotValue);
        return slotValue;
    }


    public void setRow_usingSlotValue  (Row row, Object value){
        value = encode(value);
        this.updateRow_withValue(row,value);
    }

    // --------------------------------

    public Object newColumnValueFromRow (Row row) {
        return extractValue(row);
    }

    public void setRow_usingColumnValue  (Row row, Object value){
        updateRow_withValue(row,value);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object newSlotValueFromColumnValue (Object columnValue) {
        return decode(columnValue);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected Object extractValue(Row row) {
        return row.valueForColumn(databaseColumn);  // Either returns a basic object or an array of objects
    }

    protected void updateRow_withValue(Row row, Object value) {
        row.setValueForColumn_to(databaseColumn,value);
    }

        /**
         *@param columnValue(Object)
         *@return slotValue(Object)
         */
    /*package*/ public Object decode(Object columnValue) {
        if (decoder == null) return columnValue;
        Object value = null;
        try {
            value = decoder.valueWith(columnValue);
        } catch (Exception e) {
            System.err.println("For slot "+fullName());
            System.err.println("Decoder "+decoder.toString());
            System.err.println("failed for value "+columnValue);
            e.printStackTrace();
        }
        return value;
    }

        /**
         *@param slotValue(Object)
         *@return columnValue(Object)
         */
    /*package*/ public Object encode(Object slotValue) {
            //TODO: Might want to make this a seperate boolean flag in case
            //we want to wrap construction but not encoding.
            //This would only apply if Identity management was not 
            //required on the slotValue.
        if (holderConstructor != null) slotValue = unwrapHolder(slotValue);
        
        if (encoder == null) return slotValue;
        return encoder.valueWith(slotValue);
    }

    protected Object unwrapHolder(Object slotValueHolder) {
        try {
            return ((Function0Arg) slotValueHolder).value();
        } catch (Exception e) {
            throw new MappingException("Expected a Holder [Function0Arg] object but got "+slotValueHolder.getClass(),e);
        }
    }

        /**
         * This is manually inlined, but it is documented here
         */
    protected Object constructHolder(Function0Arg slotValueGenerator) {
        return holderConstructor.valueWith(slotValueGenerator);
    }


    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    protected String fullName() {
        return mapper.name()+"=>"+name;
    }

    //**********************************************************
    //(P)************** Instance Variables *********************
    //**********************************************************

    protected String          name;
    protected ObjectMapper    mapper;   // This is probably even guaranteed to be a OM
    protected boolean         isObjectWriter = true;
    protected boolean         isRowWriter = true;
    protected boolean         needToRefreshAfterInsert = false;
    protected boolean         needToRefreshAfterUpdate = false;
    protected boolean         isOptimisticLock = false;
    protected Predicate2Arg   optimisticPredicate = null;

    protected Column          databaseColumn;
    protected Function1Arg    decoder;  // DataReader
    protected Function1Arg    encoder;  // DataWriter
    
        /**@type Function1Arg(Function0Arg) **/
    protected Function1Arg             holderConstructor;

    //**********************************************************
    //(P)****************       STATIC       *******************
    //**********************************************************

    static protected String createFullSlotName(ObjectRetriever retriever, String slotName) {
        return retriever.name()+"=>"+slotName;
    }

}