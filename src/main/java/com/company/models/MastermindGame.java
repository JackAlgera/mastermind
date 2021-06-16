package com.company.models;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Model for the Mastermind game, used to contain the digits used, whether the game is finished or non and the
 * number of total guess.
 */
public class MastermindGame {

    private final List<Integer> digits;
    private Boolean gameFinished;
    private Integer totalGuesses;

    public MastermindGame(List<Integer> digits, Boolean gameFinished, Integer totalGuesses) {
        this.digits = digits;
        this.gameFinished = gameFinished;
        this.totalGuesses = totalGuesses;
    }

    public boolean isCorrectGuess(List<Integer> guess) {
        for (int i = 0; i < digits.size(); i++) {
            if (!digits.get(i).equals(guess.get(i))) {
                return false;
            }
        }

        return true;
    }

    public void playRound() {
        totalGuesses++;
    }

    public void setGameFinished(Boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public Boolean isGameFinished() {
        return gameFinished;
    }

    public Integer getTotalGuesses() {
        return totalGuesses;
    }

    public String digitsToString() {
        return digits.stream().map(Object::toString).collect(Collectors.joining());
    }
}
