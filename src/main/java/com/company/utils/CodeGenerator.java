package com.company.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CodeGenerator {

    private static final Random random = new Random();

    public List<Integer> getRandomDigits() {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            digits.add(random.nextInt(10));
        }

        return digits;
    }

}
