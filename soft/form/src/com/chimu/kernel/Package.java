/*======================================================================
**
**  File: chimu/kernel/Package.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel;

// import com.chimu.kernel.collections.Collection;

/**
A Package is an object that represents a Java Package and its
functionality.  All Packages have a name and can say what
subpackages exist for the Package.  Subtypes can specify further
functionality available to the

<P>Packages should have a suffix of "Package" to prevent then from
colliding with a concept in the package which has the same name as
the package.

<P>Packages are where the functionality of a Package is
specified.  This is normally done in the PackageLib for the Package,
but conceptually that specification would also apply to the actual
Package interface and PackageC object.

@see PackageLib
**/
public interface Package {

    //**********************************************************
    //(P)********************** Asking *************************
    //**********************************************************

    public String name();

    //**************************************
    //**************************************
    //**************************************

    public Package[] subPackagesAsArray();
    //public Collection subPackages(); //MLF980224: Disconnect for FMS

}
