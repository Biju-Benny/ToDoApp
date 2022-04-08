package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    private lateinit  var priorityCreate:String
    private var task: String? = ""
    private var priority:String? = ""
    private var actionBar: ActionBar? = null
    lateinit var dbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        actionBar = supportActionBar
        actionBar!!.title = "Add Task"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = MyDbHelper(this)

        priorityDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                priorityCreate = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        create_btn.setOnClickListener {
            if (priorityCreate == "Select Priority Level"){
                val toast = Toast.makeText(this, "Select Priority", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()


            }
            else{
                inputData()
            }
        }
    }

    private fun inputData() {
        task = ""+createEditText.text.toString().trim()
        priority = ""+priorityCreate
        val timeStamp = System.currentTimeMillis()

        val id = dbHelper.insertRecord(
            ""+task,
            ""+priority,
            ""+timeStamp
        )
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
        onBackPressed()
        //startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //goback to previous activity
        return super.onSupportNavigateUp()
    }
}