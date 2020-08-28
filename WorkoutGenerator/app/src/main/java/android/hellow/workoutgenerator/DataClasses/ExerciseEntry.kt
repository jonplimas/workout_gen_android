package android.hellow.workoutgenerator.DataClasses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExerciseEntry (
    @PrimaryKey @NonNull @ColumnInfo(name = "name") val name : String = " ",
    @ColumnInfo(name = "type") val type : String? = null,
    @ColumnInfo(name = "description") val description : String? = null
    // @ColumnInfo(name = "reps") val reps : Int? = null,
    // @ColumnInfo(name = "sets") val sets : Int? = null
)