/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/DomainObjectAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
import com.chimu.form.mapping.*;
import com.chimu.form.client.DomainObject_1_AbsC;

/**
DomainObjectAbsC provides an inheritable implementation of
standard features needed for any DomainObject within this
scheme
**/

abstract class DomainObjectAbsC extends DomainObject_1_AbsC {

    protected DomainObjectAbsC() {};

//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.5.1 ran on: Wed Jul 30 14:15:05 PDT 1997

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    protected DomainObjectAbsC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

//}GenerateObjectBinding>

}


