/*======================================================================
**
**  File: chimu/form/mapping/SetterSlotPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

/*package*/ interface SetterSlotPi extends SetterSlot, SlotPi {
    public void setObject_usingRow (Object object, Row row);
    public void setRow_usingObject (Row row, Object object);

    public void   setObject_usingSlotValue (Object object, Object value);
    public Object newSlotValueFromObject (Object object);

    public void   setObject_usingColumnValue (Object object, Object value);
    public Object newColumnValueFromObject (Object object);
};