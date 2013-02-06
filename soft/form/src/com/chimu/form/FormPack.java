/*======================================================================
**
**  File: chimu/form/FormPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

import java.sql.Connection;

import com.chimu.form.database.*;

/**
FORM is a Framework for Object-Relational Mapping. The two root
classes two FORM are the DatabaseConnection, which describes and
connects to a particular database, and the Orm, which describes
how to map your domain objects to that database.

This class has no real functionality other than to demonstrate Island Pacific
conventions.

<H4>History</H4>
<PRE>
Phil Friesen 05/15/97 10:00. Development.
Phil Friesen 07/30/97 22:45. Updated spacing convention to cater to Visual Age.
</PRE>
<H4>History2</H4>
<DL>
<DT>Phil Friesen 05/15/97 10:00.<DD>Development.
<DT>Phil Friesen 07/30/97 22:45.<DD>Updated spacing convention to cater to Visual Age.
</DL>

@author Mark L. Fussell
@version 1.1

@see Orm
@see com.chimu.form.database.DatabaseConnection
**/
public class FormPack {

    static public final String formVersion = "1_0_5";

    //==========================================================
    //(P)================= Factory Methods ====================
    //==========================================================

        /**
         * Create a new root object mapper
         */
    static public Orm newOrm() {
        return new OrmC();
    }

    //==========================================================
    //(P)============== Databases Factory Methods ==============
    //==========================================================

        /**
         * Create a databaseConnection and determine the database type
         * by querying the database itself.
         */
    static public DatabaseConnection newDatabaseConnection(Connection jdbcConnection) {
        return DatabasePackSi.newDatabaseConnection(jdbcConnection);
    }

        /**
         * Create a databaseConnection and but delay completing the setup
         * until called explicitly later.
         */
    static public DatabaseConnection newDatabaseConnection_delayDoneSetup(Connection jdbcConnection) {
        return DatabasePackSi.newDatabaseConnection_delayDoneSetup(jdbcConnection);
    }


    //==========================================================
    //========================= MAIN ===========================
    //==========================================================

        /**
         * FORM Version, Copyright and Warranties.
         */
    static public void main(String[] args) {
        System.out.println("************************************************************************");
        System.out.println("**");
        System.out.println("**  ChiMu FORM (Framework for Object-Relational Mapping)");
        System.out.println("**  Version "+formVersion);
        System.out.println("**  Copyright (c) 1997, ChiMu Corporation, All rights reserved.");
        System.out.println("**  http://www.chimu.com/");
        System.out.println("**");
        System.out.println("**  This software is the confidential and proprietary information of");
        System.out.println("**  ChiMu Corporation (\"Confidential Information\").  You shall not");
        System.out.println("**  disclose such Confidential Information and shall use it only in");
        System.out.println("**  accordance with the terms of the license agreement you entered into");
        System.out.println("**  with ChiMu Corporation.");
        System.out.println("**");
        System.out.println("************************************************************************");
    }

    //==========================================================
    //==========================================================
    //==========================================================

    private FormPack() {}

};
