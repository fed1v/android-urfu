package com.example.retrofitdemo.screens.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.retrofitdemo.R
import com.example.retrofitdemo.navigation.BaseNavigation
import com.example.retrofitdemo.screens.profile.ProfileFragment

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.loginView)
            .setOnClickListener {
                (activity as? BaseNavigation)?.openScreen(ProfileFragment())
            }
    }
}