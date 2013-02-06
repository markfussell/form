/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/Person.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
public interface Person extends DomainObject {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name();
    public String email();
    public int height();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name);
    public void setEmail(String email);
    public void setHeight(int height);

}