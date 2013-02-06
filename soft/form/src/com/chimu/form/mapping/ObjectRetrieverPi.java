/*======================================================================
**
**  File: chimu/form/mapping/ObjectRetrieverPi.java
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

/*package*/ interface ObjectRetrieverPi extends ObjectRetrieverSi {

    /*package*/ public MappedObject newStubObject(Object identityKey);
    /*package*/ public MappedObject newObjectFromRow(Row row);

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         *@access Friend(AssociationMapper, AssociationSlot)
         */
    /*package public Array selectWhereQString_hasColumnValues(String qstring, Array columnValues)
            throws SQLException;
    /*package public Object findWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException;
    /*package public Array selectWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException;

    /*subsystem(Query) public String querySelectStringPrefixedWith(String queryVarName);*/

}
