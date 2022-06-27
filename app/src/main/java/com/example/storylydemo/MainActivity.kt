package com.example.storylydemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.example.storylydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.storylyView3.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        //binding.storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        //binding.storylyView2.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        //binding.storylyView4.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

    }
}