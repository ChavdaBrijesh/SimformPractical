package com.brijesh.simform_practical

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brijesh.simform_practical.network.MyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class APICallTest {

    @Test
    fun testCaseAPICall(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
           val userData =  MyApi.invoke().getAllUserDataList().body()?.userData?.size
            println("userData Size : $userData")
        }
    }

}