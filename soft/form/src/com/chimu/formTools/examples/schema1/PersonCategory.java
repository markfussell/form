/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/PersonCategory.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import com.chimu.kernel.collections.*;

/**
PersonCategory manages the collection of Persons within the Domain.
It is both a Factory for Persons and contains an extent of 
Persons over which it can search and retrieve specific objects.
**/
public interface PersonCategory {

    //==========================================================
    //(P)================== Retrieving =========================
    //==========================================================

        /**
         * Return any person from the extent of people
         * @cat Retrieving
         */
    public Person findAny();

        /**
         * Return all the persons from the extent of people
         * @cat Retrieving
         */
    public List selectAll();

    //==========================================================
    //(P)================= Constructing ========================
    //==========================================================

        /**
         * Create a new instance of a person
         * @cat Constructing
         */
    public Person newName_email_height(String name, String email, int height);

}

