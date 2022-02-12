package com.example.recyclerviewdrop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdrop.R
import com.example.recyclerviewdrop.databinding.ItemRvBinding
import java.util.*
import kotlin.collections.ArrayList

class NumbersAdapter(val numbersList:ArrayList<Int>):
    RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>(),
    ItemTouchHelper{


    inner class NumbersViewHolder(val itemRvBinding:ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(pos:Int){
            itemRvBinding.tv.text = (pos+1).toString()

            itemView.setOnClickListener {
                itemRvBinding.tv.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.com))
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        return NumbersViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.onBind(numbersList[position])
    }

    override fun getItemCount(): Int {
        return numbersList.size

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if(fromPosition<toPosition){
            for (i in fromPosition..toPosition){
                Collections.swap(numbersList,i,i+1)
            }
        }
        else{
            for(i in toPosition downTo fromPosition){
                Collections.swap(numbersList,i,i-1)
            }
        }
        notifyItemMoved(fromPosition,toPosition)


    }

    interface OnTouchClickListener{
        fun onItemClick(position: Int)
    }


    //    override fun onItemDismiss(position: Int) {
//       // numbersList.removeAt(position)
//        //notifyItemRemoved(position)
//
//    }
    }
