package android.hellow.workoutgenerator.DataClasses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class Workout (
    @PrimaryKey @NonNull @ColumnInfo(name = "name") val name : String = " ",
    @ColumnInfo(name = "type") val type : String? = null,
    @ColumnInfo(name = "duration")val duration : Int? = null,
    @ColumnInfo(name = "exercises") var excercises : List<ExerciseEntry>? = null,
    @ColumnInfo(name = "reps") var reps : Int? = null,
    @ColumnInfo(name = "sets") var sets : Int? = null
)