/*======================================================================
**
**  File: chimu/formTools/examples/FormExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.kernel.collections.*;


import java.io.*;
import com.chimu.formTools.test.*;

/**
**/
public class FormExamples implements com.chimu.formTools.test.FormSchemeCollection {
    public FormExamples() {}

    public void setupRootDirectory(File newRootDirectory) {
        rootDirectory = newRootDirectory ;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected boolean isInUrlMode() {
        return rootDirectory == null;
        //return getC().getName().startsWith("com.chimu");
    }

    public Array /*of FormTestScheme*/ schemes() {
        if (isInUrlMode()) {
            return defaultSchemes();
        }

        List schemes = CollectionsPack.newList();

        File directory = rootDirectory;
        if (!directory.isDirectory()) {
            return schemes;
            // throw new RuntimeException(directory.getAbsolutePath()+"is not a directory");
        };
        String[] fileNames = directory.list();
        for (int i=0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            if (isTestDirectory(fileName)) {
                FormTestScheme testScheme = createTestSchemeObjectFrom(fileName);
                if (testScheme != null) {
                    testScheme.setupRootDirectory(rootDirectory);
                    schemes.add(testScheme);
                };
            };
        };

        return schemes;
    }

    static protected List defaultSchemes = null;
    static {
        defaultSchemes = CollectionsPack.newList();
        defaultSchemes.add(com.chimu.formTools.examples.scheme1.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme1b.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme2.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme3.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme3b.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4b.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4c.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4d.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4e.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme5.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme5b.SchemeExamples.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme5c.SchemeExamples.class);

//        defaultSchemes.add(com.chimu.formTools.examples.scheme1b.SchemeExamples.class);
/*
        defaultSchemes.add(com.chimu.formTools.examples.scheme2.Scheme2.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme3.Scheme3.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4.Scheme4.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4b.Scheme4b.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4c.Scheme4c.class);
        defaultSchemes.add(com.chimu.formTools.examples.scheme4d.Scheme4d.class);
*/
    }


    protected Array /*of FormTestScheme*/ defaultSchemes() {
        List schemes = CollectionsPack.newList();
        int size = defaultSchemes.size();

        for (int i=0; i<size; i++) {
            Object testScheme = null;
            try {
                testScheme = ((Class) defaultSchemes.atIndex(i)).newInstance();
            } catch (Exception e) {};

            if (testScheme != null) {
                schemes.add(testScheme);
            }
        }
        return schemes;
    }

    protected FormTestScheme createTestSchemeObjectFrom(String name) {
        char[] nameChars = name.toCharArray();
        nameChars[0]=Character.toUpperCase(nameChars[0]);
        String className = "SchemeExamples"; // new String(nameChars);
        try {
            Class createC;
            createC = Class.forName(name+"."+className);
            return (FormTestScheme) createC.newInstance();
        } catch (Exception e) {
            //System.err.println(e);
            return null;
        }
    }

    protected boolean isTestDirectory(String fileName) {
        return true; //fileName.startsWith("scheme");
    };

    //==========================================================
    //==========================================================
    //==========================================================

    protected File rootDirectory=null;

}

