package com.nbc.youtube.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nbc.youtube.data.local.model.UserEntity

@Dao
interface UserEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserEntity(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserEntity(userId: String): UserEntity
}