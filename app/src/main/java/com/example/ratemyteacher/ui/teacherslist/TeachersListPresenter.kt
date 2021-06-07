package com.example.ratemyteacher.ui.teacherslist

import android.util.Log
import com.google.firebase.database.*
import org.koin.core.KoinComponent
import kotlin.collections.ArrayList

/**
 * @author  Csongor Nagy
 * @since  10.05.2021
 */
class TeachersListPresenter(view: TeachersListContract.View) : TeachersListContract.Presenter(view),
    KoinComponent {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val reference = database.child("App").child("Teachers").child("Departments")

    override fun getDepartments() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val categories: ArrayList<String> = ArrayList()

                for (ds in snapshot.children) {
                    categories.add(ds.key.toString())
                }
                view?.showDepartments(categories)
            }
        })
    }

    override fun getTeachers(department: String) {
        reference.child(department).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val teachers: ArrayList<String> = ArrayList()

                for (ds in snapshot.children) {
                    teachers.add(ds.value.toString())
                }
                view?.showTeachers(teachers)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)
            }

        })
    }
}