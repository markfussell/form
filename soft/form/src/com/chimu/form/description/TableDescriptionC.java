/*======================================================================
**
**  File: chimu/form/description/TableDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;
import java.io.PrintStream;
import java.io.*;


/*subsystem*/ public class TableDescriptionC extends DescriptionAbsC implements TableDescription {
    /*package*/ protected TableDescriptionC() {
    }

    public void initFrom(Table table) {
        name           = table.name();
        
        List cols  = (List) table.columns();
        int size = cols.size();
        for (int i=0; i<size; i++) {
            ColumnDescription columnD = new ColumnDescriptionC();
            columnD.initFrom((Column) cols.atIndex(i));
            addColumn(columnD);
        };
        
    }
    
    public void initFrom(Table table, SchemeDescription sc) {
        this.schemeDesc = sc;
        initFrom(table);
    }

    public void initFrom(Table table, String aKey) {  //aKey specifies the column name of the primary key
        name           = table.name();
        primaryKey           = aKey;
        
        List columns = (List) table.columns();
        int size = columns.size();
        for (int i=0; i<size; i++) {
            ColumnDescription columnD = new ColumnDescriptionC();
            columnD.initFrom((Column) columns.atIndex(i));
            this.addColumn(columnD);
        };
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************
    
    public void createTableIn(Scheme aScheme, DatabaseConnection dbConnection) {
        Table table = aScheme.table(name()); 

        int size = columnDescriptions.size();
        for (int i=0; i<size; i++) {
            ((ColumnDescriptionC) columnDescriptions.atIndex(i)).createColumnFor(table);
        };
        
    }
    
    public void buildTableIn(DatabaseConnection dbConnection) {

        String sqlString = createTableString(dbConnection.databaseProduct());

        executeString(dbConnection, sqlString);
    }
    
    public void buildTableIn_withScheme(DatabaseConnection dbConnection, Scheme aScheme) {
        //This is not as pretty as it should be.
        
        String tableName = aScheme.qualifiedName() + this.name();
        String sqlString = createTableString(dbConnection.databaseProduct(), tableName);
    }
    
/*   
    
    public Table augmentTableInScheme(Scheme scheme) {
        Table table = scheme.table(qualifiedName());
        int size = columnDescriptions.size();
        for (int i=0; i<size; i++) {
            ((ColumnDescriptionC) columnDescriptions.atIndex(i)).augmentColumnFor(table);
        };
        return table;
    }
    
    public void createTableInScheme_noColumnInfo(Scheme scheme) {
        Table table  = scheme.table(qualifiedName());
        int size = columnDescriptions.size();
        for (int i=0; i<size; i++) {
            ((ColumnDescription) columnDescriptions.atIndex(i)).createColumnFor_noColumnInfo(table);
        };
    }

*/
    //**************************************
    //**************************************
    //**************************************

    public void setName(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }
    
    public void setSchemeDescription(SchemeDescription sd) {
        this.schemeDesc = sd;
    }
    
    public SchemeDescription schemeDescription() {
        return this.schemeDesc;
    }
    
    public CatalogDescription catalogDescription() {
        return this.schemeDesc.catalogDescription();
    }
    
    public String qualifiedName() {
        if (this.schemeDescription() != null) {
            String prefix = this.schemeDescription().qualifiedName();
            if (prefix == null) {  //""
                return this.name();
            } else {
                return prefix+"."+this.name();
            }
        } else {
            return name;
        };
    }
        
    public void setColumnsList(List aList) {
        this.columnDescriptions = aList;
    }

    public String primaryKey() {
        return this.primaryKey;
    }
    
    public void setPrimaryKey(String colName) {
        this.primaryKey = colName;
    }
    
    public boolean hasPrimaryKey() {
        return this.primaryKey != null;
    }
    
    //**************************************
    //**************************************
    //**************************************

    public void addColumn(ColumnDescription column) {
        columnDescriptions.add(column);
    }
    
    public List columnDescriptions() {
        return columnDescriptions;
    }

    //**********************************************************
    //************** Database Creation Support *****************
    //**********************************************************

    public String createTableString(DatabaseProduct dbProduct) {
        return createTableString(dbProduct, qualifiedName());
    }    

    public String createTableString_inTable(DatabaseProduct dbProduct, String tableName) {
        return createTableString(dbProduct, tableName);
    }


    protected String createTableString(DatabaseProduct dbProduct, String tableName) {
        String aString = "";
        if (hasPrimaryKey()) {
            aString = entityTableString(dbProduct, tableName);
        } else {
            aString = nonEntityTableString(dbProduct, tableName);
        };
        return aString;
    }    


    
        /* generates a string that can be sent to the database for
        table creation
        */
    protected String nonEntityTableString(DatabaseProduct dbProduct, String tableName) {  
        
        String aString =  "CREATE TABLE " + tableName + " (" + columnsString(dbProduct) + ")" ;
        return aString;

    }

    /* generates a string that can be sent to the database for table
    creation and includes an 'id' column
    */
    protected String entityTableString(DatabaseProduct dbProduct, String tableName) {
        String aString = "CREATE TABLE "+ tableName +"  (" +
        //dbProduct.idColumn()+ ", "+
                        columnsString(dbProduct)+ " " + dbProduct.primaryKeyPrefix()+
                        primaryKey() + " " + dbProduct.primaryKeySuffix() + ")";

        return aString;

    }
    
        /** [MLF] This is a tentative possibility **/
    protected String createIndexString(String tableName) {
        return "CREATE UNIQUE INDEX "+tableName+"_Unique"+" ON "+name+" ("+primaryKey()+") ";
    }
    //"FormIn"+(indexCounter++)

    protected String createIndexString() {
        return createIndexString(qualifiedName());
    }


    //**********************************************************
    //***************Table Creation Support*********************
    //**********************************************************

    protected String columnsString(DatabaseProduct dbProduct) {
        String aString = "";
        ColumnDescription aColumn = (ColumnDescription) columnDescriptions.atIndex(0);
        aString = aColumn.genColumnString(dbProduct);
        int size = columnDescriptions.size();
        for (int i = 1; i <  size; i ++) {
            aColumn = (ColumnDescription) columnDescriptions.atIndex(i);            
            if (aColumn.isBasic()) {
                if (aColumn != null) {
                aString = aString + ", " + aColumn.genColumnString(dbProduct);
                };
            };
        };

        return aString;
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String name = null;
    protected String primaryKey = null;
    protected SchemeDescription schemeDesc;
    protected List /*(ColumnDescription)*/ columnDescriptions  = CollectionsPack.newList();

}


