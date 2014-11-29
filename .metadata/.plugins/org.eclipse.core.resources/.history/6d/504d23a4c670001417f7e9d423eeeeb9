/*
 * Erfan Azad
 * Date: 19 November 2014
 * This is the driver class for the two exact algorithms. [Dynamic and Exhustive]
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ExactDriver {
	
	
	public static int[] makeSet(int size){
		Random rand = new Random();
		int[] set = new int[size];
		for(int i=0; i<size; i++){
			set[i] = rand.nextInt(50);
		}
		return set;
	}
	
	public static void main(String[] args) throws IOException, UnsupportedEncodingException {
		DynamicSubsetSum dynamic = new DynamicSubsetSum();
		ExhaustiveSubsetSum exhustive = new ExhaustiveSubsetSum();
		PrintWriter writer1 = new PrintWriter("file1.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("file2.txt", "UTF-8");
		PrintWriter writer3 = new PrintWriter("file3.txt", "UTF-8");
		
		
		for(int i=0; i<70; i++){
			int[] set = makeSet(100);
			int sum = 10*i;
			long dynamicTime;
			long exhustiveTime;


			Timer.start();
			boolean dynamicResult = dynamic.subsetExists(set,sum);
			Timer.stop();
			dynamicTime = Timer.getRunTime();

			Timer.start();
			boolean exhustiveResult = exhustive.subsetExists(set, set.length, sum);
			Timer.stop();
			exhustiveTime = Timer.getRunTime();

//			if(dynamicResult){
//				System.out.println("Dynamic: Set Exists. time: "+dynamicTime + " ms");
//			}else{
//				System.out.println("Dynamic: Set Does Not Exist. time: "+dynamicTime + " ms");
//			}
//
//			if(exhustiveResult){
//				System.out.println("Exhustive: Set Exists. time: "+exhustiveTime + " ms");
//			}else{
//				System.out.println("Exhustive: Set Does Not Exist. time: "+exhustiveTime + " ms");
//			}
			System.out.println(sum + " " +dynamicTime +" " + exhustiveTime);
			writer1.println(sum);
			writer2.println(dynamicTime);
			writer3.println(exhustiveTime);
		}
		writer1.close();
		writer2.close();
		writer3.close();
	}

}
