package com.company;

import com.company.services.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MastermindLauncher implements CommandLineRunner {

    @Autowired
    private GameController gameController;

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (!gameController.isGameFinished()) {
            System.out.println(in.nextLine());
        }

    }
}
