/*======================================================================
**
**  File: chimu/kernel/KernelPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel;

/**
Kernel has functionality useful to all types of applications and to all
Layers within that application.  This is a (the) core framework within
ChiMu frameworks.

<P>Kernel is composed of multiple frameworks that build upon each other.  These
include:
<UL>
    <LI>Exceptions -- Exceptions for development errors like NotImplementedYet, FailedRequire
    <LI>Functors -- Support for object that encapsulate functions (aka Commands)
    <LI>Collections -- A type-based set of collections and collection wrappers
    <LI>Meta -- Meta capability additions to Java
    <LI>Utilities -- General Libraries (currently Translation)
</UL>
@see com.chimu.kernel.exceptions.ExceptionsPack
@see com.chimu.kernel.functors.FunctorsPack
@see com.chimu.kernel.collections.CollectionsPack
**/
public class KernelPack extends PackageLib {

    //**********************************************************
    //************************* MAIN ***************************
    //**********************************************************

        /**
         * Kernel Version, Copyright and Warranties.
         */
    static public void main(String[] args) {
        System.out.println("************************************************************************");
        System.out.println("**");
        System.out.println("**  ChiMu Kernel Frameworks");
        System.out.println("**  Version 1_1");
        System.out.println("**  Copyright (c) 1997, 1998 ChiMu Corporation, All rights reserved.");
        System.out.println("**  http://www.chimu.com/");
        System.out.println("**");
        System.out.println("**  This software is the confidential and proprietary information of");
        System.out.println("**  ChiMu Corporation (\"Confidential Information\").  You shall not");
        System.out.println("**  disclose such Confidential Information and shall use it only in");
        System.out.println("**  accordance with the terms of the license agreement you entered into");
        System.out.println("**  with ChiMu Corporation.");
        System.out.println("**");
        System.out.println("************************************************************************");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    private KernelPack() {};

};
