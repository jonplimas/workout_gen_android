package android.hellow.workoutgenerator.Exercise

import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.Exercise.ExerciseDao
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allExercises: LiveData<List<ExerciseEntry>> = exerciseDao.getExercises()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(exercise: ExerciseEntry) {
        exerciseDao.insert(exercise)
    }
}