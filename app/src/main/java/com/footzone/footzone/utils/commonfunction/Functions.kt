package com.footzone.footzone.utils.commonfunction

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.footzone.footzone.R
import com.footzone.footzone.model.Comment
import com.footzone.footzone.model.IsOpen
import com.footzone.footzone.model.RateNumberPercentage
import java.time.Duration
import java.time.LocalTime

object Functions {
    fun resRating(comments: ArrayList<Comment>): Float {
        val averageRate: Float = try {
            val sumRates: Float = comments.sumOf { it.number * it.rate }.toFloat()
            val sumRateNumber: Float = comments.sumOf { it.number }.toFloat()
            sumRates / sumRateNumber

        } catch (e: Exception) {
            0f
        }
        return averageRate
    }

    fun rateNumbers(comments: ArrayList<Comment>): RateNumberPercentage {
        val sumOfAllRates = comments.sumOf { it.number * it.rate }
        return RateNumberPercentage(0, 0, 0, 0, 0).apply {
            comments.forEach {
                when (it.rate) {
                    1 -> this.one = it.number * it.rate * 100 / sumOfAllRates
                    2 -> this.two = it.number * it.rate * 100 / sumOfAllRates
                    3 -> this.three = it.number * it.rate * 100 / sumOfAllRates
                    4 -> this.four = it.number * it.rate * 100 / sumOfAllRates
                    5 -> this.five = it.number * it.rate * 100 / sumOfAllRates
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateInHours(startTime: LocalTime, endTime: LocalTime): Double {
        return Duration.between(startTime, endTime).toMinutes() / 60.0
    }

    fun ImageView.setFavouriteBackground() {
        this.setBackgroundResource(R.drawable.imageview_circle_fill_blue)
        this.setImageResource(R.drawable.ic_bookmark_white)
    }

    fun ImageView.setUnFavouriteBackground() {
        this.setBackgroundResource(R.drawable.imageview_circle)
        this.setImageResource(R.drawable.ic_bookmark)
    }

    fun showStadiumOpenOrClose(tvOpenClose: TextView, tvOpenCloseHour: TextView, isOpen: IsOpen) {
        when {
            isOpen.time == null -> {
                tvOpenClose.text = Html.fromHtml("<font color=#C8303F>" + "Yopiq")
                tvOpenCloseHour.text = " Stadion bugun ishlamaydi."
            }
            isOpen.open -> {
                tvOpenClose.text = Html.fromHtml("<font color=#177B4C>" + "Ochiq")
                tvOpenCloseHour.text = " ${isOpen.time.substring(0, 5)} da yopiladi"
            }
            else -> {
                tvOpenClose.text = Html.fromHtml("<font color=#C8303F>" + "Yopiq")
                tvOpenCloseHour.text = " ${isOpen.time.substring(0, 5)} da ochiladi"
            }
        }
    }

    fun ImageView.loadImageUrl(url: String?) {
        Glide.with(this).load(url).placeholder(getCircularProgressDrawable())
            .error(R.drawable.iv_profile).into(this)
    }

    private fun ImageView.getCircularProgressDrawable() =
        CircularProgressDrawable(this.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            colorFilter = PorterDuffColorFilter(
                ResourcesCompat.getColor(resources, R.color.colorBlue600, null),
                PorterDuff.Mode.ADD
            )
            start()
        }

    fun textCutter(text: String, startPosition: Int, endPosition: Int): String{
        return text.subSequence(startPosition,endPosition).toString()
    }
}