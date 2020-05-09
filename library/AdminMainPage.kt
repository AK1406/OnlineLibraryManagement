package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

 class AdminMainPage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
     private lateinit var addBooks: Button
     private lateinit var bookDetails: Button

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_main_page)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

         addBooks=findViewById(R.id.add_new_book)
         bookDetails=findViewById(R.id.book_details)

         addBooks.setOnClickListener {
             val addBook = Intent(this,AddNewBooks::class.java)
             startActivity(addBook)
             finish()
         }

         bookDetails.setOnClickListener {
             val bookDetails=Intent(this,ManageBookDetails::class.java)
             startActivity(bookDetails)
             finish()
         }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.total_books_collection -> {
                Toast.makeText(this, "No. of books in library ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TotalBooks::class.java)
                startActivity(intent)
                finish()
            }
            R.id.total_books_borrowed -> {
                Toast.makeText(this, "Total Borrowed Books", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TotalBooksBorrowed::class.java)
                startActivity(intent)
                finish()
            }
            R.id.new_plan -> {
                Toast.makeText(this, "New Plan", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NewBookPlanWriter::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UpdatePasswordActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_logout -> {
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
