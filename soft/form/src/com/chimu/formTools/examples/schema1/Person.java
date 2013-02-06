/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Person.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

public interface Person {

    //==========================================================
    //(P)================== Displaying =========================
    //==========================================================

        /**
         * Provide a simple string representation of a person
         * @cat Displaying
         */
    public String toString();

        /**
         * Give some information about the person
         * @cat Displaying
         */
    public String getInfo();

    //==========================================================
    //(P)==================== Asking ===========================
    //==========================================================

                        /** @cat Asking **/
    public String getName();
                        /** @cat Asking **/
    public String getEmail();
                        /** @cat Asking **/
    public int    getHeight();

    //==========================================================
    //(P)==================== Altering =========================
    //==========================================================

                        /** @cat Altering **/
    public void setName(String name);
                        /** @cat Altering **/
    public void setEmail(String email);
                        /** @cat Altering **/
    public void setHeight(int height);

}