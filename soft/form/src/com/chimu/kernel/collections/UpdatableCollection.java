/*======================================================================
**
**  File: chimu/kernel/collections/UpdatableCollection.java
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
An UpdatableCollection allows elements to be replaced but not added or removed.

@see ExtensibleCollection
**/


public interface UpdatableCollection extends CollectionConcepts {


/**
 * Replace an occurrence of oldElement with newElement.
 * No effect if does not hold oldElement or if oldElement.equals(newElement).
 * The operation has a consistent, but slightly special interpretation
 * when applied to Sets. For Sets, because elements occur at
 * most once, if newElement is already included, replacing oldElement with
 * with newElement has the same effect as just removing oldElement.
 * @return condition:
 * <PRE>
 * let int delta = oldElement.equals(newElement)? 0 :
 *               max(1, PREV(this).occurrencesOf(oldElement) in
 *  occurrencesOf(oldElement) == PREV(this).occurrencesOf(oldElement) - delta &&
 *  occurrencesOf(newElement) ==  (this instanceof Set) ?
 *         max(1, PREV(this).occurrencesOf(oldElement) + delta):
 *                PREV(this).occurrencesOf(oldElement) + delta) &&
 *  no other element changes &&
 *  Version change iff delta != 0
 * </PRE>
 * @exception IllegalElementException if includes(oldElement) and !canInclude(newElement)
**/

  public void replace_with(Object oldElement, Object newElement)
                   throws IllegalElementException;

/**
 * Replace all occurrences of oldElement with newElement.
 * No effect if does not hold oldElement or if oldElement.equals(newElement).
 * The operation has a consistent, but slightly special interpretation
 * when applied to Sets. For Sets, because elements occur at
 * most once, if newElement is already included, replacing oldElement with
 * with newElement has the same effect as just removing oldElement.
 * @return condition:
 * <PRE>
 * let int delta = oldElement.equals(newElement)? 0 :
                   PREV(this).occurrencesOf(oldElement) in
 *  occurrencesOf(oldElement) == PREV(this).occurrencesOf(oldElement) - delta &&
 *  occurrencesOf(newElement) ==  (this instanceof Set) ?
 *         max(1, PREV(this).occurrencesOf(oldElement) + delta):
 *                PREV(this).occurrencesOf(oldElement) + delta) &&
 *  no other element changes &&
 *  Version change iff delta != 0
 * </PRE>
 * @exception IllegalElementException if includes(oldElement) and !canInclude(newElement)
**/

  public void replaceEvery_with(Object oldElement, Object newElement)
                throws IllegalElementException;

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

};

