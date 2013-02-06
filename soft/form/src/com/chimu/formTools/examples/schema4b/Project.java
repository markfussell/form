/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/Project.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
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
    public boolean isBudgeted();
    public Collection employees();
    public Double profitMargin();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================
    public void setName(String name);
    public void setIsBudgeted(boolean isBudgeted);
    public void addEmployee(Employee anEmployee);
    public void setEmployees(Collection employees);
    public void setProfitMargin(Double profit);
    public void setStartDate(Date aDate);
    public void setCompany(Company aCompany);

}

