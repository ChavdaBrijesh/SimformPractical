package com.brijesh.simform_practical.roomdata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Results")
data class UserDataModel(
    @ColumnInfo(name = "results")
    var results: String
    )
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}