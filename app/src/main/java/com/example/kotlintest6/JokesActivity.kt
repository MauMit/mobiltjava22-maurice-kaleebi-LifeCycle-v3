package com.example.kotlintest6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView


private lateinit var sharedPreferences: SharedPreferences

class JokesActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.jokes



        var rq : RequestQueue = Volley.newRequestQueue(this)
        val url =  "https://v2.jokeapi.dev/joke/Dark?format=txt"

        val textViewJoke = findViewById<TextView>(R.id.text)
        val buttonJoke = findViewById<Button>(R.id.buttonJoke)
        buttonJoke.setOnClickListener{
            val r = StringRequest(Request.Method.GET, url,
                { res -> textViewJoke.text = res.toString() } ,
                { err ->  Log.d("Maurice", err.toString())  }
            )
            rq.add(r)
        }

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
        val intent = Intent(this@JokesActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}