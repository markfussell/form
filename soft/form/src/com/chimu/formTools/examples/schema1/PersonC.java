/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/PersonC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import java.sql.Connection;
import java.util.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.kernel.meta.TypeConstants;

public class PersonC implements Person {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public PersonC(){}

    public Person initName_email_height(String name, String email, int height){
        myName   = name;
        myEmail  = email;
        myHeight = height;
        return this;
    }

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {
        PersonC newObject = new PersonC();
        newObject.initFrom(this);
        return newObject;
    }

    protected void initFrom(PersonC person) {
        myName   = person.myName;
        myEmail  = person.myEmail;
        myHeight = person.myHeight;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {
         return "<Person id={"+super.toString()+"} "+myName+ ">";
    }

    public String getInfo() {
         return myName + " is " + myHeight + " inches tall and can be contacted at "+myEmail+"@org.com";
    }

    //==========================================================
    //(P)==================== Asking ===========================
    //==========================================================

    public String getName() {
        return myName;
    }

    public String getEmail() {
        return myEmail;
    }

    public int getHeight() { 
        return myHeight;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {
        myName = name;
    }

    public void setEmail(String email) {
        myEmail = email;
    }

    public void setHeight(int height) {
        myHeight = height;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String myName;
    protected String myEmail;
    protected int    myHeight;

}