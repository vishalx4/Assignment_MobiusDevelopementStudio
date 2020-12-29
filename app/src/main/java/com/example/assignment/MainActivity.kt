package com.example.assignment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapters.CouponAdapter
import com.example.assignment.model.Coupon
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var coupons:ArrayList<Coupon>

    private lateinit var adapter : RecyclerView.Adapter<*>
    private lateinit var rv: RecyclerView
    private lateinit var lm :RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coupons = ArrayList()

        adapter =CouponAdapter(this,coupons)
        rv = findViewById(R.id.rv)
        lm = LinearLayoutManager(this)
        rv.adapter  = adapter
        rv.layoutManager = lm



        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://run.mocky.io/v3/")
            .build()

        // creating object
        val couponHolderAPI = retrofit.create(CouponHolderApi::class.java)

        // method call
        val myCall:Call<List<Coupon>> = couponHolderAPI.getCoupons()


        myCall.enqueue(object :Callback<List<Coupon>>{
            override fun onResponse(call: Call<List<Coupon>>, response: Response<List<Coupon>>) {
                val Coupons:List<Coupon> = response.body()!!
                var i:Int = 0
                for(coupon in Coupons)
                {
                    coupons.add(coupon)
                }
                adapter.notifyDataSetChanged()
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Coupon>>, t: Throwable) {
                Log.d("vishal ","something went wrong")
            }

        })

    }



}