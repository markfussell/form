/*======================================================================
**
**  File: chimu/form/query/QueryPackSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;

/*subsystem*/ public class QueryPackSi {
    private QueryPackSi() {}; //Library class, not instanciable

    //**********************************************************
    //(P)***************** Factory Methods ********************
    //**********************************************************

    //**************************************
    //(P)******** QueryDescription *********
    //**************************************

        /**@deprecated*/
    static public QueryDescription newQueryDescription() {
        return new QueryDescriptionC();
    }

    static public QueryDescription newQueryDescription(Orm orm) {
        return new QueryDescriptionC(orm);
    }

    //**************************************
    //(P)*********** SqlBuilder ************
    //**************************************

    static public SqlBuilder newSqlBuilder() {
        return new SqlBuilderC();
    };


}



