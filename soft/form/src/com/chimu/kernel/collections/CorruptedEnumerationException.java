/*======================================================================
**
**  File: chimu/kernel/collections/CorruptedEnumerationException.java
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
 * CorruptedEnumerationException is thrown by Enumeration
 * nextElement if a versioning inconsistency is detected in the process
 * of returning the next element
 * @author Doug Lea
 * @version 0.93
 *
 * <P> For an introduction to this package see <A HREF="index.html"> Overview </A>.
**/

public class CorruptedEnumerationException extends NoSuchElementException {

/**
 * The collection that this is an enumeration of
**/

 public Collection collection;

/**
 * The version expected of the collection
**/
 public int oldVersion;

/**
 * The current version of the collection
**/

 public int newVersion;

 public CorruptedEnumerationException() { super(); }

 public CorruptedEnumerationException(int oldv, int newv, Collection coll, String msg) {
   super(msg);
   oldVersion = oldv;
   newVersion = newv;
   collection = coll;
 }

}

