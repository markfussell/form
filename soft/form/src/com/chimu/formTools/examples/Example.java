/*======================================================================
**
**  File: chimu/formTools/examples/Example.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import java.sql.Connection;
import java.io.PrintWriter;
import com.chimu.formTools.test.*;

/**
An Example is a DatabaseTest that has descriptive information about the example.
**/
public interface Example extends DatabaseTest {
    public String description();
}

