/*======================================================================
**
**  File: chimu/form/query/SqlBuilderC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.database.Column;
import com.chimu.form.database.ColumnSi;
import com.chimu.form.database.Table;
import com.chimu.form.database.support.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.sql.*;

import java.math.*;

import java.util.Enumeration;
import java.util.Date;

/**
SqlBuilderC is private to the FORM Subsystem and should not be
used by FORM clients
**/
/*subsystem*/ public class SqlBuilderC implements SqlBuilderPi {
    protected SqlBuilderC() {
    };

    public void makeNotDistinct() {
        isDistinct = false;
    }

    public void addExtent_varName(Table table, String varName) {
        if (sqlExtentStringB.length() > 0) {
            sqlExtentStringB.append(", ");
        };
        sqlExtentStringB.append(table.qualifiedName()+" ");
        if (varName != null) {
            sqlExtentStringB.append(varName+" ");  // Table [AS] alias
        };
    }

    public void addSelectColumns_varName(Array columns, String varName) {
        int size = columns.size();
        if (sqlSelectStringB.length() > 0) {
            sqlSelectStringB.append(",");
        };
        for (int i = 0; i<size; i++) {
            if (i > 0) sqlSelectStringB.append(",");
            ColumnSi column = (ColumnSi) columns.atIndex(i);
            column.addWithVarName_selectSqlTo(varName,sqlSelectStringB);
        };
    }

    public void addSelectColumn_varName(Column column, String varName) {
        if (sqlSelectStringB.length() > 0) {
            sqlSelectStringB.append(",");
        };
        ((ColumnSi) column).addWithVarName_selectSqlTo(varName,sqlSelectStringB);
    }

    public void setToCount() {
        isCount = true;
    }


/*
    public void addSelectString(String string) {
        if (sqlSelectStringB.length() > 0) {
            sqlSelectStringB.append(", ");
        };
        sqlSelectStringB.append(string+" ");
    }
*/

    public void appendString(String string) {
        sqlQueryStringB.append(string);
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String sqlStringForNull() {
        return "NULL";
    }

    public String sqlStringForBoolean(Boolean value) {
        return SqlBuilderLib.sqlStringForBoolean(value);
    }

    public String sqlStringForByte(Byte value) {
        return SqlBuilderLib.sqlStringForByte(value);
    }

    public String sqlStringForShort(Short value) {
        return SqlBuilderLib.sqlStringForShort(value);
    }

    public String sqlStringForInteger(Integer value) {
        return SqlBuilderLib.sqlStringForInteger(value);
    }

    public String sqlStringForLong(Long value) {
        return SqlBuilderLib.sqlStringForLong(value);
    }

    public String sqlStringForFloat(Float value) {
        return SqlBuilderLib.sqlStringForFloat(value);
    }

    public String sqlStringForDouble(Double value) {
        return SqlBuilderLib.sqlStringForDouble(value);
    }

    public String sqlStringForBigDecimal(BigDecimal value) {
        return SqlBuilderLib.sqlStringForBigDecimal(value);
    }

    public String sqlStringForBigInteger(BigInteger value) {
        return SqlBuilderLib.sqlStringForBigInteger(value);
    }

    public String sqlStringForNumber(Number value) {
        return SqlBuilderLib.sqlStringForNumber(value);
    }

    public String sqlStringForString(String value) {
        return SqlBuilderLib.sqlStringForString(value);
    }

    public String sqlStringForDate(Date value) {
        return SqlBuilderLib.sqlStringForDate(value);
    }

    public String sqlStringForTime(Date value) {
        return SqlBuilderLib.sqlStringForTime(value);
    }

    public String sqlStringForTimestamp(Date value) {
        return SqlBuilderLib.sqlStringForDate(value);
    }

    public String sqlStringForTimestampWithFractional(Date value) {
        return SqlBuilderLib.sqlStringForTimestampWithFractional(value);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void appendValue_javaType_sqlType(Object value, int javaType, int sqlType) {
        String sqlString = SqlBuilderLib.sqlStringFor_javaType(value,javaType);
        if (sqlString == null) {
            int altJavaType = ConverterLib.defaultJavaTypeForSqlType(sqlType);
            sqlString = SqlBuilderLib.sqlStringFor_javaType(value,altJavaType);
            if (sqlString == null) {
                throw new NotImplementedYetException("Can not support query datatypes that do not have literal values: "+value+" {"+value.getClass()+"}");
            }
        }
        appendString(sqlString);
    }

    public void appendValue(Object value) {
        if (value == null) {
            appendNullValue();
            return;
        };
        try {
            appendStringValue((String) value);
            return;
        } catch (ClassCastException e) {
            try {
                appendNumberValue((Number) value);
                return;
            } catch (ClassCastException e2) {};
        };

        // this.appendParameter(value);  //This is the right way....

        //This is not
        String string = value.toString();
        appendString(string);

        /*
        if (needsQuotes(string)) {
            appendStringValue(string);
        } else {
            appendString(string);
        };
        */

    }


    protected void appendNumberValue(Number number) {
        appendString(number.toString());
    }

    protected boolean needsQuotes(String string) {
        return true;
    }

    public void appendUnboundParameter() {
        sqlQueryStringB.append("? ");
        hasUnboundParameters=true;
    }

    public void appendParameter(Object parameter) {
        if (hasUnboundParameters) {throw new RuntimeException("Already have unbound parameters");};
        sqlQueryStringB.append("? ");
        sqlParameters.add(parameter);
    }

    protected void appendNullValue() {
        sqlQueryStringB.append("NULL ");
    }

    protected void appendStringValue(String value) {
        sqlQueryStringB.append("'");
        sqlQueryStringB.append(value);
        sqlQueryStringB.append("' ");
    }


/*
        try {
            appendStringValue((String) value);
            return;
        } catch (ClassCastException e) {
            try {
                appendNumberValue((Number) value);
                return;
            } catch (ClassCastException e2) {};
        };

        this.appendParameter(value);
*/


    public void appendStartPrimary() {
        sqlQueryStringB.append("( ");
    }

    public void appendNotNull() {
        sqlQueryStringB.append("IS NOT NULL ");
    };

    public void appendIsNull() {
        sqlQueryStringB.append("IS NULL ");
    }

    public void appendEndPrimary() {
        sqlQueryStringB.append(") ");
    }

    public void joinFrom_varName_to_varName(Column fromColumn, String fromVarName, Column toColumn, String toVarName) {
        if (sqlQueryStringB.length() > 0) {
            sqlQueryStringB.append("AND ");
        };

        sqlQueryStringB.append("(");
        ((ColumnSi) fromColumn).addWithVarName_equals_varName_sqlTo(fromVarName,toColumn,toVarName,sqlQueryStringB);
        sqlQueryStringB.append(")");

        /*
                fromColumn.addVarNameTo(sqlQueryStringB);
            sqlQueryStringB.append(" = ");
                toColumn.addVarNameTo(sqlQueryStringB);
        */
    }

    public void addColumnAsExpression_varName(Column column, String varName) {
        ((ColumnSi) column).addWithVarName_expressionSqlTo(varName, sqlQueryStringB);
    }

    public String toString() {
        StringBuffer stringB = new StringBuffer();
        stringB.append(sqlString());
        stringB.append(sqlParameters);
        return stringB.toString();
    }

/*
    protected void buildSqlSelectString() {
        int size = selectColumns.size();
        for (int i = 0; i < size; i++) {
            Column column = selectColumns.atIndex(i);
            if (i > 0) sqlSelectStringB.append(",");
            column.addSelectSqlTo(sqlSelectStringB);
        };
    }
*/

    public String sqlString() {
        StringBuffer stringB = new StringBuffer();
        if (isCount) {
            stringB.append("SELECT ");
            if (sqlSelectStringB.length() >0) {
                stringB.append("COUNT(");
                if (isDistinct) stringB.append("DISTINCT ");
                stringB.append(sqlSelectStringB.toString());
                stringB.append(") ");
            } else {
                stringB.append("COUNT(*) ");
            }
        } else {
            if (sqlSelectStringB.length() >0) {
                stringB.append("SELECT ");
                if (isDistinct) stringB.append("DISTINCT ");
                stringB.append(sqlSelectStringB.toString());
                stringB.append(" ");
            };
        }

        stringB.append("FROM ");
        stringB.append(sqlExtentStringB.toString());
        if (sqlQueryStringB.length() >0) {
            stringB.append("WHERE ");
            stringB.append(sqlQueryStringB.toString());
        };
        return stringB.toString();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected StringBuffer  sqlSelectStringB = new StringBuffer();
    protected StringBuffer  sqlQueryStringB = new StringBuffer();
    protected List      sqlParameters = CollectionsPack.newList();
    protected boolean       hasUnboundParameters = false;
    protected Map           fromExtents = CollectionsPack.newMap();
    protected StringBuffer  sqlExtentStringB = new StringBuffer();
    protected boolean       isDistinct = true;
    protected boolean       isCount = false;

    protected List      selectColumns = CollectionsPack.newList();

    //**********************************************************
    //(P)****************       STATIC       *******************
    //**********************************************************
/*
    static public void main(String[] args) {
        SqlBuilder sqlB = new SqlBuilderC();
        QueryDescriptionC queryB = new QueryDescriptionC();

        queryD.newConstant("Hi");
        ExtentVar ev1 = queryD.newExtentVar("foo",mapper);
        ExtentVar ev2 = queryD.defaultExtentVar(mapper);
        AssociatedVar av1 = ev1.slot("slot");

        queryB.and_equals(ev1,ev2);
        queryB.and(QueryPack.newLessThan(ev1,dv1));
        queryB.and(QueryPack.newNotNull(dv3));

        queryB.and_equals(dv1,dv2);
        queryB.and(new LessThanConditionC(esv,dv1));
        queryB.and(new NotNullConditionC(dv3));
        queryB.or(new NotNullConditionC(dv3));
        queryB.or(new NotNullConditionC(uv1));

        DirectValueWrapper dv1 = new DirectValueWrapperC("Hi");
        DirectValueWrapper dv2 = new DirectValueWrapperC(null);
        DirectValueWrapper dv3 = new DirectValueWrapperC("You");
 //       ExtentVar ev1 = new ExtentVarC("rov",null);
 //       SlotVar sv1 = new SlotVarC(new SlotC("Slot",null,false,false,null,null,null),ev1);
 //       UnboundValueC uv1 = new UnboundValueC();

        Mapper mapper = null;

        ExtentVar ev1 = queryB.newExtentVar("rov",mapper);
//        ExtentSlotValue qsv = new QuerySlotValueC(slot, ev1);
        Slot slot = new SlotC("Slot",null,false,false,null,null,null);
        ExtentSlotValue esv = queryB.newExtentSlotValue(ev1,slot);

        UnboundValueC uv1 = queryB.newUnboundValue("uv1");



        queryB.and_equals(dv1,dv2);
        queryB.and(QueryPack.newLessThan(esv,dv1));
        queryB.and(QueryPack.newLessThan(dv3));
        queryB.or(QueryPack.newNotNull(dv3));
        queryB.or(QueryPack.newNotNull(uv1));

        // 30 seconds per 10,000
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 1; i++) {
            sqlB = new SqlBuilderC();
            queryB.condition().putSqlInto(sqlB);
        };
        System.out.println(System.currentTimeMillis());

        System.out.println(sqlB);
        System.out.println(queryB.statusString());
//        System.out.println(queryB.unattachedExtentSlotValuesToString());
        throw new NotImplementedYetException();

    };
*/

};