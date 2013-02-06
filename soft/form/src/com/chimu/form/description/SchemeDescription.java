/*======================================================================
**
**  File: chimu/form/description/SchemeDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.kernel.collections.*;
import java.io.Serializable;
import com.chimu.form.database.*;

public interface SchemeDescription extends Serializable {

    public void initFrom(Scheme sc);
    public void initFrom(Scheme sc, CatalogDescription cd);
    
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String  name();
    public String  fullName();
    public boolean isAnonymous();

    public void setName(String aName);
    public void addTableDescription(TableDescription td);

    
    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * The qualified name of a scheme includes the SQL-92 standard
         * prefixes of "<Catalog>." if this is known to
         * the table or database connection.
         */

    public String              qualifiedName();


    public CatalogDescription  catalogDescription();
    public Collection          tableDescriptions();
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void createSchemeIn(Catalog aCatalog, DatabaseConnection dbConnection);
    
    public void buildDbIn(DatabaseConnection dbConnection);
}
