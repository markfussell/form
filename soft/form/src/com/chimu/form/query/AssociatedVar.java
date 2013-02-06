/*======================================================================
**
**  File: chimu/form/query/AssociatedVar.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

/**
An AssociatedVar is tied to an QueryVar through an association slot.
For example, if the ObjectVariable has a particular value (the object 'person1')
then the AssociatedObjectValue will have the value that is derived by traversing
through the association slot (e.g. 'person1.address')
**/
public interface AssociatedVar extends DerivedVar {
    public QueryVar  associatedTo();
    public Slot      associatedThrough();
}
