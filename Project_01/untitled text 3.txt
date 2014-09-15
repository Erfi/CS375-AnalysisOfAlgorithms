/*
 * File: SortTest.java
 * Class:  CS375
 * Author: Dale Skrien
 * Date: September, 2014
 */


/**
 * This class tests the methods in the Sort class and determines the number
 *  of steps or the time the methods took.
 */
public class SortTest
{
    /**
     * prints the elements of array A to the console (System.out).
     * It prints 10 values per line
     * @param A the integer array to be printed
     */
    public static void printArray(int[] A)
    {
        System.out.println();
        for( int i = 0; i < A.length; i++ ) {
            System.out.print(A[i] + ",  ");
            if( (i+1) % 10 == 0 )
                System.out.println();
        }
    }

    /**
     * tests array A to see whether it is sorted from smallest to largest.
     * If A is not sorted, it prints out to the console (System.out)
     * the two unsorted elements that it found and then returns false.
     * Otherwise it returns true.
     * @param A the integer array to be tested
     * @return true if A is sorted from smallest to largest
     */
    public static boolean isSorted(int[] A)
    {
        if(A == null) return true;
        for( int i = 0; i < A.length-1; i++ )
            if( A[i] > A[i+1] ) {
                System.out.print("A[" + i + "] = " + A[i] +
                                " > A[" + (i+1) + "] = " + A[i+1]);
                return false;
            }
        return true;
    }

    /**
     * Sorts array A using method i and prints the time the sorting took
     * and the number of array accesses the sort took.
     * @param i the method to use (i = 1, 2, 3, 4, 5 or 6)
     * @param A the integer array to be sorted
     */
    public static void timeMethod(int i, int[] A)
    {
        long startTime = System.currentTimeMillis();
        int[] B = Sort.callMethod(i, A);
        long endTime = System.currentTimeMillis();
        System.out.print("method" + i + " time in millisecs: ");
        System.out.print(endTime - startTime);
        System.out.println(", \tmethod" + i + " count: " +
                                                Sort.getMethodCount(i));
        //to make sure it is sorted
        isSorted(B);
    }

    /**
     * tests all 6 sorting methods in the Sort class using an integer
     * array of length n storing random integers in the range 0 to n-1.
     * @param n the length of the array to be created and sorted
     */
    public static void testRandom(int n)
   {
       //create an array of n random integers in the range 0 to n-1
       int[] A = new int[n];
       for (int i = 0; i < n; i++)
           A[i] = (int) (n * Math.random());

       //uncomment the following two lines if you wish to see the
       //elements of the array A.
       //System.out.println("initial array:");
       //printArray(A);

       System.out.println("For a random array of length " + n + ":");

       //time all 6 methods
       timeMethod(1, A);
       timeMethod(2, A);
       timeMethod(3, A);
       timeMethod(4, A);
       timeMethod(5, A);
       timeMethod(6, A);
   }

    /**
     * main method that calls the testRandom methods for various values of n.
     * @param args an array of Strings (unused)
     */
    public static void main(String[] args)
    {
        testRandom(100);
    }
}