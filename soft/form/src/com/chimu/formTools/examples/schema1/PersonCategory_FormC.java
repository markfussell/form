/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/PersonCategory_FormC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import com.chimu.kernel.collections.*;


import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

public class PersonCategory_FormC implements PersonCategory {

    //==========================================================
    //(P)================== Retrieving =========================
    //==========================================================

        /**
         * Return any person from the extent of people
         * @cat Retrieving
         */
    public Person findAny() {
        return (Person) myRetriever.findAny();
    }

        /**
         * Return all the persons from the extent of people
         * @cat Retrieving
         */
    public List selectAll() {
        return (List) myRetriever.selectAll();
    }

        /**
         * Create a new instance of a person
         * @cat Constructing
         */
    public Person newName_email_height(String name, String email, int height) {
        return new Person_FormC().initName_email_height(name,email,height);
    }

    //==========================================================
    //==========================================================
    //==========================================================

    public PersonCategory initRetriever(ObjectRetriever or) {
        myRetriever = or;
        return this;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected ObjectRetriever myRetriever;
}

