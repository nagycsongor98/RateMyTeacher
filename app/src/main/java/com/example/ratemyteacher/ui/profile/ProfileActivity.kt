package com.example.ratemyteacher.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityProfileBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.reviewslist.ReviewsListActivity
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*

class ProfileActivity : BaseActivity<ProfileContract.Presenter>(), ProfileContract.View{

    override val presenter: ProfileContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityProfileBinding
    private lateinit var database: DatabaseReference
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rateImageView.setOnClickListener {
            startActivity(Intent(this, RateTeacherActivity::class.java))
            finish()
        }

        binding.teachersImageView.setOnClickListener {
            startActivity(Intent(this, TeachersListActivity::class.java))
            finish()
        }

        binding.reviewsImageView.setOnClickListener {
            startActivity(Intent(this, ReviewsListActivity::class.java))
            finish()
        }

        loadDataFromFirebase()

        binding.backButton.setOnClickListener {
            navigateBack()
        }

        binding.saveButton.setOnClickListener {
            saveUser()
        }
    }

    fun saveUser(){
        database = FirebaseDatabase.getInstance().reference
        var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        var reference =  database.child("App").child("Users").child(currentUser)
        var str = binding.profileNameText.text.toString()
        reference.child("Username").setValue(str)
        val text = binding.categorySpinner.selectedItem.toString()
        reference.child("Department").setValue(text)

    }

    fun navigateBack(){
//        startActivity(Intent(this, MainActivity::class.java))
    }

    fun loadDataFromFirebase() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {

            val categories = resources.getStringArray(R.array.Categories)
            val spinner = binding.categorySpinner
            if (spinner != null) {
                val adapter = ArrayAdapter(
                    this,
                    R.layout.support_simple_spinner_dropdown_item, categories
                )
                spinner.adapter = adapter
            }

            email = user.email.toString()
            binding.textViewEmail.text = "You're email is: " + email
            database = FirebaseDatabase.getInstance().reference
            var currentUser = FirebaseAuth.getInstance().currentUser!!.uid
            var reference = database.child("App").child("Users").child(currentUser)

            reference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d("Response", error.message)

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        if (ds.key == "Username")
                            binding.profileNameText.setText(ds.value.toString())
                        if (ds.key == "Department"){
                            var index = categories.indexOf(ds.value)
                            binding.categorySpinner.setSelection(index)
                        }

                        Log.d("Response", reference.toString())
                    }
                }
            })
        }



    }
}