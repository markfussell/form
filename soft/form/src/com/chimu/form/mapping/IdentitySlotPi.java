/*======================================================================
**
**  File: chimu/form/mapping/IdentitySlotPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import java.sql.Connection;

interface IdentitySlotPi extends IdentitySlot, SetterSlotPi {
    /*package*/ public Object retrieveInsertIdentityFor_using(Object object, Connection connection);
    /*package*/ public void generateIdentityInto(Object object);

    /*package*/ public boolean hasIdentityGenerator();
};