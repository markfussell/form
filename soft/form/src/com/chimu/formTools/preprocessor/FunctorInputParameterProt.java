/*======================================================================
**
**  File: chimu/formTools/preprocessor/FunctorInputParameterProt.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

/*package*/ interface FunctorInputParameterProt {
    public int parameterType();
    public String typeName();
    public String name();
    public String initializationValue();

    final int PARAMETER = 1, RESULT_TYPE = 3, CLASS_NAME=4;
};

