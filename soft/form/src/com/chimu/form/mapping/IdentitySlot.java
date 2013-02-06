/*======================================================================
**
**  File: chimu/form/mapping/IdentitySlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

import com.chimu.kernel.streams.Generator;
import com.chimu.kernel.functors.*;

/**
An IdentitySlot is a special type of DirectSlot that is used to specify
an Object's Identity and generate IdentityKeys.

<P>Most of the IdentitySlot interface functionality was moved into ObjectMapper,
and the remaining functionality should be considered tentative.

@see ObjectMapper
**/
public interface IdentitySlot extends SetterSlot {
        /**
         * Setup the identitySlot to use the retrieverFunction after the first
         * insert of an object to fetch the identity of the object.
         *
         * <P> The result of the retriever function will (by default) be fed into
         * the decoder function (if any) of the slot.  See the longer version of the method
         * for specifying a different decoder.
         *
         * @param retriever (Function2Arg) will be called with the object (Object) itself as
         * as its first argument and a connection (Connection) as the second value.
         */
    public void setupPostInsertIdentityRetrieverFunction(Function2Arg retriever);


        /**
         * Same as 'setupPostInsertIdentityRetrieverFunction' except that we specify
         * a different encoder to be used when fetching this slot.
         *
         * @param encoder (Function1Arg) will be called with the columnValue as its first argument
         */
    public void setupPostInsertIdentityRetrieverFunction_decoder(Function2Arg retriever, Function1Arg decoder);

    public void setupPreInsertIdentityGenerator(Generator generator);

//    public void setupDecoderEncoder(Function1Arg decoder, Function1Arg encoder);
};