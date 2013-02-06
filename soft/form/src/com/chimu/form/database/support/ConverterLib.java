/*======================================================================
**
**  File: chimu/form/database/support/ConverterLib.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.support;

import com.chimu.form.database.Table;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.streams.*;
import com.chimu.kernel.meta.*;
import com.chimu.kernel.meta.TypeConstants;

import java.sql.*;
import java.sql.Date;

import java.math.*;
import java.util.*;

import java.io.PrintWriter;

/**
Package:
Constructors?  No can just use general query capability.
Types? Does it matter? On happens at compile time anyway.

**/


interface ResultSetReader {
    Object valueFromResult_at(ResultSet rs, int pos);
}

public class ConverterLib {

        /**
         * Return the Class to use as a datatype for a column
         * or 'null' if the class is not convertable.
         */
    public static Class convertDatatypeC(Class aC) {
        return (Class) datatypeClasses.atKey(aC);
    }


    static Map datatypeClasses = CollectionsPack.newMap();

    static {
        datatypeClasses.atKey_put(Boolean.TYPE,     Boolean.class);
        datatypeClasses.atKey_put(Character.TYPE,   Character.class);
        datatypeClasses.atKey_put(Byte.TYPE,        Byte.class);
        datatypeClasses.atKey_put(Short.TYPE,       Short.class);
        datatypeClasses.atKey_put(Integer.TYPE,     Integer.class);
        datatypeClasses.atKey_put(Long.TYPE,        Long.class);
        datatypeClasses.atKey_put(Float.TYPE,       Float.class);
        datatypeClasses.atKey_put(Double.TYPE,      Double.class);

        datatypeClasses.atKey_put(Boolean.class,     Boolean.class);
        datatypeClasses.atKey_put(Character.class,   Character.class);
        datatypeClasses.atKey_put(Byte.class,        Byte.class);
        datatypeClasses.atKey_put(Short.class,       Short.class);
        datatypeClasses.atKey_put(Integer.class,     Integer.class);
        datatypeClasses.atKey_put(Long.class,        Long.class);
        datatypeClasses.atKey_put(Float.class,       Float.class);
        datatypeClasses.atKey_put(Double.class,      Double.class);

        datatypeClasses.atKey_put(Object.class,                Object.class);
        datatypeClasses.atKey_put(String.class,                String.class);

        datatypeClasses.atKey_put(java.util.Date.class,        java.util.Date.class);
        datatypeClasses.atKey_put(java.sql.Date.class,         java.sql.Date.class);
        datatypeClasses.atKey_put(java.sql.Time.class,         java.sql.Time.class);
        datatypeClasses.atKey_put(java.sql.Timestamp.class,    java.sql.Timestamp.class);

        datatypeClasses.atKey_put(java.math.BigDecimal.class,  java.math.BigDecimal.class);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public Boolean wrapBoolean(boolean isTrue, boolean wasNull) {
        if (wasNull) return null;
        return isTrue ? Boolean.TRUE : Boolean.FALSE;
    }

    static public Byte wrapByte(byte value, boolean wasNull) {
        if (wasNull) return null;
        return new Byte(value);
    }

    static public Short wrapShort(short value, boolean wasNull) {
        if (wasNull) return null;
        return new Short(value);
    }

        /**
         * This should prevent duplicates from being created...
         */
    static public Integer wrapInteger(int value, boolean wasNull) {
        if (wasNull) return null;
        return new Integer(value);
    }

    static public Long wrapLong(long value, boolean wasNull) {
        if (wasNull) return null;
        return new Long(value);
    }

    static public Float wrapFloat(float value, boolean wasNull) {
        if (wasNull) return null;
        return new Float(value);
    }

    static public Double wrapDouble(double value, boolean wasNull) {
        if (wasNull) return null;
        return new Double(value);
    }

    static public String wrapString(String value, boolean wasNull) {
        if (wasNull) return null;
        return value;
    }

    static public BigDecimal wrapBigDecimal(BigDecimal value, boolean wasNull) {
        if (wasNull) return null;
        return value;
    }

    //**************************************
    //**************************************
    //**************************************

    static public Timestamp wrapTimestamp(Timestamp value, boolean wasNull) {
        if (wasNull) return null;
        return value;
    }

    static public Date wrapDate(Date value, boolean wasNull) {
        if (wasNull) return null;
        return value;
    }

    static public Time wrapTime(Time value, boolean wasNull) {
        if (wasNull) return null;
        return value;
    }

    //**************************************
    //**************************************
    //**************************************

    static public boolean unwrapBoolean(Object anObject) {
        return ((Boolean) anObject).booleanValue();
    }

    static public byte unwrapByte(Object anObject) {
        return ((Byte) anObject).byteValue();
    }

    static public short unwrapShort(Object anObject) {
        return ((Short) anObject).shortValue();
    }

    static public int unwrapInteger(Object anObject) {
        return ((Integer) anObject).intValue();
    }

    static public long unwrapLong(Object anObject) {
        return ((Long) anObject).longValue();
    }

    static public float unwrapFloat(Object anObject) {
        return ((Float) anObject).floatValue();
    }

    static public double unwrapDouble(Object anObject) {
        return ((Double) anObject).doubleValue();
    }


    //**************************************
    //**************************************
    //**************************************

   static public java.sql.Timestamp convertDateToTimestamp(java.util.Date aDate) {
        return new Timestamp(aDate.getTime());
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public int defaultJavaTypeForSqlType(int sqlType) {
        switch (sqlType) {
            case Types.BIT:      return TypeConstants.TYPE_Boolean;

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return TypeConstants.TYPE_Integer;
            case Types.BIGINT:   return TypeConstants.TYPE_Long;

            case Types.FLOAT:    return TypeConstants.TYPE_Float;
            case Types.REAL:     return TypeConstants.TYPE_Float;
            case Types.DOUBLE:   return TypeConstants.TYPE_Double;

            case Types.NUMERIC:  return TypeConstants.TYPE_java_math_BigDecimal;
            case Types.DECIMAL:  return TypeConstants.TYPE_java_math_BigDecimal;

            case Types.CHAR:     return TypeConstants.TYPE_String;
            case Types.VARCHAR:  return TypeConstants.TYPE_String;

            case Types.DATE:      return TypeConstants.TYPE_String;
            case Types.TIME:      return TypeConstants.TYPE_String;
            case Types.TIMESTAMP: return TypeConstants.TYPE_String;

            case Types.BINARY:         throw new NotImplementedYetException("No javaType for BINARY");
            case Types.VARBINARY:      throw new NotImplementedYetException("No javaType for VARBINARY");
            case Types.LONGVARBINARY:  throw new NotImplementedYetException("No javaType for LONGVARBINARY");
        }
        throw new NotImplementedYetException("Did not recognize sqlType:"+sqlType);
    }



    static public Object getFromResultSet_atIndex_javaType(ResultSet rs, int pos, int javaType)
            throws SQLException {
if (ts != null) {ts.println("FORM: ResultSet.get<"+javaType+">["+pos+"]");};
        switch (javaType) {
            case TypeConstants.TYPE_Object:     return rs.getObject(pos+1);
            case TypeConstants.TYPE_Boolean:    return wrapBoolean(rs.getBoolean(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Byte:       return wrapByte(rs.getByte(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Short:      return wrapShort(rs.getShort(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Integer:    return wrapInteger(rs.getInt(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Long:       return wrapLong(rs.getLong(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Float:      return wrapFloat(rs.getFloat(pos+1),rs.wasNull());
            case TypeConstants.TYPE_Double:     return wrapDouble(rs.getDouble(pos+1),rs.wasNull());
            case TypeConstants.TYPE_String:     return wrapString(rs.getString(pos+1),rs.wasNull());

            case TypeConstants.TYPE_java_math_BigDecimal: return wrapBigDecimal(rs.getBigDecimal(pos+1,8),rs.wasNull());  //HACK: Requires callback

            case TypeConstants.TYPE_java_util_Date:       return wrapTimestamp(rs.getTimestamp(pos+1),rs.wasNull());

            case TypeConstants.TYPE_java_sql_Date:        return wrapDate(rs.getDate(pos+1),rs.wasNull());
            case TypeConstants.TYPE_java_sql_Time:        return wrapTime(rs.getTime(pos+1),rs.wasNull());
            case TypeConstants.TYPE_java_sql_Timestamp:   return wrapTimestamp(rs.getTimestamp(pos+1),rs.wasNull());
        }
        return null;
    }

        /**
         * SqlType will be ignored (and is irrelevant) if value != null and javaType != TYPE_Object
         */
    static public void setIntoPreparedStatement_atIndex_sqlType_to_javaType(PreparedStatement ps, int pos, int sqlType, Object value, int javaType)
            throws SQLException {
        if (value == null) {
            ps.setNull(pos+1,sqlType);
            return;
        };

        switch (javaType) {
            case TypeConstants.TYPE_Object:     ps.setObject(pos+1,value,sqlType);         return;
            case TypeConstants.TYPE_Boolean:    ps.setBoolean(pos+1,unwrapBoolean(value)); return;
            case TypeConstants.TYPE_Byte:       ps.setByte(pos+1,unwrapByte(value));       return;
            case TypeConstants.TYPE_Short:      ps.setShort(pos+1,unwrapShort(value));     return;
            case TypeConstants.TYPE_Integer:    ps.setInt(pos+1,unwrapInteger(value)); return;
            case TypeConstants.TYPE_Long:       ps.setLong(pos+1,unwrapLong(value));       return;
            case TypeConstants.TYPE_Float:      ps.setFloat(pos+1,unwrapFloat(value));     return;
            case TypeConstants.TYPE_Double:     ps.setDouble(pos+1,unwrapDouble(value));   return;
            case TypeConstants.TYPE_String:     ps.setString(pos+1,(String) value);        return;

            case TypeConstants.TYPE_java_math_BigDecimal: ps.setBigDecimal(pos+1,(BigDecimal) value); return;

            case TypeConstants.TYPE_java_util_Date:       ps.setTimestamp(pos+1,convertDateToTimestamp((java.util.Date) value)); return;

            case TypeConstants.TYPE_java_sql_Date:        ps.setDate(pos+1,(java.sql.Date) value); return;
            case TypeConstants.TYPE_java_sql_Time:        ps.setTime(pos+1,(java.sql.Time) value); return;
            case TypeConstants.TYPE_java_sql_Timestamp:   ps.setTimestamp(pos+1,(java.sql.Timestamp) value); return;
        }
    }


/**
    interface DatabaseReaderWriter {
        public void setIntoPreparedStatement_atIndex_sqlType_to_(PreparedStatement ps, int pos, int sqlType, Object value);
        public Object getFromResultSet_atIndex(ResultSet rs, int pos);
        public boolean canWorkWithSqlType(sqlType);
        public boolean useable
    }

    class ObjectDbRwC implements DatabaseReaderWriter {
        public void setIntoPreparedStatement_atIndex_sqlType_to_(PreparedStatement ps, int pos, int sqlType, Object value)
            ps.setObject(pos+1,value,sqlType)
        }

        public Object getFromResultSet_atIndex(ResultSet rs, int pos) {
            return rs.getObject(pos+1);
        }

        public boolean canWorkWithSqlType(sqlType) {
            return true;
        }

        public Class javaType() {
            return Object.class;
        }
    }

    class ObjectDbRwC implements DatabaseReaderWriter {
        public void setIntoPreparedStatement_atIndex_sqlType_to_(PreparedStatement ps, int pos, int sqlType, Object value)
            ps.setObject(pos+1,value,sqlType)
        }

        public Object getFromResultSet_atIndex(ResultSet rs, int pos) {
            return rs.getObject(pos+1);
        }

        public boolean canWorkWithSqlType(sqlType) {
            return true;
        }

        public Class javaType() {
            return Object.class;
        }

        public TypeDescription typeDescription() {
            return ...;
        }
    }

    interface TypeDescription extends Serializable {
        public String  name();
        public Class[] supportedInterfaces();
        public Class   defaultJavaC();
        public boolean isEnumerable();
        public boolean isOrdered();
        public boolean isNullable();
        public int length();            //-1
        public int precision();         //-1
    }

    class TypeDescriptionC implements TypeDescription {
        ClassDescription defaultJavaC = null;

        protected int length = -1;
        protected int precision = -1;
        protected boolean isNullable = true;
    }

**/


    //**************************************
    //**************************************
    //**************************************

/*
    static public ResultSetReader resultSetReaderFor(int javaType) {
        switch (javaType) {
            case TypeConstants.TYPE_Object:   return resultSetObjectReader;
            case TypeConstants.TYPE_boolean:  return resultSetBooleanReader;
        }
    }

    static final ResultSetReader resultSetObjectReader =
        new ResultSetReader() {public Object valueFromResult_at(ResultSet rs, int pos) {
            return rs.getObject(pos);
        }};

    static final ResultSetReader resultSetBooleanReader =
        new ResultSetReader() {public Object valueFromResult_at(ResultSet rs, int pos) {
            return rs.getBoolean(pos);
        }};

    static final ResultSetReader resultSetByteReader =
        new ResultSetReader() {public Object valueFromResult_at(ResultSet rs, int pos) {
            return rs.getByte(pos);
        }};

    static final ResultSetReader resultSetShortReader =
        new ResultSetReader() {public Object valueFromResult_at(ResultSet rs, int pos) {
            return rs.getShort(pos);
        }};


    static public Function1Arg converterFromJava_toSql(int javaType, int sqlType) {
        if (javaType == -1)        return null;
        if (sqlType == Types.NULL) return null;

        switch (javaType) {
            case TypeConstants.TYPE_Object:   return noConversionException();
            case TypeConstants.TYPE_boolean:  return convertFromBooleanTo(sqlType);
        }
    }

    static public Function1Arg converterFromSql_toJava(int sqlType, int javaType) {
        if (javaType == -1)        return null;
        if (sqlType == Types.NULL) return null;

        switch (sqlType) {
            case Types.BIT:      return convertFromSqlBitTo(sqlType2);

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return convertFromIntegerTo(javaType);
            case Types.BIGINT:   return convertFromLongTo(javaType);

            case Types.FLOAT:    return convertFromFloatTo(javaType);
            case Types.REAL:     return null;
            case Types.DOUBLE:   return convertFromDoubleTo(javaType);

            case Types.NUMERIC:  return null;
            case Types.DECIMAL:  return null;

            case Types.CHAR:
            case Types.VARCHAR:  return convertFromStringTo(sqlType2);;
        }
    }
*/

    static public Function1Arg converterFrom_to(int sqlType1, int sqlType2) {
        if (sqlType1 == sqlType2)   return null;
        if (sqlType1 == Types.NULL) return null;
        if (sqlType2 == Types.NULL) return null;

        switch(sqlType1) {
            case Types.BIT:      return convertFromBooleanTo(sqlType2);

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return convertFromIntegerTo(sqlType2);
            case Types.BIGINT:   return convertFromLongTo(sqlType2);

            case Types.FLOAT:    return convertFromFloatTo(sqlType2);
            case Types.REAL:     return null;
            case Types.DOUBLE:   return convertFromDoubleTo(sqlType2);

            case Types.NUMERIC:  return null;
            case Types.DECIMAL:  return null;

            case Types.CHAR:
            case Types.VARCHAR:  return convertFromStringTo(sqlType2);;
        }
        return null;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static protected Function1Arg convertFromBooleanTo(int sqlType2) {
        switch(sqlType2) {
            case Types.CHAR:
            case Types.VARCHAR: return booleanToStringFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromStringTo(int sqlType2) {
        switch(sqlType2) {
            case Types.BIT: return stringToBooleanFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromIntegerTo(int sqlType2) {
        switch(sqlType2) {
            case Types.BIGINT:   return numberToLongFunction;

            case Types.NUMERIC:
            case Types.DECIMAL:  return numberToBigDecimalFunction;

            case Types.CHAR:
            case Types.VARCHAR:  return objectToStringFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromLongTo(int sqlType2) {
        switch(sqlType2) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return numberToIntegerFunction;

            case Types.NUMERIC:
            case Types.DECIMAL:  return numberToBigDecimalFunction;

            case Types.CHAR:
            case Types.VARCHAR:  return objectToStringFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromFloatTo(int sqlType2) {
        switch(sqlType2) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return numberToIntegerFunction;
            case Types.BIGINT:   return numberToLongFunction;

            case Types.NUMERIC:
            case Types.DECIMAL:  return numberToBigDecimalFunction;

            case Types.CHAR:
            case Types.VARCHAR:  return objectToStringFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromDoubleTo(int sqlType2) {
        switch(sqlType2) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return numberToIntegerFunction;
            case Types.BIGINT:   return numberToLongFunction;

            case Types.NUMERIC:
            case Types.DECIMAL:  return numberToBigDecimalFunction;

            case Types.CHAR:
            case Types.VARCHAR:  return objectToStringFunction;
        };
        return null;
    }

    static protected Function1Arg convertFromBigDecimalTo(int sqlType2) {
        switch(sqlType2) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:  return numberToIntegerFunction;
            case Types.BIGINT:   return numberToLongFunction;

            case Types.CHAR:
            case Types.VARCHAR:  return objectToStringFunction;
        };
        return null;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

   static public final Function1Arg booleanToStringFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            return ((Boolean) arg).booleanValue() ? "T" : "F";
        }};

    static public final Function1Arg stringToBooleanFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            return ((String) arg).equals("T") ? Boolean.TRUE : Boolean.FALSE;
        }};

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public final Function1Arg numberToBigDecimalFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof java.math.BigDecimal) return arg;
            return new java.math.BigDecimal(arg.toString());
        }};

    static public final Function1Arg numberToIntegerFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof Integer) return arg;
            return new Integer(((Number) arg).intValue());
        }};

    static public final Function1Arg numberToLongFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof Long) return arg;
            return new Long(((Number) arg).longValue());
        }};

    static public final Function1Arg numberToFloatFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof Float) return arg;
            return new Float(((Number) arg).floatValue());
        }};

    static public final Function1Arg numberToDoubleFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof Double) return arg;
            return new Double(((Number) arg).doubleValue());
        }};

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public final Function1Arg objectToStringFunction =
        new Function1Arg() {public Object valueWith(Object arg) {
            if (arg == null) return null;
            if (arg instanceof String) return arg;
            return arg.toString();
        }};

    //**********************************************************
    //**********************************************************
    //**********************************************************


    //**************************************
    //**************************************
    //**************************************

    private ConverterLib() {};

    //**************************************
    //**************************************
    //**************************************

    static protected PrintWriter ts = null;
    static protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed

    static public void setupTraceStream (PrintWriter traceStream) {
        setupTraceStream_traceLevel(traceStream, 1);
    }

    static public void setupTraceStream_traceLevel(PrintWriter theTraceStream, int theTraceLevel) {
        ts = theTraceStream;
        traceLevel = theTraceLevel;
    }
}
