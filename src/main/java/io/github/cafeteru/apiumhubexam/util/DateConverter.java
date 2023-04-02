package io.github.cafeteru.apiumhubexam.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.cafeteru.apiumhubexam.infrastructure.constants.Dates;

public class DateConverter {
    public static LocalDateTime stringToLocalDateTime(String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Dates.PATTERN);
        return java.time.LocalDateTime.parse(applicationDate, formatter);
    }
}
