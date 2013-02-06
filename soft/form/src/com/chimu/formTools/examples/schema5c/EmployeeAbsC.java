/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/EmployeeAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;
import java.util.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

/**
Employee class models an long term worker of a company.
**/
public abstract class EmployeeAbsC extends PersonAbsC
               implements Employee {

    protected EmployeeAbsC() {
        super();
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Date hiredDate() {unstub();
        return hiredDate;
    }

    public Company employer() {unstub();
        return employer;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void hiredBy_on(Company company, Date hiredDate) {unstub();
        this.employer = company;
        this.hiredDate = hiredDate;
   }
    //==========================================================
    //(P)=======================================================
    //==========================================================

    protected void initFrom(EmployeeAbsC employee) {
        super.initFrom(employee);
        this.employer =     employee.employer;
        this.hiredDate =    employee.hiredDate;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Company   employer;
    protected Date      hiredDate;

//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Fri Sep 12 13:00:29 PDT 1997 

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.employer = (Company) slotValues.atKey("employer");
        this.hiredDate = (Date) slotValues.atKey("hiredDate");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("employer",this.employer);
        slotValues.atKey_put("hiredDate",this.hiredDate);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public EmployeeAbsC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return EmployeeAbsC.class;
    }

//}GenerateObjectBinding>

}