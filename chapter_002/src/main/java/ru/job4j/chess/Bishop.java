package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;
import ru.job4j.chess.excepts.OccupiedWayException;
import static java.util.Arrays.copyOf;

/**A rook's implementation.*/
public class Bishop extends Figure {

	/**Sets a position.
	 *@param position - position*/
	Bishop(Cell position) {
		super(position);
	}

	/**Horizontal coordinate.*/
	private char horLine = this.getCell().getPosition().charAt(0);

	/**Vertical coordinate.*/
	private char vertLine = this.getCell().getPosition().charAt(1);

	/**The max size of the coordinate.*/
	private final int size = 8;

	/**The array of crossed cells.*/
	private Cell[] cells = new Cell[size];

	/**The counter of cells.*/
	private int counter = 0;

	/**Count of possible moves.*/
	private final int count = 16;

	/**Possible moves.*/
	private String[] possibleMoves = new String[count];

	/**The next position of a cell.*/
	private String nextPos;

	/**Return possible moves.
	 *@return array of possible moves.*/
	private String[] getPossibleMoves() {
		while (this.horLine > 'a' && this.vertLine < '8') {
			this.horLine--;
			this.vertLine++;
			this.possibleMoves[this.counter++] = String.format("%s%s", this.horLine, this.vertLine);
		}
		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		while (this.horLine < 'h' && this.vertLine < '8') {
			this.horLine++;
			this.vertLine++;
			this.possibleMoves[this.counter++] = String.format("%s%s", this.horLine, vertLine);
		}
		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		while (this.horLine < 'h' && this.vertLine > '1') {
			this.horLine++;
			this.vertLine--;
			this.possibleMoves[this.counter++] = String.format("%s%s", this.horLine, this.vertLine);
		}
		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		while (this.horLine > 'a' && this.vertLine > '1') {
			this.horLine--;
			this.vertLine--;
			this.possibleMoves[counter++] = String.format("%s%s", this.horLine, this.vertLine);
		}
		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		this.counter = 0;
		return this.possibleMoves;
	}

	/**Checks, that the figure can take place in a given cell.
	 *@param dest destination
	 *@return array of cells, that must cross the figure
	 *@throws ImpossibleMoveException - if the figure can not move so
	 *@throws OccupiedWayException - if there is another figure on the way*/
	public Cell[] way(Cell dest) throws ImpossibleMoveException, OccupiedWayException {

		boolean contains  = false;
		for (String position : this.getPossibleMoves()) {
			if (dest.getPosition().equals(position)) {
				contains = true;
				break;
			}
		}

		if (!contains) {
			throw new ImpossibleMoveException("This figure does not move so.");
		}

		this.cells[this.counter++] = this.getCell();
		if (this.cells[0].getPosition().equals(dest.getPosition())) {
			return copyOf(this.cells, this.counter);
		}

		while (this.horLine > 'a' && this.vertLine < '8') {
			this.horLine--;
			this.vertLine++;
			this.nextPos = String.format("%s%s", this.horLine, this.vertLine);
			Cell cell = new Cell(this.nextPos);
			if (cell.isEmpty()) {
				this.cells[this.counter++] = cell;
				if (this.nextPos.equals(dest.getPosition())) {
					return copyOf(this.cells, this.counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}


		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		this.counter = 1;

		while (this.horLine < 'h' && this.vertLine < '8') {
			this.horLine++;
			this.vertLine++;
			this.nextPos = String.format("%s%s", this.horLine, this.vertLine);
			Cell cell = new Cell(this.nextPos);
			if (cell.isEmpty()) {
				this.cells[this.counter++] = cell;
				if (this.nextPos.equals(dest.getPosition())) {
					return copyOf(this.cells, this.counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}

		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		this.counter = 1;

		while (this.horLine < 'h' && this.vertLine > '1') {
			this.horLine++;
			this.vertLine--;
			this.nextPos = String.format("%s%s", this.horLine, this.vertLine);
			Cell cell = new Cell(this.nextPos);
			if (cell.isEmpty()) {
				this.cells[this.counter++] = cell;
				if (this.nextPos.equals(dest.getPosition())) {
					return copyOf(this.cells, this.counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}

		this.horLine = this.getCell().getPosition().charAt(0);
		this.vertLine = this.getCell().getPosition().charAt(1);
		this.counter = 1;

		while (this.horLine > 'a' && this.vertLine > '1') {
			this.horLine--;
			this.vertLine--;
			this.nextPos = String.format("%s%s", this.horLine, this.vertLine);
			Cell cell = new Cell(this.nextPos);
			if (cell.isEmpty()) {
				this.cells[this.counter++] = cell;
				if (this.nextPos.equals(dest.getPosition())) {
					return copyOf(this.cells, this.counter);
				}
			} else {
				throw new OccupiedWayException("On the way is another figure.");
			}
		}
		return this.cells;
	}
}
