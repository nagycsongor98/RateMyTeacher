package com.example.ratemyteacher.ui.teacherslist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityTeachersListBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.profile.ProfileActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.reviewslist.ReviewsListActivity
import com.google.firebase.database.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.collections.ArrayList

class TeachersListActivity : BaseActivity<TeachersListContract.Presenter>(),
    TeachersListContract.View {

    override val presenter: TeachersListContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityTeachersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rateImageView.setOnClickListener {
            startActivity(Intent(this, RateTeacherActivity::class.java))
            finish()
        }

        binding.reviewsImageView.setOnClickListener {
            startActivity(Intent(this, ReviewsListActivity::class.java))
            finish()
        }

        binding.profileImageView.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        presenter?.getDepartments()

        binding.departmentSpinnerTeacherList.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter?.getTeachers(binding.departmentSpinnerTeacherList.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("Response", "onNothingSelected")
            }

        }
    }

    override fun showDepartments(categories: ArrayList<String>) {
        var mCategories = categories
        if (mCategories.size == 0) {
            mCategories =
                ArrayList(listOf(*resources.getStringArray(R.array.Categories)))
        }
        val adapter = ArrayAdapter(
            this@TeachersListActivity,
            R.layout.support_simple_spinner_dropdown_item, mCategories
        )
        binding.departmentSpinnerTeacherList.adapter = adapter
    }

    override fun showTeachers(teachers: java.util.ArrayList<String>) {
        binding.teachersRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.teachersRecyclerView.adapter =
            TeachersAdapter(teachers){
                openWebPage()
            }
    }

    private fun openWebPage(){
        val departments = ArrayList(listOf(*resources.getStringArray(R.array.Categories)))
        val departmentsUrl = ArrayList(listOf(*resources.getStringArray(R.array.urls)))
        val departmentIndex = departments.indexOfFirst { it == binding.departmentSpinnerTeacherList.selectedItem.toString() }

        if (departmentIndex >= 0 && departmentIndex < departmentsUrl.size){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(departmentsUrl[departmentIndex])
            startActivity(intent)
        }
    }
}