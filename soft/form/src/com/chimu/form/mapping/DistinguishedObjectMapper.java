/*======================================================================
**
**  File: chimu/form/mapping/DistinguishedObjectMapper.java
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


/**
A DistinguishedObjectMapper allows you to specify that the objects for this
mapper will all have a distinguishing value in one of their slots.  This
allows you to have two Distinguishing mappers use the same database table
without them interfering with each other.  If the actual distinguishing value
of a row matches the required distinguishing value for this mapper then the
row is part of the mapper's extent and an object is replicated for it.  Otherwise
the row is ignored.

<P>Note that this has no effect on identity.  If the distinguishing value is
required to make sure identityKeys are unique then the value (column) must be
included with the identityKeySlot definition.
**/

public interface DistinguishedObjectMapper extends ObjectMapper {

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************


    //**********************************************************
    //(P)******************** Setup Slots **********************
    //**********************************************************

    public Slot newDistinguishingSlot(
            String    slotName,
            String    mappedColumnName,
            Object    distinguishingValue
    );

};
