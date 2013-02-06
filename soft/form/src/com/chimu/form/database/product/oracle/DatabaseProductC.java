/*======================================================================
**
**  File: chimu/form/database/product/oracle/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.oracle;

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
    public String shortName()           {return "Oracle";}
    public String name()                {return "Oracle 7";}
    public int    code()                {return DatabaseProductConstants.DB_ORACLE;}
    public String demoLoginName()       {return "scott";   }
    public String demoLoginPassword()   {return "tiger";   }
    public boolean matchesDescription(String description) {
        return description.startsWith("oracle");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }

    public void setupTypeStrings() {
        super.setupTypeStrings();
        typeTime = "DATE";      //Oracle has only one datatype 'date' for both date and time datatypes
        typeTimestamp = "DATE";
        typeDecimal = "NUMBER";
        typeMoney = "NUMBER";
        typeDouble = "NUMBER";
        typeFloat = "NUMBER";
        typeBoolean  = "CHAR(1)";
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}