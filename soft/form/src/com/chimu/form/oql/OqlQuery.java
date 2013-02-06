/*======================================================================
**
**  File: chimu/form/oql/OqlQuery.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;

/**
OqlQuery
**/
public interface OqlQuery {
    public String oqlString();
    public String parseString();
        /**
         * createQuery forces a new query to be created and
         * returned.
         *@see #query
         */
    public QueryDescription createQuery();
    public QueryDescription query();

    public void bindName_toValue(String name, Object value);
    public Object valueForName(String name);
}



