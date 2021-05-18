package com.example.ratemyteacher.ui.rateteacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityMainBinding
import com.example.ratemyteacher.databinding.ActivityReatTeacherBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.main.MainContract
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RateTeacherActivity : BaseActivity<RateTeacherContract.Presenter>(), RateTeacherContract.View{

    override val presenter: RateTeacherContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityReatTeacherBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReatTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categories = resources.getStringArray(R.array.Categories)
        val spinner = binding.departmentSpinner
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item, categories
            )
            spinner.adapter = adapter
        }

        val teachers = resources.getStringArray(R.array.Teachers)
        val spinnerTeachers = binding.teacherSpinner
        if (spinnerTeachers != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item, teachers
            )
            spinnerTeachers.adapter = adapter
        }

        binding.saveReviewButton.setOnClickListener {
            if (!binding.reviewTextField.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT)
                    .show()
                database = FirebaseDatabase.getInstance().reference
                database.child("App").child("Teachers").child(spinnerTeachers.selectedItem.toString()).child("Reviews").push().setValue(binding.reviewTextField.text.toString())
            } else {
                Toast.makeText(applicationContext, "Please complete the review field !", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}