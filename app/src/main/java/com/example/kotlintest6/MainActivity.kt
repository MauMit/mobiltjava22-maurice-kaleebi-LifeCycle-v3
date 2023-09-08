package com.example.kotlintest6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var rememberBox: CheckBox
    val correctUsername = "Moe";
    val correctPassword = "trilla";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sound: MediaPlayer = MediaPlayer.create(this, R.raw.cj)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val btn = findViewById<Button>(R.id.button)
        rememberBox = findViewById(R.id.rememberBox)

        sharedPreferences = getSharedPreferences("Myprefs", Context.MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        rememberBox.isChecked = isLoggedIn



        if (isLoggedIn) {
            val A2 = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(A2)
        }

        btn.setOnClickListener {

            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()


            if (rememberBox.isChecked || (enteredUsername == correctUsername && enteredPassword == correctPassword)) {
                val A2 = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(A2)
                Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()
               sound.start()

            } else {
                Toast.makeText(this@MainActivity, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            }

        }
    }


    override fun onPause() {
        super.onPause()
        sharedPreferences.edit().putBoolean("isLoggedIn", rememberBox.isChecked).apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.edit().putBoolean("isLoggedIn", rememberBox.isChecked).apply()
    }

    override fun onBackPressed() {

        Toast.makeText(applicationContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
    }
}

