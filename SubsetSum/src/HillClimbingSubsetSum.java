/*
 * This class calculates the subset sum problem using hill climbing approach 
 * as an approximation algorithm.
 * 
 * Time complexity:
 * The time complexity of this algorithm depends on the number of iterations, k,
 * and the size of the subset, however in the worst case scenario the length of the
 * subset would be equal to the length of the given set , n. The algorithm will
 * traverse the subset a constant few times for each iteration to check if a randomly chosen element
 * of the given set exists in the subset or to add the numbers in the subset;
 * hence the time complexity is of O(kn).
 */
import java.util.*;

public class HillClimbingSubsetSum {
	ArrayList<Long> set;
	Random rand;
	
	/*
	 * @param preset ArrayList<Long> that is used to initialize the "set" field of the class. 
	 * This function initialized the "set" and "rand" fields of the class.
	 */
	public void setTheSet(ArrayList<Long> preset){
		this.set = preset;
		rand = new Random();
	}
	
	/*
	 * @param s ArrayList<Long> of the subset that we are trying to find its neighboring sunset
	 * @return  Returns "t", the ArrayList<Long> of neighboring set of the input set "s".  
	 */
	@SuppressWarnings("unchecked")
	public  ArrayList<Long> getNeighbor(ArrayList<Long> s){
		ArrayList<Long> t = new ArrayList<Long>(); 
		t = (ArrayList<Long>) s.clone();
		int a, b;
		a = rand.nextInt(set.size());
		b = rand.nextInt(set.size());
		while(a == b){
			b = rand.nextInt(set.size());
		}
		Long x1 = set.get(a);
		Long x2 = set.get(b);
		if(s.contains(x1)){
			t.remove(x1);
		}else{
			t.add(x1);
		}
		if(s.contains(x2)){
			if(rand.nextFloat() > 0.5){
				t.remove(x2);
			}
		}else{
			if(rand.nextFloat() > 0.5){
				t.add(x2);
			}
		}
		return t;
	}
	
	/*
	 * @param set ArrayList<Long> which contains the subset that this 
	 * 			  function is calculating the sum of all its elements.
	 * @return    returns sum of all the elements in "set"
	 */
	public long getSum(ArrayList<Long> set){
		long sum = 0;
		for(int i=0; i<set.size(); i++){
			sum = sum + set.get(i);
		}
		return sum;
	}
	
	/*
	 *@returns Returns a random subset of the main set of this class.
	 */
	public ArrayList<Long> getRandomSubset(){
		int bound = rand.nextInt(set.size());
		ArrayList<Long> temp = new ArrayList<Long>();
		
		for(int i=0; i<bound; i++ ){
			temp.add(set.get(i));
		}
		return temp;
	}
	
	/*
	 * @param sum The sum that we are looking for in the subset sum problem
	 * @param numIterations Number of iterations or approximating the result. 
	 * @return Returns the residue (difference of (the the closest sum of the sets after numIterations)
	 * 		   and the actual "sum")
	 */
	public long getResidue(long sum, int numIterations){
		long minResidue;
		ArrayList<Long> current = getRandomSubset();
//		System.out.println("random sunset of size "+current.size()+": "+current);
		minResidue = Math.abs(sum - getSum(current));
//		System.out.println("min residue: "+ minResidue + " = " + sum +" - " + getSum(current));
		for(int i=0; i< numIterations; i++){
			ArrayList<Long> temp = getNeighbor(current);
			long tempResidue = Math.abs(getSum(temp) - sum);
			if(tempResidue < minResidue){
				current = temp;
				minResidue = tempResidue;
			}	
		}
		return minResidue;
	}
	

	/*
	 * @param k sum that is used in subset sum problem.
	 * @param runs number of iterations
	 * @param s the subset given to the subset sum problem
	 * @return returns the residue of this approximation
	 */
	public long run(long k, int runs, ArrayList<Long> s){
		setTheSet(s);
		long result = getResidue(k, runs);
		return result;
	}
	
	/*
	 * NOTE: Auxiliary function for creating the subset sum problem.
	 * @param size size of the set to be made for the subset sum problem
	 * @return returns a set of longs for the subset sum problem.
	 */
	public static ArrayList<Long> makeSet(int size){
		Random rand = new Random();
		long range = 100000000000L;
		ArrayList<Long> set = new ArrayList<Long>();
		for(int i=0; i<size; i++){
			set.add((long)(rand.nextDouble()*range));
		}
		return set;
	}
	public static void main(String[] args) {
		for(int k=0; k<100; k++){
		ArrayList<Long> set = makeSet(1000);
		long residue;
		HillClimbingSubsetSum sumInstance = new HillClimbingSubsetSum();
		
		sumInstance.setTheSet(set);		
		residue = sumInstance.getResidue(25000000000000L, 1000);
		System.out.println(residue);
		}
	}
}
