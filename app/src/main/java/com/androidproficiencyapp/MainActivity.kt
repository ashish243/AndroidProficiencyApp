package com.androidproficiencyapp

import adaptor.CanadaListAdapor
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.rvItems)

        //getting swipeRefreshLayput from xml
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeContainer)

        val adapter = CanadaListAdapor()
        // set a LinearLayoutManager with default vertical orientation
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter;
        swipeRefreshLayout.setOnRefreshListener {

            swipeRefreshLayout.isRefreshing = false
        }

    }
}
