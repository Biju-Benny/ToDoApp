package com.example.todoapp

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_card_item.view.*

class MyAdapter(val context: Context, val toDoList: ArrayList<Task>, val onClickListener: OnClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task:Task){
            itemView.taskCardTV.text = task.task
            itemView.priorityCardTV.text = task.priority

            if(task.priority == "High"){
                itemView.setBackgroundResource(R.drawable.outline_high)


            }else if(task.priority == "Medium"){
                itemView.setBackgroundResource(R.drawable.outline_medium)
            }else{
                itemView.setBackgroundResource(R.drawable.outline_low)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_card_item,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(toDoList[position])

        holder.itemView.setOnClickListener {
            onClickListener.onClickListener(position)
        }


    }

    override fun getItemCount(): Int =toDoList.size
}