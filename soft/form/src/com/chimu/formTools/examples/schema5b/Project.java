/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/Project.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;
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
    public Boolean isBudgeted();
    public Collection employees();
    public Double profitMargin();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================
    public void setName(String name);
    public void setCompany(Company aCompany);
    public void setIsBudgeted(Boolean isBudgeted);
    public void setEmployees(Collection employees);
    public void setProfitMargin(Double profit);
    public void setStartDate(Date aDate);
    public void addEmployee(Employee anEmployee);

}

