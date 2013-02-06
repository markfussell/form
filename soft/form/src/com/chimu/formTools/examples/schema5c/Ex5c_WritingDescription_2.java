/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/Ex5c_WritingDescription_2.java
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
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.description.*;

import com.chimu.formTools.test.*;
/**
**/
public class Ex5c_WritingDescription_2 extends ExampleAbsC {


    public void run (Connection jdbcConnection) {
        
        outputStream.println("Creating a database table using a description created in this example");
        outputStream.println();

        setupDatabaseConnection(jdbcConnection);        

        DatabaseDescription aDb = createDescription(dbConnection);
        
        SchemeDescription sd = (aDb.defaultCatalogDescription()).defaultSchemeDescription();
        TableDescription aTable = DescriptionPack.newTableDescriptionFrom("TestTable", sd, "id");

        ColumnDescription colA  = DescriptionPack.newColumnDescription("id", "char", false);
        ColumnDescription colB  = DescriptionPack.newColumnDescription("name", "varchar", true);
        ColumnDescription colC  = DescriptionPack.newColumnDescription("identification", "varchar", false);
        ColumnDescription colD  = DescriptionPack.newColumnDescription("description", "varchar", false);
        ColumnDescription colE  = DescriptionPack.newColumnDescription("ratio", "double", true);
        colD.setMaximumLength(230);
        colE.setScale(9);
        colE.setPrecision(3);

        aTable.addColumn(colA);
        aTable.addColumn(colB);
        aTable.addColumn(colC);
        aTable.addColumn(colD);
        aTable.addColumn(colE);
        aTable.setSchemeDescription(sd);
        
        sd.addTableDescription(aTable);
        outputStream.println("Description created");
        outputStream.println();
        
        dropDatabase(jdbcConnection);

        String fileName = "database2.ser";
        writeDescription(aDb, fileName);
        outputStream.println("Writing description to file " + fileName);
        outputStream.println();

        aDb.buildDbFromDescription(dbConnection);
        outputStream.println("Create database table from description ");
        
        outputStream.println(aTable.createTableString(dbConnection.databaseProduct()));        
        outputStream.println("TestTable created.");

    }


}