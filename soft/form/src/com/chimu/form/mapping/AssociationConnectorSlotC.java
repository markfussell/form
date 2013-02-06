/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnectorSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/*package*/ class AssociationConnectorSlotC implements AssociationConnectorSlot { //extends SlotC  {

    //(P)**************** Instance Creating ********************

    /*package*/ AssociationConnectorSlotC(
            String slotName,
            AssociationConnectorPi associationConnector,

            Column             databaseColumn,

            ObjectRetrieverPi  partnerRetriever,
            String             partnerSlotName
            ) {
        this.name = slotName;
        this.associationConnector = associationConnector;
        this.partnerRetriever = partnerRetriever;
        this.databaseColumn = databaseColumn;
        this.partnerSlotName = partnerSlotName;
    }

    /*package*/ AssociationConnectorSlotC(
            String slotName,
            AssociationConnectorPi associationConnector,

            Column          databaseColumn
            ) {
        this.name = slotName;
        this.associationConnector = associationConnector;
        this.databaseColumn = databaseColumn;
    }

        /**
         * This should only be called if we don't already have a
         * partnerRetriever [i.e. explicitly set at creation]
         */
    /*package*/ public void setupRegisterPartner_slotName(
            ObjectRetrieverPi partnerRetriever,
            String            partnerSlotName
            ){
        if (this.partnerRetriever == null) {
            this.partnerRetriever = partnerRetriever;
        };

        if (this.partnerSlotName == null) {
            this.partnerSlotName = partnerSlotName;
        } else {
            if (!this.partnerSlotName.equals(partnerSlotName)) {
                throw new ConfigurationException("Association Connector "+name()+" has conflicting external slot names: "+this.partnerSlotName+" and "+partnerSlotName);
            }
        }
    }



    //(P)*********************** Asking ************************

    public String name() {
        return name;
    }

    public String partnerTableName() {
        return partnerRetriever.tableName();
    }

    public String partnerSlotName() {
        return partnerSlotName;
    }

    public Column partnerColumn() {
        return partnerRetriever.identityKeyColumn();
    }

    public Column associationColumn() {
        return databaseColumn;
    }

    public ObjectRetrieverPi partnerRetriever() {
        return partnerRetriever;
    }

    public AssociationConnectorSlot pairedAssociationSlot() {
        return associationConnector.otherSlotFor(this);
    }

    public ObjectRetrieverPi pairedPartnerRetriever() {
        return pairedAssociationSlot().partnerRetriever();
    }

    public AssociationConnector associationConnector() {
        return associationConnector;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object newColumnValueFromRow (Row row) {
        return extractValue(row);
    }

    public void setRow_usingColumnValue  (Row row, Object value){
        updateRow_withValue(row,value);
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

    public void crossValidate() {
        if (partnerRetriever == null) {
            throw new ConfigurationException("No partner for association slot "+name+" in connector "+associationConnector);
        };
        try {
            Slot partnerSlot = partnerRetriever.slotNamed(partnerSlotName);
            if (partnerSlot == null) {
                throw new ConfigurationException("Partner's slot "+partnerRetriever+"=>"+partnerSlotName+" does not exist");
            }

            Column partnerColumn = partnerRetriever.identityKeyColumn();
            if (databaseColumn.sqlDataType() != partnerColumn.sqlDataType()) {
                throw new ConfigurationException("SQL Data Types don't match {"+databaseColumn.sqlDataType()+" vs "+partnerColumn.sqlDataType()+"} between the association slot "+name+" and the identity slot in "+partnerRetriever.name());
            };
            if (partnerColumn.javaDataType() != databaseColumn.javaDataType()) {
                if (databaseColumn instanceof BasicColumnPi) {
                    ((BasicColumnPi) databaseColumn).setJavaDataType(partnerColumn.javaDataType());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConfigurationException("Partner's slot "+partnerRetriever+"=>"+partnerSlotName+" does not exist",e);
        }
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected AssociationConnectorPi associationConnector;
    protected String              name;
    protected ObjectRetrieverPi   partnerRetriever;
    protected String              partnerSlotName;
    protected Column              databaseColumn;

};