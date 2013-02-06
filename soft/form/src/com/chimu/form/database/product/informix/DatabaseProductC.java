/*======================================================================
**
**  File: chimu/form/database/product/informix/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.informix;

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

    public String shortName()           {return "Informix";}
    public String name()                {return "Informix";}
    public int    code()                {return DatabaseProductConstants.DB_INFORMIX;}
    public String demoLoginName()       {return null;   }
    public String demoLoginPassword()   {return null;   }
    public boolean matchesDescription(String description) {
        return description.startsWith("informix");
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
        typeTime = "DATETIME HOUR TO SECOND";
        typeTimestamp = "DATETIME YEAR TO SECOND";
        typeBinary = "BYTE";
        typeDouble = "DOUBLE PRECISION";
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}