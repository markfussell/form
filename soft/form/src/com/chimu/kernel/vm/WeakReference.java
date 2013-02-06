/*======================================================================
**
**  File: chimu/kernel/vm/WeakReference.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.jdk.*;
import com.chimu.kernel.collections.impl.jgl.*;
import com.chimu.kernel.functors.*;

import java.util.Vector;

public interface WeakReference {
    public Object  value();
    public void    setValue(Object value);
    public boolean hasValue();
    public void    release();
}

