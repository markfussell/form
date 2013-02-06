/*======================================================================
**
**  File: chimu/formTools/test/DatabaseTester.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.util.Date;
import java.io.PrintWriter;

import java.sql.*;
import java.util.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.database.*;
import java.io.PrintWriter;

/**
DatabaseTester is the main program for running database tests.
**/
public class DatabaseTester {

    //==========================================================
    //==========================================================
    //==========================================================

        /**
         * runTest is the main behavior of the TestDriver.  The rest
         * of the class is for parsing the command line and preparing
         * the JdbcConnection
         */
    static void runTest(List testCases, Connection jdbcConnection) {
        testloop: for (int i = 0; i < testCases.size(); i++) {

            DatabaseTest test = null;
            String testCName = (String) testCases.atIndex(i);

            try {
                test = (DatabaseTest) Class.forName(testCName).newInstance();
            } catch (Exception e) {
                System.err.println("Could not create test named "+testCName+" because");
                e.printStackTrace();
                continue testloop;
            }

            test.setupOutput(output);
            if (tracing()) {
                if (!quiet) {
                    output.println("\nTest: ---- "+testCName+" ----");
                    traceStream.println("Test: **"+System.currentTimeMillis()+"**");
                };

                test.setupTracing(traceStream, traceLevel);
            } else {
                if (!quiet) output.println("\nTest: ---- "+testCName+" ----");
            }

            try {
                test.run(jdbcConnection);
            } catch (Exception e2) {
                output.println("Test "+testCName+" Failed with the following exception");
                e2.printStackTrace();
                continue testloop;
            }
        }
    }

    static protected boolean tracing() {
        return tracing;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    static PrintWriter traceStream  = null;  //This is a trace stream
    static PrintWriter output       = new PrintWriter(System.out,true);
    static String      demoNumber   = null;
    static boolean     tracing      = false;
    static int         traceLevel   = 1;
    static String      databaseType = "unknown";
    static String      driverType   = "jdbcodbc";
    static String      driverC  = null;
    static String      loginName    = null;
    static String      password     = null;
    static boolean     quiet        = false;
    static String      versionString = "1.0";

    static public void main(String[] args) {
        String databaseName = null;
        List testCases = CollectionsPack.newList();
        String connectionPrefix = null;

        args = getArguments(args);
        if (tracing) {
            System.setErr(System.out);
            traceStream = output;
        };

        int argIndex = 0;
        if (args.length < 2) {
            output.println("DatabaseTester Version "+versionString);
            output.println("Usage:");
            output.println("    databaseTester [LoginControl] [DatabaseOptions] [TraceOptions] database (TestC)+");
            output.println("or");
            output.println("    java com.chimu.formTools.test.DatabaseTester ...");
            output.println();
            output.println("Login Control:     [ -l loginName [-p password] ] ");
            output.println("    -l <loginName>    ");
            output.println("    -p <password>     ");
            output.println();
            output.println("Database Options:  [ (-dr driverProduct [-da databaseProduct]) | -drc driverC ]");
            output.println("    -dr <driver>      The identifier for the jdbc driver. One of:");
            output.println("                        jdbcodbc[default], fastforward, webconnect, jconnect");
            output.println("    -da <database>    The identifier for the database product. One of:");
            output.println("                        oracle, sybase, mssql, informix, sqla, db2, or access");
            output.println("    -drc <class>      The full path to a JDBC driver class.  For example:");
            output.println("                        sun.jdbc.odbc.JdbcOdbcDriver");
            output.println();
            output.println("Trace Options:     [ (-t | -tl traceLevel) [-q] ]");
            output.println("    -t                Enable Tracing at detail level 1");
            output.println("    -tl <level>       Enable Tracing at detail level <level>");
            output.println("    -q                Be quiet.  Do not include test name trace in output.");
            output.println();
            output.println("Arguments:");
            output.println("    database          The database identifier appropriate to the driver product");
            output.println("    TestC         The test class name or full path name");
            output.println();
            output.println("Examples:");
            output.println("    databaseTester FormExampleDatabase1 Test1 Test2 ");
            output.println("    databaseTester -l Alpha -p FoxTrot -t FormExampleDatabase1 Test1 Test2 ");
            return;
        };

        if (argIndex < args.length)     databaseName = args[argIndex++];
        while (argIndex < args.length) {
            testCases.add(args[argIndex++]);
        };


        if (databaseType.equals("oracle")) {
            if (loginName == null) loginName = "Demo";
            if (password == null)  password = "Demo";
        } else if (databaseType.equals("db2")) {
        } else if (databaseType.equals("access")) {
        } else if (databaseType.equals("mssql")) {
            if (loginName == null) loginName = "sa";
            if (password == null)  password = "";
        } else if (databaseType.equals("mssqlserver")) {
            if (loginName == null) loginName = "sa";
            if (password == null)  password = "";
        } else if (databaseType.equals("sybase")) {
            if (loginName == null) loginName = "sa";
            if (password == null)  password = "";
        } else if (databaseType.equals("informix")) {
            if (loginName == null) loginName = "informix";
            if (password == null)  password = "informix";
        } else {
            if (loginName == null) loginName = "";
            if (password == null)  password = "";
        };

        if (driverC == null) {
            if (driverType.equals("jdbcodbc")) {
                driverC = "sun.jdbc.odbc.JdbcOdbcDriver";
                connectionPrefix = "jdbc:odbc:"+databaseName;
            } else if (driverType.equals("weblogic")) {
                if (databaseType.equals("oracle")) {
                    driverC = "weblogic.jdbc.oci.Driver";
                    connectionPrefix = "jdbc:weblogic:"+databaseType;
                } else {
                    output.println("WebLogic doesn't work with "+databaseType); return;
                };
           } else if (driverType.equals("fastforward")) {
                connectionPrefix = "jdbc:ff-";
                if (databaseType.equals("oracle")) {
                    output.println("FastForward doesn't work with oracle "); return;
                } else if (databaseType.equals("mssqlserver") || databaseType.equals("mssql")) {
                    driverC = "connect.microsoft.MicrosoftDriver";
                    connectionPrefix += "microsoft:";
                } else {
                    output.println("FastForward doesn't work with "+databaseType); return;
                };
                connectionPrefix += databaseName;
            } else {
                output.println("Unidentified driverType: "+driverType);
                return;
            };
        } else {
            connectionPrefix = databaseName;
        }


        Connection con = null;

        // REGISTER DRIVER and GET CONNECTION
        if (traceStream != null) traceStream.println("Logging in to "+databaseName+" of type "+databaseType+" via "+connectionPrefix);
        try {
            Class.forName(driverC).newInstance();
            con = DriverManager.getConnection(connectionPrefix,loginName,password);
        } catch (Exception e) {output.println(e);
            return;
        };

        // GET CONNECTION WARNINGS
        if (traceStream != null) {
            SQLWarning warning = null;
            try {
                warning = con.getWarnings();

                if (warning == null){
                    traceStream.println("No Warnings");
                }

                while (warning != null) {
                    traceStream.println("Warning: "+warning);
                    warning = warning.getNextWarning();
                }
            } catch (Exception e){
                System.err.println(e);
            };
        };

        // Wrap the connection in a test environment
        if (tracing) {
            con = new com.chimu.kernelTools.test.sql.TraceConnectionC(con,traceStream);
        };

        runTest(testCases, con);
        output.flush();

        if (!quiet) {
            if (traceStream != null) traceStream.println("\nTest: Tests Done");
            if (traceStream != null) traceStream.println("Test: **"+System.currentTimeMillis()+"**");
        };

        try {
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        };

    }


    public static String[] getArguments(String[] args) {
        Vector files = new Vector();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-t")) {
                tracing = true;
                traceLevel = 0;
            } else if (arg.equals("-tl")) {
                tracing = true;
                try {
                    traceLevel = Integer.parseInt(args[++i]);
                } catch(Exception e) {
                        //Not a number following the -tl
                    traceLevel = 1;
                    i--;
                }
            } else if (arg.equals("-da")) {
                if (i < args.length) {
                    databaseType = args[++i].toLowerCase();
                } else {
                    output.println("No databaseType, ignoring '-da' parameter");
                }
            } else if (arg.equals("-dr")) {
                if (i < args.length) {
                    driverType = args[++i];
                } else {
                    output.println("No driverType, ignoring '-dr' parameter");
                }
            } else if (arg.equals("-drc")) {
                if (i < args.length) {
                    driverC = args[++i];
                } else {
                    output.println("No driverC, ignoring '-drc' parameter");
                }
            } else if (arg.equals("-l")) {
                if (i < args.length) {
                    loginName = args[++i];
                } else {
                    output.println("No login name, ignoring '-l' parameter");
                }
            } else if (arg.equals("-q")) {
                quiet = true;
            } else if (arg.equals("-p")) {
                if (i < args.length) {
                    password = args[++i];
                } else {
                    output.println("No password, ignoring '-p' parameter");
                }
            } else {
                files.addElement(arg);
            }
        };
        String[] result = new String[files.size()];
        files.copyInto(result);
        return result;
    }

    //==========================================================
    //==========================================================
    //==========================================================

}