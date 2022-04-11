package com.example.camerax_jetpackcomponent.ui

import android.content.ContentUris
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camerax_jetpackcomponent.adapter.SavedImageAdapter
import com.example.camerax_jetpackcomponent.adapter.SavedVideoAdapter
import com.example.camerax_jetpackcomponent.databinding.ActivitySavedVideoBinding

class SavedVideoActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySavedVideoBinding
    private lateinit var adapter : SavedVideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SavedVideoAdapter()
        val data = arrayListOf<Uri>()

        binding.RcvSavedVideo.layoutManager = LinearLayoutManager(this)
        binding.RcvSavedVideo.adapter = adapter

        val cursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Video.Media._ID),
            MediaStore.Video.Media.DATA + " like? ",
            arrayOf("%CameraX-Video%"),
            null
        )

        if (cursor != null) {
            data.clear()
            while(cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                val uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,id)
                data.add(uri)
            }
            cursor.close()
            adapter.submitData(data)
        }else{
            Log.i("content " , "cursor null")
        }
    }
}