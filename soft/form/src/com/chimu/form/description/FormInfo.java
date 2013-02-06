/*======================================================================
**
**  File: chimu/form/description/FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.Orm;
import com.chimu.form.database.DatabaseConnection;

public interface FormInfo {

        /**
         * Return the MapperBuilder that this FormInfo uses
         */
    public MapperBuilder mapperBuilder();

        /**
         * This creation method can only be used if the FormInfo
         * subclass explicitly knows its DomainC object
         */
    public void initOrm_db(Orm orm, DatabaseConnection dbConnection);
    public void initOrm_db_class(Orm orm, DatabaseConnection dbConnection, Class myC);

}

