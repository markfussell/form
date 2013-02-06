/*======================================================================
**
**  File: chimu/kernel/collections/CollectionsPack.java
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

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.factories.*;

import com.chimu.kernel.collections.impl.jdk.*;
import com.chimu.kernel.collections.impl.jgl.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.vm.*;

import java.util.Vector;


/**
Collections provides an interface-based set of collection classes.
Collection users are provided with the protocol for interaction with many different
(but related) types of collections, and they are only tied to those interface not
the specific implementation.  Collection implementations only need to conform to
the interface and can then choose what are the most important performance and
space criteria for them.  This also allows many interesting implementations like
Futures or Tracers to work within the same collection system.

<P>The main concept for the Collection framework is that clients should only be
concerned with the Type hierarchy when specifying functionality.  All
client-typing of objects (classes, methods, and variables) should be
done in terms of the type hierarchy.  Choosing among the implementations
of the types is only relevant at object creation time and should only need
to consider performance and code-base preference.

<P>Another concept within the Collection Framework is that the most commonly
used interface should have simple names.  Ideally Java would allow multiple
names for a single Type/Interface so we could have a Scientific long name
(Extensible Indexed Collection) and a more convenient short name (Sequence),
but since Java does not allow this we prefer the short name when it is very
natural.

<P><FONT size="+1">Terms</FONT>
<P>The Collection framework has precise definition of words which allows
collections to be self describing (in the long-name form).
<PRE>
Collection	An object which holds onto a number of other objects
Extensible	Able to add and remove objects
Updateable	Able to replace objects currently in the collection
Fixed	    Unable to change in state.  A FixedCollection is one that
            cannot grow in size or replace any of its elements.  A client
            can only ask questions of the collection.  In certain contexts
            the Fixed is not as restrictive and allows replacement but not growth.
Keyed	    Having a key (Object) that can return a value
Indexed	    Having a sequential index number (zero based) that can return a value
Zero-based	Having an index value of '0' return the first value in an indexed
            collection.  This is one of the many horrible Java concepts that
            came from 'C' (for which it makes sense) and should be instantly
            obliterated.  But barring that, all the collection classes are Zero
            based for consistency with (most of) the rest of Java.
</PRE>
<P><FONT size="+1">Short Names</FONT>
<PRE>
Array	    Indexed Collection (Non Extensible)
Bag	        An unordered collection for which an object can be added more than
            once and the collection will remember each occurance.
KeyedArray	Indexed and Keyed Collection (Non Extensible)
Map	        Extensible Keyed Collection.  Can replace, add, or remove keyed entries
Sequence	Extensible Indexed Collection.  A collection where entries can be
            added to the end or beginning and all members can be retrieved by
            index (zero based)
Set	        An unordered collection for which an object can only be added once.
            Subsequent additions of the same object are ignored.
</PRE>

<P>Portions of these collection classes were originally written by
Doug Lea (dl@cs.oswego.edu) and released into the public domain.

@see Collection
**/
public class CollectionsPack  {
    private CollectionsPack(){};

    //==========================================================

    static public boolean canVmSupportWeakCollections() {
        return VmPack.theVm().canSupportWeakReferences();
    }

    public boolean canSupportWeakCollections() {
        return shared_defaultFactory.canSupportWeakCollections();
    }

    static public boolean useWeakCollections() {
        return wantWeakCollections && canVmSupportWeakCollections();
    }

    //==========================================================

    static public Map newMap() {
        return shared_defaultFactory.newMap();
    }


    static public List newList() {
        return shared_defaultFactory.newList();
    }

    static public List newList(int size) {
        return shared_defaultFactory.newList(size);
    }

    static public List newListEmptySize(int size) {
        return shared_defaultFactory.newListEmptySize(size);
    }


    static public Set newSet() {
        return shared_defaultFactory.newSet();
    }

    static public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap) {
        return shared_defaultFactory.newKeyedArray(keyToIndexMap);
    }

    static public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap, Object[] values) {
        return shared_defaultFactory.newKeyedArray(keyToIndexMap, values);
    }

    static public List newListFuture(Function0Arg futureFunction) {
        return shared_defaultFactory.newListFuture(futureFunction);
    }

        /**
         * Build a WeakMap if the VM supports it, otherwise build a normal
         * Map.  A WeakMap only has a "weak" hold on its values, which
         * allows them to be garbage collected and removed from the collection.
         * This is ideal for many types of caches because you only want to
         * keep track of objects that others are using. If they stop using
         * them, you would like to release the Objects.
         */
    static public Map newWeakMap() {
        return shared_defaultFactory.newWeakMap();
    }


        /**
         * Return the default factory used to build collections when
         * you call CollectionsImplPack static methods
         */
    static public CollectionsFactory defaultFactory() {
        return shared_defaultFactory;
    }

    static protected CollectionsMill getCollectionsMill() {
        throw new NotImplementedYetException();
    }

        /**
         * Setup the default factory used to build collections when
         * you call CollectionsImplPack static methods.  This should
         * be called very early in a programs execution or
         * programs may get confused (but probably not: the interfaces
         * supported are the same no matter what).
         */
    static public void setupDefaultFactory(CollectionsFactory newDefaultFactory) {
        shared_defaultFactory = newDefaultFactory;
    }


    //==========================================================
    //======================= STARTUP ==========================
    //==========================================================

    static protected CollectionsFactory shared_defaultFactory = null;

    static public void setupDefaultFactory() {
        VmSi theVm = (VmSi) VmPack.theVm();

        shared_defaultFactory = theVm.defaultCollectionsFactory();
        if (shared_defaultFactory == null) {
            shared_defaultFactory = new JdkCollectionsFactoryC(false);
        }
    }

    static public void setWantWeakCollections(boolean want) {
        VmPack.theVm().setupUseWeakReferences(want);
    }

    static protected boolean wantWeakCollections = true;

    static {
        setupDefaultFactory();
    }
    
};
