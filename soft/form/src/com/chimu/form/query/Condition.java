/*======================================================================
**
**  File: chimu/form/query/Condition.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

/**
A Condition describes a true/false expression.  It is used in a query to 
determine whether an object is part of the return set.  Conditions are 
composable, so one condition can contain other conditions (e.g. you can 
AND two conditions together).

@see QueryPack#newOrCondition;
**/
public interface Condition extends QueryExpression {
}

