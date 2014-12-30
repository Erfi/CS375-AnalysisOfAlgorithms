//Sam Goldberg, Ian MacClancy, Erfan Azad
//Analysis of Algorithms

import java.util.*;
import java.lang.Math;


public class RandomSubsetSum{
	
	
	public long randomSubsetSum(long n, ArrayList<Long> list, int iterations){
		//Create generator and variables
		Random ranGen = new Random();
		ArrayList<Long> resList = new ArrayList<Long>();
		
		int setInd = 0;
		int total = 0;
		Long minRes = Long.MAX_VALUE;
		
		//For loop to create random subsets, precisely as many as "iterations"
		for(int i = 0; i<iterations; i++){
			total = 0;
			int subSize = ranGen.nextInt(list.size());
			ArrayList<Integer> indicies = new ArrayList<Integer>();
			ArrayList<Long> subSet = new ArrayList<Long>();
			//For Loop to create each individual subset
			for(int j = 0; j<subSize; j++){
				int subInd = ranGen.nextInt(list.size()-1);
				if (indicies.contains(subInd)){
					j--;
					continue;
				}
				subSet.add(list.get(subInd));
				total+=list.get(subInd);
				indicies.add(subInd);
			}
			resList.add(Math.abs(n - total));
		}
		//Loop to go through reslist and check for the min
		for(int k = 0; k < resList.size(); k++){
			if(minRes>resList.get(k))
				minRes = resList.get(k);
		}
		return minRes;
	}
	
	//runs the algorithm and return a boolean result
	//return true if the sum is found within the set
	//False if the sum cannot be found in the set
	public boolean run(long k, ArrayList<Long> s){
		long result = randomSubsetSum(k,s,20);
		if(result == 0){
			return true;
		}
		else{
			return false;
		}
	}
}