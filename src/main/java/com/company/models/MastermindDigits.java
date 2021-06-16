package com.company.models;

import java.util.List;

public class MastermindDigits {

    private final List<Integer> digits;

    public MastermindDigits(List<Integer> digits) {
        this.digits = digits;
    }

    public List<Integer> getDigits() {
        return digits;
    }
}
