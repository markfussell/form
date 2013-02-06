/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/City.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;
public interface City {
    //==========================================================
    //(P)=================== Displaying ========================
    //==========================================================
    public String toString();
    public String info();

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name();
    public String state();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name);
    public void setState(String state);

}