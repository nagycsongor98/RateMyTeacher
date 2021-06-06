package com.example.ratemyteacher.ui.profile

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.core.KoinComponent

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class ProfilePresenter(view: ProfileContract.View) : ProfileContract.Presenter(view),
    KoinComponent {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun saveUser(profileNameText: String, categorySpinner: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        val reference = database.child("App").child("Users").child(currentUser)
        reference.child("Username").setValue(profileNameText)
        reference.child("Department").setValue(categorySpinner)

    }

    override fun loadDataFromFirebase(categories: Array<String>) {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {

            view?.setupProfileCategories(categories)
            view?.setupProfileEmail("You're email is: ${user.email.toString()}")
            val currentUser = FirebaseAuth.getInstance().currentUser!!.uid
            val reference = database.child("App").child("Users").child(currentUser)

            reference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d("Response", error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        if (ds.key == "Username")
                            view?.setupProfileName(ds.value.toString())
                        if (ds.key == "Department") {
                            val index = categories.indexOf(ds.value)
                            view?.setupProfileSelectedCategory(index)
                        }
                        Log.d("Response", reference.toString())
                    }
                }
            })
        }
    }
}