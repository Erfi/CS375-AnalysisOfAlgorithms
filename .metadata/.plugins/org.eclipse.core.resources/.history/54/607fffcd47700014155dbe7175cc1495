
import java.util.*;

public class greedy{
	
	public greedy(){
	}
	
	public boolean greedyAlg(long k, ArrayList<Long> s){
		Collections.sort(s, Collections.reverseOrder());
		int sumS = 0;
		ArrayList<Long> subS = new ArrayList<Long>();
		
		for(int i=0; i<s.size(); i++){
			if(s.get(i) > k){
				continue;
				}
			else if(s.get(i) == k){
				return true;
				}
			else if(s.get(i) < k && (s.get(i)+ sumS) <= k){
				subS.add(s.get(i));
				sumS += s.get(i);
				if(sumS == k){
					 return true;
				}
			}
		}
		return false;
	}
	
	//runs the algorithm and return a boolean result
	//return true if the sum is found within the set
	//False if the sum cannot be found in the set
	public boolean run(long k, ArrayList<Long> s){
		return greedyAlg(k,s);
	}
	
	
	public static void main(String[] args){
		greedy me = new greedy();
		ArrayList<Long> s = new ArrayList<Long>(Arrays.asList(1L,3L,4L,5L,6L,7L,8L,0L,45L,6L,7L,5L,2L,3L,35L,14L,3L,12L,7L,22L,06L,1L,2L,3L,5L,2L,3L,24L,7L,34L,35L));
		long k = 700;
		Timer.start();
		boolean result = me.greedyAlg(k,s);
		Timer.stop();
		System.out.println(result);
		System.out.println(Timer.getRunTime());
	}
}