import java.util.*;

public class HillClimbingSubsetSum {
	ArrayList<Integer> set = new ArrayList<Integer>();
	Random rand;

	public HillClimbingSubsetSum(int[] preset){
		for(int i=0; i<preset.length; i++){//create an arraylist from the int[] 
			set.add(preset[i]);
		}
		rand = new Random();
	}
	
	@SuppressWarnings("unchecked")
	public  ArrayList<Integer> getNeighbor(ArrayList<Integer> s){
		ArrayList<Integer> t = new ArrayList<Integer>(); 
		t = (ArrayList<Integer>) s.clone();
		int a, b;
		a = rand.nextInt(set.size());
		b = rand.nextInt(set.size());
		while(a == b){
			b = rand.nextInt(set.size());
		}
		Integer x1 = set.get(a);
		Integer x2 = set.get(b);
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
	
	public int getSum(ArrayList<Integer> set){
		int sum = 0;
		for(int i=0; i<set.size(); i++){
			sum += set.get(i);
		}
		return sum;
	}
	
	public ArrayList<Integer> getRandomSubset(){
		int bound = rand.nextInt(set.size());
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i=0; i<bound; i++ ){
			temp.add(set.get(i));
		}
		return temp;
	}
	
	public int getResidue(int sum, int numIterations){
		int minResidue;
		ArrayList<Integer> current = getRandomSubset();
		minResidue = Math.abs(sum - getSum(current));
		for(int i=0; i< numIterations; i++){
			ArrayList<Integer> temp = getNeighbor(current);
			int tempResidue = Math.abs(getSum(temp) - sum);
			if(tempResidue < minResidue){
				current = temp;
				minResidue = tempResidue;
			}	
		}
		return minResidue;
	}
	
	public static void main(String[] args) {
		int[] set = {1,3,4,5,6,7,8,0,45,6,7,5,2,3,35,14,3,12,7,22,06,1,2,3,5,2,3,24,7,34,35};
		int residue;
		HillClimbingSubsetSum sumInstance = new HillClimbingSubsetSum(set);
		residue = sumInstance.getResidue(54, 200);
		System.out.println(residue);
	}
}
