/*======================================================================
**
**  File: chimu/form/mapping/CreationInfoC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

/**
**/
/*package*/ class CreationInfoC implements CreationInfo {

    /*subsystem*/ public boolean isForStub() {
        return forStub;
    }

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    protected CreationInfoC(boolean forStub) {
        this.forStub = forStub;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected boolean forStub = false;

    //**********************************************************
    //(P)****************       STATIC       *******************
    //**********************************************************

    static /*package*/ CreationInfo newForStub() {
        return forStubObject;
    }

    static /*package*/ CreationInfo newForReplicate() {
        return forReplicateObject;
    }

    //**********************************************************
    //(P)****************   STATIC PRIVATE    ******************
    //**********************************************************

    static CreationInfo forStubObject      = new CreationInfoC(true);
    static CreationInfo forReplicateObject = new CreationInfoC(false);

}