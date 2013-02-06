/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/ProjectC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;
import java.sql.Connection;
import java.util.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;


import com.chimu.form.mapping.*;
import com.chimu.form.database.*;
import com.chimu.form.*;
/**

**/
public class ProjectC extends DomainObjectAbsC implements Project {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public ProjectC(String name, Company company, Date startDate, Boolean isBudgeted, Double profit){
        super();
        this.name = name;
        this.company = company;
        this.startDate = startDate;
        this.isBudgeted = isBudgeted;
        this.profitMargin = profit;
    }

    protected ProjectC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        ProjectC newObject = new ProjectC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(ProjectC oldObject) {
        super.initFrom(oldObject);
        this.name = oldObject.name;
        this.company = oldObject.company;
        this.startDate = oldObject.startDate;
        this.isBudgeted = oldObject.isBudgeted;
        this.profitMargin = oldObject.profitMargin;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() { unstub();
        return "<" + myOid + " name " + name + " start date " + startDate + " budgeted " + isBudgeted + " profit margin " + profitMargin + " company " + company +">";
    }

    public String info() {unstub();
        String retString = "";
        if (isBudgeted.booleanValue()) {
            retString = " ";
        }
        else {
            retString = "not ";
        };
        String profitMarString =  " with unknown profit margin";
        if (profitMargin() != null) {
            profitMarString = " profit margin " + this.profitMargin;
        };

        return this.name() + " is " + retString + "currently budgeted" + profitMarString;
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name() {unstub();
        return name;
    }

    public Company company() {unstub();
        return company;
    }

    public Date startDate() {unstub();
        return startDate;
    }

    public Boolean isBudgeted() {unstub();
        return isBudgeted;
    }

    public Double profitMargin() {unstub();
        return profitMargin;
    }

    public Collection employees() { unstub();
        return employees;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {unstub();
        this.name = name;
    }

    public void setCompany(Company company) {unstub();
        this.company = company;
    }

    public void setStartDate(Date startDate) {unstub();
        this.startDate = startDate;
    }

    public void setIsBudgeted(Boolean isBudgeted) {unstub();
        this.isBudgeted = isBudgeted;
    }

    public void setProfitMargin(Double profit) {unstub();
        this.profitMargin = profit;
    }

    public void setEmployees(Collection employees) { unstub();
        this.employees = employees;
    }

    public void addEmployee(Employee anEmployee) { unstub();
        if (this.employees == null) {
            List aCollection = CollectionsPack.newList();
            aCollection.add(anEmployee);
            this.setEmployees(aCollection);
        } else {
            Collection aCollection = (Collection) this.employees;
            boolean doesInclude = aCollection.includes(anEmployee);
            if (!doesInclude) {
                aCollection.add(anEmployee);
                this.setEmployees(aCollection);
            };
        };
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String name;
    protected Company company;
    protected Date startDate;
    protected Boolean isBudgeted;
    protected Double profitMargin;
    protected Collection employees;

//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Tue Sep 16 00:07:15 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.name = (String) slotValues.atKey("name");
        this.company = (Company) slotValues.atKey("company");
        this.startDate = (Date) slotValues.atKey("startDate");
        this.isBudgeted = (Boolean) slotValues.atKey("isBudgeted");
        this.profitMargin = (Double) slotValues.atKey("profitMargin");
        this.employees = (Collection) slotValues.atKey("employees");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("name",this.name);
        slotValues.atKey_put("company",this.company);
        slotValues.atKey_put("startDate",this.startDate);
        slotValues.atKey_put("isBudgeted",this.isBudgeted);
        slotValues.atKey_put("profitMargin",this.profitMargin);
        slotValues.atKey_put("employees",this.employees);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public ProjectC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return ProjectC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new ProjectC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>

}