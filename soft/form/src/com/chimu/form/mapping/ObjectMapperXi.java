/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapperXi.java
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

/**
The ObjectMapperXi interface provides additional functionality that can
be used if a particular DomainObject (which must support the MappedObjectXi
interface) wants to be part of the save process.

@see MappedObjectXi
**/
public interface ObjectMapperXi extends ObjectMapper {
    /*extended*/ void insertIdentityForObject(MappedObject domainObject);
    /*extended*/ void afterIdentityInsertUpdateObject(MappedObject domainObject);
    
    /*extended*/ public KeyedArray newSlotRowKeyedArray(MappedObject forObject);
    
}
