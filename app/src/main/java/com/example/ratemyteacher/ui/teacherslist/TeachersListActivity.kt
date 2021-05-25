package com.example.ratemyteacher.ui.teacherslist

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityTeachersListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.collections.ArrayList

class TeachersListActivity : BaseActivity<TeachersListContract.Presenter>(), TeachersListContract.View{

    override val presenter: TeachersListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityTeachersListBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference
        var reference = database.child("App").child("Teachers").child("Departments")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("Response", error.message)

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var categories: ArrayList<String> = ArrayList()

                for (ds in snapshot.children) {
                    categories.add(ds.key.toString())
                }
                if (categories.size == 0) {
                    categories = ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.Categories)))
                }
                val spinner = binding.departmentSpinnerTeacherList
                if (spinner != null) {
                    val adapter = ArrayAdapter(
                        this@TeachersListActivity,
                        R.layout.support_simple_spinner_dropdown_item, categories
                    )
                    spinner.adapter = adapter
                }

            }
        })
    }
}