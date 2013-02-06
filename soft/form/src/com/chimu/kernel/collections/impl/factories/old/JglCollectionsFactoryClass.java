/***********************************************************************
**
**  File: chimu/kernel/collections/impl/CollectionsImplPack.java
**
**  Copyright (c) 1997, ChiMu Corporation. All Rights Reserved.
**
**  This software is the confidential and proprietary information of
**  ChiMu Corporation ("Confidential Information").  You shall not
**  disclose such Confidential Information and shall use it only in
**  accordance with the terms of the license agreement you entered into
**  with ChiMu Corporation.
**
**  Portions of these collection classes were originally written by
**  Doug Lea (dl@cs.oswego.edu) and released into the public domain.
**  Doug Lea thanks the assistance and support of Sun Microsystems Labs,
**  Agorics Inc, Loral, and everyone contributing, testing, and using
**  this code.
**      ChiMu Corporation thanks Doug Lea and all of the above.
**
***********************************************************************/

package COM.chimu.kernel.collections.impl.factories;

import COM.chimu.kernel.collections.*;
import COM.chimu.kernel.collections.impl.*;
import COM.chimu.kernel.collections.impl.jdk.*;
import COM.chimu.kernel.collections.impl.jgl.*;

import COM.chimu.kernel.functors.*;

import java.util.Vector;

/**


**/

public class JglCollectionsFactoryClass extends JdkCollectionsFactoryClass {
    public JglCollectionsFactoryClass(boolean useWeakCollections) {
        this.useWeakCollections = useWeakCollections;
    }

    public JglCollectionsFactoryClass() {}

    //**********************************************************
    //**********************************************************
    //**********************************************************


    public Set newSet() {
        return new JglHashSetWrapperClass(new COM.objectspace.jgl.HashSet()); // new JdkWrapped
    }

}

