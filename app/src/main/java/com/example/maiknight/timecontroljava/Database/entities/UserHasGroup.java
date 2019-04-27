package com.example.maiknight.timecontroljava.Database.entities;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_has_group")
public class UserHasGroup {
    @PrimaryKey
    private long id;

    @ColumnInfo(name = "fk_id_user")
    @SerializedName("fk_id_user")
    private String fkIdUser;

    @ColumnInfo(name = "fk_id_group")
    @SerializedName("fk_id_group")
    private String fkIdGrop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFkIdUser() {
        return fkIdUser;
    }

    public void setFkIdUser(String fkIdUser) {
        this.fkIdUser = fkIdUser;
    }

    public String getFkIdGrop() {
        return fkIdGrop;
    }

    public void setFkIdGrop(String fkIdGrop) {
        this.fkIdGrop = fkIdGrop;
    }
}
