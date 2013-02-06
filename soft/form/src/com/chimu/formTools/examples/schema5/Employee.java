/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/Employee.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;
import java.util.*;

public interface Employee extends Person {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Date hiredDate();
    public Company employer();
    public Float annualSalary();

    public boolean isSalaried();
    public boolean isCommissioned();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void hiredBy_on(Company company, Date hiredDate);


}