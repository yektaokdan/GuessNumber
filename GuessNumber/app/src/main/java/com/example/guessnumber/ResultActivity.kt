package com.example.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.guessnumber.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultNumber = intent.getIntExtra("resultNumber", -1)
        val randomNumbertoResult = intent.getIntExtra("randomNumbertoResult", -1)
        if (resultNumber == 1) {
            binding.imageViewResult.setImageResource(R.drawable.baseline_check_24)
            binding.textViewResult.text = "You Won!"
        } else if (resultNumber == 0) {
            binding.imageViewResult.setImageResource(R.drawable.baseline_clear_24)
            binding.textViewResult.text = "Number: $randomNumbertoResult\nGame Over"
        }

        binding.btnTryAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, GuessActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}