/*======================================================================
**
**  File: chimu/kernel/exceptions/RuntimeWrappedException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;

/**
A RuntimeWrappedException is a WrappedException but is a subclass of RuntimeException,
so does not have to be explicitly declared and caught.
@see WrappedException
**/
public class RuntimeWrappedException extends RuntimeException implements ThrowableWrapper {
    public RuntimeWrappedException(String detail) {
	    super(detail);
    }

    public RuntimeWrappedException(String detail, Throwable wrappedThrowable) {
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

    	String result = "";
    	if (wrappedThrowable != null) {
    	    result = wrappedThrowable.toString() + " <caused> ";
    	};
    	result += s;
    	if (message != null) {
    	    result += ": " + message;
    	};
    	return result;
    }

    public Throwable wrappedThrowable() {
        return wrappedThrowable;
    }

    public void printStackTrace() {
        if (wrappedThrowable != null) {
//            s.println(this.toString());
            wrappedThrowable.printStackTrace();
        }
        super.printStackTrace();
    }

    public void printStackTrace(java.io.PrintStream s) {
        if (wrappedThrowable != null) {
//            s.println(this.toString());
            wrappedThrowable.printStackTrace(s);
        }
        super.printStackTrace(s);
    }

    public void printStackTrace(java.io.PrintWriter s) {
        if (wrappedThrowable != null) {
//            s.println(this.toString());
            wrappedThrowable.printStackTrace(s);
        }
        super.printStackTrace(s);
    }

    protected Throwable wrappedThrowable = null;
}

