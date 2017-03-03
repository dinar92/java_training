package ru.job4j.chess.excepts;

/**Generates, when a figure not found in the given cell.*/
public class FigureNotFoundException extends RuntimeException {

	/**Sets a message of the exception.
	*@param info - info message*/
	public FigureNotFoundException(String info) {
		super(info);
	}
}
