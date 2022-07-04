package com.example.storylydemo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.styling.StoryGroupIconStyling
import com.appsamurai.storyly.styling.StoryGroupListStyling
import com.appsamurai.storyly.styling.StoryGroupTextStyling
import com.example.storylydemo.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding
    lateinit var storylyView: StorylyView

    private val spinnerColors = arrayOf(Color.BLACK,Color.BLACK, Color.BLUE, Color.CYAN,
        Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW)

    var textColor: Int? = null
    var textFont: Typeface? = null
    var textVisible: Boolean? = null
    var textSize: Pair<Int,Int>? = null
    var pinColor: Int? = null
    var ivodColor: Int? = null
    var groupSize: StoryGroupSize? = StoryGroupSize.Large
    var peddingFlag: Boolean? = null
    lateinit var button: RadioButton

    lateinit var iconBorderSeenColors2: Array<Int>
    lateinit var iconBorderNotSeenColors2: Array<Int>

    /*
    val iconBorderSeenColors : Array<Int> = arrayOf(Color.WHITE, Color.WHITE, Color.WHITE)
    val iconBorderNotSeenColors : Array<Int> = arrayOf(Color.WHITE, Color.WHITE, Color.WHITE)
    var iconBorderSeenColorFlag: Boolean? = null
    var iconBorderNotSeenColorFlag: Boolean? = null
    var iconColorFlag: Boolean? = null
    var iconColor: Int? = null
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initStory()
        setupCustomization()
    }

    private fun initStory(type: StoryGroupSize? = null ) {
        if (type == null) {
            storylyView = StorylyView(this)
            storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
            binding.holderView.addView(storylyView)
            binding.storylyViewDemo.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        } else {
            binding.holderView.removeView(storylyView)
            storylyView = StorylyView(this)
            storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
            binding.holderView.addView(storylyView)
            when(type) {
                StoryGroupSize.Large -> {
                    storylyView.setStoryGroupSize(StoryGroupSize.Large)
                    setupCustomization()
                }
                StoryGroupSize.Small -> {
                    storylyView.setStoryGroupSize(StoryGroupSize.Small)
                    setupCustomization()
                }
                else -> {
                    storylyView.setStoryGroupSize(StoryGroupSize.Custom)
                }
            }
        }
    }

    private fun setupCustomization() {
        textSpinner(textColor)
        textFont(textFont)
        textVisibility(textVisible)
        pincolor(pinColor)
        ivodColor(ivodColor)
        groupListStyle(peddingFlag)
        newBorder() //TODO MEMORIZE
        groupSize()
        reset()
        /*
        iconAndBorderColor(iconColorFlag,iconColor,iconBorderSeenColorFlag,iconBorderSeenColors,
               iconBorderNotSeenColorFlag,iconBorderNotSeenColors)
        */
    }

    private fun reset() {
        binding.resetStory.setOnClickListener{
            binding.holderView.removeView(storylyView)
            initStory()
        }
    }

    private fun textSpinner(color: Int?) {
        if (color == null) {
            binding.textColorPickerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding.customColorTextHidden.visibility = View.GONE
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    var postion = (binding.textColorPickerSpinner.selectedItemPosition)
                    if( postion == 0) //do nothing
                        binding.customColorTextHidden.visibility = View.GONE
                    else {
                        if (postion == 11) {
                            binding.customColorTextHidden.visibility = View.VISIBLE
                            binding.colorShowButton.setBackgroundColor(Color.LTGRAY)
                            binding.customTextColorApply.setOnClickListener {
                                val result = "#"+binding.customTextColor.text.toString()
                                textColor = Color.parseColor(result)
                                groupTextStyle(color = textColor)
                                binding.colorShowButton.setBackgroundColor(textColor!!)
                            }
                        } else {
                            binding.customColorTextHidden.visibility = View.GONE
                            val result = spinnerColors.get(postion)
                            textColor = result
                            groupTextStyle(color = textColor)
                        }
                    }
                }
            }
        } else {
            groupTextStyle(color = textColor)
        }
    }

    private fun textFont(font: Typeface?) {
        if (font == null) {
            val textFontSpinnerOptions = arrayOf(Typeface.DEFAULT,Typeface.DEFAULT,Typeface.DEFAULT,Typeface.DEFAULT_BOLD,
                Typeface.SERIF, Typeface.SANS_SERIF, Typeface.MONOSPACE)
            binding.textFontPickerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Do nothing
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val postion = (binding.textFontPickerSpinner.selectedItemPosition)
                    val result = textFontSpinnerOptions.get(postion)
                    if( postion == 0) //do nothing
                    else {
                        textFont = result
                        groupTextStyle(font = textFont)
                    }
                }
            }
        } else {
            groupTextStyle(font = textFont)
        }
    }

    private fun textVisibility(visibility: Boolean?) {
        if (visibility == null) {
            binding.checkboxTextVisible.setOnCheckedChangeListener{ buttonView, isChecked ->
                textVisible = false
                if(isChecked)
                    textVisible = true
                groupTextStyle(visible = textVisible)
            }
        } else {
            groupTextStyle(visible = textVisible)
        }
    }

    private fun newBorder() {
        var numberOfBorders: Int = 2
        binding.spinnerNumberOfBorders.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                numberOfBorders = (binding.spinnerNumberOfBorders.selectedItemPosition)
                if ( numberOfBorders != 0 ) {
                    createRadioButtons(numberOfBorders)
                    binding.newBorderColorApplyHolder.visibility = View.VISIBLE
                    binding.newBorderColorApplyHolder2.visibility = View.VISIBLE
                    binding.totalApply.visibility = View.VISIBLE
                    binding.checkboxNewBorderVisible.setOnCheckedChangeListener { compoundButton, isChecked ->
                        if (isChecked) {
                            iconBorderNotSeenColors2 = Array(numberOfBorders + 1) {0}
                            selectedBorderColorSelect(iconBorderNotSeenColors2,true)
                        } else {
                            iconBorderSeenColors2 = Array(numberOfBorders + 1) {0}
                            selectedBorderColorSelect(iconBorderSeenColors2,false)
                        }
                    }
                } else {
                    binding.newBorderColorApplyHolder.visibility = View.GONE
                    binding.newBorderColorApplyHolder2.visibility = View.GONE
                    binding.totalApply.visibility = View.GONE
                }
            }
        }
    }
    private fun selectedBorderColorSelect(arr: Array<Int>, isChecked:Boolean) {
        binding.borderRadioGroupHolder.setOnCheckedChangeListener { radioGroup, i ->
            binding.selectedBorderColorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val postion = (binding.selectedBorderColorSpinner.selectedItemPosition)
                    if ( postion == 0 ) {
                        binding.customBorderColorHex.visibility = View.GONE
                    } else {
                        if (postion == 11) {
                            binding.customBorderColorHex.visibility = View.VISIBLE
                            binding.newBorderApply.setOnClickListener {
                                val result = Color.parseColor("#"+binding.borderEditTextColor.text.toString())
                                println("BURADA $result")
                                arr[i-1] = result
                            }
                        } else {
                            binding.customBorderColorHex.visibility = View.GONE
                            arr[i-1] = spinnerColors.get(postion)
                        }
                    }
                }
            }
        }
        binding.totalApply.setOnClickListener{
            if (isChecked) {
                initStory(groupSize)
                storylyView.setStoryGroupIconBorderColorNotSeen(arr)
            } else {
                storylyView.setStoryGroupIconBorderColorSeen(arr)
            }
        }
    }
    private fun createRadioButtons(num: Int) {
        binding.borderRadioGroupHolder.removeAllViews()
        for (i in 1..num+1) {
            button = RadioButton(this)
            button.setText("$i")
            binding.borderRadioGroupHolder.addView(button)
        }
    }

    private fun pincolor(color: Int?) {
        if (color == null) {
            // Tools modifications - Pin Icon Color
            binding.pinColorPickerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Do nothing
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val postion = (binding.pinColorPickerSpinner.selectedItemPosition)
                    if ( postion == 0 ) binding.customColorPinHidden.visibility = View.GONE
                    else {
                        if ( postion == 11 ) {
                            binding.customColorPinHidden.visibility = View.VISIBLE
                            binding.pinColorShowButton.setBackgroundColor(Color.LTGRAY)
                            binding.pinCustomTextColorApply.setOnClickListener {
                                val result = "#" + binding.pinCustomTextColor.text.toString()
                                val result2 = Color.parseColor(result)
                                pinColor = result2
                                storylyView.setStoryGroupPinIconColor(result2)
                                binding.pinColorShowButton.setBackgroundColor(result2)
                            }
                        } else {
                            binding.customColorPinHidden.visibility = View.GONE
                            val result = spinnerColors.get(postion)
                            pinColor = result
                            storylyView.setStoryGroupPinIconColor(result)
                            }
                        }
                    }
                }
            } else {
                storylyView.setStoryGroupPinIconColor(pinColor!!)
            }
        }

    private fun ivodColor(color: Int?) {
        if (color == null) {
            // Tools modifications - Ivod Icon Color
            binding.ivodColorPickerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Do nothing
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    var postion = (binding.ivodColorPickerSpinner.selectedItemPosition)

                    if (postion == 0) binding.customColorIvodHidden.visibility = View.GONE
                    else {
                        if (postion == 11) {
                            binding.customColorIvodHidden.visibility = View.VISIBLE
                            binding.ivodColorShowButton.setBackgroundColor(Color.LTGRAY)
                            binding.ivodCustomTextColorApply.setOnClickListener {
                                val result = "#" + binding.ivodCustomTextColor.text.toString()
                                ivodColor = Color.parseColor(result)
                                storylyView.setStoryGroupIVodIconColor(ivodColor!!)
                                binding.ivodColorShowButton.setBackgroundColor(ivodColor!!)
                            }
                        } else {
                            binding.customColorIvodHidden.visibility = View.GONE
                            var result = spinnerColors.get(postion)
                            ivodColor = result
                            storylyView.setStoryGroupIVodIconColor(result)
                        }
                    }
                }
            }
        } else {
            storylyView.setStoryGroupIVodIconColor(color)
        }
    }

    private fun groupSize() {
        binding.radioGroupStorySize.setOnCheckedChangeListener { radioGroup, i ->
            if ( i == R.id.group_size_large) {
                binding.custimizationGroupSize.visibility = View.GONE
                groupSize = StoryGroupSize.Large
                initStory(groupSize)
            } else if ( i == R.id.group_size_small) {
                binding.custimizationGroupSize.visibility = View.GONE
                groupSize = StoryGroupSize.Small
                initStory(groupSize)
            } else {
                binding.custimizationGroupSize.visibility = View.VISIBLE
                groupSize = StoryGroupSize.Custom
                groupCustomSizeStyle()
            }
        }
    }

    private fun groupCustomSizeStyle() {
        binding.sizeApply.setOnClickListener{
            val height: Float = binding.editTextStoryGroupInput1.text.toString().toFloat()
            val width: Float = binding.editTextStoryGroupInput2.text.toString().toFloat()
            val cornerRadius: Float = binding.editTextStoryGroupInput3.text.toString().toFloat()
            groupCustomSizeStyleHelper(height,width,cornerRadius)
        }
    }

    private fun groupCustomSizeStyleHelper(height: Float = 80f, width: Float = 80f, cornerRadius: Float = 40f) {
        initStory(StoryGroupSize.Custom)
        storylyView.setStoryGroupIconStyling(StoryGroupIconStyling(height,width,cornerRadius))
        setupCustomization()
    }

    private fun groupListStyle(flag: Boolean?) {
        if (flag == null) {
            binding.peddingApply.setOnClickListener{
                if (binding.edgePedding.text.toString().toFloat() != Float.MIN_VALUE && binding.itemPedding.text.toString().toFloat() != Float.MIN_VALUE   ) {
                    initStory(groupSize)
                    peddingFlag = true
                    storylyView.setStoryGroupListStyling(StoryGroupListStyling(binding.edgePedding.text.toString().toFloat(),binding.itemPedding.text.toString().toFloat()))
                }
            }
        } else {
            storylyView.setStoryGroupListStyling(StoryGroupListStyling(binding.edgePedding.text.toString().toFloat(),binding.itemPedding.text.toString().toFloat()))
        }
    }

    private fun groupTextStyle(visible: Boolean? = null, color: Int? = null, font: Typeface? = null, size:Pair<Int, Int?>? = null) {
        storylyView.setStoryGroupTextStyling(
            StoryGroupTextStyling(
                isVisible = ((if (visible == null) {
                    if (textVisible == null) true
                    else
                        textVisible
                } else visible)!!),
                color = ((if (color == null) {
                    if (textColor == null) Color.BLACK
                    else
                        textColor
                } else color)!!),
                typeface = ((if (font == null) {
                    if (textFont == null) Typeface.DEFAULT
                    else
                        textFont
                } else font)!!),
                textSize = ((if (size == null) {
                    if (textSize == null) Pair(TypedValue.COMPLEX_UNIT_PX, 38)
                    else
                        textSize
                } else size)!!),
            ))
    }

}
/*
private fun iconAndBorderColor(iconFlag: Boolean?,
                             colorIcon: Int? ,
                             seenFlag: Boolean? ,
                             seenColors: Array<Int>?,
                             notSeenFlag: Boolean?,
                             notSeenColors: Array<Int>?) {
  // Tools modifications - Icon Color
  if (iconFlag == null) {
      binding.iconColorPickerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
          override fun onNothingSelected(p0: AdapterView<*>?) {
              //Do nothing
          }
          override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              val postion = (binding.iconColorPickerSpinner.selectedItemPosition)
              if( postion == 0) binding.customColorIconBackgroundHidden.visibility = View.GONE
              else {
                  if (postion == 11) {
                      binding.customColorIconBackgroundHidden.visibility = View.VISIBLE
                      binding.iconBackgroundColorShowButton.setBackgroundColor(Color.LTGRAY)
                      binding.iconBackgorundCustomTextColorApply.setOnClickListener {
                          val result = "#"+binding.iconBackgroundCustomTextColor.text.toString()
                          storylyView.setStoryGroupIconBackgroundColor(Color.parseColor(result))
                          binding.iconBackgroundColorShowButton.setBackgroundColor(Color.parseColor(result))
                      }
                  } else {
                      binding.customColorIconBackgroundHidden.visibility = View.GONE
                      val result = spinnerColors.get(postion)
                      iconColor = result
                      storylyView.setStoryGroupIconBackgroundColor(result)
                  }
              }
          }
      }
  } else {
      if (colorIcon != null) {
          storylyView.setStoryGroupIconBackgroundColor(colorIcon)
      }
  }
  if (seenFlag == null) {
      binding.borderColorPicker1Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
          override fun onNothingSelected(p0: AdapterView<*>?) {
              //Do nothing
          }
          override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              var postion = ( binding.borderColorPicker1Spinner.selectedItemPosition)
              var result = spinnerColors.get(postion)
              if( postion == 0) //do nothing
              else {
                  if (binding.checkboxIconBorderVisible.isChecked){
                      iconBorderNotSeenColors.set(0,result)
                  } else {
                      iconBorderSeenColors.set(0,result)
                  }
              }
          }
      }
      binding.borderColorPicker2Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
          override fun onNothingSelected(p0: AdapterView<*>?) {
              //Do nothing
          }
          override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              var postion = (binding.borderColorPicker2Spinner.selectedItemPosition)
              var result = spinnerColors.get(postion)
              if( postion == 0) //do nothing
              else {
                  if (binding.checkboxIconBorderVisible.isChecked){
                      iconBorderNotSeenColors.set(1,result)
                  } else {
                      iconBorderSeenColors.set(1,result)
                  }
              }
          }
      }
      binding.borderColorPicker3Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
          override fun onNothingSelected(p0: AdapterView<*>?) {
              //Do nothing
          }
          override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              var postion = (binding.borderColorPicker3Spinner.selectedItemPosition)
              var result = spinnerColors.get(postion)
              if( postion == 0) //do nothing
              else {
                  if (binding.checkboxIconBorderVisible.isChecked) {
                      iconBorderNotSeenColors.set(2,result)
                  } else {
                      iconBorderSeenColors.set(2,result)
                  }
              }
          }
      }
  } else {
      storylyView.setStoryItemIconBorderColor(iconBorderSeenColors)
      if ( notSeenFlag == true) {
          storylyView.setStoryGroupIconBorderColorNotSeen(iconBorderNotSeenColors)
      }
  }

  binding.borderColorApply.setOnClickListener{
      if (binding.checkboxIconBorderVisible.isChecked){
          iconBorderNotSeenColorFlag = true
          initStory(groupSize)
          storylyView.setStoryGroupIconBorderColorNotSeen(iconBorderNotSeenColors)
      } else {
          iconBorderSeenColorFlag = true
          storylyView.setStoryItemIconBorderColor(iconBorderSeenColors)
      }
  }
}
*/