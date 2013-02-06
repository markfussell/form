/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnectorSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import java.sql.SQLException;

/**
AssociationConnectorSi is private to the FORM subsystem
**/
public interface AssociationConnectorSi extends AssociationConnector {
    public void crossValidate();

    /*for Query*/
    public Table table();
}