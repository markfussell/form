/*======================================================================
**
**  File: chimu/kernel/exceptions/WrappedException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;

/**
A WrappedException is an exception that may have been caused by another
exception, so it supports the ThrowableWrapper interface.
@see RuntimeWrappedException
**/
public class WrappedException extends Exception implements ThrowableWrapper {
    public WrappedException(String detail) {
	    super(detail);
    }

    public WrappedException(String detail, Throwable wrappedThrowable) {
	    super(detail);
	    this.wrappedThrowable = wrappedThrowable;
    }

        /**
         * Returns a short description of this throwable object.
         *
         * @return  a string representation of this <code>Throwable</code>.
         * @since   JDK1.0
         */
    public String toString() {
    	String s = getClass().getName();
    	String message = getMessage();
    	if (wrappedThrowable != null) {
    	    return (message != null) ? (s + ": " + message + " <caused by:> "+ wrappedThrowable) : s;
    	} else {
    	    return (message != null) ? (s + ": " + message) : s;
    	};
    }

    public Throwable wrappedThrowable() {
        return wrappedThrowable;
    }

    public void printStackTrace(java.io.PrintStream s) {
        if (wrappedThrowable != null) wrappedThrowable.printStackTrace(s);
        s.println("<caused>");
        super.printStackTrace(s);
    }
    public void printStackTrace(java.io.PrintWriter s) {
        if (wrappedThrowable != null) wrappedThrowable.printStackTrace(s);
        s.println("<caused>");
        super.printStackTrace(s);
    }

    protected Throwable wrappedThrowable = null;
}

