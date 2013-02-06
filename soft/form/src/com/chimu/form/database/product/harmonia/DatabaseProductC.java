/*======================================================================
**
**  File: chimu/form/database/product/harmonia/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.harmonia;

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
    public String shortName()           {return "Harmonia";}
    public String name()                {return "Harmonia";}
    public int    code()                {return DatabaseProductConstants.DB_HARMONIA;}
    public String demoLoginName()       {return "";   }
    public String demoLoginPassword()   {return "";   }
    public boolean matchesDescription(String description) {
        return description.startsWith("harmonia");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }

    public boolean canSupportBoolean() {
        return true;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}