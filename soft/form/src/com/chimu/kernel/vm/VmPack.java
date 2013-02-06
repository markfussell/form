/*======================================================================
**
**  File: chimu/kernel/vm/VmPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;

import com.chimu.kernel.collections.*;


import java.util.Properties;

/**
VmPack
**/
public class VmPack {

    //**************************************
    //**************************************
    //**************************************

    static public void main(String[] args) {
        System.out.println(theVm());
        if (theVm().canSupportWeakReferences()) {
            System.out.println(theVm().newWeakReference("Hi There"));
        } else {
            System.out.println("Can't support weak references");
        }
    }

    //**************************************
    //**************************************
    //**************************************

    static public Vm theVm() {
        return vm;
    }


    static {
        setupVm();
    }

    static public void setupVm() {
        String vendorUrl = System.getProperty("java.vendor.url");


        if (vendorUrl == null) {
            vm = null;
        } else if (vendorUrl.equals("http://www.sun.com/")) {
            vm = getVmInstanceOrNull("com.chimu.kernel.vm.sun.VmC");
        } else if (vendorUrl.equals("http://www.microsoft.com/")) {
            vm = getVmInstanceOrNull("com.chimu.kernel.vm.ms.VmC");
        };
        if ( (vm == null) && VmAbsC.isJglAvailable() ) {
            vm = getVmInstanceOrNull("com.chimu.kernel.vm.jgl.VmC");
        };

        if (vm == null) {
            vm = new com.chimu.kernel.vm.generic.VmC();
        };
        vm.install();
            //Preliminary installation because other options may be chosen
            //and the VM reinstalled
    }

    static VmSi getVmInstanceOrNull(String className) {
        VmSi result = null;
        try {
            Class vmC = Class.forName(className);
            if (vmC == null) return null;
            result = (VmSi) vmC.newInstance();
        } catch (Exception e) {}

        return result;
    }

    static protected VmSi vm;

    private VmPack() {};

}
