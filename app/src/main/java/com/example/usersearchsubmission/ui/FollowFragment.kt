package com.example.usersearchsubmission.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.usersearchsubmission.R

class FollowFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    @SuppressLint("StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLabel: TextView = view.findViewById(R.id.section_label)
        val index = arguments?.getInt(ARG_POSITION, 0)
        val username = arguments?.getString(ARG_USERNAME,"username") ?: ""


        if (index == 1){
            tvLabel.text  = "Get Follower $username"
        } else {
            tvLabel.text  = "Get Following $username"
        }
    }

    companion object {
        const val ARG_POSITION = "section_number"
        const val ARG_USERNAME = "user_name"
    }

}