/*======================================================================
**
**  File: chimu/form/mapping/MappedObject.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.collections.*;

/**
The MappedObject interface is used to determine whether a particular
object is a FORM mapped object or not, and to provide the protocol
for interacting with that object.  Any object that is used with an
ObjectMapper must support this protocol.

@see com.chimu.form.client.DomainObject_1_AbsC
@see com.chimu.form.Orm
**/

public interface MappedObject {

    //**********************************************************
    //(P)******************** Asking ***************************
    //**********************************************************

      /**
       * Return the ObjectMapper responsible this mapped object.
       */
    public ObjectMapper  form_objectMapper();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void          form_initIdentity(Object identityKey);
    public Object        form_identityKey();
    public boolean       form_isStub();

    public void          form_initState(KeyedArray slotValues);
    public void          form_extractStateInto(KeyedArray slotValues);
}

