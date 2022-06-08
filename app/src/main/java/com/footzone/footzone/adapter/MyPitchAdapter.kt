package com.footzone.footzone.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.footzone.footzone.databinding.ItemMyPitchLayoutBinding
import com.footzone.footzone.model.holders.Comment
import com.footzone.footzone.model.holders.Data
import com.footzone.footzone.model.holders.Photo
import com.footzone.footzone.ui.fragments.mystadium.MyStadiumFragment
import java.time.LocalDateTime

class MyPitchAdapter(var context: MyStadiumFragment, var items: ArrayList<Data>, private var onPitchClick: ((Data) -> Unit)):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemMyPitchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPitchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        if (holder is MyPitchViewHolder){
            holder.view.apply {
                refreshImagesAdapter(data.photos as ArrayList<Photo>, rvPithPhotos)
                tvPitchName.text = data.name

                if (data.isOpen.open) {
                    tvOpenClose.text = Html.fromHtml("<font color=#177B4C>" + "Ochiq")
                    tvOpenCloseHour.text = " · ${data.isOpen.time.substring(0, 5)} da yopiladi"
                } else {
                    tvOpenClose.text = Html.fromHtml("<font color=#C8303F>" + "Yopiq")
                    tvOpenCloseHour.text = " · ${data.isOpen.time} da ochiladi"
                }
                setStrokeColorToRatingBar(rbPitch)
                rbPitch.rating = context.resRating(data.comments as ArrayList<Comment>)
                rbPitch.setIsIndicator(true)
                tvRatingNums.text = "(${data.comments.size})"
                tvPitchPrice.text = "${data.hourlyPrice.toString().substring(0, data.hourlyPrice.toString().indexOf('.'))} so'm/soat"

                btnManagement.setOnClickListener {
                    onPitchClick.invoke(data)
                }

                linearNavigation.setOnClickListener {
                    context.openMap()
                }
            }
        }
    }

    private fun refreshImagesAdapter(photos: ArrayList<Photo>, rvPithPhotos: RecyclerView) {
        val pitchImagesAdapter = PitchImagesAdapter()
        pitchImagesAdapter.submitData(photos)
        rvPithPhotos.adapter = pitchImagesAdapter
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyPitchViewHolder(val view: ItemMyPitchLayoutBinding) : RecyclerView.ViewHolder(view.root)
}