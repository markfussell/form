/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/CommissionedEmployeeC.java
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

import com.chimu.kernel.meta.TypeConstants;

/**
CommissionedEmployee class models an worker whose salary is
calculated via some percentage over the base.
**/

public class CommissionedEmployeeC extends EmployeeAbsC implements CommissionedEmployee {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public CommissionedEmployeeC(String name, String email,
                int height, Company employer, Date hiredDate,
                Integer base, Float percentage) {
        super();
        this.email = email;
        this.name = name;
        this.employer = employer;
        this.height = height;
        this.hiredDate = hiredDate;
        this.base = base;
        this.percentage = percentage;
    }

    protected CommissionedEmployeeC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        CommissionedEmployeeC newObject = new CommissionedEmployeeC();
        newObject.initFrom(this);
        return newObject;
    }

    protected void initFrom(CommissionedEmployeeC employee) {
        super.initFrom(employee);
        this.base = employee.base;
        this.percentage = employee.percentage;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
       return "<Commssioned Employee #"+myOid+" name "+name+" hiredDate " + hiredDate+" base "+ base+" percentage " + percentage +">";
    }
        /**
        ==  Returns a string that is for display.
        **/
    public String info() {unstub();
        return "CommissionedEmployee name: " + name() + " salary " + annualSalary();
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Integer base() {
        unstub();
        return base;
    }

    public Float percentage() {
        unstub();
        return percentage;
    }

    public Float annualSalary() {
        unstub();
        if (percentage != null) {
            return new Float(base.intValue() * (1 + percentage.floatValue()));
        } else {
            return null;
        };

    }

    public boolean isSalaried() {
        return false;
    }

    public boolean isCommissioned() {
        return true;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setBase(Integer base) {
        unstub();
        this.base = base;
    }

    public void setPercentage(Float percent) {
        unstub();
        this.percentage = percent;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Integer   base;
    protected Float percentage;


//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Mon Sep 15 17:25:38 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.base = (Integer) slotValues.atKey("base");
        this.percentage = (Float) slotValues.atKey("percentage");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("base",this.base);
        slotValues.atKey_put("percentage",this.percentage);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public CommissionedEmployeeC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return CommissionedEmployeeC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new CommissionedEmployeeC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>

}