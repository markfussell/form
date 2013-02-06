/*======================================================================
**
**  File: chimu/form/database/support/SqlBuilderLib.java
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

import java.math.*;
import java.util.*;

/**
Package:
Constructors?  No can just use general query capability.
Types? Does it matter? On happens at compile time anyway.

**/


public class SqlBuilderLib {


    static public String sqlStringFor_javaType(Object value, int javaType) {
        if (value == null) return sqlStringForNull();

        switch (javaType) {
            case TypeConstants.TYPE_Object:         return null;               //Can't be sure how to convert
            case TypeConstants.TYPE_Boolean:        return sqlStringForBoolean((Boolean) value);       //Don't know the conversion yet.
            case TypeConstants.TYPE_Character:      return sqlStringForCharacter((Character) value);
            case TypeConstants.TYPE_Byte:           return value.toString();
            case TypeConstants.TYPE_Short:          return value.toString();
            case TypeConstants.TYPE_Integer:        return value.toString();
            case TypeConstants.TYPE_Long:           return value.toString();
            case TypeConstants.TYPE_Float:          return value.toString();
            case TypeConstants.TYPE_Double:         return value.toString();

            case TypeConstants.TYPE_String:         return sqlStringForString((String) value);

            case TypeConstants.TYPE_java_math_BigDecimal:   return value.toString();

            case TypeConstants.TYPE_java_util_Date:       return sqlStringForDate((java.util.Date) value);

            case TypeConstants.TYPE_java_sql_Date:        return sqlStringForDate((java.util.Date) value);
            case TypeConstants.TYPE_java_sql_Time:        return sqlStringForTime((java.util.Date) value);
            case TypeConstants.TYPE_java_sql_Timestamp:   return sqlStringForTimestampWithFractional((java.util.Date) value);
        }
        return null;
    }

    static public String sqlStringForBoundValue() {
        return "?";
    }

    static public String sqlStringForNull() {
        return "NULL";
    }

    static public String sqlStringForObject(Object value) {
        if (value == null) return sqlStringForNull();
        return "?";
        //return value.toString();
    }

    static public String sqlStringForBoolean(Boolean value) {
        if (value == null) return sqlStringForNull();
        if (value.booleanValue()) {
            return "1";
        } else {
            return "0";
        }
    }

    //********************

        static public String sqlStringForByte(Byte value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForShort(Short value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForInteger(Integer value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForLong(Long value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForFloat(Float value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForDouble(Double value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForBigDecimal(BigDecimal value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

        static public String sqlStringForBigInteger(BigInteger value) {
            if (value == null) return sqlStringForNull();
            return value.toString();
        }

    //*************************************************

    static public String sqlStringForNumber(Number value) {
        if (value == null) return sqlStringForNull();
        return value.toString();
    }

    static public String sqlStringForString(String value) {
        if (value == null) return sqlStringForNull();
        if (value.indexOf('\'') == -1) return "'"+value+"'";
        return "'"+quoteString(value)+"'";
    }

    static protected String quoteString(String value) {
        StringBuffer stringB = new StringBuffer();
        int startIndex = 0;
        int position = value.indexOf('\'',startIndex);
        while (position != -1) {
            stringB.append(value.substring(startIndex,position+1));
            stringB.append('\'');
            startIndex = position+1;
            position = value.indexOf('\'',startIndex);
        };
        stringB.append(value.substring(startIndex));
        return stringB.toString();
    }

    static public String sqlStringForCharacter(Character value) {
        if (value == null) return sqlStringForNull();
        return "'"+value+"'";
    }

    static protected String preSqlStringForDate(java.util.Date value) {
            //Lifted from SQL Date
    	int year = value.getYear() + 1900;
    	int month = value.getMonth() + 1;
    	int day = value.getDate();
    	String yearString;
    	String monthString;
    	String dayString;


    	yearString = Integer.toString(year);

    	if (month < 10) {
    	    monthString = "0" + month;
    	} else {
    	    monthString = Integer.toString(month);
    	}

    	if (day < 10) {
    	    dayString = "0" + day;
    	} else {
    	    dayString = Integer.toString(day);
    	}
    	return (yearString+"-"+monthString+"-"+dayString);

    }

    static public String sqlStringForDate(java.util.Date value) {
                //{d `yyyy-mm-dd'}
        if (value == null) return sqlStringForNull();
    	return ("{d '"+ preSqlStringForDate(value) + "'}");
    }

    static protected String preSqlStringForTime(java.util.Date value) {
    	int hour = value.getHours();
    	int minute = value.getMinutes();
    	int second = value.getSeconds();
    	String hourString;
    	String minuteString;
    	String secondString;

    	if (hour < 10) {
    	    hourString = "0" + hour;
    	} else {
    	    hourString = Integer.toString(hour);
    	}
    	if (minute < 10) {
    	    minuteString = "0" + minute;
    	} else {
    	    minuteString = Integer.toString(minute);
    	}
    	if (second < 10) {
    	    secondString = "0" + second;
    	} else {
    	    secondString = Integer.toString(second);
    	}
    	return (hourString+":"+minuteString+":"+secondString);

    }

    static public String sqlStringForTime(java.util.Date value) {
                //{t `hh:mm:ss'}
        if (value == null) return sqlStringForNull();
    	return ("{t '"+ preSqlStringForTime(value)+ "'}");
    }

    static public String sqlStringForTimestamp(java.util.Date value) {
                //{ts `yyyy-mm-dd hh:mm:ss'}
        if (value == null) return sqlStringForNull();
        return sqlStringForDate(value);
        //return "{ts '"+preSqlStringForDate(value)+" "+preSqlStringForTime(value)+"'";
    }

    static public String sqlStringForTimestampWithFractional(java.util.Date value) {
                //{ts `yyyy-mm-dd hh:mm:ss.f'}
        if (value == null) return sqlStringForNull();
        if (!(value instanceof Timestamp)) {
            return sqlStringForTimestamp(value);
        };

        int nanos = ((Timestamp) value).getNanos();

    	String zeros = "000000000";
    	String nanosString = null;

    	if (nanos == 0) {
    	    nanosString = "0";
    	} else {
    	    nanosString = Integer.toString(nanos);

    	    // Add leading zeros
    	    nanosString = zeros.substring(0,(9-nanosString.length())) +
    		nanosString;

    	    // Truncate trailing zeros
    	    char[] nanosChar = new char[nanosString.length()];
    	    nanosString.getChars(0, nanosString.length(), nanosChar, 0);
    	    int truncIndex = 8;
    	    while (nanosChar[truncIndex] == '0') {
        		truncIndex--;
    	    }
    	    nanosString = new String(nanosChar,0,truncIndex+1);
    	}

        return "{ts '"+preSqlStringForDate(value)+" "+preSqlStringForTime(value)+"."+nanosString+"'}";
    }



    //**************************************
    //**************************************
    //**************************************

    private SqlBuilderLib() {};

}
