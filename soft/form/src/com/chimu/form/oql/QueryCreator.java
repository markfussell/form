/*======================================================================
**
**  File: chimu/form/oql/QueryCreator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;

interface QueryCreator {
    public abstract void buildQueryFor_into(OqlQueryPi oql, QueryDescription description);
}
