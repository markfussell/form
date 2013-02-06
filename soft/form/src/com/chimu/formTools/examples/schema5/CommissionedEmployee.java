/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/CommissionedEmployee.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;
import java.lang.*;

public interface CommissionedEmployee extends Employee {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Integer base();
    public Float percentage();


    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setBase(Integer base);
    public void setPercentage(Float percent);

}