package com.example.ratemyteacher.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ratemyteacher.R
import com.example.ratemyteacher.UserPreference
import com.example.ratemyteacher.databinding.ActivityProfileBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProfileActivity : BaseActivity<ProfileContract.Presenter>(), ProfileContract.View{

    override val presenter: ProfileContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityProfileBinding
    private lateinit var database: DatabaseReference
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        var reference =  database.child("App").child("Users")
        var str = binding.profileNameText.text.toString()
        reference.child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(str)

    }

    data class User(val username: String? = null, val email: String? = null) {

    }

    fun navigateBack(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun loadDataFromFirebase() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            email = user.email.toString()
            binding.textViewEmail.text = "You're email is: " + email
            database = FirebaseDatabase.getInstance().reference
            var reference = database.child("App").child("Users")

            reference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d("Response", error.message)

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        if (ds.key == FirebaseAuth.getInstance().currentUser!!.uid)
                            binding.profileNameText.setText(ds.value.toString())

                        Log.d("Response", reference.toString())
                    }
                }
            })
        }


        val categories = resources.getStringArray(R.array.Categories)
        val spinner = binding.categorySpinner
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item, categories
            )
            spinner.adapter = adapter
        }
    }
}