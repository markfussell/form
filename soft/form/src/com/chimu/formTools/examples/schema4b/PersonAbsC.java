/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/PersonAbsC.java
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

public abstract class PersonAbsC extends DomainObjectAbsC implements Person {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public PersonAbsC(String name, String email, int height){
        super();
        this.name = name;
        this.email = email;
        this.height = height;
    }

    protected PersonAbsC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    protected void initFrom(PersonAbsC person) {
        super.initFrom(person);
        this.name = person.name;
        this.email = person.email;
        this.height = person.height;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
         return "<Person#"+myOid+" "+name + ">";
    }

    public String info() {unstub();
         return name + " is " + height + " inches tall and can be contacted at "+email+"@org.com";
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name() {unstub();
        return name;
    }

    public String email() {unstub();
        return email;
    }

    public int height() { unstub();
        return height;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {unstub();
        this.name = name;
    }

    public void setEmail(String email) {unstub();
        this.email = email;
    }

    public void setHeight(int height) {unstub();
        this.height = height;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String name;
    protected String email;
    protected int height;

//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Sun Sep 07 02:56:40 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.name = (String) slotValues.atKey("name");
        this.email = (String) slotValues.atKey("email");
        try{ this.height = ((Integer) slotValues.atKey("height")).intValue();  } catch (Exception e) {this.height=0;};
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("name",this.name);
        slotValues.atKey_put("email",this.email);
        slotValues.atKey_put("height",new Integer(this.height));
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public PersonAbsC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return PersonAbsC.class;
    }

//}GenerateObjectBinding>
}