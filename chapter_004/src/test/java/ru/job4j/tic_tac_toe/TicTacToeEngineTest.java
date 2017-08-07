package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;


/**
 * Created by pacman on 26.07.17.
 * Tests class TicTacToeEngine.
 */
public class TicTacToeEngineTest {

    /**
     * Tests init().
     */
    @Test
    public void whenInitDefaultGameStagesThenPutThemInStoreOfStages() {
        TicTacToeEngine engine = new TicTacToeEngine(new ConsoleInput(), new ConsoleDisplay());
        int  countOfStages = 6;

        engine.init();

        Assert.assertThat(engine.stages.size(), is(countOfStages));
    }

    /**
     * Tests start().
     */
    @Test
    public void whenStartThenDoOperationsOfAllStages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ConsoleInput input = new ConsoleInput();
        ConsoleDisplay display = new ConsoleDisplay();
        TicTacToeEngine engine = new TicTacToeEngine(input, display);
        engine.stages.add(new Stage() {
            @Override
            public void showInfo() {
                display.showln("Info1");
            }

            @Override
            public void action() {
                display.showln("Action1");
            }
        });
        engine.stages.add(new Stage() {
            @Override
            public void showInfo() {
                display.showln("Info2");
            }

            @Override
            public void action() {
                display.showln("Action2");
            }
        });

        String expectOutToConsole = "Info1\nAction1\nInfo2\nAction2\n";

        engine.start();

        Assert.assertThat(out.toString(), is(expectOutToConsole));
    }
}
