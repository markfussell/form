/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/USDollarC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
import java.math.BigDecimal;

import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

public class USDollarC implements Money {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public USDollarC (BigDecimal amount){
        this.amount = amount;
    }

    protected USDollarC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public USDollarC copy() {
        USDollarC newObject = new USDollarC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(USDollarC money) {
        this.amount = money.amount;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {
       return this.amount.toString();
       }

        /**
         ==  This is the string that we use for displaying to external
         ==  world.
         **/
    public String info() {
        return "USD: $ " + this.amount;
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String currency() {
        return "USD";
    }

    public BigDecimal amount() {
        return amount;
    }
        /*
        Answers this money with the equivalent in USD
        Right now, only returns a constant.
        */
    public Money inUSD() {
        return new USDollarC(amount());
    }

    public Money toCurrency(String currency) {
        return new USDollarC(amount());
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    //==========================================================
    //(P)================== Calculations =======================
    //==========================================================

    public Money add(Money money){
        BigDecimal result = this.amount.add(money.amount());
        return new USDollarC(result);
    }

    public Money subtract(Money money){
        BigDecimal result = this.amount.subtract(money.amount());
        return new USDollarC(result);
    }

    public Money multiply(double scaling){
        BigDecimal result = new BigDecimal("32");
        return new USDollarC(result);
    }

    public Money divide(double scaling){
        BigDecimal result = new BigDecimal("32");
        return new USDollarC(result);

    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected BigDecimal amount;

    //==========================================================
    //(P)===================== Functors ========================
    //==========================================================

    static public Function1Arg encodeFunctor() {
        return new Function1Arg() {public Object valueWith(Object arg1) {//<DontAutoUnstub>//unstub();
            if (arg1 == null) return null;
            Money someMoney = (Money) arg1;
            return someMoney.amount();
        }};
    }

    static public Function1Arg decodeFunctor() {
        return new Function1Arg() {public Object valueWith(Object arg1) {//<DontAutoUnstub>//unstub();
            if (arg1 == null) return null;
            BigDecimal amount = (BigDecimal) arg1;
            return new USDollarC(amount);
        }};
    }
}

