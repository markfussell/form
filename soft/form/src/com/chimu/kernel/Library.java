/*======================================================================
**
**  File: chimu/kernel/Library.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel;

import com.chimu.kernel.exceptions.ShouldNotImplementException;

/**
A Library is a "Class" that has only static-side behavior and
will have no instances.  Static behavior is resolved at compile time
so a Library contains only "functions" and "procedures" (no methods)
which are fully resolved at compile time.

<P>All libraries should have a suffix of "Lib".  This documents that
they are a Library.  Library itself provides no inheritable functionality,
so this class is primarily to define and document the concept of a Library.

<P>This class does not have a private constructor because that inhibits
subclassing (for some bizarre reason).  All "Leaf" Libraries should not
be instanciable, so they should have a private constructor.
**/
public class Library {
    protected Library() {
        throw new ShouldNotImplementException("Libraries are not meant to be instanciated");
    }
}
