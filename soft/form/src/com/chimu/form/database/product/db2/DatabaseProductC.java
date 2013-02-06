/*======================================================================
**
**  File: chimu/form/database/product/db2/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.db2;

import com.chimu.form.database.DatabaseConnection;

import com.chimu.form.database.Table;
import com.chimu.form.database.product.DatabaseProductAbsC;
import com.chimu.form.database.product.DatabaseProductConstants;

public class DatabaseProductC extends     DatabaseProductAbsC
                           implements  DatabaseProductConstants {

    public DatabaseProductC() {
    }

    //**********************************************************
    //************************ Asking **************************
    //**********************************************************
    public String shortName()           {return "DB2";}
    public String name()                {return "DB2";}
    public int    code()                {return DatabaseProductConstants.DB_DB2;}
    public String demoLoginName()       {return "";   }
    public String demoLoginPassword()   {return "";   }
    public boolean matchesDescription(String description) {
        return (description.startsWith("ibm") || description.startsWith("db2")) ;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }

    public void setupTypeStrings() {
        super.setupTypeStrings();
        typeBoolean  = "CHAR(1)";
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}