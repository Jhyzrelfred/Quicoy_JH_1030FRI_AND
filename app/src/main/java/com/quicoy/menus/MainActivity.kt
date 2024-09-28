package com.quicoy.menus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx. appcompat. widget. Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Set up the toolbar if you're using a custom toolbar
        val toolbar: Toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)

        // Set Action Bar title and subtitle
        supportActionBar?.apply {
            title = "Bossing"
            subtitle = "Kamusta ang Buhay buhay"
        }

        // Load initial fragment
        loadFragment(MainPage())
    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        return true
    }

    // Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_first -> {
                // Navigate to the first fragment
                loadFragment(FirstFragment())
                true
            }
            R.id.menu_dialog -> {
                // Show the dialog fragment
                val dialog = com.quicoy.menus.Dialog()
                dialog.show(supportFragmentManager, "CustomDialog")
                true
            }
            R.id.action_exit -> {
                // Exit the app
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Load the selected fragment
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}