/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/SalariedEmployee.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;
import java.lang.*;

public interface SalariedEmployee extends Employee {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Integer salary();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setSalary(Integer salary);
}