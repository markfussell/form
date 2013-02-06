/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/Money.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import java.math.BigDecimal;

public interface Money {

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================
    public String toString();
    public String info();
    
    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Currency currency();
    public BigDecimal amount();
    public Money toCurrency(Currency newCurrency);

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setAmount(BigDecimal amount);


    //==========================================================
    //(P)================== Calculations =======================
    //==========================================================

    public Money add(Money money);
    public Money subtract(Money money);
    public Money multiply(double scaling);
    public Money divide(double scaling);
}
