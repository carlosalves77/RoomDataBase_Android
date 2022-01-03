package com.app.roomdemo.splashscreen

import android.app.UiModeManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.getSystemService
import com.app.roomdemo.MainActivity
import com.app.roomdemo.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getSystemService<UiModeManager>()?.nightMode = UiModeManager.MODE_NIGHT_YES

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        },3000) //3 segundos de delay para abrir a activity main



    }
}