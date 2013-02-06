/*======================================================================
**
**  File: chimu/kernelTools/test/sql/TraceStatement.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test.sql;
import java.sql.*;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class TraceStatement implements Statement {
    public TraceStatement (Statement realStatement, PrintWriter traceStream) {
        this.pst=realStatement;
        this.ts=traceStream;
    };

    Statement pst;
    PrintWriter ts;




    public ResultSet executeQuery(String sql) throws SQLException {
ts.println("SQL: {Query} "+sql);
        return pst.executeQuery(sql);
    };

    public int executeUpdate(String sql) throws SQLException {
ts.println("SQL: {Update} "+sql);
        return pst.executeUpdate(sql);
    };

    public boolean execute(String sql) throws SQLException {
ts.println("SQL: {Execute} "+sql);
        return pst.execute(sql);
    };


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

    public ResultSet getResultSet() throws SQLException {return pst.getResultSet();};

    public int getUpdateCount() throws SQLException {return pst.getUpdateCount();};

    public boolean getMoreResults() throws SQLException {return pst.getMoreResults();};

    // ************************************************************************
    // ************************************************************************

}
