package com.company.services;

import com.company.utils.CodeGenerator;
import com.company.utils.InputValidater;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @InjectMocks
    private GameController cut;

    @Mock
    private CodeGenerator codeGenerator;

    @Mock
    private InputValidater inputValidater;

    @Test
    void gameShoultNotBeFinishedWhenStarting() {
        when(codeGenerator.getRandomDigits()).thenReturn(List.of(1, 2, 3 ,4));
        cut.newGame();

        assertFalse(cut.isGameFinished());
    }

    @Test
    void shouldCorrectlyConvertIntegerArray() {
        assertEquals(List.of(1, 2, 3 ,4), cut.convertToIntegerArray("1234"));
    }

    @Test
    void shouldGenerateCorrectOutputStrings() {
        when(codeGenerator.getRandomDigits()).thenReturn(List.of(0, 4, 0 ,6));
        cut.newGame();

        List<Pair<List<Integer>, String>> inputsToTest = Arrays.asList(
                Pair.of(Arrays.asList(1, 1, 1, 0), "-"),
                Pair.of(Arrays.asList(0, 4, 0, 6), "++++"),
                Pair.of(Arrays.asList(0, 4, 0, 7), "+++"),
                Pair.of(Arrays.asList(0, 2, 0, 0), "++"),
                Pair.of(Arrays.asList(0, 1, 2, 3), "+"),
                Pair.of(Arrays.asList(9, 9, 9, 0), "-"),
                Pair.of(Arrays.asList(9, 0, 9, 0), "--"),
                Pair.of(Arrays.asList(6, 0, 4, 8), "---"),
                Pair.of(Arrays.asList(6, 0, 4, 0), "----")
        );

        inputsToTest.forEach(inputPair -> {
            assertEquals(inputPair.getRight(), cut.getAttemptOutputString(inputPair.getLeft()));
        });
    }

    @Test
    void shouldCorrectlyPlayGame() {
        when(codeGenerator.getRandomDigits()).thenReturn(List.of(0, 4, 0 ,6));
        when(inputValidater.isValidInput(anyString())).thenReturn(true);
        cut.newGame();

        cut.playRound("1234");
        assertFalse(cut.isGameFinished());

        cut.playRound("0406");
        assertTrue(cut.isGameFinished());

        cut.newGame();
        for (int i = 0; i < 10; i++) {
            cut.playRound("1234");
        }
        assertTrue(cut.isGameFinished());
    }

}
