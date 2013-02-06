/*======================================================================
**
**  File: chimu/form/description/DatabaseDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.kernel.collections.*;

import com.chimu.form.database.DatabaseConnection;
import java.io.Serializable;

public interface DatabaseDescription extends Serializable {

    public void buildDatabaseConnection(DatabaseConnection dbConnection);

    public void buildDbFromDescription(DatabaseConnection dbConnection);

//    public void buildDatabaseConnection_noDbInfo(DatabaseConnection dbConnection);

        /**
         * Add any information to the database connection that is not
         * already there.
         */
//    public void augmentDatabaseConnection(DatabaseConnection dbConnection);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void initFrom(DatabaseConnection dbConnection);

    //**********************************************************
    //**********************************************************
    //**********************************************************
    public void addCatalogDescription(CatalogDescription cd);
    public List catalogDescriptions();
    
    public CatalogDescription defaultCatalogDescription();

//    public void addTableDescription(TableDescription td);
//    public List tableDescriptions();


    


}