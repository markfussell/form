/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/Employee.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;
import java.util.*;

import com.chimu.kernel.collections.*;

public interface Employee extends Person {


    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Date hiredDate();
    public Company employer();
    public Collection projects();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void hiredBy_on(Company company, Date hiredDate);
    public void setProjects(Collection projects);

}