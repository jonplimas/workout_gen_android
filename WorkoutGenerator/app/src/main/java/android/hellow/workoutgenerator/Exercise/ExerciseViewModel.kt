package android.hellow.workoutgenerator.Exercise

import android.app.Application
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import android.hellow.workoutgenerator.Exercise.ExerciseRoomDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExerciseRepository

    // Using LiveData and caching what getExercises returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allExercises: LiveData<List<ExerciseEntry>>

    init {
        val exerciseDao = ExerciseRoomDatabase.getDatabase(application, viewModelScope).exerciseDao()
        repository =
            ExerciseRepository(
                exerciseDao
            )
        allExercises = repository.allExercises
    }

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(exercise: ExerciseEntry) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(exercise)
    }
}