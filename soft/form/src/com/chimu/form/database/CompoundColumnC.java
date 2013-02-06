/*======================================================================
**
**  File: chimu/form/database/CompoundColumnC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.Types;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

import java.util.Enumeration;

class CompoundColumnC extends ColumnAbsC
                          implements CompoundColumnPi {
    //**********************************************************
    //**********************************************************
    //**********************************************************

    CompoundColumnC(Table table, String name, List subColumns) {
        this.table = table;
        this.name = name;
        this.subColumns = subColumns;
        this.numberOfSubColumns = subColumns.size();

        this.subColumnNameToIndex = CollectionsPack.newMap();

        int size = subColumns.size();
        for (int i = 0; i < size; i++) {
            Column subcolumn = (Column) subColumns.atIndex(i);
            subColumnNameToIndex.atKey_put(subcolumn.name(),new Integer(i));
        };
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String name() {
        return name;
    }

    public Table  table() {
        return table;
    }

    /*package*/ public boolean isCompound() {return true;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public int sqlDataType() {
        return 0;
    }
    public int javaDataType() {return TypeConstants.TYPE_Object;}

    public boolean isNullable()    {
        for (int i = 0; i<numberOfSubColumns; i++) {
            if (!mySubColumnAtIndex(i).isNullable()) return false;
        };
        return true;
    }
    public int     maximumLength() {return 0;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public Object encode(Object slotValue) {
        // Array slotValueParts = (Array) slotValue;
        return slotValue;
    }

    /*package*/ public Object decode(Object dataValue) {
        return dataValue;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public Object valueForRow(RowPi row) {
        int size = subColumns.size();
        KeyedArray result = CollectionsPack.newKeyedArray(subColumnNameToIndex);

        for (int i=0; i<size; i++) {
            result.atIndex_put(i,mySubColumnAtIndex(i).valueForRow(row));
        };
        return result;
    }

    /*package*/ public void setValueForRow_to(RowPi row, Object value) {
        if (value == null) return;
        if (value instanceof Array) {
            Array values = (Array) value;
            int size = values.size();
            if (size > numberOfSubColumns()) {
                size = numberOfSubColumns();
            }
            for (int i=0; i<size; i++) {
                mySubColumnAtIndex(i).setValueForRow_to(row,values.atIndex(i));
            };
        } else if (value instanceof KeyedCollection) {
            KeyedCollection keyValues = (KeyedCollection) value;
            Enumeration keysEnum = keyValues.keys();
            while (keysEnum.hasMoreElements()) {
                String key = (String) keysEnum.nextElement();
                mySubColumnAtIndex(indexForKey(key))
                    .setValueForRow_to(row,keyValues.atKey(key));
            };
        } else {
            throw DatabasePackSi.newConfigurationException("Can not store objects of type "+value.getClass()+" in a CompoundColumn [must be a collection] ");
        }
    }

    protected ColumnPi mySubColumnAtIndex(int i) {
        return (ColumnPi) subColumns.atIndex(i);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public int numberOfSubColumns() {return numberOfSubColumns;}
    public Column subColumnAtIndex(int i) {
        return (Column) subColumns.atIndex(i);
    }
    public String nameForSubColumnAtIndex(int i) {
        return subColumnAtIndex(i).name();
    }
    public List subColumns() {
        return subColumns;
    }

    public boolean hasSingleSubColumn() {return numberOfSubColumns() == 1;}
    public Column  firstSubColumn() {return this;}
    public String  firstSubColumnName() {return nameForSubColumnAtIndex(0);}

    //**********************************************************
    //(P)********************** Pstms **************************
    //**********************************************************

    /*package*/ public void addWhereParametersTo_in(StringBuffer stringB, TableSi table) {
        // table.addFirstColumnName_to(name,stringB);
        for (int i=0; i< numberOfSubColumns; i++) {
            if (i > 0) stringB.append(" AND ");
            mySubColumnAtIndex(i).addWhereParametersTo_in(stringB,table);
        }
    }

    /*package*/ public int setPstmt_itemStart_to(PreparedStatement pstmt, int item, Object value)
            throws SQLException {
        Array sequence = (Array) value;
        for (int i=0; i< numberOfSubColumns; i++) {
            Object subValue = sequence.atIndex(i);
            item = mySubColumnAtIndex(i).setPstmt_itemStart_to(pstmt, item, subValue);
        }
        return item;
    }

    /*package*/ public void addPartialColumnMapTo(Map columnMap) {
        for (int i=0; i< numberOfSubColumns; i++) {
            mySubColumnAtIndex(i).addPartialColumnMapTo(columnMap);
        }
    }

    protected void setPstmt_item_to_ofType(PreparedStatement pstmt, int item, Object value, int sqlType)
            throws SQLException {
        if (value == null) {
            pstmt.setNull(item+1,sqlType);
        } else {
            try {
                pstmt.setObject(item+1,value,sqlType);
            } catch (Exception e) {
                throw DatabasePackSi.newJdbcException("Could not set parameter("+item+") value: "+value+" class: "+value.getClass()+" to sqlType: "+sqlType,e);
            };
        };
    }

    //**********************************************************
    //(P)******************** SQL Building *********************
    //**********************************************************

    /*package*/ public void addWithVarName_equals_varName_sqlTo(String varName, Column toColumn, String toVarName, StringBuffer sqlStringB) {
        if (!((ColumnPi) toColumn).isCompound()) {
            throw new NotImplementedYetException("Unexpected match in columns Basic and Compound");
        };
        if (((CompoundColumnPi) toColumn).numberOfSubColumns() != numberOfSubColumns) {
            throw new NotImplementedYetException("Mismatch in number of columns");
        };
        for (int i=0; i< numberOfSubColumns; i++) {
            if (i > 0) {
                sqlStringB.append(" AND ");
            }
            ColumnSi subColumn1 = (ColumnSi) this.subColumnAtIndex(i);
            ColumnSi subColumn2 = (ColumnSi) ((CompoundColumnPi) toColumn).subColumnAtIndex(i);
            subColumn1.addWithVarName_equals_varName_sqlTo(varName, subColumn2, toVarName, sqlStringB);
        }
    }

    /*package*/ public void addWithVarName_expressionSqlTo(String varName, StringBuffer sqlStringB) {
        for (int i=0; i< numberOfSubColumns; i++) {
            mySubColumnAtIndex(i).addWithVarName_expressionSqlTo(varName,sqlStringB);
        }
    }

    /*package*/ public void addWithVarName_selectSqlTo(String varName, StringBuffer sqlStringB) {
        for (int i=0; i< numberOfSubColumns; i++) {
            mySubColumnAtIndex(i).addWithVarName_selectSqlTo(varName,sqlStringB);
        }
    }

    protected int indexForKey(String name) {
        return ((Number) subColumnNameToIndex.atKey(name)).intValue();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Table    table = null;
    protected String   name = null;
    protected List subColumns;
    protected int      numberOfSubColumns = 0;

    protected Map      subColumnNameToIndex = null;
}