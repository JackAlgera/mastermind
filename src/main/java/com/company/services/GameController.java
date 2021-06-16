package com.company.services;

import com.company.models.MastermindGame;
import com.company.utils.CodeGenerator;
import com.company.utils.InputValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        System.out.printf("Number to guess %s\n\n", game.getDigits().toString());
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
                    "Too bad, you weren't able to guess the correct digits fast enough !\n" +
                            "Number of attemps : %d%n", game.getTotalAttemps());
            return;
        }

        List<Integer> attemptAsList = convertToIntegerArray(attempt);

        if (game.isCorrectGuess(attemptAsList)) {
            game.setGameFinished(true);
            System.out.printf("Congrats ! You won the game in %d guesses !%n", game.getTotalAttemps());
        }

        System.out.printf("%s\t\tGuesses left : %d\n", getAttemptOutputString(attemptAsList), MAX_ATTEMPS - game.getTotalAttemps());
    }

    public String getAttemptOutputString(List<Integer> attemptAsList) {
        String output = "placeholder";
        for (int i = 0; i < attemptAsList.size(); i++) {
            
        }

        return output;
    }

    public List<Integer> convertToIntegerArray(String attempt) {
        return Arrays.stream(attempt.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public Boolean isGameFinished() {
        return game.isGameFinished();
    }
}
