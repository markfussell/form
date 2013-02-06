/*======================================================================
**
**  File: chimu/form/query/Constant.java
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
import java.util.Enumeration;

/**
A ConstantValue allows a basic, SQL convertible "ValueObject" to be added to
a query description.  The most common examples of objects you may want to directly
add to the SQL statement are numbers, strings, and booleans.

@see QueryPack#newDirectValueWrapper
**/

public interface Constant extends QueryExpression {

};

