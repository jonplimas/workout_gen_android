package android.hellow.workoutgenerator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_new_exercise.*
import kotlinx.android.synthetic.main.activity_workout.*

class WorkoutActivity : AppCompatActivity() {

    private val GEN_REQUEST_CODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        val wName: TextInputLayout = findViewById(R.id.wNameTextInput)
        val gButton: Button = findViewById(R.id.gen_button)


        // Radio Group Listeners
        wDurationRadioGroup.setOnCheckedChangeListener{ group, checkedId ->
            val dRadio: RadioButton = findViewById(checkedId)
        }

        wTypeRadioGroup.setOnCheckedChangeListener{ group, checkedId ->
            val tRadio: RadioButton = findViewById(checkedId)
        }

        gButton.setOnClickListener {
            // create Intent
            val genIntent = Intent(this, NewWorkoutActivity::class.java)

            //Save User Input
            val myName = wName.editText!!.text
            var did = wDurationRadioGroup.checkedRadioButtonId
            val durationRadio: RadioButton = findViewById(did)
            var tid = wTypeRadioGroup.checkedRadioButtonId
            val typeRadio: RadioButton = findViewById(tid)

            // Input validation
            if(TextUtils.isEmpty(myName) || did == -1 || tid == -1){
                setResult(Activity.RESULT_CANCELED, genIntent)
            } else {
                val wName = myName.toString()
                val wDuration = durationRadio.text.toString()
                val wType = typeRadio.text.toString()

                genIntent.putExtra("wName", wName)
                genIntent.putExtra("wDuration", wDuration)
                genIntent.putExtra("wType", wType)

                startActivity(genIntent)
            }
        }
    }



}