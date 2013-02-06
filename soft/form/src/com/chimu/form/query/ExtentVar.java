/*======================================================================
**
**  File: chimu/form/query/ExtentVar.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

/**
An ExtentVar is a "free" QueryVar that takes its values from an 
extent of objects (its range).  The extent is usually defined to
be the objects available in an ObjectRetriever.
**/
public interface ExtentVar extends QueryVar {
}
