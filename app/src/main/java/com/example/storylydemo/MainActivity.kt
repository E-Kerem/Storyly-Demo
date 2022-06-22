package com.example.storylydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.example.storylydemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        binding.storylyView2.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        binding.storylyView3.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        binding.storylyView4.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
    }
}