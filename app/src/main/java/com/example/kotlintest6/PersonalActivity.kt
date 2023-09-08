package com.example.kotlintest6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView


class PersonalActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)

        sharedPreferences = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        val name = sharedPreferences.getString("name", "")
        val phone = sharedPreferences.getString("phone", "")
        val email = sharedPreferences.getString("email", "")
        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        editTextName.setText(name)
        editTextPhone.setText(phone)
        editTextEmail.setText(email)
        checkBox.isChecked = isChecked

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.information
        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }


    override fun onDestroy() {
        super.onDestroy()
        saveData()
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
        val intent = Intent(this@PersonalActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun saveData() {

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val nameToSave = editTextName.text.toString()
        val phoneToSave = editTextPhone.text.toString()
        val emailToSave = editTextEmail.text.toString()
        val checkBoxToSave = checkBox.isChecked
        val editor = sharedPreferences.edit()
        editor.putString("name", nameToSave)
        editor.putString("phone", phoneToSave)
        editor.putString("email", emailToSave)
        editor.putBoolean("isChecked", checkBoxToSave)
        editor.apply()
    }

}