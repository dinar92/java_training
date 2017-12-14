package ru.job4j.multithread.bomberman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Tests for class Hero.
 */
public class HeroTest {

    /**
     * X-axis start position of the hero on a board.
     */
    private static final int X_AXIS_START_POSITION = 3;

    /**
     * Y-axis start position of the hero on a board.
     */
    private static final int Y_AXIS_START_POSITION = 3;

    /**
     * Size of a side of a board.
     */
    private static final int SIZE_OF_BOARD = 7;

    /**
     * The game board.
     */
    private final BlockBoard board = new BlockBoard(SIZE_OF_BOARD);

    /**
     * Waiting time.
     */
    private static final long TIME_IN_MILLIS = 1000L;

    /**
     * The instance of the hero.
     */
    private static Hero hero = null;

    /**
     * Initializes the board and new hero before tests.
     */
    @Before
    public void boardAndHeroInit() {
        board.init();
        try {
            hero = new Hero(board, X_AXIS_START_POSITION, Y_AXIS_START_POSITION);
            hero.start();
        } catch (BoardIsNotInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kills the old hero.
     */
    @After
    public void killHero() {
        try {
            hero.stopMovement();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the result of moving to the left.
     */
    @Test
    public void whenMoveToLeftThenDoMove() {
        try {
            hero.moveLeft();
            Thread.currentThread().sleep(TIME_IN_MILLIS);  //Waits while hero moving.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = hero.getPosition();
        Assert.assertThat(position[0], is(X_AXIS_START_POSITION - 1));
        Assert.assertThat(position[1], is(Y_AXIS_START_POSITION));
    }

    /**
     * Checks the result of moving to the right.
     */
    @Test
    public void whenMoveToRightThenDoMove() {
        try {
            hero.moveRight();
            Thread.currentThread().sleep(TIME_IN_MILLIS);  //Waits while hero moving.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = hero.getPosition();
        Assert.assertThat(position[1], is(Y_AXIS_START_POSITION));
        Assert.assertThat(position[0], is(X_AXIS_START_POSITION + 1));
    }

    /**
     * Checks the result of moving to the up.
     */
    @Test
    public void whenMoveToUpThenDoMove() {
        try {
            hero.moveUp();
            Thread.currentThread().sleep(TIME_IN_MILLIS);  //Waits while hero moving.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = hero.getPosition();
        Assert.assertThat(position[1], is(Y_AXIS_START_POSITION + 1));
        Assert.assertThat(position[0], is(X_AXIS_START_POSITION));
    }

    /**
     * Checks the result of moving to the down.
     */
    @Test
    public void whenMoveToDownThenDoMove() {
        try {
            hero.moveDown();
            Thread.currentThread().sleep(TIME_IN_MILLIS);  //Waits while hero moving.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = hero.getPosition();
        Assert.assertThat(position[1], is(Y_AXIS_START_POSITION - 1));
        Assert.assertThat(position[0], is(X_AXIS_START_POSITION));
    }

    /**
     * When the movement is impossible, then the hero remains in place.
     */
    @Test
    public void whenMoveToLeftImpossibleThenInPlace() {
        Hero newHero = null;
        try {
            newHero = new Hero(this.board, 0, 0);
            newHero.moveLeft();
        } catch (BoardIsNotInitException | InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = newHero.getPosition();
        Assert.assertThat(position[0], is(0));
        Assert.assertThat(position[1], is(0));
    }

    /**
     * When the movement is impossible, then the hero remains in place.
     */
    @Test
    public void whenMoveToUpImpossibleThenInPlace() {
        Hero newHero = null;
        try {
            newHero = new Hero(this.board, 0, 0);
            newHero.moveUp();
        } catch (BoardIsNotInitException | InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = newHero.getPosition();
        Assert.assertThat(position[0], is(0));
        Assert.assertThat(position[1], is(0));
    }

    /**
     * When the movement is impossible, then the hero remains in place.
     */
    @Test
    public void whenMoveToRightImpossibleThenInPlace() {
        Hero newHero = null;
        try {
            newHero = new Hero(this.board, 6, 6);
            newHero.moveRight();
        } catch (BoardIsNotInitException | InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = newHero.getPosition();
        Assert.assertThat(position[0], is(6));
        Assert.assertThat(position[1], is(6));
    }

    /**
     * When the movement is impossible, then the hero remains in place.
     */
    @Test
    public void whenMoveToDownImpossibleThenInPlace() {
        Hero newHero = null;
        try {
            newHero = new Hero(this.board, 6, 6);
            newHero.moveDown();
        } catch (BoardIsNotInitException | InterruptedException e) {
            e.printStackTrace();
        }
        int[] position = newHero.getPosition();
        Assert.assertThat(position[0], is(6));
        Assert.assertThat(position[1], is(6));
    }
}