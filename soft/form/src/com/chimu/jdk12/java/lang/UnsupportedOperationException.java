/*======================================================================
**
**  File: chimu/jdk12/java/lang/UnsupportedOperationException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.lang;

/**
 * Thrown by an Object to indicate that it does not support the
 * requested operation.
 *
 * @author  Josh Bloch
 * @version 1.4 09/23/97
 * @since   JDK1.2
 */
public class UnsupportedOperationException extends RuntimeException {
    /**
     * Constructs an UnsupportedOperationException with no detail message.
     */
    public UnsupportedOperationException() {
    }

    /**
     * Constructs an UnsupportedOperationException with the specified
     * detail message.
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
