package com.company.services;

import com.company.models.MastermindGame;
import com.company.utils.CodeGenerator;
import com.company.utils.InputValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main game logic goes here.
 */
@Component
public class GameController {

    private static final Integer MAX_ATTEMPS = 10;

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private InputValidater inputValidater;

    private MastermindGame game;

    public void newGame() {
        System.out.printf(
                "\nThis is a virtualized version of the well known MasterMind game.\n" +
                "Try and guess the four digit number by well.. typing 4 digits. " +
                "You have %d guesses, good luck !%n", MAX_ATTEMPS);

        this.game = new MastermindGame(
                 codeGenerator.getRandomDigits(),
                false,
                0
        );

        System.out.printf("\n(Psst, this is the number to guess %s)\n\n", game.digitsToString());
    }

    public void playRound(String attempt) {
        if (!inputValidater.isValidInput(attempt)) {
            System.out.println("Incorrect input, please provide a number with four digits, like 1234");
            return;
        }

        game.playRound();
        if (game.getTotalGuesses() >= MAX_ATTEMPS) {
            game.setGameFinished(true);
            System.out.printf(
                    "Oh no, you don't have any more guesses left !\n" +
                            "Digits you were supposed to guess : %s\n" +
                    "Better luck next time !", game.digitsToString());
            return;
        }

        List<Integer> attemptAsList = convertToIntegerArray(attempt);

        if (game.isCorrectGuess(attemptAsList)) {
            game.setGameFinished(true);
            System.out.printf("Congrats ! You won the game in %d guesses !%n", game.getTotalGuesses());
            return;
        }

        System.out.printf("%s\t\tGuesses left : %d\n", getAttemptOutputString(attemptAsList), MAX_ATTEMPS - game.getTotalGuesses());
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

                    if (!usedGuessDigit.get(j) && secretDigit.equals(guessDigit)) {
                        usedGuessDigit.set(j, true);
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
