package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Player;
import ru.job4j.boardGame.State;

/**
 * Created by pacman on 31.07.17.
 * Abstract player to realize the possibility of exchange players in places.
 */
public class AbstractPlayer implements Player {

    /**
     * The player.
     */
    private Player player;

    /**
     * Sets the concrete player.
     *
     * @param player - player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * The player's moving.
     */
    @Override
    public void doMove() {
        this.player.doMove();
    }

    /**
     * Returns the state of the player.
     *
     * @return the current state.
     */
    @Override
    public State getState() {
        return this.player.getState();
    }

    /**
     * Returns player's name.
     *
     * @return name.
     */
    @Override
    public String toString() {
        return this.player.toString();
    }
}
