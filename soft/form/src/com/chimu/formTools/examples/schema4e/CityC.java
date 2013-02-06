/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/CityC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;


public class CityC implements City {

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    public CityC (String name, String state){
        super();
        this.name = name;
        this.state = state;
    }

    protected CityC(){}


    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

    public CityC copy() {
        CityC newObject = new CityC();
        newObject.initFrom(this);
        return newObject;
        }

    protected void initFrom(CityC city) {
        this.name = city.name;
        this.state = city.state;
    }

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

    public String toString() {
       if (this.name != null) return "City " + this.name;
       return "Unknown";
       }

        /**
         ==  This is the string that we use for displaying to external
         ==  world.
         **/
    public String info() {
        return this.name + " in " + this.state;
    }

    //==========================================================
    //(P)===================== Asking ==========================
    //==========================================================

    public String name() {
        return name;
    }

    public String state() {
        return state;
    }

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }


    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected String name;
    protected String state;

    //==========================================================
    //==========================================================
    //==========================================================


    static public void addToCities(CityC city) {
        cities.add(city);
    }

    static public void setCollection(Collection collection) {
        cities = (List) collection;
    }

    //==========================================================
    //(P)===================== Functors ========================
    //==========================================================

    static public Function1Arg cityToColumnFunctor() {
        return new Function1Arg() {public Object valueWith(Object arg1) {//<DontAutoUnstub>
            if (arg1 != null)return nameOfCity((City) arg1);
            String string = "Default San Francisco";
            return string;
        }};
    }

    static public Function1Arg columnToCityFunctor() {
        return new Function1Arg() {public Object valueWith(Object arg1) {//<DontAutoUnstub>
           if (arg1 != null) return getCity((String) arg1);
           return new CityC("Default San Francisco", "CA");
        }};
    }

    //==========================================================
    //(P)================ Support Methods  =====================
    //==========================================================

        /*
        ==  Answers the city name of the object
        ==  Answers a default, if the argument is null
        */
    static protected String nameOfCity(City aCity) {
        return aCity.name();
    }
        /*
        ==  Answers a city given the name of the city
        */
    static protected CityC getCity(String cityName) {
        Object anObject = cities.detect(includesNameFunctor(cityName));
        if (anObject != null) return (CityC) anObject;

        CityC newCity = new CityC();
        newCity.setName(cityName);
        newCity.setState("Default");
        cities.add(newCity);
        return newCity;
    }

    static protected List cities = CollectionsPack.newList();

    //==========================================================
    //(P)===================== Functors ========================
    //==========================================================

    static protected Predicate1Arg includesNameFunctor(final String someName) {
        return new Predicate1Arg() {public boolean isTrueWith(Object each) {//<DontAutoUnstub>
            return ((CityC) each).name().equals(someName);
        }};
    }

}