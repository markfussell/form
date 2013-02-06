/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/CompanyC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
import java.sql.Connection;
import java.util.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
/**
Company class models an entity that does business with external parties and
has employees
**/
public class CompanyC extends DomainObjectAbsC implements Company {


    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public CompanyC(String name, Money revenue){
        super();
        this.name = name;
        this.revenue = revenue;
    }

    protected CompanyC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        CompanyC newObject = new CompanyC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(CompanyC company) {
        super.initFrom(company);
        this.name = company.name;
        this.revenue = company.revenue;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
       return this.name;
       }

        /**  This is the string that we use for displaying to external
         ==  world.
         **/
    public String info() { unstub();
        if (this.name == null) {
            return "Company has no name";
        };
        if (this.revenue == null) {
            return this.name + " with no revenue information available.";
        } else {
            return this.name + " with revenue: " + this.revenue;
        };
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name() {unstub();
        return name;
    }

    public Money revenue() {unstub();
        return revenue;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {unstub();
        this.name = name;
    }

    public void setRevenue(Money revenue) {unstub();
        this.revenue = revenue;
    }

    public Project newProject(String name, Date startDate, Double profitMargin) {unstub();
        return new ProjectC(name,this,startDate,false, profitMargin);
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String name;
    protected Money revenue;


//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Sun Sep 07 12:24:23 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.name = (String) slotValues.atKey("name");
        this.revenue = (Money) slotValues.atKey("revenue");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("name",this.name);
        slotValues.atKey_put("revenue",this.revenue);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public CompanyC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return CompanyC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new CompanyC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>


}