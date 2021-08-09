package com.example.yourtodo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.FieldPosition


//this repository contains the source of data as we are not dealing with Room or Network Source
class TodoRepository {

    var tasks = MutableLiveData<MutableList<TodoData>>()

    init{
        tasks.value=ArrayList<TodoData>()
    }

    fun insertTask(task:TodoData){
        var updated=tasks.value
        updated?.add(task)
        tasks.postValue(updated)

    }
    fun deleteTask(position: Int){
        var updated=tasks.value
        updated?.removeAt(position)
        tasks.postValue(updated)
    }







}