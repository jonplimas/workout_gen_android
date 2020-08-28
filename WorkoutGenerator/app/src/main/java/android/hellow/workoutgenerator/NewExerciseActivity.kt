package android.hellow.workoutgenerator

import android.app.Activity
import android.content.Intent
import android.hellow.workoutgenerator.DataClasses.ExerciseEntry
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_new_exercise.*

class NewExerciseActivity : AppCompatActivity() {

    private lateinit var mName : TextInputLayout
    private lateinit var mDescr :TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_exercise)

        mName = findViewById(R.id.xNameTextInput)
        mDescr = findViewById(R.id.xDescrTextInput)


        // RADIO GROUP LISTENER
        xTypeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
        }

        // SUBMIT NEW EXERCISE
        submit_button.setOnClickListener {
            // create intent reply to Exercise List
            val replyIntent = Intent()

            //save user-input
            val n = mName.editText?.text
            val d = mDescr.editText?.text
            var xid = xTypeRadioGroup.checkedRadioButtonId
            val typeRadio: RadioButton = findViewById(xid)

            // Input Validation: IF ANY INPUT IS MISSING
            if(TextUtils.isEmpty(n) || TextUtils.isEmpty(d) || xid == -1){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                //
                val eName = n.toString()
                val eType = typeRadio.text.toString()
                val eDescr = d.toString()
                //val e = ExerciseEntry(n.toString(), typeRadio.text.toString(), d.toString())

                replyIntent.putExtra("eName", eName)
                replyIntent.putExtra("eType", eType)
                replyIntent.putExtra("eDescr", eDescr)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()

        }


    }
    companion object {
        const val EXTRA_REPLY = "com.example.android.exerciselistsql.REPLY"
    }
}