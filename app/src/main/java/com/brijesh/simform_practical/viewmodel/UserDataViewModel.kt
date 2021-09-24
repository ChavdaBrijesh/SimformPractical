package com.brijesh.simform_practical.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brijesh.simform_practical.model.UserData
import com.brijesh.simform_practical.network.MyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataViewModel() : ViewModel() {

    val userDataList : LiveData<List<UserData>> = MutableLiveData()

    init {
        Log.i("UserDataViewModel", "UserDataViewModel created!")
        viewModelScope.launch {
            userDataList as MutableLiveData
            userDataList.value = getAllUserData()
        }
    }
    private suspend fun getAllUserData(): List<UserData>? {
      return withContext(Dispatchers.IO)
      {
          MyApi.invoke().getAllUserDataList().body()?.userData
      }
    }
}