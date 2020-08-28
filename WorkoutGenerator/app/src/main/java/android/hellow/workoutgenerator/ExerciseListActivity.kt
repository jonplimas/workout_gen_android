package android.hellow.workoutgenerator

import android.app.Activity
import android.content.Intent
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.Exercise.ExerciseViewModel
import android.hellow.workoutgenerator.Exercise.ExerciseListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_exercise_list.*


class ExerciseListActivity : AppCompatActivity() {

    private val REQUEST_CODE = 2
    private lateinit var exerciseViewModel: ExerciseViewModel

    //val button4 : FloatingActionButton = findViewById(R.id.floatingActionButton2)


    // ARRAY LIST OF EXERCISES INITIALIZED
    var mExercise: ArrayList<ExerciseEntry> = arrayListOf(
        ExerciseEntry("Standard Push-up", "Upper Body", "plank position, press " +
                "against the floor"),
        ExerciseEntry("Plank", "Core", "hold supine position for 30 seconds"),
        ExerciseEntry("Squat", "Lower Body", "feet shoulder-width apart, " +
                "chest up, bend knees to a 90 degree angle"),
        ExerciseEntry("Sit-up", "Core", "while laying on back, bring torso" +
                " towards knees"),
        ExerciseEntry("Lunges", "Lower Body", "one leg forward, knee bent " +
                "while other leg was positioned behind"),
        ExerciseEntry("Arm Circles", "Upper Body", "lateral raise of arms, " +
                "move them around in a circular motion"),
        ExerciseEntry("Burpees", "Full Body", "push-up to squat jump"),
        ExerciseEntry("Squat and Press", "Full Body", "squat and overhead " +
                "pushing of the weight")
    )




    // VIEW HOLDER TO REPRESENT INDIVIDUAL ITEMS/EXERCISES
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val text1 = item.findViewById<TextView>(R.id.xNameTextView)
        val text2 = item.findViewById<TextView>(R.id.xTypeTextView)

        init {
            item.setOnClickListener {
                Toast.makeText(applicationContext, mExercise[adapterPosition].description, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_exercise)
        val adapter =
            ExerciseListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        exerciseViewModel.allExercises.observe(this, Observer { exercises ->
            // Update the cached copy of the Exercises in the adapter.
            exercises?.let { adapter.setExercises(it) }
        })


        // Button to add new Exercise Entry
        floatingActionButton2.setOnClickListener{
            val j = Intent(this, NewExerciseActivity::class.java)
            startActivityForResult(j,REQUEST_CODE )
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            val eName = data?.getStringExtra("eName")
            val eType = data?.getStringExtra("eType")
            val eDescr = data?.getStringExtra("eDescr")

            val newExercise = eName?.let {
                ExerciseEntry(
                    name = it,
                    type = eType,
                    description = eDescr
                )
            }

            if (newExercise != null) {
                exerciseViewModel.insert(newExercise)
            }

            runOnUiThread{
                //recyclerView_exercise.adapter?.notifyDataSetChanged()
            }
        }
    }
}
