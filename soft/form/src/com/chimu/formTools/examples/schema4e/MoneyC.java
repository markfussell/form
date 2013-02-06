/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/MoneyC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import java.math.BigDecimal;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;



import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

public class MoneyC implements Money {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public MoneyC (Currency currency, BigDecimal amount){
        this.currency = currency;
        this.amount = amount;
    }

    protected MoneyC(){}


    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {
        MoneyC newObject = new MoneyC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(MoneyC money) {
        this.currency = money.currency();
        this.amount = money.amount();
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {
       return this.currency.name() + " " + this.amount;
       }

        /**
         ==  This is the string that we use for displaying to external
         ==  world.
         **/
    public String info() {
        return "Money in " + this.currency.name() + " amount: " + this.amount;
    }

      //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public Currency currency() {
        return currency;
    }

    public BigDecimal amount() {
        return amount;
    }

        /* realistically, we'd get the conversion rates, and convert it.
        ==   For now, only returns a constant conversion rate.
        */
    public Money toCurrency(Currency newCurrency) {
        BigDecimal conversionRate = new BigDecimal("1.30");
        BigDecimal newAmount = this.amount.multiply(conversionRate);
        return new MoneyC(newCurrency, newAmount);
    }


    //==========================================================
    //(P)================== Calculations =======================
    //==========================================================

    public Money add(Money money){
        if (this.currency == money.currency()) {
            return new MoneyC(this.currency, (this.amount.add(money.amount())));
        } else {
            Money newArg = money.toCurrency(this.currency());
            BigDecimal newAmount = this.amount.add(newArg.amount());
            return new MoneyC(this.currency, newAmount);
        };
    }

    public Money subtract(Money money) {
        if(this.currency == money.currency()) {
            return new MoneyC(this.currency, (this.amount.subtract(money.amount())));
        } else {
            Money newArg = money.toCurrency(this.currency);
            BigDecimal newAmount = this.amount.subtract(newArg.amount());
            return new MoneyC(this.currency, newAmount);
        };
    }

    public Money multiply(double scaling){
        return new MoneyC(new CurrencyC("BogusDollar"), new BigDecimal("0.01"));
    }

    public Money divide(double scaling){
        return new MoneyC(new CurrencyC("BogusDollar"), new BigDecimal("0.01"));
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setCurrency(Currency aCurrency) {
        this.currency = aCurrency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Currency currency;
    protected BigDecimal amount;

    //==========================================================
    //(P)===================== Functors ========================
    //==========================================================

    static public Function1Arg encodeFunctor() {
        //issue a write to the database with the information
        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) {
                return null;
            } else {
                Money someMoney = (Money) arg1;
                return flattenToColumn(someMoney);
            };
        }};
    }

    static public Function1Arg decodeFunctor() {

        return new Function1Arg() {public Object valueWith(Object arg1) {
            if (arg1 == null) {
                return null;
            } else {
                Array aCollection = (Array) arg1;
                return constructFromCollection(aCollection);
            };
        }};
    }

        /*
        flatten a money into a sequence with the first element to be the currency,
        the second element amount.
        */
    static protected List flattenToColumn(Money aMoney) {
        aMoney.currency().write();  //guaranteeObjectId

        Object oidValue = ((DomainObjectAbsC) aMoney.currency()).getOid();

        List columnSeq = CollectionsPack.newListEmptySize(2);
        columnSeq.atIndex_put(0, oidValue);
        columnSeq.atIndex_put(1, aMoney.amount());
        return columnSeq;


    }

        /*
        Create a money from the argument
        */
    static protected Money constructFromCollection(Array aCollection) {

        Integer curID = (Integer) aCollection.atIndex(0);

        Currency curType = CurrencyC.getCurrency(curID);
        BigDecimal amount = (BigDecimal) aCollection.atIndex(1);

        return new MoneyC(curType, amount);

    }

}