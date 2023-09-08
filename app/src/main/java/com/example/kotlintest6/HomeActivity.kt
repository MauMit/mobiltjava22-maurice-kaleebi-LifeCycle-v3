package com.example.kotlintest6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var sharedPreferences: SharedPreferences


class HomeActivity : AppCompatActivity() {


    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)


        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.dave).into(imageView)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.home
        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }
    }

    private fun navigateToActivity(itemId: Int) {
        val intent = when (itemId) {
            R.id.home -> Intent(this, HomeActivity::class.java)
            R.id.gender -> Intent(this, GenderActivity::class.java)
            R.id.information -> Intent(this, PersonalActivity::class.java)
            R.id.jokes -> Intent(this, JokesActivity::class.java)
            R.id.logout -> {
                logOut()
                null
            }
            else -> null
        }
        if (intent != null) {
            startActivity(intent)

        }

    }

    private fun logOut() {

        sharedPreferences = getSharedPreferences("Myprefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        val intent = Intent(this@HomeActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onBackPressed() {

        Toast.makeText(applicationContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
    }
}





