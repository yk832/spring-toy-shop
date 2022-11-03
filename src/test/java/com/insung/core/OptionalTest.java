package com.insung.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    public String getDefault() {
        return "Getting Default Value";
    }

    public Optional<String> returnOpt(String val) {
        Optional<String> opString = Optional.ofNullable(val);

        return opString;
    }

    @Test

    void optionalTest() {
        String nullString = "SS";

        Optional<String> opString = Optional.of("a string in optional");
        Optional.ofNullable(nullString).ifPresent(name -> System.out.println(name));
        Optional<String> opt = returnOpt(null);

        String nullName = null;
        String s = Optional.ofNullable(nullName).orElseGet(() -> "ss");
        System.out.println(s);
    }
}
