package com.company.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputValidaterTest {

    private final InputValidater cut = new InputValidater();

    @Test
    void shouldCorrectlyValidateStrings() {
        List<Pair<String, Boolean>> inputsToTest = Arrays.asList(
                Pair.of("12345", false),
                Pair.of("123", false),
                Pair.of("123a", false),
                Pair.of("-12", false),
                Pair.of("1234", true),
                Pair.of("9999", true)
        );

        inputsToTest.forEach(pair -> {
            assertEquals(pair.getRight(), cut.isValidInput(pair.getLeft()));
        });
    }

}