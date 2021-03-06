/*
 * Erfan Azad
 * Date: 3 November 2014
 * File: Seq_QuickHull.java
 * Description: This file contains methods for solving the quick hull problem sequentially. 
 */

import java.util.*;

public class Seq_QuickHull {
	
	ArrayList<Point> result = new ArrayList<Point>();
	
	//returns the point with the maximum x value in plist of Points
	public Point getMax_X(Point[] plist){
		Point p = plist[0];
		for(int i=0; i<plist.length; i++){
			if(plist[i].x > p.x){
				p = plist[i];
			}
		}
		return p;
	}
	
	//returns the point with the minimum x value in plist of Points
	public Point getMin_X(Point[] plist){
		Point p = plist[0];
		for(int i=0; i<plist.length; i++){
			if(plist[i].x < p.x){
				p = plist[i];
			}
		}
		return p;
	}
	
	//determines coefficients A,B,C in Ax+By+C=0 given input line given two points a and b
	public float[] getCoefficients(Point a, Point b){
		float[] c = new float[3];
		c[0] = (b.y - a.y);
		c[1] = -(b.x - a.x);
		c[2] = -((b.y - a.y)*a.x -(b.x - a.x)*a.y);
		return c;
	}
	
	//returns the distance of c from a line ab.
	public float getDistance(Point a, Point b, Point c ){
		float[] coeffs = getCoefficients(a, b);
		float numerator = Math.abs((coeffs[0]*c.x)+(coeffs[1]*c.y)+(coeffs[2]));
		float denumerator = (float) Math.sqrt((coeffs[0]*coeffs[0]) + (coeffs[1]*coeffs[1]));
		float distance = numerator/denumerator;
		return distance;
	}
	
	public Point getMax_Distance(Point[] plist, Point a, Point b){
		if(plist.length == 0){
			return null;
		}
		Point p = plist[0];
		for(int i=0; i<plist.length; i++){
			if(getDistance(a,b,plist[i])>getDistance(a,b,p)){
				p = plist[i];
			}
		}
		return p;
	}
	
	//returns 1 if on left
	//returns -1 if on right
	//returns 0 if on the line
	public int isLeft(Point a, Point b, Point c) {
		//takes cross product to tell if a point is left of a line
		if(((b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x)) > 0.0){
			return 1;
		}else if(((b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x)) < 0.0){
			return -1;
		}else{
			return 0;
		}
	}
	
	public void findTopConvexHull(Point[] plist, Point pmin, Point pmax){
		if(plist.length == 0){
			return;
		}
		ArrayList<Point> leftList = new ArrayList<Point>();
		for(int i=0; i<plist.length; i++){//diving the plist to left or right
			int side = isLeft(pmin, pmax, plist[i]);
			if(side == 1){
				leftList.add(plist[i]);
			}else{
				continue;
			}
		}
		Point[] leftArray = new Point[leftList.size()];
		leftList.toArray(leftArray);
		Point leftPoint_maxDist = getMax_Distance(leftArray,pmin, pmax);
		
		if(leftPoint_maxDist != null)
			this.result.add(leftPoint_maxDist);
		
		//recursion happens here 
		findTopConvexHull(leftArray, pmin, leftPoint_maxDist);
		findTopConvexHull(leftArray, leftPoint_maxDist, pmax);
	}
	
	public void findBottomConvexHull(Point[] plist, Point pmin, Point pmax){
		if(plist.length == 0){
			return;
		}
		ArrayList<Point> rightList = new ArrayList<Point>();
		for(int i=0; i<plist.length; i++){//diving the plist to left or right
			int side = isLeft(pmin, pmax, plist[i]);
			if(side == -1){//if on the right side of the line
				rightList.add(plist[i]);
			}else{
				continue;
			}
		}
		Point[] rightArray = new Point[rightList.size()];
		rightList.toArray(rightArray);
		Point rightPoint_maxDist = getMax_Distance(rightArray,pmin, pmax);
		
		if(rightPoint_maxDist != null)
			this.result.add(rightPoint_maxDist);
		
		//recursion happens here 
		findBottomConvexHull(rightArray, pmin, rightPoint_maxDist);
		findBottomConvexHull(rightArray, rightPoint_maxDist, pmax);
	}

	public static void main(String[] args){
		//creating the list of points to test
		float min = -20.0f;
		float max = 20.0f;
		Random rand = new Random();
		Point[] plist = new Point[50];
		System.out.println("Original Points:  " + plist.length);
		for(int i=0; i<plist.length; i++){
			Point temp = new Point(rand.nextFloat()*(max - min)+min,rand.nextFloat()*(max - min)+min);
			plist[i] = temp;
			temp.point_print();
		}
		
		//creating a new instance of the problem
		Seq_QuickHull qh = new Seq_QuickHull();
		
		//pre-processing 
		Point pmin = qh.getMin_X(plist);
		Point pmax = qh.getMax_X(plist);
		qh.result.add(pmin);
		qh.result.add(pmax);
		
		//function call for the top and bottom part
		qh.findTopConvexHull(plist, pmin, pmax);
		qh.findBottomConvexHull(plist, pmin, pmax);
		
		//printing the result
		System.out.println("\nConvex Hull Points: " + qh.result.size());
		for(int j=0; j<qh.result.size(); j++){
			qh.result.get(j).point_print();
		}
		
	}
}
