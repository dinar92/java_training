package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;
import ru.job4j.chess.excepts.OccupiedWayException;

import static java.util.Arrays.copyOf;

/**A bishop's implementation.*/
public class Bishop extends Figure {

	/**Sets a position.
	*@param position - position*/
	public Bishop(Cell position) {
		super(position);
	}

	/**Checks, that the figure can take place in a given cell.
	*@param dest destination
	*@return array of cells, that must cross the figure
	*@throws ImpossibleMoveException - if the figure can not move so
	*@throws OccupiedWayException - if there is another figure on the way*/
	public Cell[] way(Cell dest) throws ImpossibleMoveException, OccupiedWayException {

		/**Horizontal coordinate.*/
		char horLine = this.getCell().getPosition().charAt(0);

		/**Vertical coordinate.*/
		char vertLine = this.getCell().getPosition().charAt(1);

		/**The max size of the coordinate.*/
		final int size = 8;

		/**The array of crossed cells.*/
		Cell[] cells = new Cell[size];

		/**The counter of cells.*/
		int counter = 0;

		/**The next position of a cell.*/
		String nextPos = null;

		cells[counter++] = this.getCell();
		if (cells[0].getPosition().equals(dest.getPosition())) {
			return copyOf(cells, counter);
		}

		while (horLine > 'a' && vertLine < '8') {
			horLine--;
			vertLine++;
			nextPos = String.format("%s%s", horLine, vertLine);
			Cell cell = new Cell(nextPos);
			if (cell.isEmpty()) {
				cells[counter++] = cell;
				if (nextPos.equals(dest.getPosition())) {
					return copyOf(cells, counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}


		horLine = this.getCell().getPosition().charAt(0);
		vertLine = this.getCell().getPosition().charAt(1);
		counter = 1;

		while (horLine < 'h' && vertLine < '8') {
			horLine++;
			vertLine++;
			nextPos = String.format("%s%s", horLine, vertLine);
			Cell cell = new Cell(nextPos);
			if (cell.isEmpty()) {
				cells[counter++] = cell;
				if (nextPos.equals(dest.getPosition())) {
					return copyOf(cells, counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}

		horLine = this.getCell().getPosition().charAt(0);
		vertLine = this.getCell().getPosition().charAt(1);
		counter = 1;

		while (horLine < 'h' && vertLine > '1') {
			horLine++;
			vertLine--;
			nextPos = String.format("%s%s", horLine, vertLine);
			Cell cell = new Cell(nextPos);
			if (cell.isEmpty()) {
				cells[counter++] = cell;
				if (nextPos.equals(dest.getPosition())) {
					return copyOf(cells, counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}

		horLine = this.getCell().getPosition().charAt(0);
		vertLine = this.getCell().getPosition().charAt(1);
		counter = 1;

		while (horLine > 'a' && vertLine > '1') {
			horLine--;
			vertLine--;
			nextPos = String.format("%s%s", horLine, vertLine);
			Cell cell = new Cell(nextPos);
			if (cell.isEmpty()) {
				cells[counter++] = cell;
				if (nextPos.equals(dest.getPosition())) {
					return copyOf(cells, counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}

		throw new ImpossibleMoveException("This figure does not move so.");
	}
}
