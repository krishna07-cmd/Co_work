package com.example.cowrok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cowrok.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set OnClickListener for the first button
        binding.button.setOnClickListener {
            redirectToDesk()
        }

        // Set OnClickListener for the second button
        binding.button2.setOnClickListener {
            redirectToDesk()
        }

        // Set OnClickListener for the third button (Booking History)
        binding.button3.setOnClickListener {
            redirectToBookingHistory()
        }
    }

    private fun redirectToDesk() {
        val intent = Intent(this, Desk::class.java)

        startActivity(intent)
    }

    private fun redirectToBookingHistory() {
        val intent = Intent(this,   Booking::class.java)

        startActivity(intent)


    }    }
