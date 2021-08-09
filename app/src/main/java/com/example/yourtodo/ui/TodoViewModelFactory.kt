package com.example.yourtodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yourtodo.data.TodoRepository
import java.lang.IllegalArgumentException

class TodoViewModelFactory(var todoRepository: TodoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java))
            return TodoViewModel(todoRepository) as T

        throw IllegalArgumentException("Unknown Argument")

    }
}