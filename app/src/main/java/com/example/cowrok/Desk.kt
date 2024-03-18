package com.example.cowrok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cowrok.databinding.ActivityDeskBinding

class Desk : AppCompatActivity(){
    private lateinit var binding: ActivityDeskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    binding = ActivityDeskBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }

    // Set OnClickListener for the first button
    binding.btn11.setOnClickListener {
        redirectToDesk()
    }


}

private fun redirectToDesk() {
    val intent = Intent(this, Available_desk::class.java)

    startActivity(intent)
}


}