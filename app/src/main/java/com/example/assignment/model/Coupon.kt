package com.example.assignment.model

class Coupon (
    val id:String,
    val valid_until:String,
    val code:String,
    val ribbon_msg:String,
    val wager_to_release_ratio_numerator:String,
    val wager_to_release_ratio_denominator:String,
    val slabs:ArrayList<Slab>,
    val wager_bonus_expiry:String,
    var expanded:Boolean
    )
{

    init {
        this.expanded = false
    }

    fun isExpanded() =  this.expanded

    fun setExpandedValue(  flag: Boolean)
    {
        this.expanded = flag
    }

}