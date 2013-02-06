/*======================================================================
**
**  File: chimu/form/oql/OqlQueryC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.*;
import com.chimu.form.ExecutionException;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;

/**
OqlQuery
**/
/*package*/ class OqlQueryC implements OqlQueryPi {
    protected OqlQueryC(Orm orm) {
        this.orm = orm;
        this.oqlString = "";
    }

    protected OqlQueryC(Orm orm, String oqlString) {
        this.orm = orm;
        this.oqlString = oqlString;
    }

    public String oqlString() {
        return oqlString;
    }

    public String parseString() {
            //Change to a Reader later
        OqlParser parser = new OqlParser(new java.io.StringBufferInputStream(oqlString));
        try {
            parser.Query();
            ((SimpleNode) parser.jjtree.rootNode()).dump("");
        } catch (Exception e) {
            throw new ExecutionException("OqlParser encountered errors during parse of: "+oqlString,e);
        }

        return "";
    }

    public QueryDescription createQuery() {
        buildQuery = orm.newQueryDescription();
        OqlParser parser = new OqlParser(new java.io.StringBufferInputStream(oqlString));
        try {
            parser.Query();
            ((SimpleNode) parser.jjtree.rootNode()).buildQueryFor_into(this,buildQuery);
        } catch (ParseError e) {
            throw new ExecutionException("OqlParser encountered errors during parse of: "+oqlString,e);
        }
        return buildQuery;
    }

    public QueryDescription query() {
        if (buildQuery != null) return buildQuery;
        return createQuery();
    }

    public void bindName_toValue(String name, Object value) {
        nameToValue.atKey_put(name,value);
    }

    public Object valueForName(String name) {
        return nameToValue.atKey(name);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected final Orm orm;
    protected String oqlString;
    protected QueryDescription buildQuery = null;

    protected List variableNames = CollectionsPack.newList();
    protected Map      nameToValue   = CollectionsPack.newMap();
}



