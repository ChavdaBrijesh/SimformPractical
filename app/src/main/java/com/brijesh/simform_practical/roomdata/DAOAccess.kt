package com.brijesh.simform_practical.roomdata

import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brijesh.simform_practical.roomdata.model.UserDataModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUsersData(randomUserData: UserDataModel)

    @Query("SELECT * FROM Results")
    fun getUsersList() : LiveData<UserDataModel>

    @Query("DELETE FROM Results")
    suspend fun deleteResultsTableRecords()
}