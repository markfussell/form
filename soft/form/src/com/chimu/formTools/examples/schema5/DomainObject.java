/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/DomainObject.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;
import com.chimu.form.mapping.*;
import com.chimu.form.client.DomainObject_1;

/**
DomainObject describes the interface expected of all domain objects.
**/

interface DomainObject extends DomainObject_1 {

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

        /**
         * Provide a simple (developer) string representation of the object
         */
    public String toString();

        /**
         * Give some facts about the object
         */
    public String info();

}


