/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/Ex5c_ReadingDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;

import java.sql.*;
import java.util.Enumeration;
import java.io.*;

import com.chimu.kernel.collections.*;


import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;

import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.description.*;

import com.chimu.formTools.test.*;
/**
**/
public class Ex5c_ReadingDescription extends ExampleAbsC {

    public void run (Connection jdbcConnection) {
        setupDatabaseConnection(jdbcConnection);


        File aFile = new File("database1.ser");
        DatabaseDescription dbDesc = readDescriptionFrom(aFile);
        if (dbDesc != null) {
            dropDatabase(jdbcConnection);
            dbDesc.buildDbFromDescription(dbConnection);
            outputStream.println("Database created");
            createAndConfigureOrm(jdbcConnection);
            outputStream.println("New tables created in database and orm configured.");
        }

    }


}