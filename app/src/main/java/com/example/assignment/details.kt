package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseLongArray
import android.widget.TextView
import com.example.assignment.model.Slab

class details : AppCompatActivity() {

   // slab 1
    lateinit var slab1MinMax:TextView
    lateinit var slab1Wagered:TextView
    lateinit var slab1OTC : TextView

    // slab 2
    lateinit var slab2MinMax:TextView
    lateinit var slab2Wagered:TextView
    lateinit var slab2OTC : TextView

    // slab 3

    lateinit var slab3MinMax: TextView
    lateinit var slab3Wagered:TextView
    lateinit var slab3OTC : TextView

    private lateinit var slab:ArrayList<Slab>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        slab = intent.getSerializableExtra("details")  as ArrayList<Slab>

        Log.d("slab ", slab[0].max)

    }
}