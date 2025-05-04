package vcmsa.ci.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import vcmsa.ci.quizapp.Quiz.Companion.questions
import kotlin.system.exitProcess

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        val scoreTextView = findViewById<TextView>(R.id.score_text_view)
        val feedbackTextView = findViewById<TextView>(R.id.feedback_text_view)
        val reviewButton = findViewById<Button>(R.id.review_button)
        val exitButton = findViewById<Button>(R.id.exit_button)

        val score = intent.getIntExtra("score", 0)
        scoreTextView.text = "Your score: $score/5"

        val feedback = if (score >= 3) {
            "Well done!"
            }else {
                "Keep trying!"
        }
        feedbackTextView.text = feedback

        reviewButton.setOnClickListener {
            //Start the ReviewActivity and pass the questions and answers
            val intent = Intent(this, Review::class,java)
            intent.putExtra("questions", Quiz,questions) //Access static property
            intent.putExtra("answers", Quiz.answers)  //Access static property
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity() //Terminate the app
            exitProcess(0)
        }

        }
}


