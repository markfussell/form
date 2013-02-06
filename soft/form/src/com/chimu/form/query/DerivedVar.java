/*======================================================================
**
**  File: chimu/form/query/DerivedVar.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

/**
A DerivedVar is tied to another QueryVar
**/
public interface DerivedVar extends QueryVar {
}


//  ov.derivedFromVariable().derivedThroughSlot().
//  ov.associatedVariable().associatedThrough()