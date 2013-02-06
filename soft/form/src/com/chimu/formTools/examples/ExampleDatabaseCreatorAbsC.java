/*======================================================================
**
**  File: chimu/formTools/examples/ExampleDatabaseCreatorAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.form.database.product.*;
import com.chimu.form.description.*;
import com.chimu.form.database.DatabaseConnection;
import com.chimu.form.database.typeConstants.*;

import com.chimu.form.description.*;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.URL;


/**
ExampleDatabaseCreatorAbsC provides functionality for creating
(creating the scheme and then populating) databases.
**/
public abstract class ExampleDatabaseCreatorAbsC extends DatabaseSetupAbsC {

        /**
         * Standard implementation of run creates a scheme then populates it
         */
    public void run(Connection jdbcConnection) {
        setupDatabaseConnection(jdbcConnection);
        setupDatabaseProductType();
        setupTypeStrings();

        if (databaseProduct.canSupportBoolean()) {
            makeBoolean = charToBoolean;
        } else {
            makeBoolean = identity;
        }

        if (disableEscapes) {
            dateify = standardQuote;
        };

        indexCounter = 1;
        createScheme();
        populateScheme();
        outputStream.println("Database Created");
    }

    public void setupDisableEscapes() {
        disableEscapes = true;
    }

    public void setupGenerateIndexes() {
        shouldGenerateIndexes = true;
    }

    protected abstract void createScheme();
    protected abstract void populateScheme();
    
    protected Function1Arg makeBoolean = charToBoolean;
    protected int indexCounter = 0;
    protected boolean disableEscapes = false;
    protected boolean shouldGenerateIndexes = false;

    //==========================================================
    //============== Table Creation Supports ===================
    //==========================================================

    protected void createEntityTable_FileString_primaryKey(String fileString, String tableName, String primaryKey) {
        TableDescription aDesc = newTableDescriptionFromString_name_primaryKey(fileString, tableName, primaryKey, databaseProduct);
        createTable_fromDescription(aDesc);
        
        if (shouldGenerateIndexes) {
            createIndex(tableName, primaryKey);
        }
    }

    protected void createNonEntityTable_FileString(String fileString, String tableName) {
        TableDescription aDesc = newTableDescriptionFromString_name(fileString, tableName, databaseProduct);
        createTable_fromDescription(aDesc);
    }

    protected void createTable_fromDescription(TableDescription aTable) {
        String createTableString = aTable.createTableString(databaseProduct);
        outputStream.println(createTableString);        
        outputStream.println();
        executeString(createTableString);
    }

    protected void createIndex(String tableName, String columnString) {
        executeString("CREATE UNIQUE INDEX "+"FormIn"+(indexCounter++)+" ON "+tableName+" ("+columnString+") ");
    }

    //==========================================================
    //================ Population supports =====================
    //==========================================================

    protected Function1Arg identity = standardIdentity;
    protected Function1Arg quote    = standardQuote;
    protected Function1Arg dateify  = standardDateify;

    static protected Function1Arg standardIdentity = new Function1Arg() {public Object valueWith(Object arg1){
            return arg1;
        }};

    static protected Function1Arg standardQuote  = new Function1Arg() {public Object valueWith(Object arg1){
            return "'"+arg1+"'";
        }};

    static protected Function1Arg standardDateify = new Function1Arg() {public Object valueWith(Object arg1){
            return "{d '"+arg1+"'}";
        }};

    //======================================
    //======================================
    //======================================
    
    static protected Function1Arg timefy = new Function1Arg() {public Object valueWith(Object arg1){
            return "{t '"+arg1+"'}";
        }};

    static protected Function1Arg timestampify = new Function1Arg() {public Object valueWith(Object arg1){
            return "{ts '"+arg1+"'}";
        }};


    static protected Function1Arg chopMoney = new Function1Arg() {public Object valueWith(Object arg1){
            String string1 = (String) arg1;
            if (string1.charAt(0) == '$') {
                return string1.substring(1);  //Chop off the first character
            } else {
                return string1;
            }
        }};

    static protected Function1Arg charToBoolean = new Function1Arg() {public Object valueWith(Object arg1){
            return arg1.equals("'T'") ? "1" : "0" ;
        }};


    protected void readAndInsertRowsFrom_into_translators(String fileName, String tableName, Function1Arg[] translators) {
        if (isInUrlMode()) {
            URL url = ExampleDatabaseCreatorAbsC.class.getResource(urlPathPrefix()+fileName);
            if (url == null) throw new RuntimeException("Could not open URL on: "+fileName+" from "+ExampleDatabaseCreatorAbsC.class);
            readAndInsertRowsFromUrl_into_translators(url,tableName,translators);
        } else {
            File aFile = new File (filePathPrefix(), fileName);
            if (!aFile.exists()) {
                URL url = ExampleDatabaseCreatorAbsC.class.getResource(urlPathPrefix()+fileName);
                if (url == null) throw new RuntimeException(
                                    "File: "+aFile+
                                    " does not exist and Could not open URL on: "+fileName+
                                    " from "+ExampleDatabaseCreatorAbsC.class);
                readAndInsertRowsFromUrl_into_translators(url,tableName,translators);
            } else {
                readAndInsertRowsFromFile_into_translators(aFile,tableName,translators);
            }
        };
    }

    protected void readAndInsertRowsFromFile_into_translators(File file, String tableName, Function1Arg[] translators) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            List data = readRowsFromReader(in);
            in.close();

            tracePrintln("Inserting "+data.size()+" rows into "+tableName);
            insertRows(tableName,data,translators);
        } catch (Exception e) {
            throw new RuntimeWrappedException("Could not populate table: "+tableName+" from "+file,e);
        }
    }

    protected void readAndInsertRowsFromUrl_into_translators(URL url, String tableName, Function1Arg[] translators) {
        try {
            InputStream stream = url.openStream();
            if (stream == null) throw new RuntimeException("Could not open URL stream: "+url);

            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            List data = readRowsFromReader(in);
            in.close();

            tracePrintln("Inserting "+data.size()+" rows into "+tableName);
            insertRows(tableName,data,translators);
        } catch (Exception e) {
            throw new RuntimeWrappedException("Could not populate table: "+tableName+" from "+url,e);
        }
    }

        //instead of a file, put in a Reader
        //InputStreamReader(InputStream(url))...

    protected void insertRows(String tableName, List rows, Function1Arg[] translators) {
        int numRows = rows.size();
        for (int i=0; i<numRows; i++) {
            StringBuffer stringB = new StringBuffer();
            stringB.append("INSERT INTO "+tableName+" VALUES (");
            List columnValues = (List) rows.atIndex(i);
            int numColumns = translators.length;

            //if (numColumns > columnValues.size()) {
            //    numColumns = columnValues.size();
            //}
            //int numDataColumns = columnValues.size();

            for (int j=0; j<numColumns; j++) {
                if (translators[j] != null) {  //enabled
                    Object columnValue = translators[j].valueWith(columnValues.atIndex(j));
                    if (j != 0) {
                        stringB.append(",");
                    };
                    stringB.append(columnValue);
                }

            };
            stringB.append(")");
            executeString(stringB.toString());

        }
    }

}