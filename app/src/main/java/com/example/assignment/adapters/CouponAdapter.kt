package com.example.assignment.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.details
import com.example.assignment.model.Coupon
import com.example.assignment.model.Slab
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

class CouponAdapter(val context:Context,val coupons:ArrayList<Coupon>):RecyclerView.Adapter<CouponAdapter.ViewHolder>()
{
    inner class ViewHolder( view:View ) : RecyclerView.ViewHolder(view)
    {


        private val couponCode:TextView = view.findViewById(R.id.code)
        private val ribbonMessage:TextView = view.findViewById(R.id.ribbonMessage)
        private val percent :TextView = view.findViewById(R.id.percent)
        private val validity :TextView = view.findViewById(R.id.validity)
        private val minDeposit:TextView = view.findViewById(R.id.MinDeposit)
        private val slabMin :TextView = view.findViewById(R.id.slabMin)
        private val secondLast:TextView = view.findViewById(R.id.secondLast)
        private val last :TextView = view.findViewById(R.id.last)
        val detailsBtn :TextView = view.findViewById(R.id.details)


        private val slab1MinMax:TextView = view.findViewById(R.id.slab1MinMax)
        private val slab1Wagered:TextView = view.findViewById(R.id.slab1Wagered)
        private val slab1OTC : TextView  = view.findViewById(R.id.slab1OTC)


        private val slab2MinMax:TextView = view.findViewById(R.id.slab2MinMax)
        private val slab2Wagered:TextView = view.findViewById(R.id.slab2Wagered)
        private val slab2OTC : TextView  = view.findViewById(R.id.slab2OTC)



        private val slab3MinMax:TextView = view.findViewById(R.id.slab3MinMax)
        private val slab3Wagered:TextView = view.findViewById(R.id.slab3Wagered)
        private val slab3OTC : TextView  = view.findViewById(R.id.slab3OTC)

         var expandaleLayout:LinearLayout = view.findViewById(R.id.expandableLayout)

        init {

        }




        fun BindViews(coupon:Coupon,position: Int)
        {
            couponCode.text = coupon.code
            ribbonMessage.text = coupon.ribbon_msg
            var totalDiscount:Int =0
            var totalRupees:Float = 0.0F
              var slab_min:Float = coupon.slabs[0].min.toFloat()

            var wegerdPercent:Float = 0.0F
            var otcPercent:Float =0.0F
            var wegerdMax:Float =0.0F
            var wegerdOtcMax:Float =0.0F


            for (i in 0 until coupon.slabs.size)
            {
                if(coupon.slabs[i].wagered_max.toFloat() > wegerdMax ) wegerdMax = coupon.slabs[i].wagered_max.toFloat()
                if(coupon.slabs[i].otc_max.toFloat()  > wegerdOtcMax ) wegerdOtcMax = coupon.slabs[i].otc_max.toFloat()
                if(coupon.slabs[i].wagered_percent.toFloat()  > wegerdPercent ) wegerdPercent = coupon.slabs[i].wagered_percent.toFloat()
                if (coupon.slabs[i].otc_percent.toFloat()  > otcPercent) otcPercent = coupon.slabs[i].otc_percent.toFloat()

                 if(coupon.slabs[i].min.toFloat() < slab_min) slab_min = coupon.slabs[i].min.toFloat()
            }

            totalDiscount = (wegerdPercent + otcPercent).toInt()
            if(totalDiscount>100) totalDiscount = 100
            totalRupees   = wegerdMax + wegerdOtcMax

            percent.text  = "Get ${totalDiscount}% upto RS $totalRupees"
            validity.text = "valid till ${coupon.valid_until} "

            slabMin.text = "RS "+slab_min.toString()


            if(coupon.slabs.size == 3)
            {

                slab1MinMax.text  = ">=" + coupon.slabs[0].min  +" & <="+ coupon.slabs[0].max
                slab1Wagered.text =  " ${coupon.slabs[0].wagered_percent}% (Max. Rs${coupon.slabs[0].wagered_max}) "
                slab1OTC.text = "${coupon.slabs[0].otc_percent}% (Max. Rs${coupon.slabs[0].otc_max} )"

                slab2MinMax.text  = ">=" + coupon.slabs[1].min  +" & <="+ coupon.slabs[1].max
                slab2Wagered.text =  " ${coupon.slabs[1].wagered_percent}% (Max. Rs${coupon.slabs[1].wagered_max}) "
                slab2OTC.text = "${coupon.slabs[1].otc_percent}% (Max. Rs${coupon.slabs[1].otc_max} )"

                slab3MinMax.text  = ">=" + coupon.slabs[2].min  +" & <="+ coupon.slabs[2].max
                slab3Wagered.text =  " ${coupon.slabs[2].wagered_percent}% (Max. Rs${coupon.slabs[2].wagered_max}) "
                slab3OTC.text = "${coupon.slabs[2].otc_percent}% (Max. Rs${coupon.slabs[2].otc_max} )"

            }
            else if( coupon.slabs.size == 2)
            {
                slab1MinMax.text  = ">=" + coupon.slabs[0].min  +" & <="+ coupon.slabs[0].max
                slab1Wagered.text =  " ${coupon.slabs[0].wagered_percent}% (Max. Rs${coupon.slabs[0].wagered_max}) "
                slab1OTC.text = "${coupon.slabs[0].otc_percent}% (Max. Rs${coupon.slabs[0].otc_max} )"

                slab2MinMax.text  = ">=" + coupon.slabs[1].min  +" & <="+ coupon.slabs[1].max
                slab2Wagered.text =  " ${coupon.slabs[1].wagered_percent}% (Max. Rs${coupon.slabs[1].wagered_max}) "
                slab2OTC.text = "${coupon.slabs[1].otc_percent}% (Max. Rs${coupon.slabs[1].otc_max} )"
            }
            else if( coupon.slabs.size == 1)
            {
                slab1MinMax.text  = ">=" + coupon.slabs[0].min  +" & <="+ coupon.slabs[0].max
                slab1Wagered.text =  " ${coupon.slabs[0].wagered_percent}% (Max. Rs${coupon.slabs[0].wagered_max}) "
                slab1OTC.text = "${coupon.slabs[0].otc_percent}% (Max. Rs${coupon.slabs[0].otc_max} )"
            }
            else{

            }

            detailsBtn.setOnClickListener {
                coupon.setExpandedValue(!coupon.isExpanded())
                notifyItemChanged(position)
            }

            secondLast.text = "For every RS${coupon.wager_to_release_ratio_numerator} bet " +
                    "RS${coupon.wager_to_release_ratio_denominator} will be realeased from the bonous amount"

            last.text = "Bonous Expiry ${coupon.wager_bonus_expiry} from the issue"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coupon_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.BindViews(coupons[position],position)

        var flag:Boolean = coupons.get(position).isExpanded()
        if(flag)
        {
            holder.expandaleLayout.visibility = View.VISIBLE
            holder.detailsBtn.text = "Hide Details"
        }
        else {
            holder.expandaleLayout.visibility = View.GONE
            holder.detailsBtn.text = "Details"
        }

    }

    override fun getItemCount() =  coupons.size

}