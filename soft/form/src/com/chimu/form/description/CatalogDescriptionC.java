/*======================================================================
**
**  File: chimu/form/description/CatalogDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.kernel.collections.*;

import com.chimu.form.database.*;


/*subsystem*/ public class CatalogDescriptionC extends DescriptionAbsC implements CatalogDescription {
    /*package*/ protected CatalogDescriptionC() {
    }

    /*package*/ protected CatalogDescriptionC(String name) {
        this.name = name;
//        this.defaultSchemeDescription = this.schemeDesc("");
    }
    
    //**************************************
    //**************************************
    //**************************************
    public void initFrom(Catalog catalog) {
        name = catalog.name();
        List aList = (List) catalog.schemes();
 
        for (int i = 0; i < aList.size(); i++) {
            Scheme aScheme = (Scheme) aList.atIndex(i);
            
            SchemeDescription sd = new SchemeDescriptionC(aScheme.name(), this);
            sd.initFrom(aScheme);
            addSchemeDescription(sd);
            
            if (aScheme.equals(catalog.defaultScheme())) {
                this.defaultSchemeDesc = sd;
            }
        }
  
    }
    
    public void addSchemeDescription(SchemeDescription sd) {
        schemeDescriptions.add(sd);
         
    }
    
    //**************************************
    //**************************************
    //**************************************
    
    public void createCatalogIn(DatabaseConnection dbConnection){
        Catalog aCatalog = dbConnection.catalog(this.name());

        int size = schemeDescriptions.size();
        String defaultName = defaultSchemeDescription().name();
        for (int i=0; i<size; i++) {
            SchemeDescription sd = (SchemeDescription) schemeDescriptions.atIndex(i);
            sd.createSchemeIn(aCatalog, dbConnection);
            Scheme aScheme = aCatalog.scheme(sd.name());
            if (sd.name().equals(defaultName)) {
                aCatalog.setDefaultScheme(aScheme);
            }

        };        
    }
    
        /** 
        **  create the catalog and its components in the relational database
        **/
    public void buildDbIn(DatabaseConnection dbConnection) {
        int size = schemeDescriptions.size();
        for (int i=0; i<size; i++) {
            SchemeDescription sd = (SchemeDescription) schemeDescriptions.atIndex(i);
            sd.buildDbIn(dbConnection);

        };        
    
    }
    
    //**************************************
    //**************************************
    //**************************************
    

    public String  name() {
        return this.name;
    }

    public String  fullName() {
        return this.name();
    }

    public boolean isAnonymous() {
        if (this.name == null) {
            return true;    
        }
        return this.name.length()==0;
    }

    public String qualifiedName() {
        if (isAnonymous()) {
            return null;
        } else {
            return name();
        }
    }

    public Collection  schemeDescriptions() {
        return schemeDescriptions;
    }

    public SchemeDescription defaultSchemeDescription() {
        return defaultSchemeDesc;
    }


        /**
         * Get or create the scheme with the name 'schemeName'
         */
    public void setName(String aName) {
        this.name = aName;
    }



    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected String  name = null;
    protected SchemeDescription defaultSchemeDesc;
    
    protected List schemeDescriptions = CollectionsPack.newList();
/*
    protected Map  schemeNames           = CollectionsPack.newMap();
*/


}