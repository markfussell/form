/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataType.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.typeConstants;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;

    /*  
    **  Each sql data type is represented by a class that implement 
    **  this interface.
    */

public interface SqlDataType {

    public boolean parameterRequired();
    public int defaultLength();
    public int defaultScale();
    public int defaultPrecision();

    public int code();
    public String key();
    public int integerRep();


    public String stringRep(DatabaseProduct dbProduct, int length, int precision);
    public String stringRep(DatabaseProduct dbProduct, int length);
    public String stringRep(DatabaseProduct dbProduct);
    public String defaultStringRep(DatabaseProduct dbProduct);
}