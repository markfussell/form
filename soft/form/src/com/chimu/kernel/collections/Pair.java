/*======================================================================
**
**  File: chimu/kernel/collections/Pair.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
**  Portions of these collection classes were originally written by
**  Doug Lea (dl@cs.oswego.edu) and released into the public domain.
**  Doug Lea thanks the assistance and support of Sun Microsystems Labs,
**  Agorics Inc, Loral, and everyone contributing, testing, and using
**  this code.
**      ChiMu Corporation thanks Doug Lea and all of the above.
**
======================================================================*/

package com.chimu.kernel.collections;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 *
 *
 * Pair extends Keyed to serve as an interface for pairs of keyed elements
 * @author Doug Lea
 * @version 0.93
 *
 * <P> For an introduction to this package see <A HREF="index.html"> Overview </A>.
**/

public interface Pair { // extends Keyed {
        /**
         * Return an object serving as a comparison key
        **/

    public Object  key();

        /**
         * Return an object serving as an element value
        **/
    public Object  element();
}

