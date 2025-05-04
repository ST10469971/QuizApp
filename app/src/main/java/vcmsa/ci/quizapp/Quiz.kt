package vcmsa.ci.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score
import org.w3c.dom.Text
import kotlin.system.exitProcess

        class Quiz : AppCompatActivity() {

          private lateinit var questionTextView: TextView
          private lateinit var trueButton: Button
          private lateinit var falseButton: Button
          private lateinit var nextButton: Button
          private lateinit var feedbackTextView: TextView

          companion object {
              val questions = arrayOf(
                  "The largest whale in the world is the blue whale",
                  "The tallest mountain in the world is Mount Everest",
                  "The fastest land animal on Earth is the Cheetah",
                  "The longest river in the world is the Nile",
                  "The sun is the center of our solar system"
              )
              val answers = booleanArrayOf(true,true,true, false, true)
          }
            private var currentQuestionIndex = 0
            private var score = 0


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContentView(R.layout.activity_quiz)

                //Initialize UI elements
                questionTextView = findViewById(R.id.question_text_view)
                trueButton = findViewById(R.id.true_button)
                falseButton = findViewById(R.id.false_button)
                nextButton = findViewById(R.id.next_button)

                //Display the first question
                displayQuestion()

                //Set click listener for the answer buttons
                trueButton.setOnClickListener { checkAnswer(true) }
                falseButton.setOnClickListener { checkAnswer(false) }

                //Set click listener for the next buttons
                nextButton.setOnClickListener {
                    currentQuestionIndex++
                    if (currentQuestionIndex < questions.size) {
                        displayQuestion()
                        feedbackTextView.text = "" //Clear feedback
                        trueButton.isEnabled = true //Enable buttons
                        falseButton.isEnabled = true
                    } else {
                        // Go to ScoreActivity
                        val intent = Intent(this, Score::class.java)
                        intent.putExtra("score", score)
                        startActivity(intent)
                        finish() //Finish QuizActivity so the user can't go back
                    }
                }
                nextButton.isEnabled = false //Disable next button at the start

            }

            private fun displayQuestion() {
                questionTextView.text = questions[currentQuestionIndex]
            }

            private fun checkAnswer(userAnswer: Boolean) {
                val correctAnswer = answers[currentQuestionIndex]

                if (userAnswer == correctAnswer) {
                    feedbackTextView.text = "Correct!"
                    feedbackTextView.setTextColor(Color.GREEN)
                    score++
                }else {
                    feedbackTextView.text = "Incorrect"
                    feedbackTextView.setTextColor(Color.RED)
                }
                trueButton.isEnabled = false
                falseButton.isEnabled = false
                nextButton.isEnabled = true
            }
        }