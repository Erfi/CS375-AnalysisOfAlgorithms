/*
 * This class solves the subset sum problem using dynamic programming algorithm.
 * Time complexity: Since this version of the algorithm is filling a table of m rows, depending on sum,
 * and n cols, depending on the length of the given set, the time complexity is of O(mn)
 */
public class DynamicSubsetSum {
	/*
	 * @param set An int array for sunset sum problem
	 * @param sum the sum that we are looking for in subset sum problem
	 * @return A boolean value indicating if subset exists or not
	 */
	public  boolean subsetExists(int[] set, int sum){
		boolean[][] Q = new boolean[sum + 1][set.length + 1];
		
		for(int i=0; i <= set.length; i++){//for all sum == 0 the Q[i][j] = 0
			Q[0][i] = true;
		}
		
		for(int i=1; i <= sum; i++){//for all empty sets with sum not 0 the Q[i][j] is false
			Q[i][0] = false;
		}
		
		for(int i=1; i<=sum; i++){
			for(int j=1; j<=set.length; j++){
				//initialize Q[i][j] to the result of the smallest subset
				//before it which calculates the same sum
				Q[i][j] = Q[i][j-1]; 
				/*
				 * only if sum is bigger than or equal to the jth element,
				 * then Q[i][j] is only true if either
				 * the smallest set calculating the same sum is true,
				 * or there exist a subset of one size smaller that has the sum of [sum - (j-1)th element] 
				 */
				if(i >= set[j-1]){
					Q[i][j] = Q[i][j] || Q[i- set[j-1]][j-1];
				}
			}
		}
		
//		//code below is for printing the Q table
//		for(int i=0; i<=sum; i++){
//			for(int j=0; j<=set.length; j++){
//				if(Q[i][j] == true){
//					System.out.print(" [ " + Q[i][j] +"  ] ");
//				}else{
//					System.out.print(" [ " + Q[i][j] +" ] ");
//				}
//			}
//			System.out.print('\n');
//		}
		
		
		return Q[sum][set.length];
	}
}