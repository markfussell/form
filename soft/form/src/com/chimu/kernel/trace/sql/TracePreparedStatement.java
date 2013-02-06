/*======================================================================
**
**  File: chimu/kernel/trace/sql/TracePreparedStatement.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.trace.sql;
import java.sql.*;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class TracePreparedStatement implements PreparedStatement{
    public TracePreparedStatement (PreparedStatement realStatement, PrintWriter traceStream) {
        this.pst=realStatement;
        this.ts=traceStream;
    };

    PreparedStatement pst;
    PrintWriter ts;

    public ResultSet executeQuery(String sql) throws SQLException {return pst.executeQuery(sql);};

    public int executeUpdate(String sql) throws SQLException {return pst.executeUpdate(sql);};

    public void close() throws SQLException {pst.close();};

    public int getMaxFieldSize() throws SQLException {return pst.getMaxFieldSize();};

    public void setMaxFieldSize(int max) throws SQLException {pst.setMaxFieldSize(max);};

    public int getMaxRows() throws SQLException {return pst.getMaxRows();};

    public void setMaxRows(int max) throws SQLException {pst.setMaxRows(max);};

    public void setEscapeProcessing(boolean enable) throws SQLException {pst.setEscapeProcessing(enable);};

    public int getQueryTimeout() throws SQLException {return pst.getQueryTimeout();};

    public void setQueryTimeout(int seconds) throws SQLException {pst.setQueryTimeout(seconds);};

    public void cancel() throws SQLException {pst.cancel();};

    public SQLWarning getWarnings() throws SQLException {return pst.getWarnings();};

    public void clearWarnings() throws SQLException {pst.clearWarnings();};

    public void setCursorName(String name) throws SQLException {pst.setCursorName(name);};

    public boolean execute(String sql) throws SQLException {return pst.execute(sql);};

    public ResultSet getResultSet() throws SQLException {return pst.getResultSet();};

    public int getUpdateCount() throws SQLException {return pst.getUpdateCount();};

    public boolean getMoreResults() throws SQLException {return pst.getMoreResults();};

    // ************************************************************************
    // ************************************************************************

    public ResultSet executeQuery() throws SQLException {
ts.println("SQL: PreparedStatement.executeQuery()");
        return pst.executeQuery();};

    public int executeUpdate() throws SQLException {
ts.println("SQL: PreparedStatement.executeUpdate()");
        return pst.executeUpdate();};

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
ts.print("["+parameterIndex+"]=<NULL>{"+sqlType+"} ");
        pst.setNull(parameterIndex,sqlType);};

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{boolean} ");
        pst.setBoolean(parameterIndex,x);};


    public void setByte(int parameterIndex, byte x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{byte} ");
        pst.setByte(parameterIndex,x);};

    public void setShort(int parameterIndex, short x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{short} ");
        pst.setShort(parameterIndex,x);};

    public void setInt(int parameterIndex, int x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{int} ");
        pst.setInt(parameterIndex,x);};

    public void setLong(int parameterIndex, long x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{long} ");
        pst.setLong(parameterIndex,x);};

    public void setFloat(int parameterIndex, float x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{float} ");
        pst.setFloat(parameterIndex,x);};

    public void setDouble(int parameterIndex, double x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{double} ");
        pst.setDouble(parameterIndex,x);};

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{BigDecimal} ");
        pst.setBigDecimal(parameterIndex,x);};

    public void setString(int parameterIndex, String x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{String} ");
        pst.setString(parameterIndex,x);};

    public void setBytes(int parameterIndex, byte x[]) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{byte[]} ");
        pst.setBytes(parameterIndex,x);};

    public void setDate(int parameterIndex, java.sql.Date x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{Date} ");
	    pst.setDate(parameterIndex,x);};

    public void setTime(int parameterIndex, java.sql.Time x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{Time} ");
        pst.setTime(parameterIndex,x);};

    public void setTimestamp(int parameterIndex, java.sql.Timestamp x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{Timestamp} ");
        pst.setTimestamp(parameterIndex,x);};

    public void setAsciiStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{AsciiStream["+length+"]} ");
        pst.setAsciiStream(parameterIndex,x,length);};

    public void setUnicodeStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{UnicodeStream["+length+"]} ");
        pst.setUnicodeStream(parameterIndex,x,length);};

    public void setBinaryStream(int parameterIndex, java.io.InputStream x, int length) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{BinaryStream["+length+"]} ");
        pst.setBinaryStream(parameterIndex,x,length);};

    public void clearParameters() throws SQLException {pst.clearParameters();};

    //----------------------------------------------------------------------
    // Advanced features:

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale)
            throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{"+targetSqlType+"} ");
        pst.setObject(parameterIndex,x,targetSqlType, scale);
    };

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
ts.print("["+parameterIndex+"]="+x+"{"+targetSqlType+"} ");
        pst.setObject(parameterIndex,x,targetSqlType);
    };

    public void setObject(int parameterIndex, Object x) throws SQLException {
ts.print("["+parameterIndex+"]="+x+" ");
        pst.setObject(parameterIndex,x);
    };

    public boolean execute() throws SQLException {return pst.execute();};

}
