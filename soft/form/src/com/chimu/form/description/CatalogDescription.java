/*======================================================================
**
**  File: chimu/form/description/CatalogDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;


import java.io.Serializable;
import com.chimu.kernel.collections.*;
import com.chimu.form.database.*;

public interface CatalogDescription extends Serializable {

    public void initFrom(Catalog catalog);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String              name();
    public String              fullName();
    public boolean isAnonymous();
    
         /**
         * The qualified name of a catalog needs no prefix.
         * 
         */
    public String              qualifiedName();

    public Collection  schemeDescriptions();
    public SchemeDescription defaultSchemeDescription();
    public void addSchemeDescription(SchemeDescription sd);


        /**
         * Get or create the scheme with the name 'schemeName'
         */
//    public SchemeDescription schemeDesc(String schemeName);

    public void setName(String aName);
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void createCatalogIn(DatabaseConnection dbConnection);
    public void buildDbIn(DatabaseConnection dbConnection);

   
}