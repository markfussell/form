/*======================================================================
**
**  File: chimu/form/mapping/ObjectRetriever.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.query.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

/**
An ObjectRetriever allows you to retrieve objects from an extent
possibly based on the values of an Object's slots.

<P>Generally ObjectRetrievers are associated with the database
(the whole point of FORM), but this is not required by the
interface: you can have a local ObjectRetriever
that supports finding objects as well.  The important property
is that the retriever works in terms of slots and that objects
can be uniquely identified by their identityKey.
**/
public interface ObjectRetriever extends Slotted {

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************


    public String name();
    public Table  table();
    public String tableName();

    //**********************************************************
    //(P)******************** Searching ************************
    //**********************************************************

    public Object findWhereQuery(QueryDescription queryD);
    public Array selectWhereQuery(QueryDescription queryD);
    public int countWhereQuery(QueryDescription queryD);


    public Object findWhereSlotNamed_equals(String slotName, Object value);
    public Array selectWhereSlotNamed_equals(String slotName, Object value);

    public Object findForSlot_whereQuery(Slot resultSlot, QueryDescription queryD);
    public Array selectForSlot_whereQuery(Slot resultSlot, QueryDescription queryD);
//    public Array countForSlot_whereQuery(Slot resultSlot, QueryDescription queryD);

        /**
         * Return all objects
         */
    public Object findAny();
    public Array selectAll();
    public int countAll();

        /**
         * Find the object with the given identity key
         */
    public Object findIdentityKey(Object value);

};
