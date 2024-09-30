package io.github.robertoaraujo.utils;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatTest {

    @org.junit.jupiter.api.Test
    void stringToDate() {
        try {
            DateFormat.stringToDate("01/01/21");
        } catch (Exception e) {
            fail("Erro ao converter data");
        }
    }

}