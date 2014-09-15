/*
 * File: ArrayIntegerSet.java
 * Class: CS 375
 * Author: djskrien
 * Date: September 2014
 */

/**
 * This class models a finite set of integers.
 * It uses an array to store the integers of the set.
 */
public class ArrayIntegerSet
{
    /** stores the integers of this set. */
    private int[] data;
    /** stores the number of integers in this set.*/
    private int size;

    /* CLASS INVARIANT
     * The first 'size' slots of the data array contain the integers
     *     stored in this set.
     * The order of the data in the array is irrelevant.
     */

    /**
     * creates a new empty ArrayIntegerSet
     */
    public ArrayIntegerSet()
    {
        data = new int[10];
        size = 0;
    }

    /**
     * creates a new ArrayIntegerSet containing the integers in the array.
     * Duplicated values are added only once.
     * It creates an empty ArrayIntegerSet if array is null.
     * @param array the array of integers to be added to the set.
     */
    public ArrayIntegerSet(int[] array)
    {
        if( array == null ) array = new int[0];

        data = new int[array.length + 10];
        size = 0;
        addAll(array);
    }

    /**
     * adds a new integer to this set.
     * If the value is already in the set, nothing happens.
     * @param value the integer to be added to the set
     */
    public void add(int value)
    {
        if (! contains(value) ) {
            ensureAvailableSpace();
            data[size] = value;
            size++;
        }
    }

    /**
     * adds the integers in an array to this set.
     * If any of the values in the array are already in the set,
     * nothing happens with those values.
     * If array is null, nothing happens.
     * @param array the array of integers to be added to the set
     */
    public void addAll(int[] array)
    {
        if (array == null) return;
        for (int x : array) {
            add(x);
        }
    }

    /**
     * adds the values in another ArrayIntegerSet to this set, removing duplicates.
     * If otherSet is null, nothing happens.
     * @param otherSet the ArrayIntegerSet containing the values to be added to
     * this set.
     */
    public void addAll(ArrayIntegerSet otherSet)
    {
	  if (otherSet == null) return;
        addAll(otherSet.toArray());
    }

    /**
     * deletes an int value from the set.
     * If the value is not in this set, then nothing happens.
     * @param value the integer to be deleted from this set.
     */
    public void remove(int value)
    {
        int index = indexOf(value);
        if (index != -1) {
            data[index] = data[size - 1];
            size--;
        }
    }

    /**
     * removes all the values in otherSet from this set.
     * If some of the values are not in this set, nothing happens
     * regarding those values.
     * @param otherSet the ArrayIntegerSet whose values are to be removed
     * from this set.
     */
    public void removeAll(ArrayIntegerSet otherSet)
    {
        removeAll(otherSet.toArray());
    }

    /**
     * removes all the values in an integer array from this set.
     * If some of the values are not in this set, nothing happens
     * regarding those values.
     * @param array the integer array whose values are to be removed
     * from this set.
     */
    public void removeAll(int[] array)
    {
        for (int x : array) {
            remove(x);
        }
    }

    /**
     * determines whether an integer is stored in this set.
     * @param value the integer to be tested for membership
     * @return true if the value is a member of this set.
     */
    public boolean contains(int value)
    {
        return (indexOf(value) >= 0);
    }

    /**
     * determines whether all the integers in another ArrayIntegerSet are in this
     * set.  That is, it tells whether the other set is a subset of this set.
     * If otherSet is null, it returns true.
     * @param otherSet the ArrayIntegerSet whose values are to be tested for
     * membership in this set.
     * @return true if this set contains all the elements of otherCollection.
     */
    public boolean containsAll(ArrayIntegerSet otherSet)
    {
        if (otherSet == null)
            return true;
        else
            return containsAll(otherSet.toArray());
    }

    /**
     * determines whether all the integers in an integer array are in this
     * set.
     * If array is null, it returns true.
     * @param array the array whose values are to be tested for
     * membership in this set.
     * @return true if this set contains all the elements of the array.
     */
    public boolean containsAll(int[] array)
    {
        if (array == null) return true;
        for (int x : array) {
            if (!contains(x)) {
                return false;
            }
        }
        return true;
    }

    /**
     * creates a new ArrayIntegerSet that is the union of this set and another
     * ArrayIntegerSet.
     * If otherSet is null, it returns a clone of this set.
     * @param otherSet the ArrayIntegerSet to be unioned with this set.
     * @return a new ArrayIntegerSet containing all the elements of this set and
     * otherSet.
     */
    public ArrayIntegerSet union(ArrayIntegerSet otherSet)
    {
        ArrayIntegerSet unionSet = (ArrayIntegerSet) this.clone();
        unionSet.addAll(otherSet);
        return unionSet;
    }

    /**
     * creates a new ArrayIntegerSet that is the intersection of this set and another
     * ArrayIntegerSet.
     * If otherSet is null, it returns null.
     * @param otherSet the ArrayIntegerSet to be intersected with this set.
     * @return a new ArrayIntegerSet containing the common elements of this set and
     * otherSet.
     */
    public ArrayIntegerSet intersection(ArrayIntegerSet otherSet)
    {
        if (otherSet == null) return null;
        ArrayIntegerSet intersectionSet = new ArrayIntegerSet();
        for (int i = 0; i < size; i++) {
            if( otherSet.contains(data[i]) )
                intersectionSet.add(data[i]);
        }
        return intersectionSet;
    }

    /**
     * creates an array containing the elements of this set.
     * The order of the elements in the array is arbitrary.
     * The length of the array is equal to the size of this set.
     * @return a new integer array with the elements of this set.
     */
    public int[] toArray()
    {
        int[] newArray = new int[size];
        System.arraycopy(data, 0, newArray, 0, newArray.length);
        return newArray;
    }

    /**
     * determines the equality of two ArrayIntegerSets, where equality means having
     * exactly the same integers.
     * @param otherSet the ArrayIntegerSet to be tested against this set.
     * @return true if this set and otherSet both have exactly the
     * same elements.
     */
    public boolean equals(ArrayIntegerSet otherSet)
    {
        if (otherSet == null)
            return false;
        else
            return this.containsAll(otherSet) && otherSet.containsAll(this);
    }

    /**
     * determines the equality of two ArrayIntegerSets, where equality means having
     * exactly the same integers.
     * @param otherSet the Object to be tested against this set.
     * @return true if otherSet is an ArrayIntegerSet and
     * this set and otherSet both have exactly the
     * same elements.
     */
    public boolean equals(Object otherSet)
    {
        if(otherSet == null || ! (otherSet instanceof ArrayIntegerSet))
            return false;
        return equals((ArrayIntegerSet) otherSet);
    }

    /**
     * determines whether this set has any elements.
     * @return true if this set has at least one integer.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * removes all integers from this set.
     */
    public void clear()
    {
        size = 0;
    }

    /**
     * @return the number of elements in this set.
     */
    public int size()
    {
        return size;
    }

    /**
     * @return a new ArrayIntegerSet containing the same elements as this set.
     */
    public Object clone()
    {
        return this.intersection(this);
    }

    /**
     * creates a string containing all elements of this set
     * surrounded by braces.  For example, if the
     * set stores the integers 1, 2, and 3, the string would be
     * "{1, 2, 3}" or a similar string with the integers in a different
     * order.  The order in which the elements are listed is irrelevant.
     */
    public String toString()
    {
        String result = "{";
        for (int i = 0; i < size-1; i++) {
            result += data[i] + ", ";
        }
        if ( size > 0 )
            result += data[size-1];
        result += "}";
        return result;
    }

    //------- private auxiliary methods ---------
    /**
     * determines whether an integer is in the data array
     * @param value the integer to be tested for inclusion
     * @return the index of the value in the array.  If the value is not in
     * the array, then -1 is returned.
     */
    private int indexOf(int value)
    {
        for (int i = 0; i < size; i++) {
            if (value == data[i])
                return i;
        }
        return -1;
    }

    /**
     * ensures that the data array has at least one unused slot.  If not, then
     * it creates a new larger data array and copies the data into it.
     */
    private void ensureAvailableSpace()
    {
        if (size == data.length) {
            int[] newData = new int[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }
}