/*======================================================================
**
**  File: chimu/kernel/collections/impl/CollectionAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;

import com.chimu.kernel.collections.*;
import java.util.Enumeration;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

public abstract class CollectionAbsC
                        extends com.chimu.jdk12.java.util.AbstractCollection
                        implements Collection {

    static boolean DEBUG = true;


    protected void notImplementedYet() {
        throw new NotImplementedYetException(getClass()+" does not implement this method yet");
    }

    public boolean canInclude(Object o) {if (DEBUG) notImplementedYet();return false;};


    public Collection copyAdding(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyAddingIfAbsent(Object o) {if (DEBUG) notImplementedYet();return null;};
    //public Collection copyCollect(Function1Arg transform) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyEmpty() {if (DEBUG) notImplementedYet();return null;};
    //public Collection copyExcluding(Object o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedArray copyKeys(Array keys) {if (DEBUG) notImplementedYet();return null;};
    //public Collection copyRemovingOneOf(Object o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyRemovingValue(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyReplacingAllOf(Object o, Object with) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyReplacingOneOf(Object o, Object with) {if (DEBUG) notImplementedYet();return null;};

    //public Object inject_into(Object o, Function2Arg function) {if (DEBUG) notImplementedYet();return null;};

    public boolean sameStructure(Collection o) {if (DEBUG) notImplementedYet();return false;};




    public int         version() {if (DEBUG) notImplementedYet();return 0;};

    //**********************************************************
    //(P)********************* Collection **********************
    //**********************************************************

    public Object any() {if (DEBUG) notImplementedYet();return null;};
    public Object copy() {if (DEBUG) notImplementedYet();return null;};

    public int size() {if (DEBUG) notImplementedYet();return 0;};;
    //public boolean isEmpty() {if (DEBUG) notImplementedYet();return false;};

    public Enumeration elements() {if (DEBUG) notImplementedYet();return null;};
    public boolean includes(Object o) {if (DEBUG) notImplementedYet();return false;};
    public int occurrencesOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    //public boolean canSatisfy(Predicate1Arg o) {if (DEBUG) notImplementedYet();return false;};
    //public Object detect(Predicate1Arg detect) {if (DEBUG) notImplementedYet();return null;};

    //public Collection copyReject(Predicate1Arg reject) {if (DEBUG) notImplementedYet();return null;};
    //public Collection copySelect(Predicate1Arg select) {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)******************* KeyedCollection *******************
    //**********************************************************

    public Object atKey(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Enumeration keys() {if (DEBUG) notImplementedYet();return null;};

    public Object aKeyFor(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Set allKeysFor(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Array atKeys(Array o) {if (DEBUG) notImplementedYet();return null;};
    public boolean canIncludeKey(Object o) {if (DEBUG) notImplementedYet();return false;};

    public KeyedCollection copyAddingPair(Pair o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyAtKey_put(Object key, Object value) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyRemovingKey(Object o) {if (DEBUG) notImplementedYet();return null;};

    public boolean includesKey(Object o) {if (DEBUG) notImplementedYet();if (DEBUG) notImplementedYet(); return false;};
    public boolean includesKey_withValue(Object key, Object value) {if (DEBUG) notImplementedYet();return false;};
    public boolean includesValue(Object o) {if (DEBUG) notImplementedYet();return false;};

    public java.util.Hashtable toJdkHashtable() {if (DEBUG) notImplementedYet();return null;};
    public java.util.Dictionary toJdkDictionary() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)******************* KeyedCollection *******************
    //**********************************************************

    public void atKey_put(Object o, Object value) {if (DEBUG) notImplementedYet();};
    public void updatePair(Pair o) {if (DEBUG) notImplementedYet();};
    public void replaceKey_value_with(Object o, Object v1, Object v2) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)*********************** Map ***************************
    //**********************************************************

    public void addKey_put(Object o, Object value) {if (DEBUG) notImplementedYet();};
    public void addPair(Pair o) {if (DEBUG) notImplementedYet();};
    public void removeKey(Object o) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)************************ Array ************************
    //**********************************************************

    public Object first() {if (DEBUG) notImplementedYet();return null;};
    public Object last() {if (DEBUG) notImplementedYet();return null;};

    public Object atIndex(int i) {if (DEBUG) notImplementedYet();return null;};

    public int firstIndexOf(Object o, int start) {if (DEBUG) notImplementedYet();return 0;};
    public int firstIndexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public int indexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public int lastIndexOf(Object o, int start) {if (DEBUG) notImplementedYet();return 0;};
    public int lastIndexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public List copyInsertingAtIndex_put(int i, Object o) {if (DEBUG) notImplementedYet();return null;};
    public List copyRemovingIndex(int i) {if (DEBUG) notImplementedYet();return null;};
    public List copyReplacingIndex_with(int index, Object o) {if (DEBUG) notImplementedYet();return null;};
    public List copySubseq(int start, int end) {if (DEBUG) notImplementedYet();return null;};

    public Object[]         toJavaArray() {if (DEBUG) notImplementedYet();return null;};
    public java.util.Vector toJdkVector() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)********************** Array **************************
    //**********************************************************

    public void atIndex_put(int i, Object value) {if (DEBUG) notImplementedYet();};
    public void atAllPut(Object o) {if (DEBUG) notImplementedYet();};
    public void atAllPutNull() {if (DEBUG) notImplementedYet();};
    public void swapAtIndex_withIndex(int index1, int index2)
            throws ArrayIndexOutOfBoundsException {if (DEBUG) notImplementedYet();};

    public void sort() {if (DEBUG) notImplementedYet();};
    public void sort(Predicate2Arg greaterThanPredicate) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)******************** Collection ***********************
    //**********************************************************

    //public void clear() {if (DEBUG) notImplementedYet();};
    //public void removeAll() {if (DEBUG) notImplementedYet();};

    //public boolean add(Object element) throws IllegalElementException {if (DEBUG) notImplementedYet(); return false;};
    //public void addAll(Collection elements) throws IllegalElementException {if (DEBUG) notImplementedYet();};

    //public void addIfAbsent(Object element) throws IllegalElementException {if (DEBUG) notImplementedYet();};
    //public void addIfAbsentAll(Collection elements) throws IllegalElementException {if (DEBUG) notImplementedYet();};
    //public void addIfAbsentElements(Enumeration e)
    //        throws IllegalElementException, CorruptedEnumerationException {if (DEBUG) notImplementedYet();};

    //public void addElements(Enumeration e)
    //        throws IllegalElementException, CorruptedEnumerationException {if (DEBUG) notImplementedYet();};

    //public boolean remove(Object element) {if (DEBUG) notImplementedYet(); return false;};
    //public boolean removeEvery(Object element) {if (DEBUG) notImplementedYet(); return false;};
    //public Object removeAny() throws NoSuchElementException {if (DEBUG) notImplementedYet();return null;};
    //public boolean removeElements(Enumeration e)
    //        throws CorruptedEnumerationException {if (DEBUG) notImplementedYet(); return false;};
    public void excludeElements(Enumeration e)
            throws CorruptedEnumerationException {if (DEBUG) notImplementedYet();};
    //public boolean removeEveryElements(Enumeration e)
    //        throws CorruptedEnumerationException {if (DEBUG) notImplementedYet(); return false;};

    //**********************************************************
    //(P)******************** List *************************
    //**********************************************************

    public void atIndex_insert(int index, Object value) {if (DEBUG) notImplementedYet();};



    //**********************************************************
    //**********************************************************
    //**********************************************************

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
         * <p>
         * This implementation always throws an UnsupportedOperationException.
         *
         * @param o element whose presence in this Collection is to be ensured.
         * @return true if the Collection changed as a result of the call.
         * @exception UnsupportedOperationException add is not supported by this
         *                  Collection.
         * @exception NullPointerException this Collection does not permit null
         *                   elements, and the specified element is null.
         * @exception ClassCastException class of the specified element
         *                   prevents it from being added to this Collection.
         * @exception IllegalArgumentException some aspect of this element prevents
         *                  it from being added to this Collection.
         * @since JDK1.2
         */
    public boolean add(Object o) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException(); //UnsupportedOperationException();
    }


        /**
         * Returns true if this Collection contains all of the elements in the
         * specified Collection.
         * <p>
         * This implementation iterates over the specified Collection, checking
         * each element returned by the Iterator in turn to see if it's
         * contained in this Collection.  If all elements are so contained
         * true is returned, otherwise false.
         *
         * @see #contains(Object)
         * @since JDK1.2
         */
    public boolean containsAll(Collection c) {
        Enumeration e = c.elements();
        while (e.hasMoreElements())
            if(!contains(e.nextElement()))
                return false;

        return true;
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean     isEmpty() {
        return this.size()==0;
    };


    public void forEachDo(Procedure1Arg procedure) {
        Enumeration enum = this.elements();
        while (enum.hasMoreElements()) {
            procedure.executeWith(enum.nextElement());
        };
    };

    public Collection copyEmptyExtensible() {
        throw new NotImplementedYetException();
    };

    public Collection copyCollect(Function1Arg function) {
        Enumeration enum = this.elements();
        Collection result = this.copyEmptyExtensible();
        while (enum.hasMoreElements()) {
            result.add(function.valueWith(enum.nextElement()));
        };
        return result;
    };

    public Collection copySelect(Predicate1Arg predicate) {
        Enumeration enum = this.elements();
        Collection result = this.copyEmptyExtensible();
        while (enum.hasMoreElements()) {
            Object value = enum.nextElement();
            if (predicate.isTrueWith(value)) {
                result.add(value);
            };
        };
        return result;
    };

    public Collection copyReject(Predicate1Arg predicate) {
        Enumeration enum = this.elements();
        Collection result = this.copyEmptyExtensible();
        while (enum.hasMoreElements()) {
            Object value = enum.nextElement();
            if (!predicate.isTrueWith(value)) {
                result.add(value);
            };
        };
        return result;
    };

    public Object detect(Predicate1Arg predicate) {
        Enumeration enum = this.elements();
        while (enum.hasMoreElements()) {
            Object value = enum.nextElement();
            if (predicate.isTrueWith(value)) {
                return value;
            };
        };
        return null;
    };

    public Object inject_into(Object initialValue, Function2Arg function) {
        Enumeration enum = this.elements();
        Object value = initialValue;
        while (enum.hasMoreElements()) {
            value = function.valueWith_with(value,enum.nextElement());
        };
        return value;
    };

    public boolean canSatisfy(Predicate1Arg predicate) {
        Enumeration enum = this.elements();
        while (enum.hasMoreElements()) {
            Object value = enum.nextElement();
            if (predicate.isTrueWith(value)) {
                return true;
            };
        };
        return false;
    };

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean addAll(Collection elements) throws IllegalElementException {
        boolean addedAny = false;
        Enumeration enum=elements.elements();
        while (enum.hasMoreElements()) {
            addedAny |= this.add(enum.nextElement());
        };
        return addedAny;
    }

    public void addElements(Enumeration e)
           throws IllegalElementException, CorruptedEnumerationException
           {
       while (e.hasMoreElements()) {
           this.add(e.nextElement());
       };
    }

    public void addIfAbsent(Object element) {
        if (!this.includes(element)) {
            this.add(element);
        };
    }

    public void addIfAbsentAll(Collection elements) throws IllegalElementException {
        Enumeration enum=elements.elements();
        while (enum.hasMoreElements()) {
            this.addIfAbsent(enum.nextElement());
        };
    }

    public void addIfAbsentElements(Enumeration e)
           throws IllegalElementException, CorruptedEnumerationException
           {
       while (e.hasMoreElements()) {
           this.addIfAbsent(e.nextElement());
       };
    }

    public Object removeAny() {
        Object result = this.any();
        this.remove(result);
        return result;
    }

    public Object takeAny() {
        Object result = this.any();
        this.remove(result);
        return result;
    }

    public final void clear() {
        removeAll();
    }

        //MLF:HACK:
    public void removeAll() {
        Enumeration enum=elements();
        while (enum.hasMoreElements()) {
            this.remove(enum.nextElement());
        };
    }

    public boolean removeAll(Collection c) {
        boolean changed = false;
        Enumeration enum=c.elements();
        while (enum.hasMoreElements()) {
            changed |= this.remove(enum.nextElement());
        };
        return changed;
    }

    public boolean retainAll(Collection c) {
        boolean changed = false;
        Enumeration enum=elements();
        while (enum.hasMoreElements()) {
            Object next = enum.nextElement();
            if (!c.includes(next)) {
                changed |= this.remove(next);
            }
        };
        return changed;
    }

    public boolean removeElements(Enumeration e)
            throws CorruptedEnumerationException {
        boolean removedAny = false;
        while (e.hasMoreElements()) {
            removedAny |= this.remove(e.nextElement());
        };
        return removedAny;
    }


    public boolean removeEveryElements(Enumeration e)
            throws CorruptedEnumerationException {
       boolean removedAny = false;
       while (e.hasMoreElements()) {
           removedAny |= this.removeEvery(e.nextElement());
       };
       return removedAny;
    }

    //**********************************************************
    //(P)******************* Collection ************************
    //**********************************************************

    public Collection copyExcluding(Object element) {
        Collection copy = (Collection) copy();
        copy.removeEvery(element);
        return copy;
    }

    public Collection  copyRemovingOneOf(Object element) {
        Collection copy = (Collection) copy();
        copy.remove(element);
        return copy;
    }




}
