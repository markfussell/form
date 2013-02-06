/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/Ex5c_WritingDescription.java
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
import com.chimu.form.database.typeConstants.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.description.*;


import com.chimu.formTools.test.*;
/**
**/
public class Ex5c_WritingDescription extends ExampleAbsC {


    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        
        //trying to create a description of the tables in the given catalog and scheme
        
        DatabaseDescription aDesc = createDescription(dbConnection);
//        outputStream.println(aDesc.catalogDescriptions());
        
        CatalogDescription cd = aDesc.defaultCatalogDescription();
        SchemeDescription sd = cd.defaultSchemeDescription();
        
/*
        outputStream.println("Catalog name " + cd.qualifiedName());
        outputStream.println("Scheme name " + sd.qualifiedName());        
*/
        List tables = (List) sd.tableDescriptions();

        outputStream.println("Number of table descriptions to be created: " + tables.size());
        outputStream.println();
        
        for (int i = 0; i < tables.size(); i++) {
            TableDescription td = (TableDescription) tables.atIndex(i);
            outputStream.println("table description name: " + td.name());
            outputStream.println();    
        }
        
        String fileName = "database1.ser";
        writeDescription(aDesc, fileName);
        outputStream.println("Wrote description to " + fileName);
    }


}