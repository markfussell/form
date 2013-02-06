/*======================================================================
**
**  File: chimu/form/description/DescriptionPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import java.sql.Connection;
import com.chimu.kernel.collections.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;
import com.chimu.form.database.typeConstants.*;


/**
**/

public class DescriptionPack {

    static public DatabaseDescription newDatabaseDescription() {
        return new DatabaseDescriptionC();
    }

    static public DatabaseDescription newDatabaseDescriptionFrom(DatabaseConnection dbConnection) {
        DatabaseDescription dbDesc = newDatabaseDescription();
        dbDesc.initFrom(dbConnection);
        return dbDesc;
    }
    //**************************************
    //**************************************
    //**************************************

    static public CatalogDescription newCatalogDescription() {
        return new CatalogDescriptionC();
    }


    static public CatalogDescription newCatalogDescriptionFrom(Catalog aCatalog) {
        CatalogDescription cd = new CatalogDescriptionC();
        cd.initFrom(aCatalog);
        return cd;
    }

    //**************************************
    //**************************************
    //**************************************

    static public SchemeDescription newSchemeDescription() {
        return new SchemeDescriptionC();
    }


    static public SchemeDescription newSchemeDescriptionFrom(Scheme aScheme){
        SchemeDescription sd = new SchemeDescriptionC();
        sd.initFrom(aScheme);
        return sd;
    }

    static public SchemeDescription newSchemeDescriptionFrom(Scheme aScheme, CatalogDescription cd){
        SchemeDescription sd = new SchemeDescriptionC();
        sd.initFrom(aScheme, cd);
        return sd;
    }


    //**************************************
    //**************************************
    //**************************************
    
    static public TableDescription newTableDescriptionFrom(Table aTable) {
        TableDescription table = new TableDescriptionC();
        table.initFrom(aTable);
        return table;
    }
    
    static public TableDescription newTableDescriptionFrom(String tableName, SchemeDescription sd, String primaryKey) {

        TableDescription td = newTableDescription(tableName, primaryKey);
        td.setSchemeDescription(sd);
        td.setPrimaryKey(primaryKey);
       
        return td;
    }

    static public TableDescription newTableDescription(String name, List colList, String primaryKey) {
        TableDescription table = new TableDescriptionC();
        table.setName(name);
        table.setPrimaryKey(primaryKey);
        table.setColumnsList(colList);
        return table;
        
    }
    
    static public TableDescription newTableDescription(String name, String primaryKey) {
        TableDescription table = new TableDescriptionC();
        table.setName(name);
        table.setPrimaryKey(primaryKey);
        return table;
    }        

    static public TableDescription newTableDescription(String name) {
        TableDescription table = new TableDescriptionC();
        table.setName(name);
        return table;
    }        
    

    //**************************************
    //**************************************
    //**************************************


    static public ColumnDescription newColumnDescription(String name, String sqlTypeString, boolean nullable) {

        ColumnDescription col = new ColumnDescriptionC(name, sqlTypeString, nullable);
        SqlDataType sqlType = SqlDataTypePack.typeFromKey(sqlTypeString);
        int typeInt = sqlType.code();

        col.setSqlDataType(typeInt);
        return col;
           
        
    }
    //**************************************
    //**************************************
    //**************************************

    private DescriptionPack() {};

};
