package com.example.mywhetherappadarsh

import android.app.Fragment
import android.os.Bundle
import android.widget.Button


import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val button = findViewById<Button>(R.id.buttontest)
    button.setOnClickListener { loadFragment(WhetherFragment()) }
}
    private fun loadFragment(fragment: Fragment) {
// create a FragmentManager
        val fm = fragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction = fm.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit() // save the changes
    }

}