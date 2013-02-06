/*======================================================================
**
**  File: chimu/form/mapping/ExternalAssociationSlotPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;

import com.chimu.kernel.collections.*;

import java.sql.SQLException;

/*package*/ interface ExternalAssociationSlotPi extends ExternalAssociationSlotSi, AssociationSlotPi {
    void deleteAssociationsFor(MappedObject object);
    void insertAssociationsFor(MappedObject object, KeyedCollection slotValues);
    void updateAssociationsFor(MappedObject object, KeyedCollection slotValues);
}