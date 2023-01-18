package com.tarik.roomapplication.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Insert
    long insertUser(User user);

    @Delete
    void deleteUser(User user);

}
