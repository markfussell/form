/*======================================================================
**
**  File: chimu/kernelTools/test/sql/TraceConnectionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test.sql;
import java.sql.*;

import java.io.PrintWriter;

public class TraceConnectionC implements Connection {
    public TraceConnectionC (Connection realConnection) {
        this.realConnection=realConnection;
        this.traceStream = new PrintWriter(System.out);
    };

    public TraceConnectionC (Connection realConnection, PrintWriter traceStream) {
        this.realConnection=realConnection;
        this.traceStream = traceStream;
    };


    public Statement createStatement() throws SQLException {
traceStream.println("SQL: Create Statement");
        return new TraceStatement(realConnection.createStatement(),traceStream);
    };
    public PreparedStatement prepareStatement(String sql)
	    throws SQLException {
traceStream.println("SQL: Create PreparedStatement: "+sql);
        return new TracePreparedStatement(realConnection.prepareStatement(sql),traceStream);
    };
    public CallableStatement prepareCall(String sql) throws SQLException {
        return realConnection.prepareCall(sql);
    };
    public String nativeSQL(String sql) throws SQLException {
        return realConnection.nativeSQL(sql);
    };
    public void setAutoCommit(boolean autoCommit) throws SQLException {
traceStream.println("SQL: SetAutoCommit: "+autoCommit);
        realConnection.setAutoCommit(autoCommit);
    };
    public boolean getAutoCommit() throws SQLException {
        return realConnection.getAutoCommit();
    };
    public void commit() throws SQLException {
traceStream.println("SQL: <COMMIT>");
        realConnection.commit();
    };
    public void rollback() throws SQLException {
traceStream.println("SQL: <ROLLBACK>");
        realConnection.rollback();
    };
    public void close() throws SQLException {;
        realConnection.close();
    };
    public boolean isClosed() throws SQLException {;
        return realConnection.isClosed();
    };
    public DatabaseMetaData getMetaData() throws SQLException {;
traceStream.println("SQL: getDbMetaData()");
        return realConnection.getMetaData();
    };
    public void setReadOnly(boolean readOnly) throws SQLException {
        realConnection.setReadOnly(readOnly);
    };
    public boolean isReadOnly() throws SQLException {
        return realConnection.isReadOnly();
    };
    public void setCatalog(String catalog) throws SQLException {
        realConnection.setCatalog(catalog);
    };
    public String getCatalog() throws SQLException {
        return realConnection.getCatalog();
    };
    public void setTransactionIsolation(int level) throws SQLException {
traceStream.println("SQL: SetTransactionIsolation: "+level);
       realConnection.setTransactionIsolation(level);
    };
    public int getTransactionIsolation() throws SQLException {
        return realConnection.getTransactionIsolation();
    };
    public SQLWarning getWarnings() throws SQLException {
        return realConnection.getWarnings();
    };
    public void clearWarnings() throws SQLException {
        realConnection.clearWarnings();
    };


    Connection realConnection = null;
    PrintWriter traceStream = null;
};
