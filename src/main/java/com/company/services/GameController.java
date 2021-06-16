package com.company.services;

import com.company.models.MastermindDigits;
import com.company.models.MastermindGame;
import com.company.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    @Autowired
    private CodeGenerator codeGenerator;

    private MastermindGame game;

    public GameController() {
        newGame();
    }

    public void newGame() {
        this.game = new MastermindGame(
                 new MastermindDigits(codeGenerator.getRandomDigits()),
                false,
                0
        );
    }

    public String playRound(String attempt) {
        return "++++";
    }

    public Boolean isGameFinished() {
        return game.isGameFinished();
    }
}
