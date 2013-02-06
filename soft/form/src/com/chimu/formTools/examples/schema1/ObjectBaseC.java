/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/ObjectBaseC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

/**
The ObjectBase provides...
**/
public class ObjectBaseC implements ObjectBase {

    public PersonCategory getPersonCategory() {
        return myPersonCategory;
    }


    public ObjectBase initialize() {
        myPersonCategory = new PersonCategoryC().initialize();
        return this;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected PersonCategory myPersonCategory;

}

