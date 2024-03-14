package com.example.appmenu

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.appmenu.helpers.SharedPreferencesHelper

class OnBoarding : AppCompatActivity() {

    private lateinit var onboardingImg: ImageView
    private lateinit var onboardingTitle: TextView
    private lateinit var onboardingDescription: TextView

    private lateinit var  appSharedPreferencesHelper: SharedPreferencesHelper


    private val onBoardingDeque: ArrayDeque<Map<String, Int>> = ArrayDeque()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        appSharedPreferencesHelper = SharedPreferencesHelper((this))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        onboardingImg = findViewById(R.id.onboarding_img)
        onboardingTitle = findViewById(R.id.onboarding_title)
        onboardingDescription = findViewById(R.id.onboarding_description)

        onBoardingDeque.addLast(mapOf(
            "image" to R.drawable.onboarding_img_1,
            "title" to R.string.onboarding_title_1,
            "description" to R.string.onboarding_description_1
        ))

        onBoardingDeque.addLast(mapOf(
            "image" to R.drawable.onboarding_img_2,
            "title" to R.string.onboarding_title_2,
            "description" to R.string.onboarding_description_2
        ))

        onBoardingDeque.addLast(mapOf(
            "image" to R.drawable.onboarding_img_3,
            "title" to R.string.onboarding_title_3,
            "description" to R.string.onboarding_description_3
        ))

        updateUnboarding()

        // Действия
        findViewById<LinearLayout>(R.id.next_btn).setOnClickListener {
            updateUnboarding()
        }

        findViewById<LinearLayout>(R.id.skip_btn).setOnClickListener {
            while (onBoardingDeque.size > 1) onBoardingDeque.removeFirst()

            updateUnboarding()
        }
    }

    private fun updateUnboarding() {

        if (onBoardingDeque.isNotEmpty()) {
            val stepContent = onBoardingDeque.first()
            onBoardingDeque.removeFirst()

            onboardingImg.setImageResource(stepContent["image"]!!)
            onboardingTitle.text = getString(stepContent["title"]!!)
            onboardingDescription.text = getString(stepContent["description"]!!)

        }
        else {
            appSharedPreferencesHelper.saveData("is_OnBoarding", true)
            finish()
        }

        if (onBoardingDeque.isEmpty()) {
            findViewById<TextView>(R.id.next_btn_text).text = getString(R.string.start_btn_text)
        }

    }

}