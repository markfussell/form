/*======================================================================
**
**  File: chimu/form/mapping/CreationInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

/**
CreationInfo has information that is used for the FORM constructor.  This
interface is semi-private to FORM: clients should not use the interface
except for the FORM specific construction binding.
**/
/*subsystem*/ public interface CreationInfo {
    /*subsystem*/ public boolean isForStub();
}