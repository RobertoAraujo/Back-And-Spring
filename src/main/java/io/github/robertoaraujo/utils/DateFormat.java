package io.github.robertoaraujo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormat {
    public static Date stringToDate(String pattern) throws ParseException {
        return new SimpleDateFormat("dd/MM/yy").parse(pattern);
    }
}
