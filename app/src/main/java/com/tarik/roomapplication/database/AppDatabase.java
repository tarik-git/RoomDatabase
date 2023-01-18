package com.tarik.roomapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tarik.roomapplication.model.User;
import com.tarik.roomapplication.model.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
