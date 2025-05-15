package com.example.retrofitdemo.screens.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.retrofitdemo.network.DotaApi
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dotaApi: DotaApi): ViewModel() {


}