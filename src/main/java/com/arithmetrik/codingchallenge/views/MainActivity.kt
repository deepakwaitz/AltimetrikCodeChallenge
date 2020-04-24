package com.arithmetrik.codingchallenge.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arithmetrik.codingchallenge.R
import com.arithmetrik.codingchallenge.views.fragments.ListingFragment

class MainActivity : AppCompatActivity(), FragmentCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Adding ListFragment Simply*/
        addFragment(ListingFragment(), false)

    }

    override fun addFragment(fragment: Fragment, addToBack: Boolean) {
        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        if (addToBack)
            fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.add(R.id.fragmentsContainer, fragment)
        fragmentTransaction.commit()
    }
}
