package ru.job4j.chess;

import ru.job4j.chess.excepts.FigureNotFoundException;
import ru.job4j.chess.excepts.ImpossibleMoveException;
import ru.job4j.chess.excepts.OccupiedWayException;

/**The chess board implementattion.*/
public class Board {

	/**Amount of figures.*/
	private final int amountOfFigures = 32;

	/**The container for figures.*/
	private Figure[] figures = new Figure[amountOfFigures];

	/**Figures counter.*/
	private int counter = 0;

	/**Adds new figure.
	*@param figure figure*/
	public void addFigure(Figure figure) {
		this.figures[this.counter++] = figure;
	}

	/**Makes chess move.
	*@param source -source cell
	*@param dest - destination cell
	*@return success or not
	*@throws ImpossibleMoveException - if the figure can not move so
	*@throws OccupiedWayException - if there is another figure on the way
	*@throws FigureNotFoundException - if source cell is empty*/
	public boolean move(Cell source, Cell dest) throws FigureNotFoundException, OccupiedWayException, ImpossibleMoveException {
		if (!source.isEmpty()) {
			for (int i = 0; i < this.counter; i++) {
				if (source.getPosition().equals(this.figures[i].way(dest)[0].getPosition())) {
					this.figures[i].clone(dest);
					return true;
				}
			}
		} else {
			throw new FigureNotFoundException("A figure is not found in the given cell.");
		}
		return false;
	}
}
