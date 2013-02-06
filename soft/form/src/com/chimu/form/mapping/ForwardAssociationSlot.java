/*======================================================================
**
**  File: chimu/form/mapping/ForwardAssociationSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

/**
A ForwardAssociationSlot handles an association using the simplest approach:
just hold onto your partner's identityKey.  This corresponds to having a 
foreign-key in your table.  A ForwardAssociationSlot can only be X-to-one.
**/
public interface ForwardAssociationSlot extends AssociationSlot{
};