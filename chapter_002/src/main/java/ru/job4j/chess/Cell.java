package ru.job4j.chess;

/**A cell implementation.*/
public class Cell {

	/**A position of the cell.*/
	private String position;

	/**Sets a position of the cell.
	*@param position - position*/
	public Cell(String position) {
		this.position = position;
	}

	/**The desk stores information about filling cells.*/
	private static boolean[][] desk = {{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true}};

	/**On occupancy inspection.
	*@return true if is empty, false if filled*/
	public boolean isEmpty() {
		int vertLine = convertToPos(this.position)[0];
		int horizontLine = convertToPos(this.position)[1];
		return desk[vertLine][horizontLine];
	}

	/**Getter of cell's position.
	*@return position of the cell*/
	public String getPosition() {
		return this.position;
	}

	/**Setter of cell's position.
	*@param pos - position of the cell*/
	public void setPosition(String pos) {
		this.position = pos;
	}

	/**Converts a string format to coords of the desk.
	*@param position - position of the cell
	*@return array of two integers, that form coords*/
	public int[] convertToPos(String position) {

		/**Contains coords.*/
		int[] pos = new int[2];

		/**Describes the horizont coords on the chess board.*/
		char horizontLine = position.charAt(0);

		/**Describes the horizont coordinate in integer format.*/
		int horizontLineInt = 0;

		/**Specified radix.*/
		final int radix = 10;

		/**One.*/
		final int one = 1;
		/**Two.*/
		final int two = 2;
		/**Three.*/
		final int three = 3;
		/**Four.*/
		final int four = 4;
		/**Five.*/
		final int five = 5;
		/**Six.*/
		final int six = 6;
		/**Seven.*/
		final int seven = 7;
		/**Eight.*/
		final int eight = 8;

		if (horizontLine == 'a') {
			horizontLineInt = one;
		} else if (horizontLine == 'b') {
			horizontLineInt = two;
		} else if (horizontLine == 'c') {
			horizontLineInt = three;
		} else if (horizontLine == 'd') {
			horizontLineInt = four;
		} else if (horizontLine == 'e') {
			horizontLineInt = five;
		} else if (horizontLine == 'f') {
			horizontLineInt = six;
		} else if (horizontLine == 'g') {
			horizontLineInt = seven;
		} else if (horizontLine == 'h') {
			horizontLineInt = eight;
		}

		pos[0] = (Character.digit(position.charAt(1), radix)) - 1;
		pos[1] = horizontLineInt - 1;
		return pos;
	}

	/**Sets empty cell in a specified possition.*/
	public void setEmpty() {
		int vertLine = convertToPos(this.position)[0];
		int horizontLine = convertToPos(this.position)[1];
		desk[vertLine][horizontLine] = true;
	}

	/**Sets filled cell in a specified possition.*/
	public void setFill() {
		int vertLine = convertToPos(this.position)[0];
		int horizontLine = convertToPos(this.position)[1];
		desk[vertLine][horizontLine] = false;
	}

}
