/*======================================================================
**
**  File: chimu/formTools/FormToolsPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools;

/**
FormTools has Tools that are used for developing FORM applications.

@see com.chimu.kernel.KernelPack
@version 1.2b2
**/
public class FormToolsPack extends com.chimu.kernel.PackageLib {
    static public final String formToolsVersion = "1_0";

    //==========================================================
    //========================= MAIN ===========================
    //==========================================================

        /**
         * Kernel Version, Copyright and Warranties.
         */
    static public void main(String[] args) {
        System.out.println("************************************************************************");
        System.out.println("**");
        System.out.println("**  ChiMu FORM Tools");
        System.out.println("**  Version "+formToolsVersion+"");
        System.out.println("**  Copyright (c) 1997, ChiMu Corporation, All rights reserved.");
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

    //==========================================================
    //==========================================================
    //==========================================================

    private FormToolsPack() {};

};
