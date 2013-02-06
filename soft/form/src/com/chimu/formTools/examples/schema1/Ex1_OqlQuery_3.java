/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_OqlQuery_3.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

import java.io.*;

/**
Ex1_OqlQuery_3 shows using BoundValues in an OQL query.  The query itself
is the same as Ex1_OqlQuery_2, but the values can be changed between runs.
**/

public class Ex1_OqlQuery_3 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);

        OqlQuery oql = orm.newOqlQuery(
                "FROM Person person "+
                "WHERE       person.height >= :minHeight "+
                "        AND person.height <= :maxHeight "+
                "     OR person.email = :emailAddress "
            );

        // /*
        oql.bindName_toValue("minHeight",new Integer(65));
        oql.bindName_toValue("maxHeight",new Integer(72));
        oql.bindName_toValue("emailAddress", "hhoudini");
        // */

        //or for user input use something like
        /*
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inputString;
            try {
                outputStream.println("Minimum height?");
                inputString = in.readLine();
                oql.bindName_toValue("minHeight",new Integer(inputString));

                outputStream.println("Maximum height?");
                inputString = in.readLine();
                oql.bindName_toValue("maxHeight",new Integer(inputString));
            } catch (Exception e) {
                System.err.println(e);
            }


        */

        List people = (List) oql.query().selectAll();

        int size = people.size();
        for (int i = 0; i<size; i++) {
            Person person = (Person) people.atIndex(i);
            outputStream.println(person.getInfo());
        }
    }
}