/*======================================================================
**
**  File: chimu/form/description/TableDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.database.*;
import com.chimu.form.database.DatabaseConnection;
import com.chimu.form.database.product.*;
import com.chimu.kernel.collections.*;

import java.io.Serializable;


public interface TableDescription extends Serializable {
    
    public void createTableIn(Scheme aScheme, DatabaseConnection dbConnection);
    public void buildTableIn(DatabaseConnection dbConnection);
    public void buildTableIn_withScheme(DatabaseConnection dbConnection, Scheme aScheme);

/*    
    public void createTableInDb(DatabaseConnection db);          
    public Table augmentTableInScheme(Scheme scheme);
    public void createTableInScheme(Scheme scheme);
    public void createTableInScheme_noColumnInfo(Scheme scheme);
*/    
 
    
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void initFrom(Table table);
    public void initFrom(Table table, String aKey);
    public void initFrom(Table table, SchemeDescription sc);
    

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String name();
    public String qualifiedName();
    
    public SchemeDescription schemeDescription();
    public CatalogDescription catalogDescription();
      
    public void setName(String name);
    public void setColumnsList(List aList);
    public void setSchemeDescription(SchemeDescription sd);
    
    public String primaryKey();
    public void setPrimaryKey(String colName);
    public boolean hasPrimaryKey();

    public void addColumn(ColumnDescription column);
    
    public List columnDescriptions();       

    
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String createTableString(DatabaseProduct dbProduct);
    public String createTableString_inTable(DatabaseProduct dbProduct, String tableName);


    
}