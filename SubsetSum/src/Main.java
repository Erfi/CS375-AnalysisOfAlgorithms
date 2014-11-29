
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.*;

public class Main {

	public static void main(String[] args){
		RandomSubsetSum s = new RandomSubsetSum();
		SimulatedAnnealingSum sa = new SimulatedAnnealingSum();
		ArrayList<Long> listSa = new ArrayList<Long>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random ranGen = new Random();
		//creates input array for SA algo
		for (int i=0; i<100; i++){
			listSa.add(ranGen.nextLong());
		}
		//creates input array for random algo
		for (int i=0; i<100; i++){
			list.add(ranGen.nextInt(100));
		}
		
		int j = 100;
		int its = 10000;
		long start = System.nanoTime();
		int k = s.randomSubsetSum(j, list, j);
		long end = System.nanoTime();
		System.out.println("Smallest Residue: " + k);
		System.out.println("Time of random algo = "+(end-start)+"\nwhere k = "+j+", for " + its + " iterations");
		
		long n = 25000000000000L;
		long start2 = System.nanoTime();
		long g = sa.SASum(n, listSa, 100);
		long end2 = System.nanoTime();
		System.out.println("Smallest Residue: " + g);
		System.out.println("Time of Simulated Annealing algo = "+ (end2-start2));
		
		
	}
}
