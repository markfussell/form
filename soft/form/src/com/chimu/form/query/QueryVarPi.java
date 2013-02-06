/*======================================================================
**
**  File: chimu/form/query/QueryVarPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.kernel.collections.*;

/**
QueryVarPi
@see QueryVar
**/
interface QueryVarPi extends QueryVar, QueryExpressionPi {
        /**
         * The QueryDescription that this QueryVar is attached to
         */
    /*package*/ public QueryDescriptionPi      queryDescription();
    /*package*/ public Array  selectAll();
    /*package*/ public Object                  findAny();
    /*package*/ public int                     countAll();

    /*package*/ public void makeNeededInResults();

    /*
    public Object findAny();
    public Object countAll();
    public boolean hasAny();
    */

    //**********************************************************
    //(P)***************** QueryGeneration *********************
    //**********************************************************
    /*package*/ public void putSelectColumnsInto(SqlBuilder sqlB);
    /*package*/ public void putObjectIdentityColumnInto(SqlBuilder sqlB);

        /**
         * Add any Extents that are required for the SqlBuilder
         */
    /*package*/ public void addNeededExtentsTo(SqlBuilder sqlB);
    /*package*/ public void addNeededJoinsTo(SqlBuilder sqlB);

};
