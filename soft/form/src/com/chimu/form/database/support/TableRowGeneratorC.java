/*======================================================================
**
**  File: chimu/form/database/support/TableRowGeneratorC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.support;

import com.chimu.form.database.Table;
import com.chimu.form.database.TableSi;
import com.chimu.form.database.Row;
import com.chimu.form.database.RowPi;
import com.chimu.form.database.ColumnPi;
import com.chimu.form.database.ConfigurationException;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import com.chimu.kernel.streams.GeneratorAbsC;

/**
TableRowGeneratorC implements one type of DatabaseGenerator:
that which uses a single row in a table to store counter values.
**/
public class TableRowGeneratorC
                    extends    GeneratorAbsC
                    implements GeneratorFromDatabase {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public TableRowGeneratorC(Table table, Object distinguishingValue, String counterColumn) {
        this.table = (TableSi) table;
        this.distinguishingValue = distinguishingValue;
        this.counterColumn = counterColumn;
    }

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************


    public void setupRead_write_transformations(
            Function1Arg readFunction,
            Function1Arg writeFunction) {
        columnToValueFunction = readFunction;
        valueToColumnFunction = writeFunction;
    }

    public void setupMakeWriteBack() {
        shouldWriteBack = true;
    }

    public void setupDontCommitConnection() {
        canCommitConnection = false;
    }

    public void setupCommitConnection() {
        canCommitConnection = true;
    }

    public void setupStartingValue(Object value) {
        startingValue = value;
    }

    public void setupTable(Table table) {
        this.table = (TableSi) table;
    }

        /**
         * needToFetchPredicate can be null which means use
         * currentValue.equals(limitValue) as the predicate
         */
    public void setupNext_limit_needToFetch_functors(
             Function1Arg nextValueFunction,
             Function1Arg limitValueFunction,
             Predicate2Arg needToFetchPredicate) {
        this.nextValueFunction = nextValueFunction;
        this.limitValueFunction = limitValueFunction;
        this.needToFetchPredicate = needToFetchPredicate;
    }

    public void doneSetup() {
        if (table == null) throw new ConfigurationException(
            "No table specified for generator");
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************


    public void clearCache() {
        currentValue = null;
    }

    public Object currentValue() {
        return currentValue;
    }

    public Object nextValue() {
        if (needToFetch()) {
            if (shouldWriteBack) {
                currentValue = lockAndRetrieveValue();
            } else {
                currentValue = retrieveValue();
            };
            if (currentValue == null) {
                currentValue = startingValue;
                limitValue = limitValueFor(currentValue);
                if (shouldWriteBack) {
                    insertValue_andUnlock(nextValueFor(limitValue));
                };
            } else {
                limitValue = limitValueFor(currentValue);
                if (shouldWriteBack) {
                    updateValue_andUnlock(nextValueFor(limitValue));
                };
            }
        } else {
            currentValue = nextValueFor(currentValue);
        }

        return currentValue;
    }

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    protected Object retrieveValue() {
        Row row = table.findPrimaryKey(distinguishingValue);
        if (row == null) return null;
        Object columnValue = ((ColumnPi) table.columnNamed(counterColumn)).valueForRow((RowPi) row);
        return columnToValue(columnValue);
    }

    protected Object lockAndRetrieveValue() {
        table.lockTable();
        Row row = table.findPrimaryKey(distinguishingValue);
        if (row == null) return null;
        Object columnValue = ((ColumnPi) table.columnNamed(counterColumn)).valueForRow((RowPi) row);
        return columnToValue(columnValue);
    }

    protected void insertValue_andUnlock(Object value) {
        Row outputRow = table.newDbRow();
        outputRow.atKey_put(table.primaryKeyColumn().name(),distinguishingValue);
        ((ColumnPi) table.columnNamed(counterColumn)).setValueForRow_to((RowPi) outputRow,valueToColumn(value));
        try {
            table.insertRow(outputRow);
        } catch (Exception e) {
            throw new RuntimeWrappedException("Couldn't insert the generated value",e);
        }
        if (canCommitConnection) {
            table.unlockTable();
        } else {
            table.tryToUnlockTableWithoutCommit();
        }
    }

    protected void updateValue_andUnlock(Object value) {
        Row outputRow = table.newDbRow();
        outputRow.atKey_put(table.primaryKeyColumn().name(),distinguishingValue);
        ((ColumnPi) table.columnNamed(counterColumn)).setValueForRow_to((RowPi) outputRow,valueToColumn(value));
        table.updateRow(outputRow,distinguishingValue);
        if (canCommitConnection) {
            table.unlockTable();
        } else {
            table.tryToUnlockTableWithoutCommit();
        }
    }

    //**********************************************************
    //(P)******************** Utilities ************************
    //**********************************************************

    protected boolean needToFetch() {
        if (currentValue == null) return true;
        if (limitValue == null)   return true;  //This should be impossible
        if (needToFetchPredicate == null) return currentValue.equals(limitValue);
        return needToFetchPredicate.isTrueWith_with(currentValue,limitValue);
    }

    protected Object nextValueFor(Object aValue) {
        return nextValueFunction.valueWith(aValue);
    }

    protected Object limitValueFor(Object aValue) {
        if (limitValueFunction == null) return aValue;
        return limitValueFunction.valueWith(aValue);
    }

    protected Object valueToColumn(Object value) {
        if (valueToColumnFunction == null) return value;
        return valueToColumnFunction.valueWith(value);
    }

    protected Object columnToValue(Object columnValue) {
        if (columnToValueFunction == null) return columnValue;
        return columnToValueFunction.valueWith(columnValue);
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected TableSi  table = null;
    protected Object   distinguishingValue = null;

    protected String   counterColumn = null;

    //**************************************
    //(P)***** Generation Values ***********
    //**************************************

    protected Object startingValue = null;

    protected Object        currentValue = null;
    protected Object        limitValue = null;

    protected Function1Arg  nextValueFunction  = null;
    protected Function1Arg  limitValueFunction = null;
    protected Predicate2Arg needToFetchPredicate = null;

    //**************************************
    //**************************************
    //**************************************

        /**
         * Should the limitValue be writen back to the database after
         * fetching the currentValue?  True indicates this is under
         * client control, which would be appropriate for databases
         * without SP.
         */
    protected boolean shouldWriteBack = false;

    protected Function1Arg  valueToColumnFunction = null;
    protected Function1Arg  columnToValueFunction = null;

    protected boolean canCommitConnection = true;
}