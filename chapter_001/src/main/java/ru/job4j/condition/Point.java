package ru.job4j.condition;

/**
*Class implements point in the coordinate system.
*@author gimazetdinov
*@version 1.0
*@since 23.01.2017
*/
public class Point {

	/**x point.*/
	private double x;

	/**y point.*/
	private double y;

	/**
	*Constructor creates new specified point.
	*@param x - x point
 	*@param y - y point
	*/
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	*Method calculates a distance between current point and specified point.
	*@param point - specified point
	*@return distance to specified point
	*/
	public double distanceTo(Point point) {
		int exponent = 2;
		return Math.sqrt(Math.pow(this.x - point.x, exponent) + Math.pow(this.y - point.y, exponent));
	}
}
