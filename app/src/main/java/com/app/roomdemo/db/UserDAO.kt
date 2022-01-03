package com.app.roomdemo.db

import androidx.room.*

@Dao
interface UserDAO {

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>

    @Insert
    fun insertUser(user: UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun uptadeUser(user: UserEntity?)


}