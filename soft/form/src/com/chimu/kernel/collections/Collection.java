/*======================================================================
**
**  File: chimu/kernel/collections/Collection.java
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
import com.chimu.jdk12.java.util.Iterator;
import com.chimu.kernel.functors.*;

/**
Collection is the base abstraction of a "collection of objects".  
**/
public interface Collection extends CollectionBase, com.chimu.jdk12.java.util.Collection { // extends ImplementationCheckable, Cloneable {

        /**
        public version of java.lang.Object.clone
        All Collections implement clone. But this is a protected method.
        Copy allows public access.
        @see clone
        **/

    public Object       copy();

        /**
         * Convert the elements in the collection to a JavaArray
         */
    // public Object[] toJavaArray();
    // toOrderedCollection();

        /**
         * Report the number of elements in the collection.
         * No other spurious effects.
         * @return number of elements
        **/
    public int size();

        /**
         * Report whether this collection has no elements.
         * Behaviorally equivalent to <CODE>size() == 0</CODE>.
         * @return true iff size() == 0
        **/
    public boolean isEmpty();

        /**
         * Returns true if this Collection contains the specified element.  More
         * formally, returns true if and only if this Collection contains at least
         * one element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.
         *
         * @param o element whose presence in this Collection is to be tested.
         * @since JDK1.2
         */
    public boolean contains(Object o);

        /**
         * Returns an Iterator over the elements in this Collection.  There are
         * no guarantees concerning the order in which the elements are returned
         * (unless this Collection is an instance of some class that provides a
         * guarantee).
         *
         * @since JDK1.2
         */
    public Iterator iterator();

        /**
         * Returns an array containing all of the elements in this Collection.  If
         * the Collection makes any guarantees as to what order its elements are
         * returned by its Iterator, this method must return the elements in the
         * same order.  The returned array will be "safe" in that no references to
         * it are maintained by the Collection.  (In other words, this method must
         * allocate a new array even if the Collection is backed by an Array).
         * The caller is thus free to modify the returned array.
         * <p>
         * This method acts as bridge between array-based and Collection-based
         * APIs.
         *
         * @since JDK1.2
         */
    Object[] toArray();


    public Object      any();

        /**
         * Report whether the collection COULD contain element,
         * i.e., that it is valid with respect to the Collection's
         * element screener if it has one.
         * Always returns false if element == null.
         * A constant function: if canInclude(v) is ever true it is always true.
         * (This property is not in any way enforced however.)
         * No other spurious effects.
         * @return true if non-null and passes element screener check
        **/
    public boolean     canInclude(Object element);


        /**
         * Report the number of occurrences of element in collection.
         * Always returns 0 if element == null.
         * Otherwise Object.equals is used to test for equality.
         * @param element the element to look for
         * @return the number of occurrences (always nonnegative)
        **/
    public int         occurrencesOf(Object element);

        /**
         * Report whether the collection contains element.
         * Behaviorally equivalent to <CODE>occurrencesOf(element) &gt;= 0</CODE>.
         * @param element the element to look for
         * @return true iff contains at least one member that is equal to element.
        **/
    public boolean     includes(Object element);

//**************************************************************
//**************************************************************
//**************************************************************


/*
  public void forEachPerform(Procedure1Arg procedure);
*/

    public Object detect(Predicate1Arg predicate);

    public Object inject_into(Object initialValue, Function2Arg function);

    public boolean canSatisfy(Predicate1Arg predicate);

        /**
         * Return an enumeration that may be used to traverse through
         * the elements in the collection. Standard usage, for some
         * collection c, and some operation `use(Object obj)':
         * <PRE>
         * for (Enumeration e = c.elements(); e.hasMoreElements(); )
         *   use(e.nextElement());
         * </PRE>
         * (The values of nextElement very often need to
         * be coerced to types that you know they are.)
         * <P>
         * All Collections return instances
         * of Enumeration, that can report the number of remaining
         * elements, and also perform consistency checks so that
         * for UpdatableCollections, element enumerations may become
         * invalidated if the collection is modified during such a traversal
         * (which could in turn cause random effects on the collection.
         * TO prevent this,  Enumerations
         * raise CorruptedEnumerationException on attempts to access
         * nextElements of altered Collections.)
         * Note: Since all collection implementations are synchronizable,
         * you may be able to guarantee that element traversals will not be
         * corrupted by using the java <CODE>synchronized</CODE> construct
         * around code blocks that do traversals. (Use with care though,
         * since such constructs can cause deadlock.)
         * <P>
         * Guarantees about the nature of the elements returned by  nextElement of the
         * returned Enumeration may vary accross sub-interfaces.
         * In all cases, the enumerations provided by elements() are guaranteed to
         * step through (via nextElement) ALL elements in the collection.
         * Unless guaranteed otherwise (for example in Seq), elements() enumerations
         * need not have any particular nextElement() ordering so long as they
         * allow traversal of all of the elements. So, for example, two successive
         * calls to element() may produce enumerations with the same
         * elements but different nextElement() orderings.
         * Again, sub-interfaces may provide stronger guarantees. In
         * particular, Seqs produce enumerations with nextElements in
         * index order, ElementSortedCollections enumerations are in ascending
         * sorted order, and KeySortedCollections are in ascending order of keys.
         * @return an enumeration e such that
         * <PRE>
         *   e.numberOfRemainingElements() == size() &&
         *   foreach (v in e) includes(e)
         * </PRE>
        **/

    public Enumeration elements();

        /**
         * Report whether other has the same element structure as this.
         * That is, whether other is of the same size, and has the same
         * elements() properties.
         * This is a useful version of equality testing. But is not named
         * `equals' in part because it may not be the version you need.
         * <P>
         * The easiest way to decribe this operation is just to
         * explain how it is interpreted in standard sub-interfaces:
         * <UL>
         *  <LI> Seq and ElementSortedCollection: other.elements() has the
         *        same order as this.elements().
         *  <LI> Bag: other.elements has the same occurrencesOf each element as this.
         *  <LI> Set: other.elements includes all elements of this
         *  <LI> Map: other includes all (key, element) pairs of this.
         *  <LI> KeySortedCollection: other includes all (key, element)
         *       pairs as this, and with keys enumerated in the same order as
         *       this.keys().
         *</UL>
         * @param other, a Collection
         * @return true if considered to have the same size and elements.
        **/

    public boolean sameStructure(Collection other);

    public Collection copyEmpty();
    public Collection copyCollect(Function1Arg transform);
    public Collection copySelect(Predicate1Arg select);
    public Collection copyReject(Predicate1Arg reject);

        /**
         * Construct a new Collection that is a clone of self except
         * that it does not include any occurrences of the indicated element.
         * It is NOT an error to exclude a non-existent element.
         *
         * @param element the element to exclude from the new collection
         * @return a new Collection, c, with the sameStructure as this
         * except that !c.includes(element).
        **/
    public Collection copyExcluding(Object element);


        /**
         * Construct a new Collection that is a clone of self except
         * that it does not include an occurrence of the indicated element.
         * It is NOT an error to remove a non-existent element.
         *
         * @param element the element to exclude from the new collection
         * @return a new Collection, c, with the sameStructure as this
         * except that c.occurrencesOf(element) == max(0,occurrencesOf(element)-1)
        **/
    public Collection  copyRemovingOneOf(Object element);

        /**
         * Construct a new Collection that is a clone of self except
         * that one occurrence of oldElement is replaced with
         * newElement.
         * It is NOT an error to replace a non-existent element.
         *
         * @param oldElement the element to replace
         * @param newElement the replacement
         * @return a new Collection, c, with the sameStructure as this, except:
         * <PRE>
         * let int delta = oldElement.equals(newElement)? 0 :
         *               max(1, this.occurrencesOf(oldElement) in
         *  c.occurrencesOf(oldElement) == this.occurrencesOf(oldElement) - delta &&
         *  c.occurrencesOf(newElement) ==  (this instanceof Set) ?
         *         max(1, this.occurrencesOf(oldElement) + delta):
         *                this.occurrencesOf(oldElement) + delta) &&
         * </PRE>
         * @exception IllegalElementException if includes(oldElement) and !canInclude(newElement)
        **/
    public Collection  copyReplacingOneOf(Object oldElement, Object newElement)
                       throws IllegalElementException;

        /**
         * Construct a new Collection that is a clone of self except
         * that all occurrences of oldElement are replaced with
         * newElement.
         * It is NOT an error to convert a non-existent element.
         *
         * @param oldElement the element to replace
         * @param newElement the replacement
         * @return a new Collection, c, with the sameStructure as this except
         * <PRE>
         * let int delta = oldElement.equals(newElement)? 0 :
                           occurrencesOf(oldElement) in
         *  c.occurrencesOf(oldElement) == this.occurrencesOf(oldElement) - delta &&
         *  c.occurrencesOf(newElement) ==  (this instanceof Set) ?
         *         max(1, this.occurrencesOf(oldElement) + delta):
         *                this.occurrencesOf(oldElement) + delta)
         * </PRE>
         * @exception IllegalElementException if includes(oldElement) and !canInclude(newElement)
        **/

    public Collection  copyReplacingAllOf(Object oldElement, Object newElement)
                       throws IllegalElementException;



// =====================================

        /**
         * Construct a new Collection that is a clone of self except
         * that it includes indicated element. This can be used
         * to create a series of Collections, each differing from the
         * other only in that they contain additional elements.
         *
         * @param the element to add to the new Bag
         * @return the new Collection c, with the sameStructure as this except that
         * c.occurrencesOf(element) == occurrencesOf(element)+1
         * @exception IllegalElementException if !canInclude(element)
        **/
    public Collection   copyAdding(Object element)
                       throws IllegalElementException;

        /**
         * Construct a new Collection that is a clone of self except
         * that it adds the indicated element if not already present. This can be used
         * to create a series of collections, each differing from the
         * other only in that they contain additional elements.
         *
         * @param element the element to include in the new collection
         * @return a new collection c, with the sameStructure as this, except that
         * c.occurrencesOf(element) = min(1, occurrencesOfElement)
         * @exception IllegalElementException if !canInclude(element)
        **/
    public Collection  copyAddingIfAbsent(Object element)
                       throws IllegalElementException;


//**************************************************************
//**************************************************************
//**************************************************************

        /**
         * Cause the collection to become empty.
         * @return condition:
         * <PRE>
         * isEmpty() &&
         * Version change iff !PREV(this).isEmpty();
         * </PRE>
        **/
    public void clear();
    public void removeAll();
   
        /**
         * Removes from this Collection all of its elements that are contained in
         * the specified Collection (optional operation).  After this call returns,
         * this Collection will contains no elements in common with the specified
         * Collection.
         *
         * @param c elements to be removed from this Collection.
         * @return true if this Collection changed as a result of the call.
         * @exception UnsupportedOperationException removeAll is not supported
         *                   by this Collection.
         * @see #remove(Object)
         * @see #contains(Object)
         * @since JDK1.2
         */
    public boolean removeAll(Collection c);

        /**
         * Ensures that this Collection contains the specified element (optional
         * operation).  Returns true if the Collection changed as a result of the
         * call.  (Returns false if this Collection does not permit duplicates and
         * already contains the specified element.)  Collections that support this
         * operation may place limitations on what elements may be added to the
         * Collection.  In particular, some Collections will refuse to add null
         * elements, and others will impose restrictions on the type of elements
         * that may be added.  Collection classes should clearly specify in their
         * documentation any restrictions on what elements may be added.
         *
         * @param o element whose presence in this Collection is to be ensured.
         * @return true if the Collection changed as a result of the call.
         * @exception UnsupportedOperationException add is not supported by this
         *                  Collection.
         * @exception ClassCastException class of the specified element
         *                   prevents it from being added to this Collection.
         * @exception IllegalArgumentException some aspect of this element prevents
         *                  it from being added to this Collection.
         * @since JDK1.2
         */
    public boolean add(Object o);

        /**
         * Adds all of the elements in the specified Collection to this Collection
         * (optional operation).  The behavior of this operation is undefined if
         * the specified Collection is modified while the operation is in progress.
         * (This implies that the behavior of this call is undefined if the the
         * specified Collection is this Collection, and this Collection is
         * nonempty.) 
         *
         * @param c elements to be inserted into this Collection.
         * @return true if this Collection changed as a result of the call.
         * @exception UnsupportedOperationException addAll is not supported
         *                   by this Collection.
         * @exception ClassCastException class of an element of the specified
         *                   Collection prevents it from being added to this Collection.
         * @exception IllegalArgumentException some aspect of an element of
         *                  the specified Collection prevents it from being added to
         *                  this Collection. 
         * @see #add(Object)
         * @since JDK1.2
         */
    public boolean addAll(Collection c);

        /**
         * Add an occurrence of the indicated element if it
         * is not already present in the collection.
         * No effect if the element is already present.
         * @param element the element to add
         * @return condition:
         * <PRE>
         * occurrencesOf(element) == min(1, PREV(this).occurrencesOf(element) &&
         * no spurious effects &&
         * Version change iff !PREV(this).includes(element)
         * </PRE>
         * @exception IllegalElementException if !canInclude(element)
        **/
    public void addIfAbsent(Object element) throws IllegalElementException;
    public void addIfAbsentAll(Collection elements) throws IllegalElementException;
    public void addIfAbsentElements(Enumeration e)
       throws IllegalElementException, CorruptedEnumerationException;

        /**
         * Add all elements of the enumeration to the collection.
         * Behaviorally equivalent to
         * <PRE>
         * while (e.hasMoreElements()) add(e.nextElement());
         * </PRE>
         * @param e the elements to include
         * @exception IllegalElementException if !canInclude(element)
         * @exception CorruptedEnumerationException propagated if thrown
        **/
    public void addElements(Enumeration e)
        throws IllegalElementException, CorruptedEnumerationException;


        /**
         * Removes a single instance of the specified element from this Collection,
         * if it is present (optional operation).  More formally, removes an
         * element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>, if the Collection contains one or more such
         * elements.  Returns true if the Collection contained the specified
         * element (or equivalently, if the Collection changed as a result of the
         * call).
         *
         * @param o element to be removed from this Collection, if present.
         * @return true if the Collection changed as a result of the call.
         * @exception UnsupportedOperationException remove is not supported
         *                  by this Collection.
         * @since JDK1.2
         */
    public boolean remove(Object o);

        /**
         * Exclude all occurrences of the indicated element from the collection.
         * No effect if element not present.
         * @param element the element to exclude.
         * @return condition:
         * <PRE>
         * !includes(element) &&
         * size() == PREV(this).size() - PREV(this).occurrencesOf(element) &&
         * no other element changes &&
         * Version change iff PREV(this).includes(element)
         * </PRE>
        **/
    public boolean removeEvery(Object element);

        /**
         * Remove and return an element.  Implementations
         * may strengthen the guarantee about the nature of this element.
         * but in general it is the most convenient or efficient element to remove.
         * <P>
         * Example usage. One way to transfer all elements from
         * UpdatableCollection a to UpdatableBag b is:
         * <PRE>
         * while (!a.empty()) b.add(a.take());
         * </PRE>
         * @return an element v such that PREV(this).includes(v)
         * and the postconditions of removeOneOf(v) hold.
         * @exception NoSuchElementException iff isEmpty.
        **/
    public Object takeAny();


        /**
         * Exclude all occurrences of each element of the Enumeration.
         * Behaviorally equivalent to
         * <PRE>
         * while (e.hasMoreElements()) exclude(e.nextElement());
         * </PRE>
         * @param e the enumeration of elements to exclude.
         * @exception CorruptedEnumerationException is propagated if thrown
        **/
    public void excludeElements(Enumeration e)
        throws CorruptedEnumerationException;

        /**
         * Remove all occurrences of each element of the Enumeration.
         * Behaviorally equivalent to
         * <PRE>
         * while (e.hasMoreElements()) removeEvery(e.nextElement());
         * </PRE>
         * @param e the enumeration of elements to remove.
         * @exception CorruptedEnumerationException is propagated if thrown
        **/
    public boolean removeEveryElements(Enumeration e)
        throws CorruptedEnumerationException;


        /**
         * Remove an occurrence of each element of the Enumeration.
         * Behaviorally equivalent to
         * <PRE>
         * while (e.hasMoreElements()) remove(e.nextElement());
         * </PRE>
         * @param e the enumeration of elements to remove.
         * @exception CorruptedEnumerationException is propagated if thrown
        **/
    public boolean removeElements(Enumeration e)
        throws CorruptedEnumerationException;



//**************************************************************

        /**
         * Retains only the elements in this Collection that are contained in the
         * specified Collection (optional operation).  In other words, removes from
         * this Collection all of its elements that are not contained in the
         * specified Collection. 
         *
         * @param c elements to be retained in this Collection.
         * @return true if this Collection changed as a result of the call.
         * @exception UnsupportedOperationException retainAll is not supported
         *                   by this Collection.
         * @see #remove(Object)
         * @see #contains(Object)
         * @since JDK1.2
         */
    boolean retainAll(Collection c);

        /**
         * Compares the specified Object with this Collection for equality.
         * <p>
         * While Collection adds no stipulations to the general contract for
         * Object.equals, programmers who implement Collection "directly" (in
         * other words, create a class that is a Collection but is not a Set or a
         * List) must exercise care if they choose to override Object.equals.  It
         * is not necessary to do so, and the simplest course of action is to rely
         * on Object's implementation, but the implementer may wish to implement
         * a "value comparison" in place of the default "reference comparison."
         * (Lists and Sets mandate such value comparisons.)
         * <p>
         * The general contract for Object.equals states that equals
         * must be reflexive (in other words, <code>a.equals(b)</code> if and only
         * if <code>b.equals(a)</code>).  The contracts for List.equals and
         * Set.equals state that Lists are only equal to other Lists, and
         * Sets to other Sets.  Thus, a custom equals method for a Collection
         * that is neither a List nor a Set must return false when this
         * Collection is compared to any List or Set.
         *
         * @param o Object to be compared for equality with this Collection.
         * @return true if the specified Object is equal to this Collection.
         * @see Object#equals(Object)
         * @see Set#equals(Object)
         * @see List#equals(Object)
         * @since JDK1.2
         */
    boolean equals(Object o);

        /**
         * Returns the hash code value for this Collection.  While Collection
         * adds no stipulations to the general contract for Object.hashCode,
         * programmers should take note that any class that overrides
         * Object.equals must also override Object.hashCode, in order to satisfy
         * the general contract for Object.hashCode.  In particular,
         * <code>c1.equals(c2)</code> implies that
         * <code>c1.hashCode()==c2.hashCode()</code>.
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @since JDK1.2
         */
    int hashCode();

}
