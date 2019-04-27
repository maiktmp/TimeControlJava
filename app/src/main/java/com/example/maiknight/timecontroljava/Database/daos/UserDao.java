package com.example.maiknight.timecontroljava.Database.daos;

import com.example.maiknight.timecontroljava.Database.entities.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    long upsert(User user);

    @Query("SELECT * FROM user WHERE id = :id")
    User find(long id);

    @Query("SELECT * FROM user LIMIT 1")
    User findFirst();

    @Query("DELETE FROM user")
    void deleteAll();
}
