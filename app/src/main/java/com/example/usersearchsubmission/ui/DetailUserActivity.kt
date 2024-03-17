package com.example.usersearchsubmission.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.usersearchsubmission.R
import com.example.usersearchsubmission.data.response.DetailUserResponse
import com.example.usersearchsubmission.data.retrofit.ApiConfig
import com.example.usersearchsubmission.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        private const val TAG = "DetailUserActivity"
        private var USERNAME = "ahmad"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        USERNAME = intent.getStringExtra("username") ?: ""

        supportActionBar?.hide()
        findUser()


        val sectionsPagerAdapter = SectionsPagerAdapter(this, USERNAME)
//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun findUser() {
        showLoading(true)
        val client = ApiConfig.getApiService().getDetailUser(USERNAME)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Toast.makeText(this@DetailUserActivity, "Failed to get user data", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@DetailUserActivity, "Failed to get user data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUserData(user: DetailUserResponse) {
        Glide.with(this)
            .load(user.avatarUrl)
            .transform(CircleCrop()) // Add a circular transformation for user profile
            .into(binding.ivUserProfilePhoto)
        binding.tvName.text = user.name
        binding.tvUsername.text = user.login
        val followingText = getString(R.string.followingCount, user.following)
        val followersText = getString(R.string.followersCount, user.followers)
        binding.tvFollowing.text = followingText
        binding.tvFollowers.text = followersText

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarUser.visibility = View.VISIBLE
        } else {
            binding.progressBarUser.visibility = View.GONE
        }
    }
}