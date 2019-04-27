package com.example.maiknight.timecontroljava.Database.daos;

import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.Database.entities.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(Group group);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(Group... group);

    @Query("SELECT * FROM `Group` WHERE id = :id")
    Group find(long id);

    @Query("SELECT * FROM `group`")
    List<Group> getAll();

    @Query("DELETE FROM `Group`")
    void deleteAll();

}
