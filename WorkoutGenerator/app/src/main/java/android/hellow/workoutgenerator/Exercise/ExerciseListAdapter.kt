package android.hellow.workoutgenerator.Exercise

import android.content.Context
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ExerciseListAdapter internal constructor(
    context : Context
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)

    // cached copy of exercises
    private var exercises = emptyList<ExerciseEntry>()

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItemView: TextView = itemView.findViewById(R.id.xNameTextView)
        val typeItemView: TextView = itemView.findViewById(R.id.xTypeTextView)
        val descrItemView : TextView = itemView.findViewById(R.id.xDescrTextView)

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, exercises[adapterPosition].description,
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.nameItemView.text = current.name
        holder.typeItemView.text = current.type
        holder.descrItemView.text = current.description
    }

    internal fun setExercises(exercises: List<ExerciseEntry>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size
}
