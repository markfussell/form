/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/SalariedEmployeeC.java
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
SalariedEmployee class models an worker whose salary is a fixed
number annually.
**/

public class SalariedEmployeeC extends EmployeeAbsC implements SalariedEmployee {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public SalariedEmployeeC(String name, String email,
                int height, Company employer, Date hiredDate,
                Integer salary) {
        super();
        this.email = email;
        this.name = name;
        this.employer = employer;
        this.height = height;
        this.hiredDate = hiredDate;
        this.salary = salary;
    }

    protected SalariedEmployeeC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        SalariedEmployeeC newObject = new SalariedEmployeeC();
        newObject.initFrom(this);
        return newObject;
    }

    protected void initFrom(SalariedEmployeeC employee) {
        super.initFrom(employee);
        this.salary   =   employee.salary;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
       return "<Salaried Employee #"+myOid+" name "+name+ " email " + email +" hiredDate " + hiredDate+" salary " + salary + ">";
    }
        /**
        ==  Returns a string that is for display.
        **/
    public String info() {unstub();
        return "Salaried Employee name: " + name() + " salary " + annualSalary();
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Integer salary() {unstub();
        return salary;
    }

    public Float annualSalary() {unstub();
        if (salary != null) {
            return new Float(salary.floatValue());
        } else {
            return null;
        };

    }

    public boolean isSalaried() {
        return true;
    }

    public boolean isCommissioned() {
        return false;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setSalary(Integer salary) {unstub();
        this.salary = salary;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Integer salary;


//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Mon Sep 15 17:15:19 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.salary = (Integer) slotValues.atKey("salary");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("salary",this.salary);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public SalariedEmployeeC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return SalariedEmployeeC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new SalariedEmployeeC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>
}