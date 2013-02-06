/*======================================================================
**
**  File: chimu/form/mapping/AssociationSlotPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;

import java.sql.SQLException;

/*package*/ interface AssociationSlotPi extends SetterSlotPi, AssociationSlot {
    /*package*/ public void deleteCascadeFor(MappedObject object);
};