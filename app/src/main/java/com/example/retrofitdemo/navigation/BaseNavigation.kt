package com.example.retrofitdemo.navigation

import androidx.fragment.app.Fragment

interface BaseNavigation {
    fun openScreen(fragment: Fragment)
}