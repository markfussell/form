/*======================================================================
**
**  File: chimu/form/mapping/DistinguishingObjectMapperPi.java
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
A DistinguishingObjectMapper allows you to specify that the objects for this
mapper will all have a distinguishing value for one of their slots.  This
allows you to have two Distinguishing mappers use the same database table
without them interfering with each other.  If the actual distinguishing value
of a row matches the required distinguishing value for this mapper then the
row is part of the mapper's extent and the object is created for it.  Otherwise
the row is ignored.

<P>Note that this has no effect on identity.  If the distinguishing value is
required to make sure identityKeys are unique then the value (column) must be
included with the identityKeySlot definition.
**/

/*package*/ interface DistinguishingObjectMapperPi
                    extends DistinguishingObjectMapper, ObjectMapperPi
                    {

    /*package*/ public SlotPi distinguishingSlot();
    /*package*/ public Object distinguishingValue();


}
