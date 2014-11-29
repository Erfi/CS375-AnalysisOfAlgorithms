/*
 * this class solves the subset sum problem using recursive exhaustive search algorithm.
 * Time complexity O(2^n)
 */
public class ExhaustiveSubsetSum {
	
	/*
	 * @param set An int array for sunset sum problem
	 * @param size Size of the given set of ints.
	 * @param sum the sum that we are looking for in subset sum problem
	 * @return A boolean value indicating if subset exists or not.
	 */
	public  boolean subsetExists(int[] set, int size,  int sum){
		if(sum==0){ //if the sum that we are looking for is zero, empty set will do.
			return true;
		}
		if((size == 0) && (sum != 0)){// if the sum is not zero but the length of the input is zero, its impossible. 
			return false;
		}
		if(set[size-1] > sum){//starting from the end of the set, if the element is bigger than the sum repeat without it.
			return subsetExists(set,size-1,sum);
		}
		/*
		 * if the element is less than the sum check if you can find a subset satisfying the
		 * problem with a smaller set or 
		 * check if there is a subset that can satisfy [sum - element]
		 */
		return subsetExists(set, size-1, sum) || subsetExists(set, size-1, sum-set[size-1]);
	}
}