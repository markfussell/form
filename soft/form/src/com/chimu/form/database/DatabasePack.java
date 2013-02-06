/*======================================================================
**
**  File: chimu/form/database/DatabasePack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.Connection;
import com.chimu.kernel.collections.*;
import com.chimu.form.*;


/**
DatabasePack contains the FORM database interface.  This is a level above
JDBC which understands: associations, virtual columns, database product
differences, and driver differences.

<P>FORM clients will only need to work with DatabaseConnections, Tables,
and possible Compound Columns (virtual columns).  Generally client
only need to create or retrieve the object: all the specific interaction is
private to FORM. To create a DatabaseConnection you interact with FormPack
and to get Tables and Columns you interact with DatabaseConnection and Table
respectively.

@see com.chimu.form.Orm
@see DatabaseConnection
@see Table
@see CompoundColumns
**/


public class DatabasePack {


    //**********************************************************
    //********************** Exceptions ************************
    //**********************************************************

    static final String EXCEPTION_GROUP      = "form.database";
    static final String EXCEPTION_RECOGNIZER = "form.database";



    static public FormAnomaly jdbcAnomaly() {return jdbcAnomaly;}


    static protected FormAnomaly jdbcAnomaly = null;

    static {
        FormAnomalyC anomaly = null;

        anomaly = new FormAnomalyC();
            anomaly.initGroup_code(EXCEPTION_GROUP,1);
            jdbcAnomaly = anomaly;
    }



    //**************************************
    //**************************************
    //**************************************

    private DatabasePack() {};

};
