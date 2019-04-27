package com.example.maiknight.timecontroljava.Database.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Group {
    @PrimaryKey
    private long id;
    private String name;
    @ColumnInfo(name = "start_hour")
    @SerializedName("start_hour")
    private String startHour;
    @ColumnInfo(name = "end_hour")
    @SerializedName("end_hour")
    private String endHour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
}
