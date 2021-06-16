package com.company.utils;

import org.springframework.stereotype.Service;

@Service
public class InputValidater {

    private static final String REGEX = "[0-9]+";

    public boolean isValidInput(String input) {
        return input.matches(REGEX) && input.length() == 4;
    }

}
