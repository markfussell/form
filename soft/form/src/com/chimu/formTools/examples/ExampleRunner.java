/*======================================================================
**
**  File: chimu/formTools/examples/ExampleRunner.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.PrintStream;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.database.*;
import com.chimu.form.database.driver.*;
import com.chimu.form.database.product.*;
import java.io.*;

public class ExampleRunner {

	public static void main(String args[]) {
	    String defaultsFileName = null;

	    if (args.length > 0) {
	        defaultsFileName=args[0];
	    } else {
	        defaultsFileName="defaults.ser";
	    }

	    ExampleRunner anApp = new ExampleRunner();
	    anApp.init(defaultsFileName);
	}

    //======================================
    //======================================
    //======================================

    public ExampleRunner() {}

    protected void init(String defaultsFileName) {
        this.defaultsFileName = defaultsFileName;
        readDefaultsFrom(defaultsFileName);

	    //debug2();

	    myWindow = new ExampleRunnerWindow(this);
        myWindow.setVisible(true);

    }

    public boolean acceptCloseRequest() {
        writeDefaultsTo(defaultsFileName);
        return true;
    }


    //==========================================================
    //================ Defaults Reading/Writing ================
    //==========================================================

    protected void readDefaultsFrom(String filename) {
        boolean success = false;
            String defaultRootDirectory = null;
            String defaultDbUrl         = null;
            String defaultLoginName     = null;
            String defaultDriverC   = null;

        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(in);

            defaultRootDirectory = (String) ois.readObject();
            defaultDbUrl         = (String) ois.readObject();
            defaultLoginName     = (String) ois.readObject();
            defaultDriverC   = (String) ois.readObject();

            ois.close();
            success=true;
        } catch (Exception e) {
            System.err.println("Could not read defaults from "+filename+" because "+e);
        };

        if (success) {
            setRootDirectory(defaultRootDirectory);
            dbUrl = defaultDbUrl;
            loginName = defaultLoginName;
            customDbDriverCName = defaultDriverC;
        } else {
            setRootDirectory("");
            dbUrl = "FormExampleDatabase";
            loginName = "chimu";
            customDbDriverCName = "";
        };
    }

    protected void writeDefaultsTo(String filename) {
        try {
            FileOutputStream   out  = new FileOutputStream(filename);
            ObjectOutputStream oos  = new ObjectOutputStream(out);

            oos.writeObject(rootDirectoryName);
            oos.writeObject(dbUrl);
            oos.writeObject(loginName);
            oos.writeObject(customDbDriverCName);

            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.err.println("Could not write defaults to "+filename+" because "+e);
        };
    }

    //==========================================================
    //==========================================================
    //==========================================================

    	/**
    	 *  Returns a list of database names from DatabaseProductPack
    	 */
	protected List dbProductNames() {
	    List dbProductNames = CollectionsPack.newList();
	    for (int i = 0; i < dbProducts.size(); i++) {
	        DatabaseProduct aProduct = (DatabaseProduct) dbProducts.atIndex(i);
            dbProductNames.add(aProduct.shortName());
        }
	    return dbProductNames;
	}

	protected void updateDriverDbProducts() {
        dbProducts = CollectionsPack.newList();
        List possibleDbProducts = DatabaseProductPack.products();
	    for (int i = 0; i < possibleDbProducts.size(); i++) {
	        DatabaseProduct aProduct = (DatabaseProduct) possibleDbProducts.atIndex(i);
	        if (dbDriver.canWorkWithDatabase(aProduct)) {
                dbProducts.add(aProduct);
            }
        }
	}

	protected List dbDriverNames() {
	    List databaseDrivers = DatabaseDriverPack.availableDrivers();
	    List dbDriverNames = CollectionsPack.newList();
	    for (int i = 0; i < dbDrivers.size(); i++) {
	        DatabaseDriver aDriver = (DatabaseDriver) databaseDrivers.atIndex(i);
            dbDriverNames.add(aDriver.name());
        }
        dbDriverNames.add("CUSTOM");
	    return dbDriverNames;
	}


	protected List getSchemeList() {
	    if (schemes != null) return schemes;
	    if (exampleSchemeCollection == null) return CollectionsPack.newList();
	    List sequence = (List) exampleSchemeCollection.schemes();
	    sequence.sort();
	    return sequence;
	}

	protected List getSchemeNameList() {
	    List aSeq = getSchemeList();
	    List retSeq = CollectionsPack.newList();
	    for (int i = 0; i < aSeq.size(); i++) {
	        TestScheme aScheme =(TestScheme) aSeq.atIndex(i);
	        retSeq.add(aScheme.toString());
	    }
	    //System.out.println("Scheme names = "+retSeq);
	    return retSeq;
	}

//d:/usersbackup/chimu/development/java/examples/ui/UIStuff/
	public List getQueryList() {
	    return currentTests;
	}

	public List getQueryNamesList() {
	    List aSeq = getQueryList();

	    List retSeq = CollectionsPack.newList();
	    for (int i = 0; i < aSeq.size(); i++) {
            Class testC = (Class) aSeq.atIndex(i);
            //try {
            //    DatabaseTest aTest = (DatabaseTest) testC.newInstance();
            //} catch (Exception e) {};
            if (testC != null) {
    	        retSeq.add(testC.getName());
    	    }
	    }
	    return retSeq;
	}

    //==========================================================
    //==========================================================
    //==========================================================

	public void setRootDirectory(String aRootDirStr) {
	    if (rootDirectoryName.equals(aRootDirStr)) return;
        updateRootDirectoryTo(aRootDirStr);

	    setCurrentScheme(0);

	    if (myWindow != null) myWindow.noteSchemeListChanged();
	}

    protected void setCurrentScheme(int anIndex) {
        TestScheme newScheme = null;
        if ((anIndex >= 0) && (anIndex < schemes.size())) {
            newScheme = (TestScheme) schemes.atIndex(anIndex);
        };
        if (newScheme == currentScheme) return;

        currentScheme = newScheme;

        buildCurrentTests();

	    if (myWindow != null) myWindow.noteQueryListChanged();
    }

    //======================================
    //======================================
    //======================================

    protected void updateRootDirectoryTo(String aRootDirStr) {
	    if (aRootDirStr.equals("")) {
	        rootDirectory = null;
	        exampleSchemeCollection = new FormExamples();
	    } else {
    	    rootDirectory = new File(aRootDirStr);
	        if (!rootDirectory.isDirectory()) {
                printlnResultText(rootDirectory+" is not a directory ");
	        }
	        exampleSchemeCollection = new FormExamples();
	        exampleSchemeCollection.setupRootDirectory(rootDirectory);
	    }
	    rootDirectoryName = aRootDirStr;

	    schemes = (List) exampleSchemeCollection.schemes();
	    schemes.sort();
    }

    protected void buildCurrentTests() {
        currentTests = CollectionsPack.newList();
        if (currentScheme != null) {
            List tests = (List) currentScheme.tests();
            if (tests != null) {
                currentTests = tests;
            }
        };
    }

    //==========================================================
    //==========================================================
    //==========================================================

	public void setLoginName(String aLoginName) {
	    loginName = aLoginName;
	    //if (myWindow != null) myWindow.noteLoginNameChanged();
	}

	public void setPassword(String aPassword) {
	    password = aPassword;
	    //if (myWindow != null) myWindow.notePasswordChanged();
	}

	public void setCatalog(String aString) {
	    catalog = aString;
	    //if (myWindow != null) myWindow.notePasswordChanged();
	}

	public void setScheme(String aString) {
	    scheme = aString;
	    //if (myWindow != null) myWindow.notePasswordChanged();
	}


	public void setDbUrl(String anUrl) {
	    dbUrl = anUrl;
	}

	public void setCustomDbDriverC(String aCName) {
	    customDbDriverCName = aCName;
	}

    protected void setDbDriverFromName(String dbDriverStr) {
        setDbDriver(
                DatabaseDriverPack.driverFromKey(dbDriverStr.toLowerCase())
            );
    }

    protected void setDbDriverFromIndex(int dbDriverIndex) {
        if (dbDriverIndex >= dbDrivers.size()) {
            setCustomDbDriver();
            return;
        }
        setDbDriver((DatabaseDriver) dbDrivers.atIndex(dbDriverIndex));
    }

    protected void setCustomDbDriver() {
        this.dbDriver = null;
	    if (myWindow != null) myWindow.noteDbDriverChanged();
    }

    protected void setDbDriver(DatabaseDriver dbDriver) {
        this.dbDriver = dbDriver;
        updateDriverDbProducts();
	    if (myWindow != null) myWindow.noteDbDriverChanged();
        setDbProduct((DatabaseProduct) dbProducts.atIndex(0));
    }

	protected void setDbProductFromName(String dbProductStr) {
	    setDbProduct(
	            DatabaseProductPack.productFromKey(dbProductStr.toLowerCase())
	        );
	}

	protected void setDbProduct(DatabaseProduct dbProduct) {
	    this.dbProduct = dbProduct;
	    if (myWindow != null) myWindow.noteDbProductChanged();
	}

    protected void setTracing(boolean tracing) {
        this.isTracing = tracing;
    }

        //traceLevel is 0-based (-1 is no tracing)
    protected void setTraceLevel(int traceLevel) {
        if (traceLevel >= 0) {
            isTracing = true;
            this.traceLevel = traceLevel;
        } else {
            isTracing = false;
            this.traceLevel = -1;
        }
    }

    //==========================================================
    //==========================================================
    //==========================================================

	protected Connection getConnection() {
        if ((loginName == null) &&  (password==null)) {
            loginName   = dbProduct.demoLoginName();
            password    = dbProduct.demoLoginPassword();
        };

        String driverC   = null;
        String connectionUrl = null;
        if (dbDriver != null) {
            if (!dbDriver.canWorkWithDatabase(dbProduct)) {
                printlnResultText(dbDriver+" can not work with "+dbProduct);
                return null;
            }

            driverC   = dbDriver.driverCNameForDatabase(dbProduct);
            if (dbUrl.startsWith("jdbc:")) {
                connectionUrl = dbUrl;
            } else {
                connectionUrl = dbDriver.jdbcUrlForProduct_database(dbProduct,dbUrl);
            }
        } else {
            driverC = customDbDriverCName;
            if (dbUrl.startsWith("jdbc:")) {
                connectionUrl = dbUrl;
            } else {
                connectionUrl = "jdbc:"+dbUrl;
            }
        }

        if (isTracing) {
            printlnResultText("Connecting to "+connectionUrl+" via class "+driverC);
        };

        Connection con = null;
        try {
            Class.forName(driverC).newInstance();

            con = DriverManager.getConnection(connectionUrl,loginName,password);
        } catch (Throwable e) {
            if (dbDriver == null) {
                printlnResultText("Could not establish a connection with login "+loginName+" {DriverC="+driverC+" URL="+connectionUrl+"} because "+e);
            } else {
                printlnResultText("Could not establish a connection for "+dbDriver+" connecting to "+dbProduct+" with login "+loginName+" {DriverC="+driverC+" URL="+connectionUrl+"} because "+e);
            }
            return null;
        };


        return con;
    }


    protected String run(Array testCases, Connection jdbcConnection) {
        StringWriter testOutputWriter = new StringWriter();
        PrintWriter testPrintWriter = new PrintWriter(testOutputWriter, true);

        if (traceLevel >= 0) {
            jdbcConnection = new com.chimu.kernelTools.test.sql.TraceConnectionC(jdbcConnection,testPrintWriter);
        };


        testloop: for (int i = 0; i < testCases.size(); i++) {
            DatabaseTest test = (DatabaseTest) testCases.atIndex(i);
            test.setupOutput(testPrintWriter);

            if (isTracing) {
                testPrintWriter.println("\n  Test: ---- "+test.name()+" ----");
                testPrintWriter.println("  Test: **"+System.currentTimeMillis()+"**");

                test.setupTracing(testPrintWriter, traceLevel);
            } else {
                testPrintWriter.println("\n  Test: ---- "+test.name()+" ----");

            }
            
            if ((catalog != null) || (scheme != null)) {
                FormDatabaseTest formTest = (FormDatabaseTest) test;
                formTest.setupCatalog_scheme(catalog,scheme);
            }

            try {
                test.run(jdbcConnection);

            } catch (Exception e2) {
                testPrintWriter.println("Test "+test.name()+" Failed with the following exception");
                e2.printStackTrace(testPrintWriter);
                continue testloop;
            }
        };

        return testOutputWriter.toString();
    }

    //==========================================================
    //====================== "Commands" ========================
    //==========================================================
    /*
        The following are "commands":
            (1) They produce no result values,
            (2) Are expected to be called by the UI,
            (3) Cause a change in state of the ApplicationModel
            (4) Notify the UI of that state change

        They also all consistently modify the 'ResultText' to
        notify of problems
    */

    public void doTestLogin() {
        resetResultText();

        Connection connection = getConnection();
        if (connection == null) {
            printlnResultText("No Connection");
            return;
        }
        List testCases = CollectionsPack.newList();
        Object test = new com.chimu.formTools.test.ConnectionInfo();
        testCases.add(test);

        printlnResultText(run(testCases, connection));
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }


    public void doRunQueries(int[] indices) {
        resetResultText();

        Connection connection = getConnection();
        if (connection == null) {
            printlnResultText("No Connection");
            return;
        }
        List allTests = getQueryList();
        List testCases = CollectionsPack.newList();
        for (int i = 0 ; i < indices.length; i++) {
            Class testC = (Class) allTests.atIndex(indices[i]);
            DatabaseTest test = null;
            try {
                test = (DatabaseTest) testC.newInstance();
            } catch (Throwable e) {};

            if (test != null) {
                //System.out.println("Queries " + test.name());
                testCases.add(test);
            };
        }

        printlnResultText(run(testCases, connection));
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    public void doPopulate() {
        doPopulate(false,false);
    }

    public void doPopulate(boolean shouldDisableEscapes, boolean shouldGenerateIndexes) {
        resetResultText();
        if (currentScheme == null) {
            printlnResultText("No Current Scheme");
            return;
        }

        List testCases = CollectionsPack.newList();

        Object test = currentScheme.dropDatabaseTest();
        if (test == null) {
            printlnResultText("No drop script for "+currentScheme);
            return;
        }
        testCases.add(test);

        test = currentScheme.createDatabaseTest();
        if (test == null) {
            printlnResultText("No populate script for "+currentScheme);
            return;
        } else {
            if (shouldDisableEscapes) {
                ((ExampleDatabaseCreatorAbsC) test).setupDisableEscapes();
            }
            
            if (shouldGenerateIndexes) {
                ((ExampleDatabaseCreatorAbsC) test).setupGenerateIndexes();
            }
        }

        testCases.add(test);

        Connection connection = getConnection();
        if (connection == null) {
            printlnResultText("No Connection");
            return;
        }

        printlnResultText(run(testCases, connection));
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    public void doDrop() {
        resetResultText();
        if (currentScheme == null) {
            printlnResultText("No Current Scheme");
            return;
        }


        List testCases = CollectionsPack.newList();

        Object test = currentScheme.dropDatabaseTest();
        if (test == null) {
            printlnResultText("No drop script for "+currentScheme);
            return;
        }

        testCases.add(test);

        Connection connection = getConnection();
        if (connection == null) {
            printlnResultText("No Connection");
            return;
        }

        printlnResultText(run(testCases, connection));
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //==========================================================
    //================= ResultText Management ==================
    //==========================================================

    protected StringBuffer resultTextB = new StringBuffer();

    public String resultText() {
        return resultTextB.toString();
    }

    public void resetResultText() {
        resultTextB = new StringBuffer();
        if (myWindow != null) myWindow.noteResultTextChanged();
    }

    public void setResultText(String text) {
        resultTextB = new StringBuffer();
        resultTextB.append(text);
        if (myWindow != null) myWindow.noteResultTextChanged();
    }

    public void printResultText(String text) {
        resultTextB.append(text);
        if (myWindow != null) myWindow.noteResultTextChanged();
    }

    public void printlnResultText(String text) {
        resultTextB.append(text);
        resultTextB.append("\n");
        if (myWindow != null) myWindow.noteResultTextChanged();
    }


    //==========================================================
    //==========================================================
    //==========================================================

       /**
        purely for debugging prints all instance variables
       **/
    protected void debug() {
        System.out.println("Root Directory " + rootDirectory);
        System.out.println("DB URL " + dbUrl);
        System.out.println("Log in name " + loginName);

        System.out.println("password " + password);
        System.out.println("DB Driver " + dbDriver.key());
        System.out.println("Db Product " + dbProduct.shortName());
        System.out.println("Scheme " + currentScheme);
        System.out.println("isTracing "+ isTracing);
    }

    protected void debug2() {
        System.out.print("dbProductNames: ");
        System.out.println(dbProductNames());

        System.out.print("dbDriverNames: ");
        System.out.println(dbDriverNames());

        System.out.print("getSchemeList: ");
        System.out.println(getSchemeList());

        System.out.print("getSchemeNameList: ");
        System.out.println(getSchemeNameList());
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String defaultsFileName = null;


    protected ExampleRunnerWindow myWindow;

    protected String  customDbDriverCName = null;
    protected String  dbUrl = null;

    protected String  loginName = null;
    protected String  password = null;
    protected String  scheme = null;
    protected String  catalog = null;
    protected boolean isTracing  = false;
    protected int     traceLevel = -1;

//    protected SchemePack schemes;


    protected String rootDirectoryName = "sample";
    protected File   rootDirectory = null;

    protected FormExamples
                         exampleSchemeCollection;
    protected List   schemes;
    protected TestScheme currentScheme;
    protected List   currentTests = CollectionsPack.newList();

    //======================================
    //======================================
    //======================================

    protected List        dbDrivers  = null;
    protected DatabaseDriver  dbDriver   = null;

    protected List        dbProducts = null;
    protected DatabaseProduct dbProduct  = null;

    {
        dbDrivers = DatabaseDriverPack.availableDrivers();
        setDbDriver((DatabaseDriver) dbDrivers.atIndex(0));
        updateDriverDbProducts();
        setDbProduct(
                (DatabaseProduct) dbProducts.atIndex(0)
            );
    }
}