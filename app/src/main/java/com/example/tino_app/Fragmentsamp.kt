package com.example.tino_app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Fragmentsamp : AppCompatActivity() {

    private var isFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_samp)

        // Display initial fragment
        replaceFragment(FragmentA())

        findViewById<Button>(R.id.btn_toggle_fragment).setOnClickListener {
            // Toggle between FragmentA and FragmentB
            val fragment = if (isFragmentA) FragmentB() else FragmentA()
            replaceFragment(fragment)
            isFragmentA = !isFragmentA
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
