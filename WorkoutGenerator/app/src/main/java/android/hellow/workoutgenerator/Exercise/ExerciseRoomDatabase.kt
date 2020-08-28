package android.hellow.workoutgenerator.Exercise

import android.content.Context
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the ExerciseEntry class
@Database(entities = arrayOf(ExerciseEntry::class), version = 1, exportSchema = false)
public abstract class ExerciseRoomDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    private class ExerciseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var exerciseDao = database.exerciseDao()

                    // Delete all content here.
                    exerciseDao.deleteAll()

                    // Add sample words.
                    var myExercise = ExerciseEntry("Standard Push-up", "Upper Body",
                        "plank position, press against the floor")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Plank", "Core",
                        "hold supine position for 30 seconds")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Lunges", "Lower Body",
                        "one leg forward, knee bent while other leg was positioned behind")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Squat", "Lower Body",
                        "feet shoulder-width apart, chest up, bend knees to a 90 degree angle")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Sit-up", "Core",
                        "while laying on back, bring torso towards knees")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Arm Circles", "Upper Body",
                        "lateral raise of arms, move them around in a circular motion")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Burpees", "Full Body", "push-up to squat jump")
                    exerciseDao.insert(myExercise)

                    myExercise = ExerciseEntry("Squat and Press", "Full Body",
                        "squat, and overhead shoulder press")
                        exerciseDao.insert(myExercise)


                    myExercise = ExerciseEntry("Mountain Climbers", "Core",
                        "in plank position, knee drives to chest for 30 seconds")
                    exerciseDao.insert(myExercise)

                }
            }
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExerciseRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ExerciseRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "exercise_database"
                )
                    .addCallback(
                        ExerciseDatabaseCallback(
                            scope
                        )
                    )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}