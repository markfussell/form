/*======================================================================
**
**  File: chimu/kernel/functors/FunctorsPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.functors;

/**
Functors contains interfaces that support using object that encapsulate
functions (aka Commands).

<P>Functors are objects that model operations that can be performed.
In their simplest form they are somewhat like function pointers:
they allow a client to call an unknown method with a standard interface.
Because functors are full-blown objects they can do quite a bit more
than function pointers: they can record and take advantage of their own
state as well as allowing references to other objects be used in the
method execution.  "Smart" functors can also allow the client to ask
questions about what operations are available and what the functor
requires for proper execution.  Basic functors don't provide this
functionality but are easier to create which makes them more useful.

<P>The basic functors are Interfaces with a single-method protocol
that makes them easy to implement.  They are about as close to having
Smalltalk block simplicity as is possible with Java right now.  Functors
vary on two axes: what type of value they return and how many arguments
they take.  There are three types of Functors based on return value:
<PRE>
Type        Returns         Method Prefix
Procedure   nothing (void)   execute
Function    An Object        value
Predicate   A boolean        isTrue
</PRE>
<P>There can be any number of parameters, but currently there is support
for only 0, 1, 2, and 3 argument functors.  All functors only take Objects
as parameters.
<PRE>
#   Type Suffix  Method Suffix
0   0Arg         () [none]
1   1Arg         With(Object arg1)
2   2Arg         With_with(Object arg1, Object arg2)
</PRE>

<P>See all the interfaces for the specific combinations of Functors available,
and see TranslationLib for some example Functors.

@see com.chimu.kernel.utilities.TranslationLib
**/
public class FunctorsPack  {

    //**********************************************************
    //************************* MAIN ***************************
    //**********************************************************


    //**********************************************************
    //**********************************************************
    //**********************************************************

    private FunctorsPack() {};

};
