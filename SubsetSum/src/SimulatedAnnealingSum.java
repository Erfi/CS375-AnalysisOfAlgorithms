//Sam Goldberg, Ian MacClancy, Erfan Azad
//Analysis of Algorithms

import java.util.*;
import java.lang.Math;
import java.lang.Long;


public class SimulatedAnnealingSum{
	
	
	
	public long getRes(long n, ArrayList<Long> subset){
		long total = 0L;
		for(int i=0; i<subset.size()-1; i++){
			total+=(long)subset.get(i);
		}
		return Math.abs(((long)(n-total)));
	}
	
	public ArrayList<Long> randNeighbor(ArrayList<Long> set, ArrayList<Long> subSet){
		ArrayList<Long> clone = new ArrayList<Long>();
		Random ranGen = new Random();
		clone = (ArrayList<Long>)subSet.clone();
		int i = ranGen.nextInt(subSet.size()-1);
		int j = ranGen.nextInt(subSet.size()-1);
		while (j != i){
			j = ranGen.nextInt(set.size()-1);
		}
		if (subSet.contains(set.get(i))){
			clone.remove(i);
		}
		else
			clone.add(set.get(i));
		if(subSet.contains(set.get(j))){
			if(ranGen.nextDouble()>=0.5){
				clone.remove(j);
			}
		}
		else{
			if(ranGen.nextDouble()>=0.5){
				clone.add(set.get(j));
			}
		}
		return clone;
	}
	
	public long SASum(long n, int iterations, ArrayList<Long> list){
		//Create generator and variables
		Random ranGen = new Random();
		ArrayList<Long> subSet = new ArrayList<Long>();
		ArrayList<Long> resList = new ArrayList<Long>();
		long minRes = Long.MAX_VALUE;
		//resList.add(getRes(n, subSet));
		for (int i = 0; i<list.size()-1; i++){
			if(ranGen.nextDouble()>=0.5)
				subSet.add(list.get(i));
		}
		for (double i=0.0; i<iterations; i+=1.0){
			ArrayList<Long> newNeigh = (ArrayList<Long>)randNeighbor(list, subSet);
			if(getRes(n, newNeigh) < getRes(n, subSet)){
				subSet = (ArrayList<Long>)newNeigh.clone();
			}
			else{
				double pow = (Math.pow(0.8,(i/300.0)));
				long denom = (10000000000L * (long)pow);
				long pe = Math.round((Math.pow(0.8,(i/300.0))));
				long denom2 = (10000000000L * pe);
				if (denom == 0){
					denom = 1;
				}
				long t = ((getRes(n,newNeigh) - getRes(n,subSet)) / denom);
				if(ranGen.nextDouble() <= Math.pow(Math.E, -1*t))
					subSet = (ArrayList<Long>)newNeigh.clone();
			}
			resList.add( (long)getRes(n, subSet) );
		}
		
		//Loop to go through reslist and check for the min
		for(int k = 0; k < resList.size(); k++){
			if(minRes>resList.get(k))
				minRes = (long)resList.get(k);
		}
		return minRes;
		
	}
	
	//runs the algorithm and return a boolean result
	//return true if the sum is found within the set
	//False if the sum cannot be found in the set
	public boolean run(long k, int iterations, ArrayList<Long> list){
		long result = SASum(k, iterations, list);
		if(result == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	
	
}	