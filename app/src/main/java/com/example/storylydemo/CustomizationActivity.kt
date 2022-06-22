package com.example.storylydemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyListener
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.styling.StoryGroupIconStyling
import com.example.storylydemo.databinding.ActivityCustomizationBinding

class CustomizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizationBinding

    companion object {
        val TOKEN_2 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40\n"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storylyView: StorylyView = StorylyView(this)
        storylyView.storylyInit = StorylyInit(TOKEN_2)

        binding.storylyViewHolderProgrammatic.addView(storylyView)

        storylyView.setStoryItemTextColor(Color.CYAN)
        storylyView.setStoryGroupIconBackgroundColor(Color.BLACK)
        storylyView.setStoryGroupPinIconColor(Color.BLUE)
        storylyView.setStoryGroupSize(StoryGroupSize.Custom)
        storylyView.setStoryGroupIconStyling(StoryGroupIconStyling(350f,350f,25f))
        storylyView.setStoryGroupPinIconColor(Color.RED)
    }

}