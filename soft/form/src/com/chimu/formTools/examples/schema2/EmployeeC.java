/*======================================================================
**
**  File: com/chimu/formTools/examples/schema2/EmployeeC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema2;
import java.util.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

/**
Employee class models an long term worker of a company.
**/
public class EmployeeC extends PersonAbsC
               implements Employee {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public EmployeeC(String name, String email,
                int height, Company employer, Date hiredDate) {
        super(name,email,height);
        this.employer = employer;
        this.hiredDate = hiredDate;
    }

    protected EmployeeC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        EmployeeC newObject = new EmployeeC();
        newObject.initFrom(this);
        return newObject;
    }

    protected void initFrom(EmployeeC employee) {
        super.initFrom(employee);
        this.employer =     employee.employer;
        this.hiredDate =    employee.hiredDate;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
       return "<Employee#"+myOid+" "+name + ">";
    }
        /**
        ==  Returns a string that is for display.
        **/
    public String info() {unstub();
       return "Employee " + name;
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
        ((CompanyC) company).noteHiredEmployee(this);  //Access the <Friend> protocol
   }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Company   employer;
    protected Date      hiredDate;


//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Sun Sep 07 05:33:13 PDT 1997

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

    /*friend:FORM*/ public EmployeeC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return EmployeeC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new EmployeeC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>
}