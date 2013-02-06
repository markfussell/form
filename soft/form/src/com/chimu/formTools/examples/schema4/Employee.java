/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/Employee.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
import java.util.*;

import com.chimu.kernel.collections.*;

public interface Employee extends Person {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Date hiredDate();
    public Collection projects();
    public Company employer();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void hiredBy_on(Company company, Date hiredDate);
}