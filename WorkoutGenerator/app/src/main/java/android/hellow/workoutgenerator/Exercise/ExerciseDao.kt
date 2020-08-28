package android.hellow.workoutgenerator.Exercise

import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise_table ORDER BY name ASC")
    fun getExercises(): LiveData<List<ExerciseEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: ExerciseEntry)

    @Query("DELETE FROM exercise_table")
    suspend fun deleteAll()




}