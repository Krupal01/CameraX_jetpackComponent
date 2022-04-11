package com.example.camerax_jetpackcomponent.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_jetpackcomponent.R
import com.example.camerax_jetpackcomponent.databinding.VideoRowItemBinding

class SavedVideoAdapter : RecyclerView.Adapter<SavedVideoAdapter.VideoViewHolder>() {
    var data :ArrayList<Uri> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: ArrayList<Uri>){
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class VideoViewHolder(val binding : VideoRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videoUri : Uri){
            val mediaController = MediaController(binding.root.context)
            mediaController.setAnchorView(binding.itemVideoView)
            binding.itemVideoView.setMediaController(mediaController);
            binding.itemVideoView.setVideoURI(videoUri);
            binding.itemVideoView.requestFocus();

            binding.itemVideoView.setOnCompletionListener {
                it.seekTo(0)
                binding.itembtnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }

            binding.itembtnPlay.setOnClickListener {
                if(!binding.itemVideoView.isPlaying){
                    binding.itemVideoView.start();
                    binding.itembtnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
                }else{
                    binding.itemVideoView.pause()
                    binding.itembtnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                }
            }
        }

    }
}