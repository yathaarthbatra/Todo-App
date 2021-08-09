package com.example.yourtodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourtodo.R
import com.example.yourtodo.adapters.TodoAdapter
import com.example.yourtodo.data.TodoData
import com.example.yourtodo.data.TodoRepository
import com.example.yourtodo.databinding.ActivityTodoBinding
import java.text.FieldPosition

class TodoMainActivity : AppCompatActivity(), BottomSheetFragment.ItemClickListener, RecyclerItemClickInterface{

    lateinit var binding:ActivityTodoBinding
    lateinit var viewModel: TodoViewModel
    lateinit var viewModelFactory: TodoViewModelFactory
    lateinit var adapter:TodoAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_todo)

        //binding contains the reference of the views of the Layout file
        //setting the layout of the Activity

        //initializing the viewModel Factory instance variable with repository object passed
        viewModelFactory= TodoViewModelFactory(TodoRepository())

        //initializing the viewModel instance
        viewModel=ViewModelProvider(this,viewModelFactory).get(TodoViewModel::class.java)


        //setting the recycler view adapter and layoutManager
        binding.taskRecyclerView.layoutManager=LinearLayoutManager(this)
        adapter=TodoAdapter()
        adapter.registerListener(this)
        binding.taskRecyclerView.adapter=adapter






        binding.addButtonTaskPage.setOnClickListener {
            //by clicking this button bottom SHeet Fragment will get open
            supportFragmentManager.let {
                BottomSheetFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }

        }


        viewModel.todoRepository.tasks.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    override fun onItemClick(item: String, taskName: String) {
        //this implementation is of Add Button on the Bottom Sheet
        //now here the task needs to go to dataBase and should be inserted
        viewModel.insertTask(TodoData(taskName,false))
        var size= viewModel.todoRepository.tasks.value?.size
        var i=0
        while(i < size!!){
            var element=viewModel.todoRepository.tasks.value?.get(i)
            Log.d("Main","Element at $i index is $element ")
            i++;
        }
        Log.d("Main","Size of the updated List is $size")

    }

    override fun onItemRecyclerView(position: Int) {
        //implementation of recycler view onClick which will delete the item
        Log.d("Main","OnButtonRecyler Clicked with position = $position ")
        viewModel.deleteTask(position)
        var size= viewModel.todoRepository.tasks.value?.size
        Log.d("Main","Size of the updated List is $size")
        adapter.notifyDataSetChanged()

    }
}

