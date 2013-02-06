/*======================================================================
**
**  File: chimu/kernel/collections/ExtensibleCollection.java
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
An ExtensibleCollection can have elements added and removed from it.
**/

public interface ExtensibleCollection extends CollectionConcepts  {


    /**
 * All updatable collections maintain a `version number'. The numbering
 * scheme is arbitrary, but is guaranteed to change upon every
 * modification that could possibly affect an elements() enumeration traversal.
 * (This is true at least within the precision of the `int' representation;
 * performing more than 2^32 operations will lead to reuse of version numbers).
 * Versioning
 * <EM>may</EM> be conservative with respect to `replacement' operations.
 * For the sake of versioning replacements may be considered as
 * removals followed by additions. Thus version numbers may change
 * even if the old and new  elements are identical.
 * <P>
 * All element() enumerations for Updatable Collections track version
 * numbers, and raise inconsistency exceptions if the enumeration is
 * used (via nextElement()) on a version other than the one generated
 * by the elements() method.
 * <P>
 * You can use versions to check if update operations actually have any effect
 * on observable state.
 * For example, clear() will cause cause a version change only
 * if the collection was previously non-empty.
 * @return the version number
**/

  public int         version();


}

