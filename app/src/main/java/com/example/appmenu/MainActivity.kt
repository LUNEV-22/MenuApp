package com.example.appmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.appmenu.helpers.SharedPreferencesHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appSharedPreferencesHelper = SharedPreferencesHelper(this)

        if (!appSharedPreferencesHelper.getBooleanData("is_OnBoarding")) {
            val OnBoardingActivity = Intent(this@MainActivity, OnBoarding::class.java)
            startActivity(OnBoardingActivity)
        }
    }
}