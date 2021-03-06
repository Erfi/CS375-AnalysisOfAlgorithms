import java.util.*;

/* 1)Find the points with minimum and maximum x coordinates, those are 
* bound to be part of the convex hull.
* 2) Use the line formed by the two points to divide the set in two 
* subsets of points, which will be processed recursively.
* distance from the line. The two points found before along with this 
* one form a triangle.
* 4) The points lying inside of that triangle cannot be part of the 
* convex hull and can therefore be ignored in the next steps.
* 5) Repeat the previous two steps on the two lines formed by the 
* triangle (not the initial line).
* 6) Keep on doing so on until no more points are left, the recursion 
* has come to an end and the points selected constitute the convex 
* hull.
*/

public abstract class QuickHull {
	
	//Coefficients from Ax + By + C = 0
	
	public static float[] computeStandardForm(float x1, float y1, float x2, float y2) {
		//determines coefficients A,B,C in Ax+By+C=0 given input line
		
		//(x1, y1) and (x2,y2) are points on the dividing line. (x3,y3) is the point in question
		
		//Get Ax + By + C = 0
		//compute standard form equation from points
		//1) compute slope
		float coef1, coef2, coef3;
		float a,b,c;
		float standardForm[] = new float[3];
		//y-coef1 = slope(x-coef2)
		//--> y-coef1 = slope(x) + (-slope(coef2))
		float slope = ((x1-x2)/(y1-y2));
		
		System.out.println(slope);
		
		a = -slope;
		standardForm[0] = a;
		
		coef2 = (slope*(-x1));
		coef1 = (y1);
		
		//y = Ax + coef3
		
		coef3 = coef2+coef1;
		
		//y-Ax-coef3 = 0 <==> Ax + By + C = 0
		b = 1;
		standardForm[1] = b;
		
		c = -(coef3);
		standardForm[2] = c;
		
		System.out.println(Arrays.toString(standardForm));
		
		return standardForm;	
	}
	
	public static float computeDistanceFromLine(float x1, float y1, float x2, float y2, float x3, float y3) {
		//x1,y1,x2,y2 all part of line,
		//x3,y3 is the point that is being compared to the line
		float standardForm[] = computeStandardForm(x1,y1,x2,y2);
		//gives you A,B,C in Ax+By+C=0
		
		//formula: distance from point (m,n) to line Ax+By+C=0 given by:
		//d=(abs(Am+Bn+C)/((A^2+B^2)^1/2))
		float Am = standardForm[0]*x3;
		float Bn = standardForm[1]*y3;
		float C = standardForm[2];
		
		float Asq = standardForm[0]*standardForm[0];
		float Bsq = standardForm[1]*standardForm[1];
		
		float distance = (float) ((Math.abs(Am+Bn+C))/(Math.pow(Asq+Bsq, 1/2)));
		
		return distance;
		
		
	}
	
	public boolean isLeft(float x1, float y1, float x2, float y2, float x3, float y3) {
		//takes cross product to tell if a point is left of a line
		return ((x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)) > 0;
	}
	
	public static void main(String[] args) {
			computeStandardForm(-1,-1,1,5);
	}
	
}
