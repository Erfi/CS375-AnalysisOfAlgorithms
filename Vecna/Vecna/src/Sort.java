/*
 * Modified by Erfan Azad on 30 December 2014
 * File: Sort.java
 * Description: This class contains methods of sorting
 * an array of integers.
 */

public class Sort {
	/*
	 * This method is using the same O(n^2) algorithm as the 
	 * original version however it is more generic.
	 * 
	 * Limitations: The elements in the array cannot
	 * be bigger than Integer.MAX_VALUE
	 * 
	 * @param	unsorted	array of unsorted integers.
	 * @param	sorted		an array that will hold the sorted integers.
	 * @return	this method does not return anything but prints out the sorted array.
	 */
	public static void reverseSort(int[] unsorted, int[] sorted){
		if(unsorted.length != sorted.length){
			System.out.println("ERROR: reverseSort >>> input arrays have different sizes!");
			return;
		}
		 for(int i = 0; i < sorted.length; i++){
	          int low = Integer.MAX_VALUE;
	          int lowIndex = -1;
	          for(int j = 0; j < unsorted.length; j++)
	          {
	            if(unsorted[j] < low)
	            {
	              low = unsorted[j];
	              lowIndex = j;
	            }
	          }
	          sorted[i] = low;
	          unsorted[lowIndex] = Integer.MAX_VALUE;
		 }
	}
	
	
    public static void main(String[] args) {
        //This code will sort the numbers in the array.
        //task: Modify the algorithm to reverse the sort, then comment on its efficiency in the space provided below:
        //
        //The simple version of this algorithm uses O(n^2) time complexity to sort an array of integers, it also 
        //uses 2n space to sort an array of size n. In addition it modifies the unsorted array.
        //Unlike the generic version, the simple version only works for array on size 100. 
        //
        int[] unsorted = new int[100];
        int[] sorted = new int[100];
        
        //creating unsorted
        for(int i = 0; i < 100; i++)
          unsorted[i] = (int)(Math.random()*100);
        
        //printing unsorted
        System.out.println("Here are the unsorted numbers:");
        for(int i = 0; i < 100; i++) System.out.print(unsorted[i]+" ");
        System.out.println();
       
        
        //Modified sorting (simple) 
        for(int i = 0; i < 100; i++)
        {
          int low = 101;
          int lowIndex = -1;
          for(int j = 0; j < 100; j++)
          {
            if(unsorted[j] < low)
            {
              low = unsorted[j];
              lowIndex = j;
            }
          }
          sorted[i] = low;
          unsorted[lowIndex] = 101;
        }
        
        //Modified sorting (generic)
        //reverseSort(unsorted, sorted);
        
        //printing sorted
        System.out.println("Here are the sorted numbers:");
        for(int i = 0; i < 100; i++) System.out.print(sorted[i]+" ");
        System.out.println();
      }
}

////Original sorting 
//for(int i = 0; i < 100; i++)
//{
//int hi = -1;
//int hiIndex = -1;
//for(int j = 0; j < 100; j++)
//{
//  if(unsorted[j] > hi)
//  {
//    hi = unsorted[j];
//    hiIndex = j;
//  }
//}
//sorted[i] = hi;
//unsorted[hiIndex] = -1;
//}
