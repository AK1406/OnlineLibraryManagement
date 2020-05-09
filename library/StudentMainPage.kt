package com.example.library

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class StudentMainPage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var listBooks: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_main_page)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.student_drawer_layout)
        navView = findViewById(R.id.student_nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        listBooks=findViewById(R.id.books_in_library)
        listBooks.setOnClickListener {
            val intent=Intent(this,ListOfBooks::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.borrow_book -> {
                Toast.makeText(this, "Borrow Book Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,BorrowBooks::class.java)
                startActivity(intent)
                finish()
            }
            R.id.return_book -> {
                Toast.makeText(this, "Return Book Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ReturnBook::class.java)
                startActivity(intent)
                finish()
            }
            R.id.see_plan -> {
                Toast.makeText(this, "See plan", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ShowBookPlan::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_update2 -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UpdatePasswordStudent::class.java)
                startActivity(intent)
                finish()
            }
            R.id.map -> {
                Toast.makeText(this, "Google Map Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_logout2 -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}