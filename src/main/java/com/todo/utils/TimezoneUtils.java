package com.todo.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimezoneUtils {

    public static String getTimezone() {
        return "America/Sao_Paulo";
    }

    public static LocalDateTime getLocalDateTimeWithTimeZone() {
        return LocalDateTime.now(ZoneId.of(getTimezone()));
    }
}
