/*======================================================================
**
**  File: chimu/kernel/PackageLib.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel;

/**
A PackageLib is a root class (more a root concept) of Package
"objects" implemented solely as static-side functionality (i.e.
as a Library).

<P>The PackageLib are where the functionality of a Package is specified/documented.

<P>All PackageLibs should have a suffix of "Pack".  This documents that
they are a PackageLibrary and prevents collisions with the normal types
and classes within the package.

<P>PackageLibs are closely related with Packages and PackageClasses.  If
a given Java Package has a PackageLib and a PackageC then they should
support almost identical behavior.  In many cases the PackageC
should have functionality implemented via a PackageLib.

<P>PackageLib currently provides no inheritable functionality,
so this class is primarily to define and document the concept of a
PackageLib.

@see Package
@see Library
**/
public abstract class PackageLib extends Library {
    protected PackageLib() {}
}
