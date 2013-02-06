/*======================================================================
**
**  File: chimu/form/client/DomainObject_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.client;

import com.chimu.form.mapping.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

/**
DomainObject_1 is a sample domain object public interface.  This is
also the public interface to DomainObject_1_AbsC implementation.
@see DomaionObject_1_AbsC
**/
public interface DomainObject_1 {

    //==========================================================
    //(P)================= Storage Changing ====================
    //==========================================================
    /*(PC)
       The following are some methods which could be expected
       of all domain objects to support saving them to the
       database.
    */

        /**
         * Write will either insert or update the object
         * to the database depending on the whether it
         * is a new object or a database replicate.
         */
    public void write();

        /**
         * Delete removes the domain object from the database.
         */
    public void delete();

        /**
         * Create a new domain object which has all its attributes
         * copied from the current object.
         */
    public Object copy();
}
