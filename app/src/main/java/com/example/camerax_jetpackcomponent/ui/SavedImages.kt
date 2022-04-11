package com.example.camerax_jetpackcomponent.ui

import android.annotation.SuppressLint
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Images
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camerax_jetpackcomponent.R
import com.example.camerax_jetpackcomponent.adapter.SavedImageAdapter
import com.example.camerax_jetpackcomponent.databinding.ActivitySavedImagesBinding


class SavedImages : AppCompatActivity() {

    private lateinit var adapter: SavedImageAdapter
    private lateinit var binding: ActivitySavedImagesBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Recycle", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = SavedImageAdapter()
        val data = arrayListOf<Uri>()

        binding.RcvSaved.layoutManager = LinearLayoutManager(this)
        binding.RcvSaved.adapter = adapter

        val cursor = contentResolver.query(
            Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(Images.Media._ID),
            Images.Media.DATA + " like? ",
            arrayOf("%CameraX-Image%"),
            null
        )

        if (cursor != null) {
            data.clear()
            while(cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Images.Media._ID))
                val uri = ContentUris.withAppendedId(Images.Media.EXTERNAL_CONTENT_URI,id)
                data.add(uri)
            }
            cursor.close()
            adapter.submitData(data)
        }else{
            Log.i("content " , "cursor null")
        }

    }
}