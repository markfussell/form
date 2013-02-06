/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/PersonCategoryC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import com.chimu.kernel.collections.*;


public class PersonCategoryC implements PersonCategory {

    //==========================================================
    //(P)================== Retrieving =========================
    //==========================================================

        /**
         * Return any person from the extent of people
         * @cat Retrieving
         */
    public Person findAny() {
        return (Person) myExtent.any();
    }

        /**
         * Return all the persons from the extent of people
         * @cat Retrieving
         */
    public List selectAll() {
        return myExtent;
    }

        /**
         * Create a new instance of a person
         * @cat Constructing
         */
    public Person newName_email_height(String name, String email, int height) {
        return new PersonC().initName_email_height(name,email,height);
    }

    //==========================================================
    //==========================================================
    //==========================================================

    public PersonCategory initialize() {
        myExtent = CollectionsPack.newList();
        
        String[][] data = new String[][]{
                {"Art Larsson","larssar","63"},
                {"Lino Buchanan","buchali","75"},
                {"Julie Fonseca","fonseju","66"}
            };
            
        populateFrom(data);
        
        return this;    
    }
    
    protected void populateFrom(String[][] data) {
        for (int i=0; i<data.length; i++) {
            String[] eachEntry = data[i];
            String name   = eachEntry[0];
            String email  = eachEntry[1];
            int    height = Integer.parseInt(eachEntry[2]);
            Person person = new PersonC().initName_email_height(name,email,height);
            myExtent.add(person);
        }
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected List myExtent;
}

