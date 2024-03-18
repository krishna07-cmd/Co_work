package com.example.cowrok
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import android.text.method.LinkMovementMethod
import android.graphics.Color
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize ApiService
        apiService = Retrofit.Builder()
            .baseUrl("https://demo0413095.mockable.io/digitalflake/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val textViewCreateAccount = findViewById<TextView>(R.id.textViewCreateAccount)
        val spannableString = SpannableString("New user? Create an account")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@Login, Register::class.java))
            }
        }
        spannableString.setSpan(clickableSpan, 9, 27, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewCreateAccount.text = spannableString
        textViewCreateAccount.movementMethod = LinkMovementMethod.getInstance()
        textViewCreateAccount.highlightColor = Color.TRANSPARENT



        findViewById<Button>(R.id.btn_1).setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        // Run the code block on the main thread
        runOnUiThread {
            // Fetch email and password from EditText fields
            val email = findViewById<EditText>(R.id.edt_email_pass).text.toString().trim()
            val password = findViewById<EditText>(R.id.edt_pass).text.toString().trim()

            // Create login request object
            val loginRequest = LoginRequest(email, password)

            // Make login API call
            apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null && loginResponse.success) {
                            // Login successful, navigate to Home activity
                            Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Login, Home::class.java))
                            finish()
                        } else {
                            // Login failed, display error message
                            Toast.makeText(applicationContext, "Login failed: ${loginResponse?.message}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Handle unsuccessful response
                        Toast.makeText(applicationContext, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Handle network errors
                    Toast.makeText(applicationContext, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
