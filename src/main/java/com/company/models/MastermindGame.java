package com.company.models;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MastermindGame {

    private List<Integer> digits;
    private Boolean gameFinished = true;
    private Integer totalAttemps = 0;

    public MastermindGame(List<Integer> digits, Boolean gameFinished, Integer totalAttemps) {
        this.digits = digits;
        this.gameFinished = gameFinished;
        this.totalAttemps = totalAttemps;
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
        totalAttemps++;
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

    public Integer getTotalAttemps() {
        return totalAttemps;
    }

    public String digitsToString() {
        return digits.stream().map(Object::toString).collect(Collectors.joining());
    }
}
