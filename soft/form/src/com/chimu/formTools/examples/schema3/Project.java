/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/Project.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;
import java.util.*;

import com.chimu.kernel.collections.*;

/**
Part models
**/
public interface Project extends DomainObject {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name();
    public Company company();
    public Date startDate();
    public Collection employees();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================
    public void setName(String name);
    public void setCompany (Company aCompany);
    public void setStartDate(Date startDate);
    public void addEmployee(Employee anEmployee);
    public void setEmployees(Collection employees);

}

