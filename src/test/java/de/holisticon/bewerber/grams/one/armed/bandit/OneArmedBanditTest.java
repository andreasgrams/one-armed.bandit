package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

public class OneArmedBanditTest {

    @Test
    public void shouldStartAGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), new Credit(10));
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        boolean won = gameResult.isGameWon();
        Credit score = gameResult.getScore();
        Wheel[] wheels = gameResult.getWheelState();
    }

}
