/*======================================================================
**
**  File: chimu/form/database/CatalogC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;

import java.sql.Types;

/**
A Catalog represents the second "grouping" within a Database and will
contain multiple Schemes, which in tern contain the actual Tables.
**/
/*package*/ class CatalogC implements Catalog {

    /*package*/ protected CatalogC(String name, DatabaseConnection dbConnection) {
        this.name = name;
        this.dbConnection = dbConnection;
        this.defaultScheme = this.scheme("");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         *<P>To allow the default catalogs to be changed
         */
    public void setName(String aName) {
        this.name = aName;
    }

    public String              name() {
        return this.name;
    }

    public String              fullName() {
        return this.name();
    }
    
    public boolean isAnonymous() {
        return this.name.length()==0;
    }


        /**
         * The qualified name of a scheme includes the SQL-92 standard
         * prefixes of "<Catalog>." if this is known to
         * the table or database connection.
         */
    public String qualifiedName() {
        if (isAnonymous()) {
            return null;
        } else {
            return name();
        }
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Collection  schemes() {
        return schemes;
    }
    
    public Scheme defaultScheme() {
        return defaultScheme;
    }
    
    public void setDefaultScheme(Scheme newScheme) {
        defaultScheme = newScheme;
    }
    
    protected boolean updateScheme_withName(Scheme scheme, String newSchemeName) {
        String oldSchemeName = scheme.name();
        Scheme entry = (Scheme) schemeNames.atKey(newSchemeName);
        if (entry != null) return false;
        
        schemeNames.removeKey(oldSchemeName);
        schemeNames.atKey_put(newSchemeName,scheme);
        return true;
    }
    
    public Scheme scheme(String schemeName) {
        Scheme result = (Scheme) schemeNames.atKey(schemeName);
        if (result == null) {
            result = privateNewScheme(schemeName);
            schemes.add(result);
            schemeNames.atKey_put(schemeName,result);
        };
        return result;
    }

    protected Scheme privateNewScheme(String schemeName) {
        return new SchemeC(schemeName, dbConnection, this);
    }

    public boolean equals(Catalog aCatalog) {
        
        return ((this.name() == null) && (aCatalog.name() == null)) 
            ||(this.name()).equals(aCatalog.name());
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected String  name = null;
    protected DatabaseConnection  dbConnection;
    
    protected List /*of Scheme*/ schemes = CollectionsPack.newList();
    protected Map  schemeNames           = CollectionsPack.newMap();
    protected Scheme defaultScheme;

};