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
	
	public void findConvexHull(Point[] plist, Point pmin, Point pmax){
		if(plist.length == 0){
			return;
		}
		ArrayList<Point> leftList = new ArrayList<Point>();
		ArrayList<Point> rightList = new ArrayList<Point>();
		for(int i=0; i<plist.length; i++){//diving the plist to left or right
			int side = isLeft(pmin, pmax, plist[i]);
			if(side == 1){
				leftList.add(plist[i]);
			}else if(side == -1){
				rightList.add(plist[i]);
			}else{
				continue;
			}
		}
		Point[] leftArray = new Point[leftList.size()];
		Point[] rightArray = new Point[rightList.size()];
		Point left_maxDist = getMax_Distance(leftList.toArray(leftArray),pmin, pmax);
		Point right_maxDist = getMax_Distance(rightList.toArray(rightArray),pmin, pmax);
		if(left_maxDist != null)
			this.result.add(left_maxDist);
		if(right_maxDist != null)
			this.result.add(right_maxDist);
		
		//recursion happens here 
		findConvexHull(leftArray, pmin, left_maxDist);
		findConvexHull(leftArray, left_maxDist, pmax);
		findConvexHull(rightArray, pmax, right_maxDist);
		findConvexHull(rightArray, right_maxDist, pmin);
	}

	public static void main(String[] args){
//		float min = -20.0f;
//		float max = 20.0f;
//		Random rand = new Random();
		Point[] plist = new Point[5];
		plist[0] = new Point(-1,-1);
		plist[1] = new Point(0,0);
		plist[2] = new Point(1,1);
		plist[3] = new Point(1,-1);
		plist[4] = new Point(-1,1);
		
		System.out.println("Original Points:  " + plist.length);
//		for(int i=0; i<plist.length; i++){
//			Point temp = new Point(rand.nextInt(10),rand.nextInt(10));
//			plist[i] = temp;
//			temp.point_print();
//		}
		
		Seq_QuickHull qh = new Seq_QuickHull();
		
		Point pmin = qh.getMin_X(plist);
		Point pmax = qh.getMax_X(plist);
		qh.result.add(pmin);
		qh.result.add(pmax);
		
		qh.findConvexHull(plist, pmin, pmax);
		HashSet hs = new HashSet();
		hs.addAll(qh.result);
		qh.result.clear();
		qh.result.addAll(hs);
		System.out.println("Convex Hull Points: " + qh.result.size());
		for(int j=0; j<qh.result.size(); j++){
			qh.result.get(j).point_print();
		}
		
	}
}