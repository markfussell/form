/*======================================================================
**
**  File: chimu/form/query/QueryDescriptionPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import java.util.Enumeration;


/**
A QueryDescription describes the information necessary for building a
query including what extent/s of objects will be searched and how to
determine what objects will be selected from those extent/s.

<P>Since the QueryDescription has to keep track of what variables are
still unbound,

<P>A QueryDescription has after-definition flexibility by not having its
ObjectVars attached to a mapper and by having unbound values.

@see QueryPack#newQueryDescription
**/
/*package*/ interface QueryDescriptionPi extends QueryDescription {
    /*package*/ public void addExtentVar(ExtentVar ev);
    /*package*/ public void addQueryVar(QueryVar qv);

    // boolean hasVariableNamed(String name);
    /*package*/ public String variableNameFromPrefix(String name);
    /*package*/ public String variableNameFromPrefix(String nameStart, String nameMiddle, String nameEnd);

};

