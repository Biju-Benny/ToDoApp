package com.example.todoapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context:Context?): SQLiteOpenHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION)

{
    override fun onCreate(db: SQLiteDatabase) {
        //create table on that db
        db.execSQL(Constants.CREATE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS"+Constants.TABLE_NAME)
        onCreate(db)

    }

    fun insertRecord(
        task:String?,
        priority:String?,
        addedTime:String?
    ):Long{
        //get writable data base as we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be put automatically as auto increment is implemented
        //insert data
        values.put(Constants.T_TASK,task)
        values.put(Constants.T_PRIORITY,priority)
        values.put(Constants.T_ADDED_TIME,addedTime)

        //insert row, it will return record id of saved record

        val id = db.insert(Constants.TABLE_NAME,null,values)

        //close db connection

        db.close()
        //return  id of inserted record
        return id

    }

    @SuppressLint("Range")
    fun getAllData(orderBy:String):ArrayList<Task>{
        val toDoList = ArrayList<Task>()
        val selectQuery = "SELECT * FROM ${Constants.TABLE_NAME} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if (cursor.moveToFirst()){
            do{
                val record = Task(
                    ""+cursor.getInt(cursor.getColumnIndex(Constants.T_ID)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.T_TASK)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.T_PRIORITY)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.T_ADDED_TIME))
                )
                //add record to list
                toDoList.add(record)
            }while (cursor.moveToNext())
        }
        db.close()
        return toDoList
    }

    fun editRecord(
        id:String?,
        task:String?,
        priority:String?,
        addedTime:String?
    ):Long{
        //get writable data base as we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be put automatically as auto increment is implemented
        //insert data
        values.put(Constants.T_TASK,task)
        values.put(Constants.T_PRIORITY,priority)
        values.put(Constants.T_ADDED_TIME,addedTime)



        return db.update(Constants.TABLE_NAME,values,"${Constants.T_ID}=?",
        arrayOf(id)).toLong()







    }

    fun deleteRecord(id:String){
        val db =writableDatabase
        db.delete(
            Constants.TABLE_NAME,
            "${Constants.T_ID} = ?",
            arrayOf(id)
        )
        db.close()
    }


}