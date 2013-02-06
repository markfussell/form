/*======================================================================
**
**  File: chimu/form/mapping/ExternalAssociationSlotSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;

import com.chimu.kernel.collections.*;

import java.sql.SQLException;

/**
ExternalAssociationSlotSi is private to the FORM system and should not be used
by FORM clients
**/
/*subsystem(Query)*/ public interface ExternalAssociationSlotSi extends ExternalAssociationSlot, SlotSi {
    /*subsystem(Query)*/ public AssociationConnector     associationConnector();
    /*subsystem(Query)*/ public AssociationConnectorSlot associationSlot();
}