package ru.job4j.chess.excepts;

/**Generates, when on the way is another figure.*/
public class OccupiedWayException extends RuntimeException {

	/**Sets a message of the exception.
	*@param info - info message*/
	public OccupiedWayException(String info) {
		super(info);
	}
}
