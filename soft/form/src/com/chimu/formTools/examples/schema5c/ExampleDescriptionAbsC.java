/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/ExampleDescriptionAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.description.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import java.sql.*;
import java.io.*;

public abstract class ExampleDescriptionAbsC extends com.chimu.formTools.examples.ExampleAbsC {


    protected DatabaseProduct databaseProduct;
    protected DatabaseConnection dbConnection = null;
 
    protected Statement  stmt = null;


    //==========================================================
    //==========================================================
    //==========================================================

    protected void writeDescription(DatabaseDescription aDescription, String fileName) {
        File aFile = new File(fileName);
        openOutputFile(aFile);
        writeObject(aDescription);
        closeOutputFile();
    }
    
        /** 
        ==  Creates a database description, catalog description, 
        ==  scheme description, and table descriptions for the example.
        ==  and sets the names appropriately.
        **/
    public DatabaseDescription createDescription(DatabaseConnection dbConnection) {
        Catalog aCatalog = setupCatalog(catalogName);
        Scheme aScheme = setupScheme(aCatalog, schemeName);
       
        DatabaseDescription aDesc =
            DescriptionPack.newDatabaseDescriptionFrom(dbConnection);
        
        return aDesc;
    }

    //==========================================================
    //==========================================================
    //==========================================================

        /**
        ==  Figures out an appropriate catalog given the
        ==  catalog name in the input field.
        ==  Returns the default catalog of the connection, if no name is given
        **/
    protected Catalog setupCatalog(String aName) {
        Catalog aCatalog = null;
        if (catalogName != null) {
            aCatalog = dbConnection.catalog(catalogName);
        } else {
            aCatalog = dbConnection.defaultCatalog();
        }
        return aCatalog;
    }

        /**
        ==  Figures out an appropriate scheme given the scheme
        ==  and catalog name in the input fields.
        ==  Returns the default scheme if no name is given
        **/

    protected Scheme setupScheme(Catalog aCatalog, String schemeName) {
        if (schemeName != null) {
            aCatalog.defaultScheme().setName(schemeName);
        } 
        return aCatalog.defaultScheme();
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected DatabaseDescription readDescriptionFrom(File descFile) {
        DatabaseDescription dbDesc = null;
        if (descFile.exists()) {
            openInputFile(descFile);
            dbDesc = (DatabaseDescription) readObject();
            List catalogs = dbDesc.catalogDescriptions();
            for (int i = 0; i < catalogs.size(); i++) {
                CatalogDescription aCatalog = (CatalogDescription) catalogs.atIndex(i);
                List schemes = (List) aCatalog.schemeDescriptions();
                for (int j = 0; j < schemes.size(); j++) {
                    SchemeDescription sd = (SchemeDescription) schemes.atIndex(j);
                    List tables = (List) sd.tableDescriptions();
                    for (int k = 0; k < tables.size(); k++) {
                        TableDescription aTable = (TableDescription) tables.atIndex(k);
                    };
                }
            }
            closeInputFile();
        } else {
            outputStream.println("File named " + descFile.getName() + " does not exists");
        };

        return dbDesc;
    }


    //==========================================================
    //==========================================================
    //==========================================================

 


    protected void setupTypeStrings() {
        databaseProduct.setupTypeStrings();

    }

    protected void setupDatabaseConnection(Connection connection) {
        dbConnection = FormPack.newDatabaseConnection(connection);
        databaseProduct = dbConnection.databaseProduct();
        databaseProduct.setupTypeStrings();
    }


    protected void executeString_ignoreError(String sqlString) {
        try {
            Statement stmt = dbConnection.connection().createStatement();

            stmt.executeUpdate(sqlString);
        } catch (Exception e){
            tracePrintln(e.toString());
            if (traceLevel > 0) {
                e.printStackTrace(traceStream);          }
        };
    }

    protected void dropDatabase(Connection jdbcConnection) {

        dropScheme();
        outputStream.println("Dropped all existing tables.");
    }



    protected void dropScheme() {
        executeString_ignoreError("DROP TABLE GeneratorCounters");

        executeString_ignoreError("DROP TABLE Person");
        executeString_ignoreError("DROP TABLE Employee");
        executeString_ignoreError("DROP TABLE Company");

        executeString_ignoreError("DROP TABLE Project");
        executeString_ignoreError("DROP TABLE ProjectMembers");
        executeString_ignoreError("DROP TABLE TestTable");
    }


    //==========================================================
    //==========================================================
    //==========================================================

    FileInputStream   in;
    ObjectInputStream ois;

    FileOutputStream    out;
    ObjectOutputStream  oos ;


    void openInputFile(File file) {
        try {
            in = new FileInputStream(file);
            ois = new ObjectInputStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    Object readObject() {
        try {
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }

    void closeInputFile() {
        try {
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //======================================
    //======================================
    //======================================

    void openOutputFile(File file) {
        try {
            out = new FileOutputStream(file);
            oos = new ObjectOutputStream(out);
        } catch (Exception e) {
            e.printStackTrace();
        };

    }

     void writeObject(Object anObject) {
        try {
            oos.writeObject(anObject);
        } catch (Exception e) {
            e.printStackTrace();
        };

    }

    void closeOutputFile() {
        try {
            oos.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

}