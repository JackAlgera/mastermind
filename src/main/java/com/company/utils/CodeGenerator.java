package com.company.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CodeGenerator {

    private static final int BOARD_SIZE = 4;
    private static final Random random = new Random();

    public List<Integer> getRandomDigits() {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            digits.add(random.nextInt(10));
        }

        return digits;
    }

}
