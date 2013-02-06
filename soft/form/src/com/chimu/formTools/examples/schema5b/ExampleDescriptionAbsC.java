/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/ExampleDescriptionAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.description.*;
import com.chimu.form.database.product.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import java.sql.Connection;
import java.io.*;

public abstract class ExampleDescriptionAbsC extends FormDatabaseTestAbsC {

    protected String filename = "database.ser";
    protected File   descFile = new File(filename);
    
    protected DatabaseProduct databaseProduct;
    protected DatabaseConnection dbConnection = null;
 

    //==========================================================
    //==========================================================
    //==========================================================

    protected void buildConnection_fromDescription(DatabaseConnection dbConnection) {
        if (descFile.exists()) {
            openInputFile(descFile);
            DatabaseDescription dbDesc = (DatabaseDescription) readObject();
            dbDesc.buildDatabaseConnection(dbConnection);

            closeInputFile();
        };
    }

    protected void writeDescription(DatabaseConnection dbConnection) {
        if (!(descFile.exists())) {
            Catalog aCatalog = setupCatalog(catalogName);
            Scheme aScheme = setupScheme(aCatalog, schemeName);
            
            DatabaseDescription dbDesc =
                DescriptionPack.newDatabaseDescriptionFrom(dbConnection);
            openOutputFile(descFile);
            writeObject(dbDesc);
            closeOutputFile();
        };
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