/*======================================================================
**
**  File: chimu/form/query/SqlBuilder.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.database.Column;
import com.chimu.form.database.Table;
import com.chimu.kernel.collections.*;

import java.util.Date;

/**
A SqlBuilder creates SQL (both the QueryString and the unbound values)
appropriate to the specific database the builder is for.

@see QueryPack#newSqlBuilder();
**/

public interface SqlBuilder {
    public void makeNotDistinct();
    public void setToCount();


    public void appendString(String string);
    public void appendParameter(Object parameter);
    public void appendUnboundParameter();
    public void appendValue(Object value);
    public void appendValue_javaType_sqlType(Object value, int javaType, int sqlType);
    public void appendStartPrimary();
    public void appendEndPrimary();
    public void appendNotNull();
    public void appendIsNull();


    public String sqlStringForNumber(Number value);
    public String sqlStringForString(String value);
    public String sqlStringForDate(Date value);
    public String sqlStringForBoolean(Boolean value);
    public String sqlStringForNull();
    public String sqlStringForTimestampWithFractional(Date value);
    public String sqlStringForTime(Date value);

        /**
         * Return the current SQL String for this builder
         */
    public String sqlString();

    public void joinFrom_varName_to_varName(Column fromColumn, String fromVarName, Column toColumn, String toVarName);
    public void addExtent_varName(Table table, String varName);
    public void addColumnAsExpression_varName(Column column, String varName);
    public void addSelectColumns_varName(Array columns, String varName);
    public void addSelectColumn_varName(Column column, String varName);
   // public void addSelectString(String string);

};