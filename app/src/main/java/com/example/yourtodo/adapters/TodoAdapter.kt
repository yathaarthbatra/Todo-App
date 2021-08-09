package com.example.yourtodo.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yourtodo.data.TodoData
import com.example.yourtodo.databinding.SingleTaskDesignBinding
import com.example.yourtodo.ui.RecyclerItemClickInterface

class TodoAdapter  : ListAdapter<TodoData, TodoAdapter.MainViewHolder>(DataDiffCallBack()){

    private var mlistener:RecyclerItemClickInterface?=null

    fun registerListener(context:Context){
        mlistener=context as RecyclerItemClickInterface
    }

    private class DataDiffCallBack : DiffUtil.ItemCallback<TodoData>(){
        override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // in this we will inflate the view
        var layoutInflater = LayoutInflater.from(parent.context)
        var binding = SingleTaskDesignBinding.inflate(layoutInflater)
        return MainViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(getItem(position),position)

    }
    inner class MainViewHolder(var binding:SingleTaskDesignBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(data:TodoData,position: Int){
            //in this fun we will bind the data to the Views
            binding.taskCheckBox.isChecked=data.completed
            binding.taskName.text=data.taskName

            //setting onClick Listener on the specific ViewHolder
            binding.root.setOnClickListener {
                mlistener?.onItemRecyclerView(position)
            }
            binding.taskCheckBox.setOnClickListener {
                mlistener?.onItemRecyclerView(position)
            }



        }
    }
}