/*======================================================================
**
**  File: chimu/form/database/product/access/SqlBuilderC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.access;

import com.chimu.form.mapping.*;
import com.chimu.form.database.Column;
import com.chimu.form.database.ColumnSi;
import com.chimu.form.database.Table;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.sql.*;

import java.math.*;

import java.util.Enumeration;
import java.util.Date;

/*package*/ class SqlBuilderC extends com.chimu.form.query.SqlBuilderC {
    /*package*/ SqlBuilderC() {super();};

        /**
         * Access is incapable of executing the following correctly with
         * DISTINCTS on count columns
         */
    public String sqlString() {
        StringBuffer stringB = new StringBuffer();
        if (isCount) {
            stringB.append("SELECT ");
            stringB.append("COUNT(*) ");
        } else {
            if (sqlSelectStringB.length() >0) {
                stringB.append("SELECT ");
                if (isDistinct) stringB.append("DISTINCT ");
                stringB.append(sqlSelectStringB.toString());
                stringB.append(" ");
            };
        }

        stringB.append("FROM ");
        stringB.append(sqlExtentStringB.toString());
        if (sqlQueryStringB.length() >0) {
            stringB.append("WHERE ");
            stringB.append(sqlQueryStringB.toString());
        };
        return stringB.toString();
    }

}