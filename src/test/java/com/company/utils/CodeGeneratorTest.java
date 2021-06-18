package com.company.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeGeneratorTest {

    private final CodeGenerator cut = new CodeGenerator();

    @Test
    void shouldGenerateRandomDigits() {
        List<Integer> digits = cut.getRandomDigits();

        assertEquals(4, digits.size());

        for (Integer digit : digits) {
            assertEquals(0, digit / 10);
        }
    }

}