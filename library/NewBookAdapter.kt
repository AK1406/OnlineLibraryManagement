package com.example.library


import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ArrayAdapter
import android.widget.TextView


class NewBookAdapter(private val ctx: Context, private val layoutResId:Int, private val msgList:List<NewBookPlanModel>)
    : ArrayAdapter<NewBookPlanModel>(ctx,layoutResId,msgList){

    @SuppressLint("ViewHolder", "WrongConstant")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)
        val view:View = layoutInflater.inflate(layoutResId,null)

        val messageView: TextView =view.findViewById(R.id.textMessage)

        val msg = msgList[position]
        messageView.text=msg.message
        return view
    }

}