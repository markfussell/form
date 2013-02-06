/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/ObjectBase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

/**
An ObjectBase represents and holds the whole, closed,
collection of interconnected Domain objects that together
represent the state of an object model.

<P>An ObjectBase is the root object for a transactional
DomainObjectModel and can be used to:
<UL>
<LI>Find DomainObjects when the client has no DomainObjects to start with.
<LI>Control the transaction for the model
<LI>Include new DomainObjects as part of the state of the business
</UL>

<P>An ObjectBase is important conceptually but should
be considered a second-class object: it should only
have responsibilities that can not be allocated to
the Entity domain objects or the supporting Category
domain objects.
**/
public interface ObjectBase {
    public PersonCategory getPersonCategory();
}

