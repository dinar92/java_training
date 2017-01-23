package ru.job4j;

/**
* Class Calculator implementation.
* @author gimazetdinov
* @since 18.01.2017
*/
public class Calculator {
	/**
	*@value result of arithmetical expression
	*/
	private double result;

	/**
	*Result getter.
	*@return double type result of arithmetical expression
	*/
	public double getResult() {
		return this.result;
	}

	/**
	*Sum of two double type parameters.
	*@param first first double parameter
	*@param second second double parameter
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	*Substraction operation.
	*@param first first double parameter
	*@param second second double parameter
	*/
	public void substract(double first, double second) {
		this.result = first - second;
	}


	/**
	*Division operation.
	*@param first first double parameter
	*@param second second double parameter
	*@throws ArithmeticException when division by zero
	*/
	public void div(double first, double second) throws ArithmeticException {
		if (second == 0.0) {
			throw new ArithmeticException();
		}
		this.result = first / second;
	}

	/**
	*Multiplication operation.
	*@param first first double parameter
	*@param second second double parameter
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
}
