package com.company.services;

import com.company.models.MastermindGame;
import com.company.utils.CodeGenerator;
import com.company.utils.InputValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameController {

    private static final Integer MAX_ATTEMPS = 10;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private InputValidater inputValidater;

    private MastermindGame game;

    public void newGame() {
        this.game = new MastermindGame(
                 codeGenerator.getRandomDigits(),
                false,
                0
        );

        System.out.printf("(Psst, this is the number to guess %s)\n\n", game.digitsToString());
    }

    public void playRound(String attempt) {
        if (!inputValidater.isValidInput(attempt)) {
            System.out.println("Incorrect input, please provide a number with four digits, like 1234");
            return;
        }

        game.playRound();
        if (game.getTotalAttemps() >= MAX_ATTEMPS) {
            game.setGameFinished(true);
            System.out.printf(
                    "Oh no, you don't have any more guesses left !\n" +
                            "Digits to guess : %s" +
                            "Number of attemps : %d%n", game.digitsToString(), game.getTotalAttemps());
            return;
        }

        List<Integer> attemptAsList = convertToIntegerArray(attempt);

        if (game.isCorrectGuess(attemptAsList)) {
            game.setGameFinished(true);
            System.out.printf("Congrats ! You won the game in %d guesses !%n", game.getTotalAttemps());
            return;
        }

        System.out.printf("%s\t\tGuesses left : %d\n", getAttemptOutputString(attemptAsList), MAX_ATTEMPS - game.getTotalAttemps());
    }

    public String getAttemptOutputString(List<Integer> attemptAsList) {
        StringBuilder output = new StringBuilder();
        List<Boolean> usedSecretDigit = Arrays.asList(false, false, false, false);
        List<Boolean> usedGuessDigit = Arrays.asList(false, false, false, false);

        // First check if correct digit and in correct position
        for (int i = 0; i < 4; i++) {
            if (game.getDigits().get(i).equals(attemptAsList.get(i))) {
                usedSecretDigit.set(i, true);
                usedGuessDigit.set(i, true);
                output.append("+");
            }
        }

        // Then check if correct digit but in wrong position
        for (int i = 0; i < 4; i++) {
            if (!usedSecretDigit.get(i)) {
                Integer secretDigit = game.getDigits().get(i);
                for (int j = 0; j < attemptAsList.size(); j++) {
                    Integer guessDigit = attemptAsList.get(j);

                    if (!usedGuessDigit.get(i) && secretDigit.equals(guessDigit)) {
                        usedGuessDigit.set(i, true);
                        output.append("-");
                        break;
                    }
                }
            }
        }

        return output.toString();
    }

    public List<Integer> convertToIntegerArray(String attempt) {
        return Arrays.stream(attempt.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public Boolean isGameFinished() {
        return game.isGameFinished();
    }
}
