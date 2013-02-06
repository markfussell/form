/*======================================================================
**
**  File: chimu/kernel/collections/IndexedCollection.java
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

//The next import is just for the converting method
import java.util.Vector;

/**
FixedIndexedCollections provide the ability to retrieve a value
for a specific integer index.  Indexes are zero-based, so they
range from 0..size()-1.  Enumerations and iterations through an
indexed collection are guaranteed to be traversed in sequential
order of the index.
**/

public interface IndexedCollection extends CollectionConcepts {


}


