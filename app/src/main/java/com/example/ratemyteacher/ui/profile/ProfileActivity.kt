package com.example.ratemyteacher.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ratemyteacher.R
import com.example.ratemyteacher.databinding.ActivityProfileBinding
import com.example.ratemyteacher.ui.base.BaseActivity
import com.example.ratemyteacher.ui.login.LoginActivity
import com.example.ratemyteacher.ui.rateteacher.RateTeacherActivity
import com.example.ratemyteacher.ui.reviewslist.ReviewsListActivity
import com.example.ratemyteacher.ui.teacherslist.TeachersListActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProfileActivity : BaseActivity<ProfileContract.Presenter>(), ProfileContract.View {

    override val presenter: ProfileContract.Presenter? by inject { parametersOf(this) }
    private lateinit var binding: ActivityProfileBinding
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

        presenter?.loadDataFromFirebase(resources.getStringArray(R.array.Categories))

        binding.saveButton.setOnClickListener {
            presenter?.saveUser(
                binding.profileNameText.text.toString(),
                binding.categorySpinner.selectedItem.toString()
            )
        }

        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun setupProfileCategories(categories: Array<String>) {
        val adapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item, categories
        )
        binding.categorySpinner.adapter = adapter
    }

    override fun setupProfileEmail(email: String) {
        binding.textViewEmail.text = email
    }

    override fun setupProfileName(name: String) {
        binding.profileNameText.setText(name)
    }

    override fun setupProfileSelectedCategory(index: Int) {
        binding.categorySpinner.setSelection(index)
    }
}