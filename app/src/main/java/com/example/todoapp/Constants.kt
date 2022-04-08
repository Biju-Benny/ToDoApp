package com.example.todoapp

object Constants {
    //db name
    const val DB_NAME = "TO_DO_LIST_DB"
    //db version
    const val DB_VERSION = 1

    const val TABLE_NAME ="TO_DO_TABLE"

    //columns
    const val T_ID = "ID"
    const val T_TASK = "TASK"
    const val T_PRIORITY = "PRIORITY"
    const val T_ADDED_TIME = "ADDED_TIME_STAMP"

    //create table query
    const val CREATE_TABLE =(
            "CREATE TABLE "+ TABLE_NAME+ "("+ T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + T_TASK+ " TEXT,"
                    + T_PRIORITY+ " TEXT,"
                    + T_ADDED_TIME+ " TEXT"
                    + ")"

            )
}