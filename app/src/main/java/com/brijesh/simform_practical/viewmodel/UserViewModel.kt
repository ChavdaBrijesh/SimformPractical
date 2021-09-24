package com.brijesh.simform_practical.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.brijesh.simform_practical.roomdata.model.UserDataModel
import com.brijesh.simform_practical.repository.UserRepository


class UserViewModel : ViewModel() {

    var liveDataUser: LiveData<UserDataModel>? = null

    fun insertData(context: Context, randomuserList: String) {
        UserRepository.insertData(context, randomuserList)
    }

    fun getUserDetails(context: Context) : LiveData<UserDataModel>? {
        liveDataUser = UserRepository.getUserDetails(context)
        return liveDataUser
    }

}