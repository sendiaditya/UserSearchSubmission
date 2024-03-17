package com.example.usersearchsubmission.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersearchsubmission.data.response.UserItem

class MainViewModel : ViewModel() {
    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String> = _photo
    private val _listUser  = MutableLiveData<List<UserItem>>()
    val listReview : LiveData<List<UserItem>> = _listUser
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    companion object{
        private const val TAG = "MainActivity"
        private var USERNAME = "ahmad"
    }

    init {
        findRestaurant()
    }

}