/*======================================================================
**
**  File: chimu/kernel/utilities/TranslationLib.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.utilities;

import com.chimu.kernel.functors.*;

/**
TranslationLib provides Functors and other services useful for translating
among the datatypes in Java and in Kernel.
**/
public class TranslationLib {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public Function1Arg identityFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            return arg1;
        }};
    }

    //**********************************************************
    //(P)*********************  Numeric ************************
    //**********************************************************

        /**
         * Create a Function1Arg (Functor) that given a java.lang.Number
         * object (or subclass) as its parameter returns a java.math.BigDecimal
         * object using the doubleValue of the number.
         */
    static public Function1Arg numberToBigDecimalFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1 instanceof java.math.BigDecimal) return arg1;
            return new java.math.BigDecimal(arg1.toString());
        }};
    }

//The old, right, but wrong method because Java BigDecimal has a bug
/*
    static public Function1Arg numberToBigDecimalFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            return new java.math.BigDecimal(((java.lang.Number) arg1).doubleValue());
        }};
    }
*/

        /**
         * Create a Function1Arg (Functor) that given a java.lang.Number
         * object (or subclass) as its parameter returns a java.lang.Double
         * object using the doubleValue of the number.
         */
    static public Function1Arg numberToDoubleFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1 instanceof Double) return arg1;
            return new Double(((java.lang.Number) arg1).doubleValue());
        }};
    }

    static public Function1Arg numberToIntegerFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1 instanceof Integer) return arg1;
            return new Integer(((java.lang.Number) arg1).intValue());
        }};
    }


    //**********************************************************
    //(P)********************* Database ************************
    //**********************************************************

        /**
         * Create a Function1Arg (Functor) that given a java.util.Date
         * object (or subclass) as its parameter returns a java.sql.Timestamp
         * object with the same time
         */
    static public Function1Arg dateToSqlTimestampFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1.getClass() == java.sql.Timestamp.class) return arg1;
            return new java.sql.Timestamp(((java.util.Date) arg1).getTime());
        }};
    }

        /**
         * Create a Function1Arg (Functor) that given a java.util.Date
         * object (or subclass) as its parameter returns a java.sql.Date
         * object with the same time
         */
    static public Function1Arg dateToSqlDateFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1.getClass() == java.sql.Date.class) return arg1;
            return new java.sql.Date(((java.util.Date) arg1).getTime());
        }};
    }

        /**
         * Create a Function1Arg (Functor) that given a java.util.Date
         * object (or subclass) as its parameter returns a java.sql.Time
         * object with the same time
         */
    static public Function1Arg dateToSqlTimeFunction() {
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) return null;
            if (arg1.getClass() == java.sql.Time.class) return arg1;
            return new java.sql.Time(((java.util.Date) arg1).getTime());
        }};
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    private TranslationLib() {};

}
