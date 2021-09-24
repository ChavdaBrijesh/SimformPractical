package com.brijesh.simform_practical.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brijesh.simform_practical.databinding.ActivityMainBinding
import com.brijesh.simform_practical.model.UserData
import com.brijesh.simform_practical.network.ConnectionType
import com.brijesh.simform_practical.network.NetworkMonitorUtil
import com.brijesh.simform_practical.ui.adapter.MainAdapter
import com.brijesh.simform_practical.viewmodel.UserDataViewModel
import com.brijesh.simform_practical.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserDataViewModel
    private lateinit var userViewModel: UserViewModel
    private val networkMonitor = NetworkMonitorUtil(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val adapter =
            MainAdapter { position: Int, userdata: UserData -> onListItemClick(position, userdata) }
        binding.layoutMain.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.layoutMain.recyclerView.setHasFixedSize(true)
        binding.layoutMain.recyclerView.adapter = adapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable)
                {
                    true ->
                    {
                        when (type) {
                            ConnectionType.Wifi -> {
                                showOnlinedata(adapter)
                            }
                            ConnectionType.Cellular -> {
                                showOnlinedata(adapter)
                            }
                            else -> { }
                        }


                    }
                    false -> {
                        showOfflineData(adapter)
                    }
                }
            }
        }

    }

    private fun showOfflineData(adapter: MainAdapter) {
        binding.layoutMain.tvStatus.text = "Offline"
        binding.layoutMain.progressBar.visibility = View.GONE
        showMsg("No Internet connection")
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUserDetails(this)?.observe(this, Observer {

            if (it == null) {
                showMsg("Data Not Found")
            } else {

                Log.d("BJC", "Load Data From Local DB :  ${it.results}")

                val turnsType = object : TypeToken<List<UserData>>() {}.type
                val turns = Gson().fromJson<List<UserData>>(it.results, turnsType)
                adapter.setUserDataList(turns)
                //showMsg("Load Data From Local DB")
            }
        })
    }

    private fun showMsg(msg:String) {
        Snackbar.make(
            this,
            binding.root,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showOnlinedata(adapter: MainAdapter) {

        binding.layoutMain.tvStatus.text = "Online"
        viewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)
        viewModel.userDataList.observe(this, Observer {
            Log.d("BJC", "onCreate: $it")

            binding.layoutMain.progressBar.visibility = View.GONE
            adapter.setUserDataList(it)

            val gson = Gson()
            val resultsData: String = gson.toJson(it)
            Log.d("BJC", "resultsDataIntoString :  $resultsData")

            userViewModel.insertData(this, resultsData)


        })
    }

    // In MainActivity.kt
    private fun onListItemClick(position: Int, userdata: UserData) {
        userdata.name?.first?.let {
            Snackbar.make(
                this,
                binding.root,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
        showUserDetail(userdata)
    }


    private fun showUserDetail(userdata: UserData) {
        val intent = Intent(this@MainActivity, UserDetailsActivity::class.java)
        val userName: String =
            userdata.name?.title + ". " + userdata.name?.first + " " + userdata.name?.last
        val userEmail: String = userdata.email!!
        val userAvatar: String = userdata.picture?.medium!!
        intent.putExtra("userName", userName)
        intent.putExtra("userEmail", userEmail)
        intent.putExtra("userAvatar", userAvatar)
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }


    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}