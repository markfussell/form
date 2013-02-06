/*======================================================================
**
**  File: chimu/form/description/ColumnDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.*;
//import com.chimu.form.mapping.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;
import com.chimu.form.database.typeConstants.*;

import java.sql.Types;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

import java.util.Enumeration;
import java.io.*;

public class ColumnDescriptionC extends DescriptionAbsC implements ColumnDescription  {

    
    //**********************************************************
    //******************* Constructors *************************
    //**********************************************************

        /**
         *Constructs a basic column description with defaults
         *
         *<P>Note that this creates a default basic column description
         *with a translated sql data type (int) using the specific string
         *type from SqlDataType package. 
         *@see com.chimu.form.database.TypeConstants
         */

    public ColumnDescriptionC(String aName, String sqlTypeString, boolean nullable) {
        this.name = aName;
        this.sqlDataTypeString = sqlTypeString;
        this.isNullable = nullable;
       
    }

    protected ColumnDescriptionC() {
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************
    public Column createColumnFor(Table inTable) {
        TableSi table = (TableSi) inTable;

        if (columnType == BASIC_COLUMN) {
            return putDbColumnInfoInto((BasicColumnPi) table.newBasicColumn_type(name, javaDataType()));
        } else if (columnType == COMPOUND_COLUMN) {
            return table.newCompoundColumnNamed(name,subColumnNames);
        } else {
            throw new DevelopmentException("Unexpected column type "+columnType);
        }
        
    }

    public Column createColumnFor_inDb(Table inTable, DatabaseConnection dbConnection) {
        return createColumnFor(inTable);
    }

    public Column createColumnFor_noColumnInfo(Table inTable) {    
        TableSi table = (TableSi) inTable;

        if (columnType == BASIC_COLUMN) {
            return table.newBasicColumn_type(name,javaDataType());
        } else if (columnType == COMPOUND_COLUMN) {
            return table.newCompoundColumnNamed(name,subColumnNames);
        } else {
            throw new DevelopmentException("Unexpected column type "+columnType);
        };
    }
    
    public Column createColumnFor_inDb_noColumnInfo(Table inTable, DatabaseConnection dbConnection) {
        return createColumnFor_noColumnInfo(inTable);
    }
    
    public Column augmentColumnFor(Table inTable) {
        TableSi table = (TableSi) inTable;

        Column existingColumn = table.columnNamed(name);
        if (existingColumn != null) {
            if (!existingColumn.isCompound()) {
                putDbColumnInfoInto((BasicColumnPi) existingColumn);
            }
            return existingColumn;
        };

        if (columnType == BASIC_COLUMN) {
            return putDbColumnInfoInto((BasicColumnPi) table.newBasicColumn_type(name, javaDataType()));
        } else if (columnType == COMPOUND_COLUMN) {
            return table.newCompoundColumnNamed(name,subColumnNames);
        } else {
            throw new DevelopmentException("Unexpected column type "+columnType);
        };
        
    }
    

    public Column augmentColumnFor_inDb(Table inTable, DatabaseConnection dbConnection) {
        return augmentColumnFor(inTable);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************


    protected BasicColumnPi putDbColumnInfoInto(BasicColumnPi column) {
        column.setSqlDataType(sqlDataType);
        column.setIsNullable(isNullable);
        column.setMaximumLength(maximumLength);
        return column;
    }

    public void initFrom(Column column) {
        name       = column.name();
        tableName  = column.table().name();
        if (column.isCompound()) {
            columnType = COMPOUND_COLUMN;

            List subColumns = ((CompoundColumn) column).subColumns();
            int size = subColumns.size();
            for (int i=0; i<size; i++) {
                Column subColumn = (Column) subColumns.atIndex(i);
                this.addSubColumnName(subColumn.name());
            }
        } else {
            setJavaDataType(column.javaDataType());
            setSqlDataType(column.sqlDataType());
            isNullable = column.isNullable();
            setMaximumLength(column.maximumLength());
        }
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String name() {
        return name;
    }

    public void  setName(String name) {
        this.name = name;
    }

    public String  tableName() {
        return tableName;
    }

    public void  setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isCompound() {
        return columnType == COMPOUND_COLUMN;
    }

    public boolean isBasic() {
        return columnType == BASIC_COLUMN;
    }

    public int columnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public int javaDataType() {
        return javaDataType;
    }

    public void setJavaDataType(int aDataType) {
        this.javaDataType = aDataType;
    }

    public void addSubColumnName(String name) {
        subColumnNames.add(name);
    }
    
    public int sqlDataType() {
        return sqlDataType;
    }
    
   
/*
    protected void setIsNullable(boolean aBoolean) {
        this.isNullable = aBoolean;
    }
*/    

   //The following method implementations vary depending on the sqlDataType
    
    public int maximumLength() {
        return maximumLength;
    }
    
    public int scale() {
        return scale;
    }
    
    public int precision() {
        return precision;
    }
    

    
    //**********************************************************
    //**********************************************************
    //**********************************************************


        /**
         *Set the SQL data type for the column.
         *
         *<P>Note that this information normally comes from the
         *database itself and is not user controllable.  The
         *columnType in a description simply allows the database
         *information to be cached, which may or may not be
         *appropriate.
         *<Require>BasicColumn: this.isBasic()
         *@see java.sql.Types
         */
    public void setSqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

   //The following method implementations vary depending on the sqlDataType

    public void setMaximumLength(int length) {
        this.maximumLength = length;
    }
    
    public void setScale(int scaleInt) {
        this.scale = scaleInt;
    }
    
    public void setPrecision(int precisionInt) {
        this.precision = precisionInt;
    }
    
    public String sqlDataTypeString() {
        return sqlDataTypeString;
    }
    
    public void setSqlDataTypeString(String aString) {
        this.sqlDataTypeString = aString;
    }



    

    //**********************************************************
    //************ Table generation Support ********************
    //**********************************************************

    public String genString(SqlDataType dataType) {
        String aString = "";
        if (dataType.key() == "BYTE") {        //Byte
            aString = "("+ maximumLength + ")";
        }; 
        if (dataType.key() == "FLOAT") {
            aString = "("+ scale + ", " + precision + ")";
        }
        return aString;
    }

    /*
    setLength(...) {
        if (!dataType.requiresLength()) {
            throw new DevelopmentException("This datatype does not use length... are you sure this is coded correctly");
        }
    }
    
    */

    public String genColumnString(DatabaseProduct dbProduct) {

        if (isBasic()) {
            SqlDataType aDataType = null;
            if (sqlDataTypeString != null) {
                aDataType = SqlDataTypePack.typeFromKey(sqlDataTypeString);
            } else {
                aDataType = SqlDataTypePack.typeFromCode(sqlDataType); 
            };
            
            String aString = "";            
            if (aDataType.parameterRequired()) {
                aString = aDataType.stringRep(dbProduct, maximumLength);
            } else {
                aString = aDataType.stringRep(dbProduct);
            };
            
            String retString =  name + " " + aString + " " + nullString(dbProduct);
            return retString;               

        };
        return "";

    }

            /*
            if (dataType.requiresLength()) {
                
            } else {
                
            }
            
                //This may throw an exception if length is null and required
            dataType.genColumnStringFor_given(dbProduct,length,scale,precision);
            */

    protected String nullString(DatabaseProduct dbProduct) {
        String nullable = dbProduct.notNull();
        if (isNullable) {
            nullable = dbProduct.nullable();
        }
        return nullable;
    }
    

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String    name = null;
    protected int       columnType = BASIC_COLUMN;

    protected List  subColumnNames = CollectionsPack.newList();

    protected String    tableName;

    protected boolean   isNullable;
    protected String    sqlDataTypeString = null;  //(SqlDataTypePack.typeFromCode(0)).key();
    protected int       sqlDataType = Types.NULL;
    protected int       javaDataType = TypeConstants.TYPE_Object;
    protected int       maximumLength = 0;
    protected int       precision = 0;
    protected int       scale = 0;
    
}