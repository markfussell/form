/*======================================================================
**
**  File: chimu/jdk12/java/util/Arrays.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class contains various methods for manipulating arrays (such as
 * sorting and searching).  It also contains a static factory that allows
 * arrays to be viewed as Lists.
 *
 * @author  Josh Bloch
 * @version 1.11 11/04/97
 * @see Comparable
 * @see Comparator
 * @since   JDK1.2
 */

public class Arrays {
    // Sorting

    /**
     * Sorts the specified array of longs into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(long[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of ints into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(int[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of shorts into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(short[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of chars into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(char[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of bytes into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(byte[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of doubles into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(double[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified array of floats into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @since JDK1.2
     */
    public static void sort(float[] a) {
        sort1(a, 0, a.length);
    }

    /*
     * The code for each of the seven primitive types is identical.
     * C'est la vie.
     */

    /**
     * Sorts the specified sub-array of longs into ascending order.
     */
    private static void sort1(long x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        long v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(long x[], int a, int b) {
        long t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(long x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed longs.
     */
    private static int med3(long x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }

    /**
     * Sorts the specified sub-array of integers into ascending order.
     */
    private static void sort1(int x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        int v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int x[], int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(int x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed integers.
     */
    private static int med3(int x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }

    /**
     * Sorts the specified sub-array of shorts into ascending order.
     */
    private static void sort1(short x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        short v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(short x[], int a, int b) {
        short t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(short x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed shorts.
     */
    private static int med3(short x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }


    /**
     * Sorts the specified sub-array of chars into ascending order.
     */
    private static void sort1(char x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        char v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(char x[], int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(char x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed chars.
     */
    private static int med3(char x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }


    /**
     * Sorts the specified sub-array of bytes into ascending order.
     */
    private static void sort1(byte x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        byte v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(byte x[], int a, int b) {
        byte t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(byte x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed bytes.
     */
    private static int med3(byte x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }


    /**
     * Sorts the specified sub-array of doubles into ascending order.
     */
    private static void sort1(double x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        double v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(double x[], int a, int b) {
        double t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(double x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed doubles.
     */
    private static int med3(double x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }


    /**
     * Sorts the specified sub-array of floats into ascending order.
     */
    private static void sort1(float x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && x[j-1]>x[j]; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        float v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(float x[], int a, int b) {
        float t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(float x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed floats.
     */
    private static int med3(float x[], int a, int b, int c) {
        return (x[a] < x[b] ?
                (x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
                (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }


    /**
     * Sorts the specified array of objects into ascending order, according
     * to the <i>natural comparison method</i> of its elements.  All
     * elements in the array must implement the Comparable interface.
     * Furthermore, all elements in the array must be <i>mutually
     * comparable</i> (that is, e1.compareTo(e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the array).
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     * 
     * @exception ClassCastException array contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers).
     * @see Comparable
     * @since JDK1.2
     */
    public static void sort(Object[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified sub-array of integers into ascending order according
     * to their "natural sort function."
     */
    private static void sort1(Object x[], int off, int len) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off &&
                     ((Comparable)x[j-1]).compareTo((Comparable)x[j])>0; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s);
                m = med3(x, m-s,   m,   m+s);
                n = med3(x, n-2*s, n-s, n);
            }
            m = med3(x, l, m, n); // Mid-size, med of 3
        }
        Comparable v = (Comparable)x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            int r;
            while (b <= c && (r = ((Comparable)x[b]).compareTo(v)) <= 0) {
                if (r == 0)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && (r = ((Comparable)x[c]).compareTo(v)) >= 0) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s);
        if ((s = d-c) > 1)
            sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(Object x[], int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(Object x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed Objects,
     * according to their natural compare function.
     */
    private static int med3(Object x[], int a, int b, int c) {
        Comparable xa = (Comparable)x[a];
        Comparable xb = (Comparable)x[b];
        Comparable xc = (Comparable)x[c];

        return (xa.compareTo(xb) < 0 ?
                (xb.compareTo(xc) < 0 ? b : xa.compareTo(xc) < 0 ? c : a) :
                (xb.compareTo(xc) > 0 ? b : xa.compareTo(xc) > 0 ? c : a));
    }


    /**
     * Sorts the specified array according to the order induced by the
     * specified Comparator.  All elements in the array must be <i>mutually
     * comparable</i> by the specified comparator (that is,
     * comparator.compare(e1, e2) must not throw a typeMismatchException for
     * any elements e1 and e2 in the array).
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @exception ClassCastException array contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator.
     * @see Comparator
     * @since JDK1.2
     */
    public static void sort(Object[] a, Comparator c) {
        sort1(a, 0, a.length, c);
    }

    /**
     * Sorts the specified sub-array of integers into ascending order according
     * to the specified Comparator.
     */
    private static void sort1(Object x[], int off, int len, Comparator f) {
        // Insertion sort on smallest arrays
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j=i; j>off && f.compare(x[j-1], x[j]) > 0; j--)
                    swap(x, j, j-1);
            return;
        }

        // Choose a partition element, v
        int m = off + len/2;       // Small arrays, middle element
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        // Big arrays, pseudomedian of 9
                int s = len/8;
                l = med3(x, l,     l+s, l+2*s, f);
                m = med3(x, m-s,   m,   m+s,   f);
                n = med3(x, n-2*s, n-s, n,     f);
            }
            m = med3(x, l, m, n, f); // Mid-size, med of 3
        }
        Object v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            int r;
            while (b <= c && (r = f.compare(x[b], v)) <= 0) {
                if (r == 0)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && (r = f.compare(x[c], v)) >= 0) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }

        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

        // Recursively sort non-partition-elements
        if ((s = b-a) > 1)
            sort1(x, off, s, f);
        if ((s = d-c) > 1)
            sort1(x, n-s, s, f);
    }

    /**
     * Returns the index of the median of the three indexed Objects,
     * according to the specified Comparator.
     */
    private static int med3(Object x[], int a, int b, int c, Comparator f) {
        return (f.compare(x[a],x[b]) < 0 ?
                (f.compare(x[b],x[c])<0 ? b : f.compare(x[a],x[c])<0 ? c : a) :
                (f.compare(x[b],x[c])>0 ? b : f.compare(x[a],x[c])>0 ? c : a));
    }



    // Searching

    /**
     * Searches the specified array of longs for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(<i>insertion point</i>) - 1).  The <i>insertion
     *               point</i> is defined as the the point at which the value would
     *                be inserted into the array: the index of the first element
     *               greater than the value, or a.length, if all elements in 
     *               the array are less than the specified value.  Note that this
     *               guarantees that the return value will be &gt;= 0 if and only
     *               if the object is found.
     * @see #sort(long[])
     * @since JDK1.2
     */
    public static int binarySearch(long[] a, long key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of ints for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(int[])
     * @since JDK1.2
     */
    public static int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of shorts for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(short[])
     * @since JDK1.2
     */
    public static int binarySearch(short[] a, short key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            short midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of chars for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(char[])
     * @since JDK1.2
     */
    public static int binarySearch(char[] a, char key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            char midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of bytes for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(byte[])
     * @since JDK1.2
     */
    public static int binarySearch(byte[] a, byte key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            byte midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of doubles for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(double[])
     * @since JDK1.2
     */
    public static int binarySearch(double[] a, double key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            double midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of floats for the specified value using
     * the binary search algorithm.  The array must <strong>must</strong> be
     * sorted (as by the sort method, above) prior to making this call.  If
     * it is not sorted, the results are undefined: in particular, the call
     * may enter an infinite loop.  If the array contains multiple elements
     * equal to the specified object, there is no guarantee which instance
     * will be found.
     *
     * @return index of the search key, if it is contained in the array;
     *               otherwise, (-(the "insertion point") - 1).
     * @see #sort(float[])
     * @since JDK1.2
     */
    public static int binarySearch(float[] a, float key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            float midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array for the specified Object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the <i>natural comparison method</i> of its elements (as by
     * Sort(Object[]), above) prior to making this call. The array must
     * <strong>must</strong> be sorted (as by the sort method, above) prior to
     * making this call.  If it is not sorted, the results are undefined: in
     * particular, the call may enter an infinite loop.  If the array contains
     * multiple elements equal to the specified object, there is no guarantee
     * which instance will be found.
     *
     * @exception ClassCastException array contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers), or the search key in not mutually comparable
     *                  with the elements of the array.
     * @see Comparable
     * @see #sort(Object[])
     * @since JDK1.2
     */
    public static int binarySearch(Object[] a, Object key) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            Object midVal = a[mid];
            int cmp = ((Comparable)midVal).compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array for the specified Object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the specified Comparator (as by Sort(Object[], Comparator),
     * above), prior to making this call.  If it is not sorted, the results are
     * undefined: in particular, the call may enter an infinite loop.  If the
     * array contains multiple elements equal to the specified object, there is
     * no guarantee which instance will be found.
     *
     * @exception ClassCastException array contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator,
     *                  or the search key in not mutually comparable with the
     *                  elements of the array using this Comparator.
     * @see Comparable
     * @see #sort(Object[], Comparator)
     * @since JDK1.2
     */
    public static int binarySearch(Object[] a, Object key, Comparator c) {
        int low = 0;
        int high = a.length-1;

        while (low <= high) {
            int mid =(low + high)/2;
            Object midVal = a[mid];
            int cmp = c.compare(midVal, key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }


    /**
     * Returns a fixed-size List backed by the specified array.  (Changes to
     * the returned List "write through" to the array.)  This method acts
     * as bridge between array-based and Collection-based APIs, in
     * combination with Collection.toArray.
     *
     * @see Collection#toArray()
     * @since JDK1.2
     */
    public static List toList(Object[] a) {
        return new ArrayList(a);
    }

    private static class ArrayList extends AbstractList implements Cloneable {
        private Object[] a;

        ArrayList(Object[] array) {
            a = array;
        }

        public int size() {
            return a.length;
        }

        public Object[] toArray() {
            return (Object[]) a.clone();
        }

        public Object get(int index) {
            return a[index];
        }

        public Object set(int index, Object element) {
            Object oldValue = a[index];
            a[index] = element;
            return oldValue;
        }

        public Object clone() {
            return new ArrayList(toArray());
        }
    }
}
