/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/CurrencyC.java
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

public class CurrencyC extends DomainObjectAbsC implements Currency {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================
    public CurrencyC(String name){
        super();
        this.name = name;
    }

    protected CurrencyC(){}

    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public Object copy() {unstub();
        CurrencyC newObject = new CurrencyC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(CurrencyC aCurrency) {
        super.initFrom(aCurrency);
        this.name = aCurrency.name;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {unstub();
       return "<Currency #"+myOid+" name="+this.name+">";
       }

        /**  This is the string that we use for displaying to external
         ==  world.
         **/
    public String info() { unstub();

        return "Currency name: " + this.name;
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name() {unstub();
        return name;
    }


    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {unstub();
        this.name = name;
    }

    static protected Currency getCurrency(String curName) {

        Object anObject = currencyList.detect(includesNameFunctor(curName));
        if (anObject != null) return (Currency) anObject;

        System.err.println("Search failed for "+curName);

        CurrencyC newCur = new CurrencyC();
        newCur.setName(curName);
        newCur.write();
        return newCur;
    }

    static protected Currency getCurrency(Integer curID) {
        Object anObject = currencyList.detect(includesIDFunctor(curID));
        if (anObject != null) return (Currency) anObject;

        return defaultCurrency();
    }


    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String name;
    protected static List currencyList = CollectionsPack.newList();


    //==========================================================
    //(P)===================== Functors ========================
    //==========================================================

    static protected Predicate1Arg includesNameFunctor(final String someName) {
        return new Predicate1Arg() {public boolean isTrueWith(Object each) {//<DontAutoUnstub>
            return ((CurrencyC) each).name().equals(someName);
        }  };
    }

    static protected Predicate1Arg includesIDFunctor(final Integer someID) {
        return new Predicate1Arg() {public boolean isTrueWith(Object each) {//<DontAutoUnstub>
            return ((CurrencyC) each).form_identityKey().equals(someID);
        }  };
    }


    //==========================================================
    //(P)================== Static methods =====================
    //==========================================================

    public static List allCurrencies() {
        return currencyList;
    }

    public static Currency defaultCurrency() {
        return getCurrency("USD");
    }

    public static void setCurrencyList(List aList) {
        currencyList = aList;
    }


    //<GenerateObjectBinding{
    //FORM Preprocessor Version 1.8 ran on: Sat May 30 01:18:33 PDT 1998

    //==========================================================
    //(P)=========== Slots, Initializing & Extracting ==========
    //==========================================================

    /*friend:FORM*/ public void form_initState(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_initState(slotValues);

        this.name = (String) slotValues.atKey("name");
    }

    /*friend:FORM*/ public void form_extractStateInto(com.chimu.kernel.collections.KeyedArray slotValues) {
        super.form_extractStateInto(slotValues);

        slotValues.atKey_put("name",this.name);
    }

    //==========================================================
    //(P)==================== Constructor ======================
    //==========================================================

    /*friend:FORM*/ public CurrencyC(com.chimu.form.mapping.CreationInfo cInfo) {
        super(cInfo);
    }

    //==========================================================
    //(P)==================== Class Info =======================
    //==========================================================

    static Class myC() {
        return CurrencyC.class;
    }

    static /*friend:FORM*/ public com.chimu.form.mapping.CreationFunction form_creationFunction() {
        return new com.chimu.form.mapping.CreationFunction() {
            public com.chimu.form.mapping.MappedObject valueWith(com.chimu.form.mapping.CreationInfo cInfo){
                return new CurrencyC(cInfo);
            }
        };
    }

//}GenerateObjectBinding>
}