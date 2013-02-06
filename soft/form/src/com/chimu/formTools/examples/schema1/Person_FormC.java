/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Person_FormC.java
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

public class Person_FormC extends DomainObjectAbsC implements Person {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public Person_FormC(){}

    public Person initName_email_height(String name, String email, int height){
        myName   = name;
        myEmail  = email;
        myHeight = height;
        return this;
    }

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        Person_FormC newObject = new Person_FormC();
        newObject.initFrom(this);
        return newObject;
    }

    protected void initFrom(Person_FormC person) {
        super.initFrom(person);
        myName   = person.myName;
        myEmail  = person.myEmail;
        myHeight = person.myHeight;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
         return "<Person#"+myOid+" "+myName+ ">";
    }

    public String getInfo() {unstub();
         return myName + " is " + myHeight + " inches tall and can be contacted at "+myEmail+"@org.com";
    }

    //==========================================================
    //(P)==================== Asking ===========================
    //==========================================================

    public String getName() {unstub();
        return myName;
    }

    public String getEmail() {unstub();
        return myEmail;
    }

    public int getHeight() { unstub();
        return myHeight;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {unstub();
        myName = name;
    }

    public void setEmail(String email) {unstub();
        myEmail = email;
    }

    public void setHeight(int height) {unstub();
        myHeight = height;
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String myName;
    protected String myEmail;
    protected int    myHeight;


//<GenerateObjectBinding{
    //FORM Preprocessor Version 1.7 ran on: Sun Sep 07 04:50:15 PDT 1997

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        myName        = (String) slotValues.atKey("name");
        myEmail       = (String) slotValues.atKey("email");
        try{ myHeight = ((Integer) slotValues.atKey("height")).intValue();  } catch (Exception e) {myHeight=0;};
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("name",myName);
        slotValues.atKey_put("email",myEmail);
        slotValues.atKey_put("height",new Integer(myHeight));
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public Person_FormC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return Person_FormC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new Person_FormC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>
}