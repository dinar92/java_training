package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**Tests Cell.*/
public class CellTest {

    /**Tests hashCode().*/
    @Test
    public void whenEqualCellsThenEqualHashCode() {
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(1, 2);
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    /**Tests equals().*/
    @Test
    public void whenCellsEqualsThenTrue() {
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(1, 2);
        assertThat(cell1.equals(cell2), is(true));
    }
}
