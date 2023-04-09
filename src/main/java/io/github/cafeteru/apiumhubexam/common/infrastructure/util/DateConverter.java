package io.github.cafeteru.apiumhubexam.common.infrastructure.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static final String PATTERN = "yyyy-MM-dd-HH.mm.ss";

    private DateConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime stringToLocalDateTime(String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(applicationDate, formatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return localDateTime.format(formatter);
    }
}
