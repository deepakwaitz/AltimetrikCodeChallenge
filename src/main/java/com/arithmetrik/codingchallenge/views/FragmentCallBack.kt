package com.arithmetrik.codingchallenge.views

import androidx.fragment.app.Fragment

interface FragmentCallBack {
    fun addFragment(fragment: Fragment, addToBack: Boolean)
}