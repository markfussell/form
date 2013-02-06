/*======================================================================
**
**  File: chimu/formTools/preprocessor/InfoBuilder.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;
import java.io.PrintWriter;

/**
An InfoBuilder knows how to take the field information coming from the FormPreprocessor
and convert it into the specific format desired in the FormInfo and Domain files.

@see InfoBuilderC
**/
public interface InfoBuilder {
    public void prepareConverter(SourceConverterPrinter converter);
    
    public void printDeclarations(SourceConverterPrinter ostr,String typeName, String varName);
    
}

