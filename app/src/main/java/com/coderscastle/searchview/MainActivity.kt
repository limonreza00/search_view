package com.coderscastle.searchview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        val listView = findViewById<ListView>(R.id.listView)

        val data = listOf("Abram", "Baby", "Cinderella", "Daria","Evans","Fan","Goat","High","Ice","Jack","Klara","Lemon"
        ,"ant","idioms","jack","eve","Ox","Zebra","xo","snakeLord","snake")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listView.adapter=adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()

                if(data.contains(query)){
                    adapter.filter.filter(query)
                }else{
                    adapter.filter.filter("")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.filter.filter(newText)
                return false
            }

        })
    }
}