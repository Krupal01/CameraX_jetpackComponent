package com.example.camerax_jetpackcomponent.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_jetpackcomponent.databinding.ImageRowItemBinding
import com.squareup.picasso.Picasso

class SavedImageAdapter : RecyclerView.Adapter<SavedImageAdapter.ImageViewHolder>() {

    var data :ArrayList<Uri> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: ArrayList<Uri>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding : ImageRowItemBinding = ImageRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
       return data.size
    }

    class ImageViewHolder(val binding : ImageRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imgUri : Uri){
            Picasso.with(binding.root.context)
                .load(imgUri)
                .resize(400,400)
                .centerCrop()
                .into(binding.itemImage)
        }
    }

}