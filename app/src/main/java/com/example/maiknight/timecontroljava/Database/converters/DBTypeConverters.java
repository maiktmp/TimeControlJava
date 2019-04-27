package com.example.maiknight.timecontroljava.Database.converters;

import java.util.Date;
import androidx.room.TypeConverter;


public class DBTypeConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
