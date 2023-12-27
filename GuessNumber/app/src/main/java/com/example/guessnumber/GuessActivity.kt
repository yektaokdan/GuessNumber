package com.example.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.guessnumber.databinding.ActivityGuessBinding
import kotlin.random.Random

class GuessActivity : AppCompatActivity() {
    var randomNumber = Random.nextInt(1, 101)
    var chances: Int = 5
    private lateinit var binding: ActivityGuessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewHelp.visibility = View.INVISIBLE
        Log.e("Random number:", randomNumber.toString())
        binding.textViewChances.text = "$chances Chances Left"
        binding.buttonGuess.setOnClickListener {
            val userGuess = binding.editTextGuess.text.toString().toIntOrNull()
            userGuess?.let {
                checkGuess(it)
            }
        }
    }

    private fun checkGuess(userGuess: Int) {
        when {
            userGuess > randomNumber && chances > 0 -> updateUI("Decrease")
            userGuess < randomNumber && chances > 0 -> updateUI("Increase")
            userGuess == randomNumber -> goToResultActivity(1, randomNumber)
        }

        if (chances == 0) {
            goToResultActivity(0, randomNumber)
        }
    }

    private fun updateUI(message: String) {
        chances -= 1
        binding.textViewHelp.text = message
        binding.textViewHelp.visibility = View.VISIBLE
        binding.textViewChances.text = "$chances Chances Left"
    }

    private fun goToResultActivity(resultNumber: Int, randomNumbertoResult: Int) {
        val intent = Intent(this@GuessActivity, ResultActivity::class.java)
        intent.putExtra("resultNumber", resultNumber)
        intent.putExtra("randomNumbertoResult", randomNumbertoResult)
        startActivity(intent)
        finish()
    }
}

