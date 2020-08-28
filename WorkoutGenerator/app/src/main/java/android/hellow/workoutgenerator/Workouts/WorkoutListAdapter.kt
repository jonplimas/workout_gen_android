package android.hellow.workoutgenerator.Workouts

import android.content.Context
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WorkoutListAdapter internal constructor(
    context : Context
) : RecyclerView.Adapter<WorkoutListAdapter.WorkoutViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)

    // cached copy of exercises
    private var exercises = emptyList<ExerciseEntry>()

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItemView: TextView = itemView.findViewById(R.id.wExerciseTV)
        val repsItemView: TextView = itemView.findViewById(R.id.wRepsTV)
        val setsItemView : TextView = itemView.findViewById(R.id.wSetsTV)



        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, exercises[adapterPosition].description,
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val itemView = inflater.inflate(R.layout.workout, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val current = exercises[position]
        holder.nameItemView.text = current.name
        holder.repsItemView.text = current.type
        holder.setsItemView.text = current.description
    }

    internal fun setWorkout(exercises: List<ExerciseEntry>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size
}