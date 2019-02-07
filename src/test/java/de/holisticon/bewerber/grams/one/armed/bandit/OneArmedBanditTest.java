package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OneArmedBanditTest {

    @Test
    public void shouldStartAGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), new Credit(10));
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult).isNotNull();
    }

}
