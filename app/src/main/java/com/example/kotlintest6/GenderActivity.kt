package com.example.kotlintest6


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var sharedPreferences: SharedPreferences

class GenderActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE)
        val radioMale = findViewById<RadioButton>(R.id.radioButtonMale)
        val radioFemale = findViewById<RadioButton>(R.id.radioButtonFemale)
        val radioOther = findViewById<RadioButton>(R.id.radioButtonOther)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.gender

        val isMaleChecked = sharedPreferences.getBoolean("isMaleChecked", false)
        val isFemaleChecked = sharedPreferences.getBoolean("isFemaleChecked", false)
        val isOtherchecked = sharedPreferences.getBoolean("isOtherChecked", false)


        radioMale.isChecked = isMaleChecked
        radioFemale.isChecked = isFemaleChecked
        radioOther.isChecked = isOtherchecked




        radioMale.setOnClickListener {
            sharedPreferences.edit().putBoolean("isMaleChecked", true).apply()
            sharedPreferences.edit().putBoolean("isFemaleChecked", false).apply()
            sharedPreferences.edit().putBoolean("isOtherChecked", false).apply()
            Toast.makeText(this@GenderActivity, "You da man", Toast.LENGTH_SHORT).show()
        }


        radioFemale.setOnClickListener {
            sharedPreferences.edit().putBoolean("isFemaleChecked", true).apply()
            sharedPreferences.edit().putBoolean("isMaleChecked", false).apply()
            sharedPreferences.edit().putBoolean("isOtherChecked", false).apply()
            Toast.makeText(this@GenderActivity, "Yousa Woman", Toast.LENGTH_SHORT).show()
        }


        radioOther.setOnClickListener {
            sharedPreferences.edit().putBoolean("isOtherChecked", true).apply()
            sharedPreferences.edit().putBoolean("isFemaleChecked", false).apply()
            sharedPreferences.edit().putBoolean("isMaleChecked", false).apply()
            Toast.makeText(this@GenderActivity, "You are other", Toast.LENGTH_SHORT).show()

        }


        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }

    }

        override fun onPause() {
        super.onPause()
        val editor = sharedPreferences.edit()
        val radioMale = findViewById<RadioButton>(R.id.radioButtonMale)
        val radioFemale = findViewById<RadioButton>(R.id.radioButtonFemale)
        val radioOther = findViewById<RadioButton>(R.id.radioButtonOther)
        editor.putBoolean("isMalechecked", radioMale.isChecked)
        editor.putBoolean("isFemalechecked", radioFemale.isChecked)
        editor.putBoolean("isOtherchecked", radioOther.isChecked)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor = sharedPreferences.edit()
        val radioMale = findViewById<RadioButton>(R.id.radioButtonMale)
        val radioFemale = findViewById<RadioButton>(R.id.radioButtonFemale)
        val radioOther = findViewById<RadioButton>(R.id.radioButtonOther)
        editor.putBoolean("isMalechecked", radioMale.isChecked)
        editor.putBoolean("isFemalechecked", radioFemale.isChecked)
        editor.putBoolean("isOtherchecked", radioOther.isChecked)
        editor.apply()
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
        val intent = Intent(this@GenderActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

