/*======================================================================
**
**  File: chimu/form/description/ColumnDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.database.Column;
import com.chimu.form.database.Table;
import com.chimu.form.database.DatabaseConnection;
import com.chimu.form.database.product.*;
import com.chimu.form.database.typeConstants.*;

import com.chimu.kernel.collections.*;

import java.io.Serializable;

/**
A ColumnDescription hold onto all the information needed to create a
FORM Database Column.  This is divided into two types of information:
<OL>
  <LI>User controllable information like the Java type that the column
represents,
  <LI>Database specific information which is retrieved from the database

**/
public interface ColumnDescription extends Serializable {
    public final int    BASIC_COLUMN = 1;
    public final int COMPOUND_COLUMN = 2;

         


        /**
         *Create a table column from this description
         */
    public Column createColumnFor_inDb(Table table, DatabaseConnection dbConnection);

        /**
        *Create a table column from this description
        */
    public Column createColumnFor(Table table);


    public Column createColumnFor_noColumnInfo(Table table);
    public Column augmentColumnFor(Table table);


        /**
         *Create a column from this description but do not put any of the
         *database column information (i.e. cached database information)
         *into the column.
         */

    public Column createColumnFor_inDb_noColumnInfo(Table table, DatabaseConnection dbConnection);
    public Column augmentColumnFor_inDb(Table table, DatabaseConnection dbConnection);

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         *Initialize the description from the information in Column
         */
    public void initFrom(Column column);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String name();
    public void   setName(String name);

    public String  tableName();
    public void    setTableName(String name);

        /**
         *Set the type of column.  Currently a choice of
         *BASIC_COLUMN or COMPOUND_COLUMN
         */
    public void    setColumnType(int columnType);
    public int     columnType();
    public boolean isCompound();
    public boolean isBasic();

        /**
         *@see com.chimu.kernel.meta.TypeConstants
         */
    public int javaDataType();

        /**
         *Set the Java data type expected from column.
         *@see com.chimu.kernel.meta.TypeConstants
         */
    public void setJavaDataType(int javaDataType);

        /**
         *<Require>compoundColumn: this.isCompound()
         */
    public void addSubColumnName(String name);

        /**
         *@see com.chimu.form.database.typeConstants
         */
         
    public void setSqlDataType(int sqlDataType);     

    public String sqlDataTypeString();
/*
   
    public void setSqlDataTypeString(String aString);
*/

    public int sqlDataType();
    
        //Vary depending on the sqlDataType
    public int maximumLength();
    public int scale();
    public int precision();

        //Vary depending on the sqlDataType
    public void setMaximumLength(int length);
    public void setScale(int scale);
    public void setPrecision(int precision);

    /*
        public void setColumnState(ColumnState aState);
        public void createColumnState();
    */
    
    //**********************************************************
    //***************Table Creation Support*********************
    //**********************************************************

    public String genColumnString(DatabaseProduct dbProduct);


}