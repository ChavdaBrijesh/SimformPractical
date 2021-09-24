package com.brijesh.simform_practical.roomdata

import android.content.Context
import androidx.room.*
import com.brijesh.simform_practical.roomdata.model.UserDataModel


@Database(entities = [UserDataModel::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun UsersDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabaseClient(context: Context) : UsersDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, UsersDatabase::class.java, "USER_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}