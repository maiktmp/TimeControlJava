package com.example.maiknight.timecontroljava.Database;

import com.example.maiknight.timecontroljava.Database.converters.DBTypeConverters;
import com.example.maiknight.timecontroljava.Database.daos.GroupDao;
import com.example.maiknight.timecontroljava.Database.daos.UserDao;
import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.Database.entities.User;
import com.example.maiknight.timecontroljava.Database.entities.UserHasGroup;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {
                User.class,
                Group.class,
                UserHasGroup.class,
        }, version = 2,
        exportSchema = false
)
@TypeConverters({DBTypeConverters.class})
public abstract class DBTime extends RoomDatabase {
        public static DBTime db;

        public abstract UserDao userDao();
        public abstract GroupDao groupDao();
}
