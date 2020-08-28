package android.hellow.workoutgenerator

import android.app.Activity
import android.content.Intent
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.DataClasses.Workout
import android.hellow.workoutgenerator.Exercise.ExerciseViewModel
import android.hellow.workoutgenerator.Workouts.WorkoutListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_workout.*
import org.w3c.dom.Text

class NewWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

        val workoutName : TextView = findViewById(R.id.wNameTV)
        val workoutDur : TextView = findViewById(R.id.wDurationTV)
        val workoutType : TextView = findViewById(R.id.wTypeTV)

        workoutName.text = intent.getStringExtra("wName")
        workoutDur.text = "Duration: " + intent.getStringExtra("wDuration") + " minutes"
        workoutType.text = "Type: " + intent.getStringExtra("wType")

        /**          TO DO LIST
         * - IMPLEMENT RECYCLER VIEW FOR NEWLY GENERATED WORKOUT
         * - LEARN HOW TO PULL FROM EXERCISE DATABASE IN OTHER ACTIVITIES
         *
         ***/

    }
}
