
public class ExhustiveSubsetSum {
	public static boolean subsetExists(int[] set, int size,  int sum){
		if(sum==0){
			return true;
		}
		if((size == 0) && (sum != 0)){
			return false;
		}
		if(set[size-1] > sum){ 
			return subsetExists(set,size-1,sum);
		}
		return subsetExists(set, size-1, sum) || subsetExists(set, size-1, sum-set[size-1]);
	}
	
	public static void main(String[] args) {
		int[] set = {1,3,4,5,6,7,8,0,45,6,7,5,2,3,35,14,3,12,7,22,06,1,2,3,5,2,3,24,7,34,35,};
		int sum = 700;
		long time;
		Timer.start();
		boolean result = subsetExists(set,set.length,sum);
		Timer.stop();
		time = Timer.getRunTime();
		if(result==true){
			System.out.println("yup");
			System.out.println("Runtime: "+ time);
		}else{
			System.out.println("nope");
			System.out.println("Runtime: "+ time);
		}
	}

}
