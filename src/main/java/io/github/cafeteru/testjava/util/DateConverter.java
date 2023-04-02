package io.github.cafeteru.testjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.cafeteru.testjava.infrastructure.constants.Dates;

public class DateConverter {
    public static LocalDateTime stringToLocalDateTime(String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Dates.PATTERN);
        return java.time.LocalDateTime.parse(applicationDate, formatter);
    }
}
