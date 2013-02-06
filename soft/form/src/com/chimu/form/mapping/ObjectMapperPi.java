/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapperPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.query.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import java.sql.SQLException;


//Public temporarily for the Query interface
/*package*/ interface ObjectMapperPi extends ObjectMapperSi, ObjectRetrieverPi {
        /**
         *  Make sure the object has an identity both locally and on the server
         *@return True if we had to change the object
         */
    /*package*/ boolean forceServerIdentityOn(MappedObject domainObject);


    // This is an update if there were changes needed... for example, are there
    // any ForwardAssociations?, because those are not saved
    // but if there is nothing unusual, we don't actually have to do a second save
    //
    /*package*/ void postIdentityInsertUpdateObject(MappedObject domainObject);


    /*package*/ Column         identityKeyColumn();
    /*package*/ IdentitySlotPi identityKeySlot();


    //**********************************************************
    //**********************************************************
    //(P)********************* Testing *************************
    //**********************************************************
    //**********************************************************


}
