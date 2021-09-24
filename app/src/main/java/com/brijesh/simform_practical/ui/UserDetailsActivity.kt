package com.brijesh.simform_practical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brijesh.simform_practical.databinding.ActivityUserDetailsBinding
import com.brijesh.simform_practical.model.UserData
import com.bumptech.glide.Glide

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private var userName: String = ""
    private var userEmail: String = ""
    private var userAvatar: String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        userName = intent.getStringExtra("userName")!!
        userEmail = intent.getStringExtra("userEmail")!!
        userAvatar = intent.getStringExtra("userAvatar")!!


        if (userName.isNotEmpty() && userEmail.isNotEmpty() && userAvatar.isNotEmpty()) {

            binding.layoutDetail.tvUserName.text = userName
            binding.layoutDetail.tvUserEmail.text = userEmail
            Glide.with(this).load(userAvatar).into(binding.tvAvatar)

        }

    }

}