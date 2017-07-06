package ru.job4j.condition;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
*Class implements triangle with area calculate function.
*@author gimazetdinov
*@since 23.01.2017
*/
public class Triangle {

	/**Point a of a triangle.*/
	private Point a;
	/**Point b of a triangle.*/
	private Point b;
	/**Point c of a triangle.*/
	private Point c;

	/**
	*Constructor creates triangle with specified vertices.
	*@param a - point a;
	*@param b - point b;
	*@param c - point c;
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	*Method calculates area of triangle.
	*@return area - area
	*/
	public double area() {
		double sideA = new BigDecimal(this.a.distanceTo(this.b)).setScale(2, RoundingMode.UP).doubleValue();
		double sideB = new BigDecimal(this.b.distanceTo(this.c)).setScale(2, RoundingMode.UP).doubleValue();
		double sideC = new BigDecimal(this.c.distanceTo(this.a)).setScale(2, RoundingMode.UP).doubleValue();

		if (sideA == 0.0 || sideB == 0.0 || sideC == 0.0) {
			return Double.NaN;
		} else if (sideA + sideB == sideC) {
			return Double.NaN;
		} else if (sideB + sideC == sideA) {
			return Double.NaN;
		} else if (sideC + sideA == sideB) {
			return Double.NaN;
		}

		double semiperimeter = (sideA + sideB + sideC) / 2;

		return Math.sqrt(semiperimeter
								* (semiperimeter - sideA)
								* (semiperimeter - sideB)
								* (semiperimeter - sideC));
	}
}
