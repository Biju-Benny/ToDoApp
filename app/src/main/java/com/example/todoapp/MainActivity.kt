package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var dbHelper: MyDbHelper
    var listItems: ArrayList<Task> = arrayListOf()
    private val NEWEST_FIRST = "${Constants.T_ADDED_TIME} DESC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDbHelper(this)

        loadRecords()


        addTaskButton.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))

        }
    }

    private fun loadRecords() {
        mainRecyclerview.layoutManager = LinearLayoutManager(this)
        listItems = dbHelper.getAllData(NEWEST_FIRST)
        val adapter = MyAdapter(this,listItems,this)
        mainRecyclerview.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        loadRecords()
    }

    override fun onClickListener(position: Int) {
        val intent = Intent(this,EditActivity::class.java)
        intent.putExtra("id",listItems[position].id)
        intent.putExtra("task",listItems[position].task)
        intent.putExtra("priority",listItems[position].priority)
        startActivity(intent)
    }


}