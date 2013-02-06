/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/Ex5c_WritingDescription_3.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;

import java.sql.Connection;
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
import com.chimu.formTools.examples.DescriptionGenC;
/**
**/
public class Ex5c_WritingDescription_3 extends ExampleDescriptionAbsC {

        //Creates database description from a file and save the serialized version to
        //a file named database3.ser

    public void run (Connection jdbcConnection) {

        outputStream.println("Read and write a database description to a file ");
        setupDatabaseConnection(jdbcConnection);

        DatabaseDescription aDb = createDescription(dbConnection);

        SchemeDescription sd = (aDb.defaultCatalogDescription()).defaultSchemeDescription();

        TableDescription td =
            newTableDescriptionFromString_name_primaryKey(
                            "testTable.txt", "TestTable", "id", dbConnection.databaseProduct());

        td.setSchemeDescription(sd);
        sd.addTableDescription(td);

        String fileName = "database3.ser";
        writeDescription(aDb, fileName);
        outputStream.println("Description serialized to file named: " + fileName);
        outputStream.println();

        dropScheme();
        aDb.buildDbFromDescription(dbConnection);

        outputStream.println(td.createTableString(dbConnection.databaseProduct()));
        outputStream.println("TestTable created.");

    }


}