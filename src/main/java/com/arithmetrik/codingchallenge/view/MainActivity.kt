package com.arithmetrik.codingchallenge.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arithmetrik.codingchallenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add the fragment to the 'fragmentsContainer' FrameLayout
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentsContainer, ListingFragment()).commit()
    }
}
