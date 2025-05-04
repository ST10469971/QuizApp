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

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Initialize UI elements
        val welcomeMessage = findViewById<TextView>(R.id.welcome_message)
        val appDescription = findViewById<TextView>(R.id.app_description)
        val startButton = findViewById<Button>(R.id.start_button)
        val exitButton = findViewById<Button>(R.id.exit_button)

        //Set welcome message and app description
        welcomeMessage.text = "Welcome to the True/False Quiz!"
        appDescription.text = "Test your knowledge with these fun flashcards"

        //Set click Listener for the Start Button
        startButton.setOnClickListener {
            //Start the QuizActivity
            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
            //startActivity(Intent(this, Quiz::class.java))
        }

        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }


        }
    }