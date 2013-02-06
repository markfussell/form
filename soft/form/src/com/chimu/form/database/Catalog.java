/*======================================================================
**
**  File: chimu/form/database/Catalog.java
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
public interface Catalog {

    public String              name();
    public String              fullName();
    public boolean isAnonymous();

        /**
         * The qualified name of a catalog needs no prefix,
         * but could "conceptually" contain the prefix for the
         * actual database connection.
         */
    public String              qualifiedName();
    
    public Collection  schemes();
    public Scheme defaultScheme();
    
        /**
         * Get or create the scheme with the name 'schemeName'
         */
    public Scheme scheme(String schemeName);

    public void setName(String aName);
    
    public boolean equals(Catalog aCatalog);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void setDefaultScheme(Scheme newScheme);

};