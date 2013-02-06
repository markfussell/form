/*======================================================================
**
**  File: chimu/form/description/SchemeDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.kernel.functors.*;

import com.chimu.form.database.product.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.database.*;


/*subsystem*/ 
public class SchemeDescriptionC extends DescriptionAbsC implements SchemeDescription {
    /*package*/ protected SchemeDescriptionC() {
    }

    /*package*/ protected SchemeDescriptionC(String name, CatalogDescription cd) {
        this.name = name;
        this.catalogDesc = cd;
//        this.defaultSchemeDescription = this.schemeDesc("");
    }
    
    //**************************************
    //**************************************
    //**************************************

    public void initFrom(Scheme sc) {
        this.name = sc.name();
        List aList = (List) sc.tables();
        int size = aList.size();
        for (int i = 0; i < size; i++) {
            Table aTable = (Table) aList.atIndex(i);
            TableDescription td = new TableDescriptionC();
            td.initFrom(aTable, this);
            addTableDescription(td);
        };
    }
    
    public void initFrom(Scheme sc, CatalogDescription cd) {
        this.initFrom(sc);
        this.catalogDesc = cd;
    }

    //**************************************
    //**************************************
    //**************************************

    public void createSchemeIn(Catalog aCatalog, DatabaseConnection dbConnection){
        Scheme aScheme = aCatalog.scheme(name());        
//        aScheme.setDbConnection(dbConnection);        need to figure out if is necessary
        int size = tableDescriptions().size();
        for (int i = 0; i < size; i++) {
            TableDescription td = (TableDescription) tableDescriptions.atIndex(i);
            td.createTableIn(aScheme, dbConnection);
        };
        
    }

    
    public void buildDbIn(DatabaseConnection dbConnection) {
        
        int size = tableDescriptions().size();
        for (int i = 0; i < size; i++) {
            TableDescription td = (TableDescription) tableDescriptions.atIndex(i);
            td.buildTableIn(dbConnection);
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
        };
        return this.name.length()==0;
    }

        /**
         * The qualified name of a scheme includes the SQL-92 standard
         * prefixes of "<Catalog>." if this is known to
         * the table or database connection.
         */
    public String qualifiedName() {
         String prefix = catalogDescription().qualifiedName();
        if (prefix == null) {
            if (isAnonymous()) {
                return null;
            } else {
                return name();
            }
        } else {
            if (isAnonymous()) {
                return prefix + ".";
            } else {
                return prefix + "." + name();
            }
        }
    }


    public Collection  tableDescriptions() {
        return tableDescriptions;
    }

    public CatalogDescription catalogDescription() {
        return catalogDesc;
    }


        /**
         * Get or create the scheme with the name 'schemeName'
         */
    public void setName(String aName) {
        this.name = aName;
    }

    //**************************************
    //**************************************
    //**************************************

    public void addTableDescription(TableDescription td) {
        Object anObject = tableDescriptions.detect(includesNameFunctor(td.name()));
        if (anObject == null) {
            tableDescriptions.add(td);
        };  
//need to replace the element with the new td        
            
    }
    
    //**********************************************************
    //(P)**************** Scheme Generation ********************
    //**********************************************************
        
       
        
        
    //**********************************************************
    //(P)********************* Functor *************************
    //**********************************************************

    protected Predicate1Arg includesNameFunctor(final String someName) {
        return new Predicate1Arg() {public boolean isTrueWith(Object each) {//<DontAutoUnstub>
            return ((TableDescription) each).name().equals(someName);
        }  };
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected String  name = null;
    protected CatalogDescription catalogDesc;

    protected List tableDescriptions = CollectionsPack.newList();
/*
    protected Map  schemeNames           = CollectionsPack.newMap();
*/


}