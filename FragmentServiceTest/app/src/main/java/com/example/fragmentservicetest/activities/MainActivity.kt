package com.example.fragmentservicetest.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentservicetest.R
import com.example.fragmentservicetest.fragments.FragmentOne
import com.example.fragmentservicetest.fragments.FragmentTwo
import com.example.fragmentservicetest.services.MyService

class MainActivity : AppCompatActivity() {
    var isOneFragmentLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val change = findViewById<Button>(R.id.btn_change)
        val stopBtn = findViewById<Button>(R.id.btn_service_stop)
        loadFragmentOne()
        change.setOnClickListener {
            if(isOneFragmentLoaded) {
                loadFragmentTwo()
            } else {
                loadFragmentOne()
            }
        }

        val intent = Intent(this, MyService::class.java)
        startService(intent)

        stopBtn.setOnClickListener {
            stopService(intent)
        }
    }

    fun loadFragmentOne() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isOneFragmentLoaded = true
    }
    fun loadFragmentTwo() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isOneFragmentLoaded = false
    }
}