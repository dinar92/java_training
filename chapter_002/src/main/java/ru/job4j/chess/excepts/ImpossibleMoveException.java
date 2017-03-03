package ru.job4j.chess.excepts;

/**Generates, when a figure does not move so.*/
public class ImpossibleMoveException extends RuntimeException {

	/**Sets a message of the exception.
	*@param info - info message*/
	public ImpossibleMoveException(String info) {
		super(info);
	}
}
