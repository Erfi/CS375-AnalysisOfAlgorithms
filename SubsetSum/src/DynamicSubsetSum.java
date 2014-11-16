
public class DynamicSubsetSum {
	public static boolean subsetExists(int[] set, int sum){
		boolean[][] Q = new boolean[sum + 1][set.length + 1];
		
		for(int i=0; i <= set.length; i++){//for all sum == 0 the Q[i][j] = 0
			Q[0][i] = true;
		}
		
		for(int i=1; i <= sum; i++){//for all empty sets with sum not 0 the Q[i][j] is false
			Q[i][0] = false;
		}
		
		for(int i=1; i<=sum; i++){
			for(int j=1; j<=set.length; j++){
				Q[i][j] = Q[i][j-1];
				if(i >= set[j-1]){//if sum is bigger than or equal to the j-1th element of set
					//then 
					Q[i][j] = Q[i][j] || Q[i- set[j-1]][j-1];
				}
			}
		}
		
		//code below is for printing the Q table
		for(int i=0; i<=sum; i++){
			for(int j=0; j<=set.length; j++){
				if(Q[i][j] == true){
					System.out.print(" [ " + Q[i][j] +"  ] ");
				}else{
					System.out.print(" [ " + Q[i][j] +" ] ");
				}
			}
			System.out.print('\n');
		}
		
		
		return Q[sum][set.length];
	}
	public static void main(String[] args) {
		int[] set = {1, 7, 21, 0, 4 ,8 ,4, 3, 9};
		int sum = 56;
		if(subsetExists(set,sum)==true){
			System.out.println("yup");
		}else{
			System.out.println("nope");
		}
	}

}
