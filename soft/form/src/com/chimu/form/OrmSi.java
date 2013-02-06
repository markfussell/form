/*======================================================================
**
**  File: chimu/form/OrmSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;


/**
An Orm (Object-Relational Mapper) is a root object that holds
onto all the information needed for mapping objects in a particular
application to the possibly-multiple databases involved.  ObjectMappers
are created within the context of an Orm and are uniquely named within
the Orms description.  Queries are also performed on the contents of
an Orm and so are created from the Orm methods listed below.
**/

public interface OrmSi extends Orm {
    List objectMappers();
}
