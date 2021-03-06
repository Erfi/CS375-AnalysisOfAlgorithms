/*
 * Erfan Azad
 * Date: 3 November 2014
 * File: point.java
 * Description: This file makes a point class to be used for solving the Convex Hull problem
 */
public class Point {
	float x;
	float y;
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void point_print(){
		System.out.printf("( %.2f , %.2f )\n", this.x, this.y);
	}
}
