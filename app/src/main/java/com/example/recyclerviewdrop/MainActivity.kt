package com.example.recyclerviewdrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdrop.adapter.NumbersAdapter
import com.example.recyclerviewdrop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var numbersAdapter: NumbersAdapter
    private lateinit var numbersList:ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        numbersAdapter = NumbersAdapter(numbersList)
        binding.rv.adapter = numbersAdapter

        val callback:ItemTouchHelper.Callback = object :ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP + ItemTouchHelper.DOWN + ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,dragFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                numbersAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // something
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rv)

    }

    private fun loadData() {
        numbersList = ArrayList()
        for (i in 0..29){
            numbersList.add(i)
        }
    }
}