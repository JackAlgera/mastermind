package com.company.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MastermindGameTest {

    @Test
    void isCorrectGuess() {
        MastermindGame game = new MastermindGame(
                List.of(1, 2, 3, 4),
                false,
                0
        );

        assertTrue(game.isCorrectGuess(List.of(1, 2, 3, 4)));
        assertFalse(game.isCorrectGuess(List.of(1, 2, 3, 5)));
        assertFalse(game.isCorrectGuess(List.of(1, 2)));
    }

}
