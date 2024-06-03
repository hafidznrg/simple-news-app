package com.hafidznrg.newsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hafidznrg.newsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, MainFragment())
        fragmentTransaction.commit()
    }
}

