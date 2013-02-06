/*======================================================================
**
**  File: chimu/form/client/DomainObject_1_AbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.client;

import com.chimu.form.mapping.MappedObject;
import com.chimu.form.mapping.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

/**
DomainObject_1_AbsC provides an implementation of FORM oriented
functionality for DomainObjects.  It implements the MappedObject
protocol that FORM requires and also provides a little DomainObject
functionality (#copy,#write,#delete).

DomainObject_1_AbsC's implementation uses two instance variables
('oid' and 'isStub') and a default identity slot (also 'oid').
The 'oid' instance variable is just an 'Object' and is completely
opaque to this class and FORM.  The 'oid' object just has to be able to
compare itself (#equals) to another value and return true appropriately.
To set the 'oid' slot, this class provides a shorthand method:
'createOidSlotInto_columnName(ObjectMapper mapper, String columnName)'.

<P>There are two constructors for this class: A general 'blank' constructor
that subclasses will use to build new domain objects and a FORM specific
constructor which only FORM should use when it tries to Replicate a server
object.  The FORM constructor is the only way to produce an object in the
'isStub' state.

<P>Three remarks about FORM and abstract classes.
First, FORM does not require the use of an abstract class: you can have each
domain class itself support the "binding" required to interact with FORM.
Second, the source to this class is completely under your control: you are
licensed to copy this source to you own development directory and modify
it to satisfy your needs and standards.  You should leave the copyright
information within the file if you base it on this one.
Third, you can also start from scratch and define you own abstract class
as long as it implements the DomainObject interface.

@see DomainObject_1
@see DomainObject_1_Di
@see com.chimu.form.mapping.MappedObject
**/
/*
Simplicity
because your domain classes can focus on the specifics of their
mapping leaving the general specification up to the abstract class.
Consistency because as many general features


*/
public abstract class DomainObject_1_AbsC
                    implements MappedObject, MappedObjectXi
                    {

    //==========================================================
    //(P)================ MappedObject =========================
    //==========================================================
    /*(PC)
        This is the interface expected of a MappedObject
    */

    /*friend:FORM*/ public ObjectMapper form_objectMapper() {
        ObjectMapper mapper = objectMapperFor(this.getClass());
if (mapper == null) throw new RuntimeException("No mapper for this class "+this.getClass());
        return mapper;
    }


    //==========================================================
    //(P)=========== Initializing & Extracting =================
    //==========================================================

    /*friend:FORM*/ public void form_initIdentity(Object identityKey) {
        myOid = identityKey;
    }

    /*friend:FORM*/ public Object form_identityKey() {
        return myOid;
    }
    
    /*friend:FORM*/ public boolean form_isStub() {
        return this.isStub;
    }

    //======================================
    //======================================
    //======================================

        /**
         * initState should initialize the state of the object
         * from the slot values and then make sure the object
         * knows it is not a stub object anymore.
         *
         * <P> Each sublcass should call their parent's <CODE>initState</CODE>
         * method before doing their own.
         *@see #form_extractState
         */
    /*friend:FORM*/ public void form_initState (KeyedArray slotValues) {
        myOid     = (Object) slotValues.atKey("oid"); //
        makeNotStub();
    }

        /**
         * extractState should extract state information from
         * the current object and place it into the slotValues
         * KeyedArray.
         *
         *<P> Each subclass should call their parent's <CODE>extractState</CODE>
         * method before extracting their own state.
         *@see #form_initState
         */
    /*friend:FORM*/ public void form_extractStateInto(KeyedArray slotValues) {
        slotValues.atKey_put("oid",myOid);
    }

    //==========================================================
    //(P)=============== Midwrite Callbacks ====================
    //==========================================================
    /*(PC)
        These are exposed to support phantom objects that
        should be treated just like nulls.
    */

        /**
         * This should return true if an identity insert was required
         */
    /*friend:FORM*/ public boolean form_forceIdentityFor(ObjectMapperXi om) {
        if (form_identityKey() != null) return false;

        om.insertIdentityForObject(this);
        return true;
    }

    /*friend:FORM*/ public void form_updateAfterIdentityFor(ObjectMapperXi om) {
        om.afterIdentityInsertUpdateObject(this);
    }

    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================
    /*(PC)
        These are constructors just for subclasses
    */

        //FORM specific constructor
    /*friend:FORM*/ protected DomainObject_1_AbsC(CreationInfo parameter) {
        if (parameter != null) {
            this.isStub = parameter.isForStub();
        } else {
            makeStub();
        }
    }

        //General constructor
    protected DomainObject_1_AbsC() {
        makeNotStub();
    }

    //==========================================================
    //(P)================= Storage Changing ====================
    //==========================================================
    /*(PC)
       The following are some methods which could be expected
       of all domain objects to support saving them to the
       database.
    */

        /**
         * Save has the same functionality as 'write' currently
         * but is deprecated so save can be used for saving/synching
         * the state of a domain object, which will include deleting
         * if necessary.
         *@deprecated Use write() instead
         */
    public void save() {
        write();
    }

        /**
         * Write will either insert or update the object
         * to the database depending on the whether it
         * is a new object or not.
         */
    public void write() {
        if (isNewObject()) {
            insert();
        } else {
            update();
        };
    }

    public void delete() {
        mapper().deleteObject(this);
    }

    public void insert() {
        mapper().insertObject(this);
    }
    public void update() {
        mapper().updateObject(this);
    }


    //==========================================================
    //(P)==================== PRIVATE ==========================
    //==========================================================


    //==========================================================
    //(P)===================== Copying =========================
    //==========================================================

        /**
         * This method should be called to initialize an object
         * from the values in another object.  This is primarily
         * for "#copy"ing objects.  Subclasses should call the
         * parents #initFrom(...) before doing their own initialization
         *
         *@see #copy
         */
    protected void initFrom(DomainObject_1_AbsC oldObject) {
        makeNotStub();   //Same as if we are initializing the object
    }

    //==========================================================
    //(P)============== Object Mapping Support =================
    //==========================================================
    /*(PC)
        These are methods that provide the functionality FORM
        requires of a mapped object.  FORM learns of these methods
        using the Functors defined below in the Static Mapping
        section.
    */

        //This is backward compatibility
    public ObjectMapper mapper() {
        return form_objectMapper();
    }

    protected final boolean isNewObject() {
        return (myOid == null);
    }

    //======================================
    //============= Stubbing ===============
    //======================================

    protected boolean isStub() {
        return isStub;
    }

        /**
         * Convenience method to turn off the isStub state flage
         */
    protected final void makeNotStub() {
        this.isStub = false;
    }

        /**
         * Convenience method to turn on and off stubbing
         */
    protected final void makeStub() {
        this.isStub = true;
    }

    protected final void unstub() {
        if (this.isStub()) forceUnstub();
    }

    protected void forceUnstub() {
        if (this.isNewObject()) return;  //[2]

        makeNotStub();     //[1]
        try {
            this.mapper().unstubObject(this);
        } finally {
            makeStub();
        };
        makeNotStub();     //[1]

        /*
         [1] The statement 'isStub = false;' should conceptually be at the end of the method,
         but in rare circumstances with full FORM tracing a recursion problem can occur
         because we are in the process of unstubbing but don't know it.  By setting isStub
         to false before the unstub we avoid the recursion in exchange for having to reset
         'isStub' if an exception occured.  Unfortunately Java doesn't have an
         'OnExceptionFinally' so we have to turn isStub on and then back off in the
         normal control flow.  We could also just accept an invalid isStub state.
         [2] New objects can't be unstubbed from the database.
         */
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

        /**
         * The oid provides the real (server) identity of this object.
         * Oid are only generated, stored, and tested for equality,
         * so they can be any type of immutable Object.  
         * Integers or Strings work well.
         */
    protected Object  myOid    = null;

        /**
         * This is the (conceptually) simplest implementation of
         * identifying a stub object: use an isStub flag.  This
         * does require constantly managing the flag during
         * stub transitions (where having isStub be derived would
         * not have this property).
         */
    protected boolean isStub = false;


    //==========================================================
    //(P)================       STATIC       ===================
    //==========================================================

    //==========================================================
    //(P)==================== MAPPING ==========================
    //==========================================================

        /**
         * configMapper_for<Class> sets up an already created ObjectMapper
         * with the mapping required for a given class.  Each subclass
         * should call the superclass's configMapper_for<Class>
         *
         * <P>All of the general Domain to FORM binding should be specified in
         * root abstract classes so subclasses only have to worry about their own
         * specific functionality.
         */

    static public void configMapper_forDomainObject(ObjectMapper mapper) {

    }


    //==========================================================
    //(P)================= Mapping Support =====================
    //==========================================================

        /**
         * buildOidSlotInto_columnName allows subclasses to specify a particular
         * table column to use for storing the oid object.  This method is
         * approximately equivalent to mapper.newDirectSlot("oid",columnName).
         *
         * <P> If all domain classes used the same identity column (e.g. "id")
         * than this would be unnecessary.
         */
    static protected void buildOidSlotInto_columnName(ObjectMapper mapper, String columnName) {
        mapper.newIdentitySlot_column("oid",columnName);
    }

        /**
         * createOidSlotInto_columnName is like buildOidSlot... but it returns the
         * built slot.
         */
    static protected IdentitySlot createOidSlotInto_columnName(ObjectMapper mapper, String columnName) {
        return mapper.newIdentitySlot_column("oid",columnName);
    }


    //==========================================================
    //(P)=============== Mapper Registry =======================
    //==========================================================


    static public void setMapper(Class aC, ObjectMapper newMapper) {
        setupObjectMapper(aC,newMapper);
    }
    static public void setupObjectMapper(Class aC, ObjectMapper newMapper) {
        (ClassInformationRegistry.newInformationForC(aC)).atKey_put("form_ObjectMapper",newMapper);
    }

        /**
         * Link the class to the mapper supporting the class.  This handles the
         * common and simple case where all objects of a single class will
         * belong to the same mapper.
         */
    static public void linkC_toMapper(Class aC ,ObjectMapper newMapper)  {
        (ClassInformationRegistry.newInformationForC(aC)).atKey_put("form_ObjectMapper",newMapper);
    }

    static public void linkMapper_toC(ObjectMapper newMapper, Class aC) {
        (ClassInformationRegistry.newInformationForC(aC)).atKey_put("form_ObjectMapper",newMapper);
    }

    static public ObjectMapper mapperFor(Class aC) {
        return objectMapperFor(aC);
    }

    static public ObjectMapper objectMapperFor(Class aC) {
        Map classInformation = ClassInformationRegistry.getInformationForC(aC);
        if (classInformation == null) return null;
        return (ObjectMapper) classInformation.atKey("form_ObjectMapper");
    }

}


