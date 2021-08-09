package com.example.yourtodo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yourtodo.data.TodoData
import com.example.yourtodo.data.TodoRepository

class TodoViewModel(var todoRepository: TodoRepository):ViewModel() {

    fun insertTask(task:TodoData){
        todoRepository.insertTask(task)
    }

    fun deleteTask(position:Int){
        todoRepository.deleteTask(position)
    }




}