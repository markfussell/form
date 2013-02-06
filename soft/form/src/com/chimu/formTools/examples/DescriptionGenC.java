/*======================================================================
**
**  File: chimu/formTools/examples/DescriptionGenC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.kernel.meta.TypeConstants;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.streams.*;
import com.chimu.kernel.exceptions.*;

import com.chimu.form.database.DatabaseConnection;
import com.chimu.form.database.typeConstants.*;
import com.chimu.form.description.*;
import com.chimu.form.database.product.*;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public abstract class DescriptionGenC extends com.chimu.formTools.test.FormDatabaseTestAbsC  {

    public TableDescription newTableDescriptionFromString_name(String fileName, String tableName, DatabaseProduct dbProduct) {
        TableDescription aDesc = DescriptionPack.newTableDescription(tableName);    
        
        return setColumnDescriptionFor_fromString(aDesc, fileName, dbProduct);
    }    
    
    public TableDescription newTableDescriptionFromString_name_primaryKey(String fileName, String tableName, String primaryKey, DatabaseProduct dbProduct){
        TableDescription aDesc = DescriptionPack.newTableDescription(tableName, primaryKey);
        return setColumnDescriptionFor_fromString(aDesc, fileName, dbProduct);
    }
    
    public TableDescription setColumnDescriptionFor_fromString(TableDescription aDesc, String fileName, DatabaseProduct dbProduct) {
        if (isInUrlMode()) {
            URL url = ExampleDatabaseCreatorAbsC.class.getResource(urlPathPrefix()+fileName);
            if (url == null) throw new RuntimeException("Could not open URL on: "+fileName+" from "+ExampleDatabaseCreatorAbsC.class);
            return setColumnDescriptionFor_Url(aDesc,  url, dbProduct);
        } else {
            File aFile = new File (filePathPrefix(), fileName);
            if (!aFile.exists()) {
                URL url = ExampleDatabaseCreatorAbsC.class.getResource(urlPathPrefix()+fileName);
                if (url == null) throw new RuntimeException(
                                    "File: "+aFile+
                                    " does not exist and Could not open URL on: "+fileName+
                                    " from "+ExampleDatabaseCreatorAbsC.class);
                 return setColumnDescriptionFor_Url(aDesc,  url, dbProduct);

            } else {
                 return setColumnDescriptionFor_File(aDesc, aFile, dbProduct);
            }
        };
       
    }

    protected TableDescription setColumnDescriptionFor_File(TableDescription aDesc, File aFile, DatabaseProduct dbProduct) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(aFile));
            List data = readSchemeRowsFromReader(in);
            in.close();
            setColumnDescriptionFor_fromList(aDesc, data, dbProduct);

        } catch (Exception e) {
            System.err.println("Could not generate table: from " + aFile);
        };
        return aDesc;


    }

    protected TableDescription setColumnDescriptionFor_Url(TableDescription aDesc, URL url, DatabaseProduct dbProduct) {
        try {
            InputStream stream = url.openStream();
            if (stream == null) throw new RuntimeException("Could not open URL stream: "+url);

            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            List data = readSchemeRowsFromReader(in);
            in.close();
            
            setColumnDescriptionFor_fromList(aDesc, data, dbProduct);

        } catch (Exception e) {
            throw new RuntimeWrappedException("Could not generate table: from "+url, e);
        };

        return aDesc;

    }


    //==========================================================
    //==========================================================
    //==========================================================

    //This generates a table description based on a file
    public TableDescription setColumnDescriptionFor_fromList(TableDescription tableDesc, List colData, DatabaseProduct dbProduct) {
        for (int i = 0; i < colData.size(); i++) {
            List aRow = (List) colData.atIndex(i);
            ColumnDescription aCol = genColumnDescription(aRow);

            tableDesc.addColumn(aCol);            
        }

        return tableDesc;
    }


    protected ColumnDescription genColumnDescription(List data) {
        
        String  name              = (String) data.atIndex(0);
        String  sqlDataTypeString = ((String) data.atIndex(1)).toUpperCase();
        boolean nullable          = makeBoolean((String) data.atIndex(2));
        ///String  parameterString   = (String) data.atIndex(3);

        ColumnDescription aCol = DescriptionPack.newColumnDescription(name, sqlDataTypeString, nullable);
        //SqlDataType aDataType = aCol.sqlDataType();
        
        SqlDataType aDataType = SqlDataTypePack.typeFromKey(sqlDataTypeString);
        
        if (aDataType.parameterRequired()) {
            if (data.size() > 3) {
                String parameterString = (String) data.atIndex(3);
                int number = Integer.parseInt(parameterString);
                aCol.setMaximumLength(number);
            } else {
                aCol.setMaximumLength(aDataType.defaultLength());
            };

        };
        return aCol;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected boolean makeBoolean(String aString) {
        if (aString == null) return false;
        return aString.equals("T")  ? true : false ;
        
    }
    
    protected boolean isInUrlMode() {
        return getClass().getName().startsWith("com.chimu");
    }

    protected String urlPathPrefix() {
        return "create/data/";
    }

    protected String filePathPrefix() {
        return "create/data";
    }
    
    
    //==========================================================
    //==========================================================
    //==========================================================

    protected List readSchemeRowsFromReader(BufferedReader in) {
        List rows = CollectionsPack.newList();
        try {
            String inString = in.readLine();
            while (inString != null) {
                StringTokenizer tokenizer = new StringTokenizer(inString,"\t", true);
                List columnValues = CollectionsPack.newList();
                boolean haveExtraTab = false;
                while (tokenizer.hasMoreTokens()) {
                    haveExtraTab = true;
                    String aToken = tokenizer.nextToken();
                    if (!aToken.equals("\t")){
                        columnValues.add(aToken);
                        if (tokenizer.hasMoreTokens()) {  //Fetch the delimiting tab
                            aToken = tokenizer.nextToken();
                            if (aToken.equals("\t")) {
                                 // GOOD...
                            } else {
                                 throw new Exception("foo");
                            }
                        } else {
                            haveExtraTab = false;
                        }
                    } else {
                       columnValues.add(null);
                       // Already fetched the tab
                    }

                };

                if (haveExtraTab) {
                    columnValues.add(null);
                }

                if (columnValues.size() > 0) {  //ignore blank lines
                    rows.add(columnValues);
                }
                inString = in.readLine();
            }
        } catch(Exception e) {
            System.err.println("Could not read from reader: "+in+" because");
            e.printStackTrace();
        }
        return rows;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected List readRowsFromReader(BufferedReader in) {
        List rows = CollectionsPack.newList();
        try {
            String inString = in.readLine();
            while (inString != null) {
                StringTokenizer tokenizer = new StringTokenizer(inString,"\t", true);
                List columnValues = CollectionsPack.newList();
                boolean haveExtraTab = false;
                while (tokenizer.hasMoreTokens()) {
                    haveExtraTab = true;
                    String aToken = tokenizer.nextToken();
                    if (!aToken.equals("\t")){
                        columnValues.add(aToken);
                        if (tokenizer.hasMoreTokens()) {  //Fetch the delimiting tab
                            aToken = tokenizer.nextToken();
                            if (aToken.equals("\t")) {
                                 // GOOD...
                            } else {
                                 throw new Exception("foo");
                            }
                        } else {
                            haveExtraTab = false;
                        }
                    } else {
                       columnValues.add("NULL");
                       // Already fetched the tab
                    }

                };

                if (haveExtraTab) {
                    columnValues.add("NULL");
                }

                if (columnValues.size() > 0) {  //ignore blank lines
                    rows.add(columnValues);
                }
                inString = in.readLine();
            }
        } catch(Exception e) {
            System.err.println("Could not read from reader: "+in+" because");
            e.printStackTrace();
        }
        return rows;
    }

}
