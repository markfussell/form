/*======================================================================
**
**  File: chimu/kernel/collections/impl/ListAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.protocols.Future;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**


**/

public abstract class ListAbsC
                                    extends CollectionAbsC
                                    implements  List {


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean equals(Object object) {
        if ( !(object instanceof Array) ) return false;
        Array arr = (Array) object;
        int size = size();
        if (arr.size() != size) return false;
        for (int i=0; i< size; i++) {
            if ( !arr.atIndex(i).equals(this.atIndex(i)) ) return false;
        }
        return true;
    }

    //**********************************************************
    //(P)******************** Sorting **************************
    //**********************************************************

    public void atIndex_insertAll(int index, Collection c) {
        int size = size();
        if (index >= size) {
            addAll(c);
            return;
        }
        if (index <= 0) index=0;
        Enumeration enum = c.elements();
        while (enum.hasMoreElements()) {
            atIndex_put(index++,enum.nextElement());
        }
    }

    public abstract void removeIndex(int index);
    public void remove(int index) {removeIndex(index);}

    public void removeFrom_to(int index1, int index2) {
        int size = size();
        if (index1 < 0) index1=0;
        if (index2 >= size) index2=size-1;
        int reps = index2-index1 + 1;
        while (reps-- > 0) {
            removeIndex(index1);
        }
    }

    public void swapAtIndex_withIndex(int index1, int index2) {
        Object tmp = this.atIndex(index1);
        this.atIndex_put(index1,this.atIndex(index2));
        this.atIndex_put(index2,tmp);
    }

    public void sort(Predicate2Arg greaterThanPredicate) {
        if (size() > 0) {
            quickSort(this,0,size()-1,greaterThanPredicate);
        };
    }

    public void sort() {
        sort(stringGreaterThanPredicate());
    }

    static public Predicate2Arg stringGreaterThanPredicate() {
        return new Predicate2Arg() {public boolean isTrueWith_with(Object arg1, Object arg2) {
            return arg1.toString().compareTo(arg2.toString()) > 0;
        }};
    }



    static public void quickSort(Array s, int left, int right, Predicate2Arg greaterThan) {
        // See Sedgewick or similar reference for this algorithm
        // Note that certain optimizations are different from Sedgewick because the
        // operation of comparison is relatively expensive

        int n = right - left + 1;
        if (n <= 1) return;

        Object leftObject = s.atIndex(left);
        Object rightObject = s.atIndex(right);

        if (greaterThan.isTrueWith_with(leftObject,rightObject)) {
            s.swapAtIndex_withIndex(left,right);
            Object tmp = leftObject;
            leftObject = rightObject;
            rightObject = tmp;
        };

        if (n <= 2) return;

        int midPoint = (left+right) / 2;
        Object pivotObject = s.atIndex(midPoint);

        if (greaterThan.isTrueWith_with(leftObject,pivotObject)) {
            s.swapAtIndex_withIndex(left,midPoint);
            pivotObject = leftObject; //leftObject is now obsolete
        } else {
            if (greaterThan.isTrueWith_with(pivotObject,rightObject)) {
                s.swapAtIndex_withIndex(midPoint,right);
                pivotObject = rightObject;
            };
        }

        if (n <= 3) return;

        /*
        More than three elements, so we need to continue the quickSort of the partitions.
		Find leftUp>left and rightDown<right such that leftUpObject and rightDownObject
		are on the wrong sides of the pivotObject. Then swap the elements at leftUp and rightDown.
		Repeat this procedure until leftUp and rightDown have passed over all elements.
		*/

        int leftUp = left;
        int rightDown = right;

        while (leftUp <= rightDown) {
                //Skip over rightDowns that are greater than the pivotObject (i.e. in correct position)
            do {
                rightDown--;
            } while (    (leftUp <= rightDown)
                      && (greaterThan.isTrueWith_with(s.atIndex(rightDown),pivotObject))
                );

                //Skip over leftUps that are less than the pivotObject (i.e. in correct position)
            do {
                leftUp++;
            } while (    (leftUp <= rightDown)
                      && (greaterThan.isTrueWith_with(pivotObject,s.atIndex(leftUp)))
                );

            if (leftUp < rightDown) { //Swap the two out of position elements
                s.swapAtIndex_withIndex(leftUp,rightDown);
            };
        }
        quickSort(s, left, rightDown, greaterThan);
        quickSort(s, leftUp, right, greaterThan);
	}
}

/**
 * An implementation of Quicksort using medians of 3 for partitions.
 * Used internally by sort.
 * @param s, the array to sort
 * @param lo, the least index to sort from
 * @param hi, the greatest index
 * @param cmp, the comparator to use for comparing elements
**/

/*
  static public void quickSort(Array s, int lo, int hi, IntFunction2Arg cmp) {

    if (lo >= hi) return;

    //   Use median-of-three(lo, mid, hi) to pick a partition.
    //   Also swap them into relative order while we are at it.

    int mid = (lo + hi) / 2;

    if (cmp.valueWith_with(s.atIndex(lo),s.atIndex(mid)) > 0) {
      Object tmp = s.atIndex(lo);
      s.atIndex_put(lo,s.atIndex(mid));
      s.atIndex_put(mid,tmp);  //swap
    }

    if (cmp.valueWith_with(s.atIndex(mid),s.atIndex(hi)) > 0) {
      Object tmp = s.atIndex(mid);
      s.atIndex_put(mid,s.atIndex(hi));
      s.atIndex_put(hi,tmp);  //swap

      if (cmp.valueWith_with(s.atIndex(lo),s.atIndex(mid)) > 0) {
          Object tmp2 = s.atIndex(lo);
          s.atIndex_put(lo,s.atIndex(mid));
          s.atIndex_put(mid,tmp2);  //swap
      }
    }

    int left = lo+1;           // start one past lo since already handled lo
    int right = hi-1;          // similarly
    if (left >= right) return; // if three or fewer we are done

    Object partition = s.atIndex(mid);

    for (;;) {

      while (cmp.valueWith_with(s.atIndex(right), partition) > 0) --right;

      while (left < right && cmp.valueWith_with(s.atIndex(left), partition) <= 0) ++left;

      if (left < right) {
          Object tmp = s.atIndex(left);
          s.atIndex_put(left,s.atIndex(right));
          s.atIndex_put(right,tmp);  //swap
          --right;
      }
      else break;
    }

    quickSort(s, lo, left, cmp);
    quickSort(s, left+1, hi, cmp);

  }
*/

/**
 * An implementation of Quicksort using medians of 3 for partitions.
 * Used internally by sort.
 * @param s, the array to sort
 * @param lo, the least index to sort from
 * @param hi, the greatest index
 * @param cmp, the comparator to use for comparing elements
**/
/*
  static public void quickSort(IndexedCollection s, int lo, int hi, Predicate2Arg greaterThan) {

    if (lo >= hi) return;


    //   Use median-of-three(lo, mid, hi) to pick a partition.
    //   Also swap them into relative order while we are at it.


    int mid = (lo + hi) / 2;

    if (greaterThan.isTrueWith_with(s.atIndex(lo),s.atIndex(mid))) {
      Object tmp = s.atIndex(lo);
      s.atIndex_put(lo,s.atIndex(mid));
      s.atIndex_put(mid,tmp);  //swap
    }

    if (greaterThan.isTrueWith_with(s.atIndex(mid),s.atIndex(hi))) {
      Object tmp = s.atIndex(mid);
      s.atIndex_put(mid,s.atIndex(hi));
      s.atIndex_put(hi,tmp);  //swap

      if (greaterThan.isTrueWith_with(s.atIndex(lo),s.atIndex(mid))) {
          Object tmp2 = s.atIndex(lo);
          s.atIndex_put(lo,s.atIndex(mid));
          s.atIndex_put(mid,tmp2);  //swap
      }
    }

    int left = lo+1;           // start one past lo since already handled lo
    int right = hi-1;          // similarly
    if (left >= right) return; // if three or fewer we are done

    Object partition = s.atIndex(mid);

    for (;;) {

      while (greaterThan.isTrueWith_with(s.atIndex(right), partition)) --right;

      while (left < right && greaterThan.isTrueWith_with(partition, s.atIndex(left))) ++left;

      if (left < right) {
          Object tmp = s.atIndex(left);
          s.atIndex_put(left,s.atIndex(right));
          s.atIndex_put(right,tmp);  //swap
          --right;
      }
      else break;
    }

    quickSort(s, lo, left, cmp);
    quickSort(s, left+1, hi, cmp);

  }

    */
