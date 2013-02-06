/*======================================================================
**
**  File: chimu/form/mapping/ObjectRetrieverSi.java
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

/*subsystem*/ public interface ObjectRetrieverSi extends ObjectRetriever {

    /*package*/ public Array selectWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue);
    /*package*/ public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) ;

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem(Query)*/ public Column identityKeyColumn();

        /**
         * tableName is accessed by the Query system
         */
    /*subsystem*/ public String tableName();
    /*subsystem*/ public String identityKeyColumnName();

    /*subsystem(Query)*/ public Array selectColumns();

}
