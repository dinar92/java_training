package ru.job4j.chess;

/**
 * A chess board's cell.
 * @author gimazetdinov
 * @version 1.1
 */
class Cell {

    /**The horizontal coordinate.*/
    final int horCoord;

    /**The vertical coordinate.*/
    final int vertCoord;

    /**Sets coordinates.
     * @param horCoord - the horizontal coordinate
     * @param vertCoord - the vertical coordinate*/
    Cell(int horCoord, int vertCoord) {
        this.horCoord = horCoord;
        this.vertCoord = vertCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (this.horCoord != cell.horCoord) {
            return false;
        }
        return this.vertCoord == cell.vertCoord;
    }

    @Override
    public int hashCode() {
        int result = horCoord;
        result = 31 * result + vertCoord;
        return result;
    }
}
