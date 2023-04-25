package com.project.employees.util.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.project.employees.util.javafx.JavaFXUtil.resultLabel;

public class DateUtil {
    public final static String[] CUSTOM_DATE_FORMATS = {
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "MM-dd-yyyy",
            "dd/MM/yyyy",
            "MM/dd/yyyy",
            "yyyy/MM/dd",
            "dd.MM.yyyy",
            "MM.dd.yyyy",
            "yyyy.MM.dd",
            "dd-MMM-yyyy",
            "MMM-dd-yyyy",
            "yyyy-MMM-dd",
            "dd/MMM/yyyy",
            "MMM/yyyy/dd",
            "yyyy/MMM/dd"
    };

    public static LocalDate parseDate(String dateString, String format) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(dateString, formatter);
        } catch (Exception ignored) {

        }

        resultLabel.setText("Unable to parse date: " + dateString + ". \nThe following format is expected: " + format);
        throw new DateTimeParseException("Unable to parse date: " + dateString + " because following format is expected: " + format, dateString, 0);
    }
}
