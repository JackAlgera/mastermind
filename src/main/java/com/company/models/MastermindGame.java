package com.company.models;

public class MastermindGame {

    private MastermindDigits currentGameDigits;
    private Boolean gameFinished = true;
    private Integer totalAttemps = 0;

    public MastermindGame(MastermindDigits currentGameDigits, Boolean gameFinished, Integer totalAttemps) {
        this.currentGameDigits = currentGameDigits;
        this.gameFinished = gameFinished;
        this.totalAttemps = totalAttemps;
    }

    public MastermindDigits getCurrentGameDigits() {
        return currentGameDigits;
    }

    public Boolean isGameFinished() {
        return gameFinished;
    }

    public Integer getTotalAttemps() {
        return totalAttemps;
    }
}
