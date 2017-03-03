package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;
import ru.job4j.chess.excepts.OccupiedWayException;

/**Describes to chess figure.*/
public abstract class Figure {

	/**The cell, that indicates the position of the figure on the desk.*/
	private final Cell position;

	/**Constructor sets a position of the figure.
	*@param position cell*/
	public Figure(Cell position) {
		this.position = position;
		this.position.setFill();
	}

	/**Getter of the current cell.
	*@return cell */
	public Cell getCell() {
		return this.position;
	}

	/**Clones the figure to destionation.
	*@param dest - destination
	*@return cloned figure*/
	public Figure clone(Cell dest) {
		this.position.setEmpty();
		this.position.setPosition(dest.getPosition());
		this.position.setFill();
		return this;
	}

	/**Checks, that the figure can take place in a given cell.
	*@param dest destination
	*@return array of cells, that must cross the figure
	*@throws ImpossibleMoveException - if the figure can not move so
	*@throws OccupiedWayException - if there is another figure on the way*/
	abstract Cell[] way(Cell dest) throws ImpossibleMoveException, OccupiedWayException;
}
