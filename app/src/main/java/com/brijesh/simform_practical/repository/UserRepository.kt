package com.brijesh.simform_practical.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.brijesh.simform_practical.roomdata.model.UserDataModel
import com.brijesh.simform_practical.roomdata.UsersDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class UserRepository {

    companion object {

        var userDatabase: UsersDatabase? = null

        var userDataModel: LiveData<UserDataModel>? = null

        fun initializeDB(context: Context): UsersDatabase {
            return UsersDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, userData: String) {

            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.UsersDao().deleteResultsTableRecords()
                Log.d("BJC", "Delete All Records")
                val loginDetails = UserDataModel(userData)
                userDatabase!!.UsersDao().InsertUsersData(loginDetails)
            }

        }

        fun getUserDetails(context: Context): LiveData<UserDataModel>? {

            userDatabase = initializeDB(context)

            userDataModel = userDatabase!!.UsersDao().getUsersList()

            return userDataModel
        }

    }
}