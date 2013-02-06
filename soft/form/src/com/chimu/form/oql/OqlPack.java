/*======================================================================
**
**  File: chimu/form/oql/OqlPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.mapping.*;
import com.chimu.form.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;

/**
Oql provides the functionality to work with FORM OQL Queries and
convert them into the FORM Query system queries for execution
**/
public class OqlPack {
    private OqlPack() {}; //Library class, not instanciable

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static /*subsystem*/ public OqlQuery newQuery(Orm orm) {
        return new OqlQueryC(orm);
    }

    static /*subsystem*/ public OqlQuery newQuery(Orm orm, String oqlString) {
        return new OqlQueryC(orm, oqlString);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public static void main(String args[]) {
        OqlQuery oqlQuery = newQuery(null,"FROM People person WHERE person.name = \"foo\"");
        System.out.println(oqlQuery.parseString());
    }
}



