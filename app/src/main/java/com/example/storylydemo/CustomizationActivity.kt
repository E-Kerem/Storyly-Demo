package com.example.storylydemo

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.styling.StoryGroupIconStyling
import com.appsamurai.storyly.styling.StoryGroupTextStyling
import com.appsamurai.storyly.styling.StoryHeaderStyling
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
        val colors : Array<Int> = arrayOf(Color.BLUE, Color.RED, Color.GREEN)
        storylyView.setStoryGroupIconBorderColorSeen(colors)
        val textSize = Pair(100, 100)
        storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(true, Typeface.DEFAULT,textSize, 100,100,5,Color.GREEN))
        storylyView.setStoryGroupIconBorderColorNotSeen(colors)
        storylyView.setStoryGroupIVodIconColor(Color.GRAY)
        storylyView.setStoryGroupIconImageThematicLabel("dark")
        val barColors : Array<Int> = arrayOf(Color.YELLOW, Color.GREEN)
        storylyView.setStoryItemProgressBarColor(barColors)
        storylyView.setStoryItemIconBorderColor(colors)
        storylyView.setStoryHeaderStyling(StoryHeaderStyling(true,true,
            false,null,null))
        //storylyView.setStoryInteractiveTextTypeface(Typeface.DEFAULT_BOLD)
    }
}