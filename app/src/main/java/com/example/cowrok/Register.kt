package com.example.cowrok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize ApiService
        Retrofit.Builder()
            .baseUrl("https://demo0413095.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java).also { apiService = it }

        val textViewLogIn = findViewById<TextView>(R.id.textView05)


        val spannableString = SpannableString("Existing user? Log In")
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Handle click event here
                onLogInClicked()
            }
        }, 14, 21, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE) // Set the clickable part range

        // Set the SpannableString to the TextView
        textViewLogIn.text = spannableString

        // Make the TextView clickable
        textViewLogIn.movementMethod = LinkMovementMethod.getInstance()
        textViewLogIn.highlightColor = Color.TRANSPARENT // Remove highlight color
    }

    // Function to handle click event
    private fun onLogInClicked() {
        startActivity(Intent(this@Register, Login::class.java))
        finish()
    }

    private fun registerUser() {
        // Fetch user input from EditText fields
        val fullName = findViewById<EditText>(R.id.edt_f_name).text.toString().trim()
        val mobileNumber = findViewById<EditText>(R.id.edt_m_num).text.toString().trim()
        val email = findViewById<EditText>(R.id.edt_eid).text.toString().trim()

        // Create registration request object
        val registerRequest = RegisterRequest(fullName, mobileNumber, email)

        // Make registration API call
        apiService.registerUser(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    // Registration successful, navigate to login activity
                    Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Register, Login::class.java))
                    finish()
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(applicationContext, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                // Handle network errors
                Toast.makeText(applicationContext, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
