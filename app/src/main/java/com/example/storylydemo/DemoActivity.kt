package com.example.storylydemo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.styling.StoryGroupTextStyling
import com.example.storylydemo.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding

    lateinit var textColorPicker: Spinner
    lateinit var textFontPicker: Spinner
    lateinit var textVisible: CheckBox

    lateinit var iconColorPicker: Spinner

    lateinit var bordercolorPicker1: Spinner
    lateinit var bordercolorPicker2: Spinner
    lateinit var bordercolorPicker3: Spinner
    lateinit var bordercolorApply: Button
    lateinit var bordercolorVisible: CheckBox

    lateinit var pincolorPicker: Spinner
    lateinit var ivodcolorPicker: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Storyly initialization
        val storylyView: StorylyView =StorylyView(this)
        storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

        binding.storylyViewHolderDemo.addView(storylyView)
        binding.storylyViewDemo.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

        // Customization tools initialization
        textColorPicker = findViewById(R.id.textColorPicker_spinner) as Spinner
        textFontPicker = findViewById(R.id.textFontPicker_spinner) as Spinner
        textVisible = findViewById(R.id.checkbox_textVisible) as CheckBox
        iconColorPicker = findViewById(R.id.iconColorPicker_spinner) as Spinner

        bordercolorPicker1 = findViewById(R.id.borderColorPicker1_spinner) as Spinner
        bordercolorPicker2 = findViewById(R.id.borderColorPicker2_spinner) as Spinner
        bordercolorPicker3 = findViewById(R.id.borderColorPicker3_spinner) as Spinner
        bordercolorApply = findViewById(R.id.border_color_apply) as Button
        bordercolorVisible = findViewById(R.id.checkbox_icon_border_visible) as CheckBox

        pincolorPicker = findViewById(R.id.pinColorPicker_spinner) as Spinner

        ivodcolorPicker = findViewById(R.id.ivodColorPicker_spinner) as Spinner

        // Tools modifications - Text Spinner
        var textColorSpinnerOptions = arrayOf(Color.BLACK,Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY,
            Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW)

        val textSize = Pair(TypedValue.COMPLEX_UNIT_PX, 40)

        textColorPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (textColorPicker.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                    else
                    storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(true,
                        Typeface.DEFAULT,textSize, 2,3,3,
                        result))
            }
        }
        // Tools modifications - Text font
        var textFontSpinnerOptions = arrayOf(Typeface.DEFAULT,Typeface.DEFAULT,Typeface.DEFAULT_BOLD,
                                        Typeface.SERIF, Typeface.SANS_SERIF, Typeface.MONOSPACE)

        textFontPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (textFontPicker.selectedItemPosition)
                var result = textFontSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else
                    storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(true,
                        result,textSize, 2,3,3, Color.BLACK))
            }
        }

        // Tools modifications - Text Visible
        textVisible.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) {
                storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(true,
                    Typeface.DEFAULT,textSize, 2,3,3, Color.BLACK))
            } else {
                storylyView.setStoryGroupTextStyling(StoryGroupTextStyling(false,
                    Typeface.DEFAULT,textSize, 2,3,3, Color.BLACK))
            }
        }

        // Tools modifications - Icon Color
        iconColorPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (iconColorPicker.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else
                    storylyView.setStoryGroupIconBackgroundColor(result)
            }
        }
        // Tools modifications - Border Icon Color
        val iconBorderColors : Array<Int> = arrayOf(Color.BLACK, Color.BLACK, Color.BLACK)
        val iconBorderColors2 : Array<Int> = arrayOf(Color.BLACK, Color.BLACK, Color.BLACK)
        bordercolorPicker1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (bordercolorPicker1.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else {
                    if (bordercolorVisible.isChecked){
                        println("Burasi")
                        iconBorderColors2.set(0,result)
                    } else {
                        iconBorderColors.set(0,result)
                    }
                }
            }
        }
        bordercolorPicker2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (bordercolorPicker2.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else {
                    if (bordercolorVisible.isChecked){
                        println("Burasi2")
                        iconBorderColors2.set(1,result)
                    } else {
                        iconBorderColors.set(1,result)
                    }
                }
            }
        }
        bordercolorPicker3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (bordercolorPicker3.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else {
                    if (bordercolorVisible.isChecked){
                        iconBorderColors2.set(2,result)
                    } else {
                        iconBorderColors.set(2,result)
                    }
                }
            }
        }
        bordercolorApply.setOnClickListener{
            if (bordercolorVisible.isChecked){
                storylyView.setStoryGroupIconBorderColorNotSeen(iconBorderColors2)
            } else {
                storylyView.setStoryItemIconBorderColor(iconBorderColors)
            }
        }

        // Tools modifications - Pin Icon Color
        pincolorPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (pincolorPicker.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else
                    storylyView.setStoryGroupPinIconColor(result)
            }
        }

        // Tools modifications - Ivod Icon Color
        ivodcolorPicker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var postion = (ivodcolorPicker.selectedItemPosition)
                var result = textColorSpinnerOptions.get(postion)
                if( postion == 0) //do nothing
                else
                    storylyView.setStoryGroupIVodIconColor(result)
            }
        }
    }
}