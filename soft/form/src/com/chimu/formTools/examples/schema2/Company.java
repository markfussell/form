/*======================================================================
**
**  File: com/chimu/formTools/examples/schema2/Company.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema2;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.form.mapping.*;

import java.util.Date;
import java.math.BigDecimal;

/**
Company class models an entity that does business with external parties and
has employees
**/
public interface Company extends DomainObject {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String     name();
    public BigDecimal revenue();
    public Collection employees();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name);
    public void setRevenue(BigDecimal revenue);

}