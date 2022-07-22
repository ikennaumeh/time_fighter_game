package com.example.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private val TAG = MainActivity::class.java.simpleName
private var score = 0
private var gameStarted : Boolean = false
private lateinit var  countDownTimer: CountDownTimer
private var initialCountDown: Long = 6000
private var countDownInterval: Long = 1000
private var timeLeft = 60

class MainActivity : AppCompatActivity() {

    private  lateinit var gameScoreTextView: TextView
    private  lateinit var timeLeftTextView: TextView
    private  lateinit var tapMeButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate called. Score is: $score")

        /// connect view to variables
        gameScoreTextView = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)

        tapMeButton.setOnClickListener { incrementScore() }

        resetGame()
    }

    private fun incrementScore(){
        //increment score logic
        if(!gameStarted){
            startGame()
        }
        score++

        val newScore = getString(R.string.your_score, score)
        gameScoreTextView.text = newScore
    }

    private fun resetGame(){
        //reset game
        score = 0

        val initialScore = getString(R.string.your_score, score)
        gameScoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.time_left, 60)
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000

                val timeLeftString = getString(R.string.time_left, timeLeft)
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish() {
                //To be implemented later
                endGame()
            }
        }
        gameStarted = false
    }

    private  fun startGame(){
        //start game logic
        countDownTimer.start()
        gameStarted = true
    }

    private  fun endGame(){
        //end game logic
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
    }
}