package com.brijesh.simform_practical

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brijesh.simform_practical.network.MyApi

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.brijesh.simform_practical", appContext.packageName)
    }

    /*@Test
    suspend fun testApiResponse() {
        MyApi.invoke().getAllUserDataList().isSuccessful
        var isSuccess = MyApi.invoke().getAllUserDataList().isSuccessful
        if (isSuccess) {
            println("Success")
        } else {
            println("Fail")
        }

    }*/
}