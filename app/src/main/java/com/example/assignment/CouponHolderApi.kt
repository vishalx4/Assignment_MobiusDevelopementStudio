package com.example.assignment

import com.example.assignment.model.Coupon
import retrofit2.Call
import retrofit2.http.GET

interface CouponHolderApi {
    @GET("4c663239-03af-49b5-bcb3-0b0c41565bd2")
    fun getCoupons() : Call<List<Coupon>>
}