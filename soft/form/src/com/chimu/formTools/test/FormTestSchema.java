/*======================================================================
**
**  File: chimu/formTools/test/FormTestScheme.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import com.chimu.kernel.collections.*;
import java.io.File;

/**
**/
public interface FormTestSchema extends TestScheme {
    public void setupRootDirectory(File newRootDirectory);
}

