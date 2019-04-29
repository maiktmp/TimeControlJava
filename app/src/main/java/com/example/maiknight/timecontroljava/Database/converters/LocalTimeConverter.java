package com.example.maiknight.timecontroljava.Database.converters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class LocalTimeConverter {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public LocalTime fromDatabase(String value) {
        return value == null ? null : LocalTime.parse(value);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public String fromDate(LocalTime value) {
        DateTimeFormatter dtf = ofPattern("HH:mm");
        return value == null ? null : value.format(dtf);
    }
}