/*======================================================================
**
**  File: chimu/form/description/DatabaseDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;


import com.chimu.form.database.*;
import com.chimu.form.database.typeConstants.*;

import com.chimu.kernel.streams.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;

import com.chimu.kernel.meta.*;




import java.util.Date;

import java.util.*;
import java.io.*;


/*temp*/ public class DatabaseDescriptionC implements DatabaseDescription {

  
    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*temp*/ public DatabaseDescriptionC() {}

    public void initFrom(DatabaseConnection dbConnection) {
        List catalogs = (List) dbConnection.catalogs();
        int size = catalogs.size();
        for (int i=0; i<size; i++) {
            CatalogDescriptionC cd = new CatalogDescriptionC();
            Catalog aCatalog = (Catalog) catalogs.atIndex(i);
            cd.initFrom(aCatalog);
            addCatalogDescription(cd);
            if (aCatalog.equals(dbConnection.defaultCatalog())) {
                this.defaultCatalogDesc = cd;
            }
        }

        databaseProductType = dbConnection.databaseProduct().code();
    }

    //**********************************************************
    //(P)********************* Getter **************************
    //**********************************************************
    public List catalogDescriptions() {
        return this.catalogDescriptions;
    }

    public CatalogDescription defaultCatalogDescription() {
        return this.defaultCatalogDesc;   
           
    }
    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public String toString() {
        StringBuffer stringB = new StringBuffer();
        stringB.append("CATALOG ");
        int size = catalogDescriptions.size();
        stringB.append("{");
        for (int i=0; i<size; i++) {
            if (i > 0) stringB.append(",");
            stringB.append(catalogDescriptions.atIndex(i));
        }
        stringB.append("}");

        return stringB.toString();
    }

    //**********************************************************
    //(P)******************** Utilities ************************
    //**********************************************************

    public void addCatalogDescription(CatalogDescription cd) {
       
        Object anObject = catalogDescriptions.detect(includesNameFunctor(cd.name()));
        if (anObject == null) {
            catalogDescriptions.add(cd);
        };  
//need to replace the element with the new cd        
            
    }

    //**********************************************************
    //(P)******************** Utilities ************************
    //**********************************************************
    
    //MLF 980807: REVIEW -- Inconsistency of the recursion levels
    //            between Database, Catalog, Scheme.

    public void buildDatabaseConnection(DatabaseConnection dbConnection) {
        int size = catalogDescriptions.size();
        String defaultCatalogName = defaultCatalogDescription().name();
        for (int i=0; i<size; i++) {
            CatalogDescription cd = 
                (CatalogDescription) catalogDescriptions.atIndex(i);

            cd.createCatalogIn(dbConnection);
            if (cd.name().equals(defaultCatalogName)) {
                dbConnection.setDefaultCatalog(dbConnection.catalog(cd.name()));
            }
        }

    }


    //**********************************************************
    //(P)************* Create DB Table Support *****************
    //**********************************************************

    public void buildDbFromDescription(DatabaseConnection aConnection){
        
        aConnection.databaseProduct().setupTypeStrings();
        for (int i = 0; i < catalogDescriptions.size(); i++) {
            CatalogDescription cd = (CatalogDescription) catalogDescriptions.atIndex(i);
            cd.buildDbIn(aConnection);
        }
    }

    //**********************************************************
    //(P)********************* Functor *************************
    //**********************************************************

    protected Predicate1Arg includesNameFunctor(final String someName) {
        return new Predicate1Arg() {public boolean isTrueWith(Object each) {//<DontAutoUnstub>
            return ((CatalogDescription) each).name().equals(someName);
        }  };
    }

   
    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected List catalogDescriptions = CollectionsPack.newList();
//    protected List        tableDescriptions  = CollectionsPack.newList();
    protected int databaseProductType = -1;     //From DataProductCodes
    protected CatalogDescription defaultCatalogDesc;
}