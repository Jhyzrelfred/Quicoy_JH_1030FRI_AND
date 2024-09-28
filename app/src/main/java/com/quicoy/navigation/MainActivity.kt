package com.quicoy.navigation;

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bottomnavbar.Calculator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout.Tab
import com.quicoy.navigation.R


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            if (savedInstanceState == null) {
                loadFragment(Profiles())
            }


            bottomNav = findViewById(R.id.bottomNav)

            // Set listener for navigation
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.Profile -> {
                        loadFragment(Profiles())
                        true
                    }
                    R.id.calcu -> {
                        loadFragment(Calculator())
                        true
                    }
                    R.id.tab -> {
                        loadFragment(TabList())
                        true
                    }
                    else -> false
                }
            }


            bottomNav.selectedItemId = R.id.Profile
        }


        private fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
}