package com.androidproficiencyapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidproficiencyapp.R
import com.androidproficiencyapp.R.*
import com.androidproficiencyapp.data.api.ApiHelper
import com.androidproficiencyapp.data.api.RetrofitBuilder
import com.androidproficiencyapp.data.model.AboutCanada
import com.androidproficiencyapp.ui.base.ViewModelFactory
import com.androidproficiencyapp.ui.main.adapter.MainAdapter
import com.androidproficiencyapp.ui.main.viewmodel.MainViewModel
import com.androidproficiencyapp.utils.CheckNetworkConnection
import com.androidproficiencyapp.utils.Status.ERROR
import com.androidproficiencyapp.utils.Status.LOADING
import com.androidproficiencyapp.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.rvItems
import kotlinx.android.synthetic.main.activity_main.swipeContainer
import kotlinx.android.synthetic.main.activity_main.textViewMessage

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    lateinit var cd: CheckNetworkConnection
    var isFromPullToRefresh: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        supportActionBar?.title = ""
        isFromPullToRefresh = false
        cd = CheckNetworkConnection()
        setupUI()
        initialize()

    }

    private fun initialize(){
        if (cd.isNetworkConnected(this@MainActivity)) {
            setupViewModel()
            setupObservers()
        }else{
            supportActionBar?.title = ""
            isFromPullToRefresh = false
            rvItems.visibility = View.GONE
            textViewMessage.visibility = View.VISIBLE
            textViewMessage.text = getString(R.string.no_connection)
        }
    }

    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {

        rvItems.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        rvItems.addItemDecoration(
            DividerItemDecoration(
                rvItems.context,
                (rvItems.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvItems.adapter = adapter

        swipeContainer.setOnRefreshListener {
            isFromPullToRefresh = true
            initialize()
            swipeContainer.isRefreshing = false
        }
    }

    private fun setupObservers() {
        viewModel.getList().observe(this, Observer {
            it?.let { resource ->
               when (resource.status) {
                    SUCCESS -> {
                        rvItems.visibility = View.VISIBLE
                        textViewMessage.visibility = View.GONE
                        Log.d("MainActivity","data successfully loaded")
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        rvItems.visibility = View.VISIBLE
                        textViewMessage.visibility = View.GONE
                        Log.d("MainActivity","Error Message "+it.message)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        Log.d("MainActivity", "Loading values from serve");

                        if (!isFromPullToRefresh) {
                            textViewMessage.visibility = View.VISIBLE
                            textViewMessage.text = getString(R.string.contentLoding)
                            isFromPullToRefresh = true
                        } else {
                            isFromPullToRefresh = false
                            textViewMessage.visibility = View.GONE
                        }
                    }
                }
            }
        })
    }

    private fun retrieveList(aboutCanada: AboutCanada) {

        supportActionBar?.title = aboutCanada.title
        val rows = aboutCanada.rows
        adapter.apply {
            addUsers(rows)
            notifyDataSetChanged()
        }
    }

}
