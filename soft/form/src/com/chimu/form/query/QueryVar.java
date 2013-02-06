/*======================================================================
**
**  File: chimu/form/query/QueryVar.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

/**
A QueryVar is the basic variable in Queries.  A variable takes 
on values during the evaluation of a Query and can be used to
restrict the queries results (when used in a condition) and to
specify which values are desired (when used as the result value).

<P>The main QueryVar subclasses are ExtentVar and AssociatedVar 
(aka SlotVar).

@see ExtentVar
@see AssociatedVar
**/
public interface QueryVar extends QueryExpression{

    public String name();
    
        /**
         * The ObjectRetriever (Type) for the values that this
         * QueryVar will select over
         */
    public ObjectRetriever valuesRetriever();

        /**
         * Create a new QueryVar by creating an AssociatedVar (SlotVar)
         * that is connected to this QueryVar through the named slot.
         * All QueryVars supply this method but it is only valid if 
         * the object has a slot with the slotName specified.  Non-slotted
         * QueryVars will fail for all slotNames.
         */
    public AssociatedVar slotNamed(String slotName);

        /**
         *@deprecated Use slotNamed instead.
         */
    public AssociatedVar slot(Slot slot);

}
