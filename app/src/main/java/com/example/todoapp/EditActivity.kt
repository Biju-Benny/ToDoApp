package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    private lateinit  var priorityEdit:String
    private var actionBar: ActionBar? = null
    private var task: String? = ""
    private var priority:String? = ""
    lateinit var dbHelper: MyDbHelper
    lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        actionBar = supportActionBar
        actionBar!!.title = "Edit Task"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = MyDbHelper(this)

        id = intent.getStringExtra("id").toString()
        val taskItem:String = intent.getStringExtra("task").toString()
        val priority:String = intent.getStringExtra("priority").toString()

        editTaskEditText.setText(taskItem)

        editPriorityDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                priorityEdit= adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        edit_btn.setOnClickListener {
            if (priorityEdit == "Select Priority Level"){
                val toast = Toast.makeText(this, "Select Priority", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()


            }
            else{
                editData()
            }
        }

        delete_btn.setOnClickListener {
            deleteData()
        }







    }

    private fun deleteData() {
        dbHelper.deleteRecord(id)
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    private fun editData() {
        task = ""+editTaskEditText.text.toString().trim()
        priority = ""+priorityEdit
        val timeStamp = System.currentTimeMillis()

        dbHelper.editRecord(
            "$id",
            "$task",
            "$priority",
            "$timeStamp"
        )
        Toast.makeText(this, "Task edited", Toast.LENGTH_SHORT).show()
        onBackPressed()
        //startActivity(Intent(this,MainActivity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //goback to previous activity
        return super.onSupportNavigateUp()
    }
}