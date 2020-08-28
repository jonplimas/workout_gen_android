package android.hellow.workoutgenerator

import android.content.Intent
import android.hellow.workoutgenerator.DataClasses.Workout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 : Button = findViewById(R.id.exercises_button)
        val button2 : Button = findViewById(R.id.new_button)
        val button3 : Button = findViewById(R.id.saved_button)

        button1.setOnClickListener {
            val i = Intent(this, ExerciseListActivity::class.java)
            startActivity(i)
        }

        button2.setOnClickListener {
            val i2 = Intent(this, WorkoutActivity::class.java)
            startActivity(i2)
        }
        button3.setOnClickListener {
            //val i3 = Intent(this, SavedWorkoutActivity::class.java)
            //startActivity(i3)
        }


    }
}