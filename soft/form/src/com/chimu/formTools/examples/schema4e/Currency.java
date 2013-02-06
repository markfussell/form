/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/Currency.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import java.math.BigDecimal;
import com.chimu.kernel.collections.*;

public interface Currency extends DomainObject{

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================
    public String toString();
    public String info();

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name();
    
    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name);


}
