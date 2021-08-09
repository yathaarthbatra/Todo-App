package com.example.yourtodo.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourtodo.databinding.BottomSheetDesignBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {


    //refrence variable to access the views
    //it has to be initialized using inflate by its generated class
    lateinit var binder:BottomSheetDesignBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder= BottomSheetDesignBinding.inflate(inflater,container,false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //setting onClickListener on Add Button
        binder.bottomSheetAddButton.setOnClickListener {
            var taskName:String=binder.bottomSheetTaskName.text.toString()
            mListener?.onItemClick("Add",taskName) //the behaviour of this will be handled in the activity
            dismiss()
        }

    }





    //declaring a listener instance of the interface which can be null
    private var mListener:ItemClickListener?=null
    //you must be confused how this itemListener will get initialized , we will set in OnAttach fun




    //creating an interface for the onClick behaviour in the Bottom Sheet
    interface ItemClickListener {
        fun onItemClick(item: String,taskName:String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener) {
            mListener = context as ItemClickListener
            //setting actually to the activity so that we can override in it and hence its behaviour can be showed here
        } else {
            throw RuntimeException(
                context.toString()
                    .toString() + " must implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}