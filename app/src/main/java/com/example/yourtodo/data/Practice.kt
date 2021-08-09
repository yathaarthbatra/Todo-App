package com.example.yourtodo.data

fun main(){

    var task:MutableList<Int> = mutableListOf()
    task.add(20)
    task.add(30)
    task.add(40)
    task.add(50)
    task.add(60)

    task.removeAt(0)
    println("${task[0]}")


}