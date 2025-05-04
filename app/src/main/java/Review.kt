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
import kotlin.system.exitProcess



class Review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)


        val reviewTextView = findViewById<TextView>(R.id.review_text_view)
        val restartBtn = findViewById<Button>(R.id.restart_button)
        val exitBtn = findViewById<Button>(R.id.exit_button)


        //Get the questions and answers passed from ScoreActivity
        val questions = intent.getShortArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]}\n")
                reviewText.append("  Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewTextView.text = reviewText.toString()
        } else {
            reviewTextView.text = "Error loading review data"
        }
        restartBtn.setOnClickListener {
            startActivity(intent(this, Quiz::class.java))
        }

        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }