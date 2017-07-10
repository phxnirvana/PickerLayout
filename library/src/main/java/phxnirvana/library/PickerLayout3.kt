package phxnirvana.library

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import phxnirvana.library.base.BasePicker
import phxnirvana.library.base.PickerDataInterface

/**
 * 滚动选取界面，最多支持三行
 * 适用于3个列表item不同的情况

 * @author phxnirvana
 * *         2017/5/23
 */

open class PickerLayout3<U : PickerDataInterface<S>, S : PickerDataInterface<T>, T : PickerDataInterface<*>>(context: Activity) : BasePicker(context) {
    private var mOnDataChangeListener: OnDataChangeListener<U, S, T>? = null
    private var cancelTv: TextView? = null
    private var confirmTv: TextView? = null
    private lateinit var numberPicker1: NumberPicker
    private lateinit var numberPicker2: NumberPicker
    private lateinit var numberPicker3: NumberPicker
    private var mData: List<U>? = null
    private var pickerNum: Int = 0

    init {
        init()
    }

    interface OnDataChangeListener<U, S, T> {
        fun onDataChanged(data1: U, data2: S?, data3: T?)
    }

    private fun init() {
        val rootLayout = LayoutInflater.from(mContext).inflate(R.layout.picker_layout_main_view,
                contentContainer, true)
        cancelTv = rootLayout.findViewById(R.id.cancelTv) as TextView
        confirmTv = rootLayout.findViewById(R.id.confirmTv) as TextView
        numberPicker1 = rootLayout.findViewById(R.id.numberPicker1) as NumberPicker
        numberPicker2 = rootLayout.findViewById(R.id.numberPicker2) as NumberPicker
        numberPicker3 = rootLayout.findViewById(R.id.numberPicker3) as NumberPicker

        cancelTv!!.setOnClickListener { v -> dismiss() }
        confirmTv!!.setOnClickListener { v ->
            notifyDataChanged()
            dismiss()
        }
        numberPicker1.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        numberPicker2.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        numberPicker3.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
    }

    private fun setPickers() {
        when (pickerNum) {
            1 -> {
                numberPicker2.visibility = GONE
                numberPicker3.visibility = GONE
                setWeight(numberPicker1, 1f)
            }
            2 -> {
                numberPicker3.visibility = GONE
                setWeight(numberPicker1, 0.5f)
                setWeight(numberPicker2, 0.5f)
            }
            3 -> {
                setWeight(numberPicker3, 0.33f)
                setWeight(numberPicker1, 0.33f)
                setWeight(numberPicker2, 0.33f)
            }
        }
    }

    private fun setWeight(view: View, weight: Float) {
        val layoutParams = view.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = weight
        view.layoutParams = layoutParams
    }

    /**
     * 左上方按钮设置
     * @param resId           id
     * @param onClickListener listener
     */
    fun setLeftText(resId: Int, onClickListener: View.OnClickListener) {
        setLeftText(mContext.getString(resId), onClickListener)
    }

    /**
     * 左上方按钮设置
     * @param s               s
     * @param onClickListener listener
     */
    fun setLeftText(s: String, onClickListener: View.OnClickListener) {
        cancelTv!!.text = s
        cancelTv!!.setOnClickListener(onClickListener)
    }

    /**
     * 右上方按钮设置

     * @param resId           id
     * *
     * @param onClickListener listener
     */
    fun setRightText(resId: Int, onClickListener: View.OnClickListener) {
        setRightText(mContext.getString(resId), onClickListener)
    }

    /**
     * 右上方按钮设置

     * @param s               s
     * *
     * @param onClickListener listener
     */
    fun setRightText(s: String, onClickListener: View.OnClickListener) {
        confirmTv!!.text = s
        confirmTv!!.setOnClickListener(onClickListener)
    }

    /**
     * 设置数据和要显示的picker数目

     * @param optionsItems 数据
     * *
     * @param pickerNum    picker 数
     */
    fun setLinkagePicker(optionsItems: List<U>?, pickerNum: Int) {
        if (pickerNum in 4..0) {
            throw IllegalArgumentException("PickerNum incorrect!!!")
        }
        this.pickerNum = pickerNum
        setPickers()
        mData = optionsItems
        if (optionsItems != null && optionsItems.isNotEmpty()) {
            numberPicker1.setOnValueChangedListener { picker: NumberPicker, oldVal: Int, newVal: Int ->
                if (pickerNum > 1) {
                    val picker2List = optionsItems[newVal].secondaryList
                    if (picker2List != null && picker2List.isNotEmpty()) {
                        setPickerData(numberPicker2, picker2List)
                        if (pickerNum > 2) {
                            val picker3List = picker2List[0].secondaryList
                            if (picker3List != null && picker3List.isNotEmpty()) {
                                setPickerData(numberPicker3, picker3List)
                            }
                        }
                    }
                }
                //                notifyDataChanged();
            }
            setPickerData(numberPicker1, optionsItems)
            if (pickerNum > 1) {
                numberPicker2.setOnValueChangedListener { picker, oldVal, newVal ->
                    if (pickerNum > 2) {
                        setPickerData(numberPicker3,
                                optionsItems[numberPicker1.value].secondaryList?.get(newVal)?.secondaryList)
                    }
                    //                    notifyDataChanged();
                }
                setPickerData(numberPicker2, optionsItems[0].secondaryList)
            }
            if (pickerNum > 2) {
                setPickerData(numberPicker3, optionsItems[0].secondaryList?.get(0)?.secondaryList)
            }
            numberPicker1.value = 0
            numberPicker2.value = 0
            numberPicker3.value = 0
        }
    }

    /**
     * 通知外部当前值
     */
    private fun notifyDataChanged() {
        if (mOnDataChangeListener != null) {
            when (pickerNum) {
                1 -> mOnDataChangeListener!!.onDataChanged(mData!![numberPicker1.value], null, null)
                2 -> mOnDataChangeListener!!.onDataChanged(
                        mData!![numberPicker1.value],
                        mData!![numberPicker1.value].secondaryList?.get(numberPicker2.value), null)
                3 -> mOnDataChangeListener!!.onDataChanged(
                        mData!![numberPicker1.value],
                        mData!![numberPicker1.value]
                                .secondaryList?.get(numberPicker2.value),
                        mData!![numberPicker1.value]
                                .secondaryList?.get(numberPicker2.value)
                                ?.secondaryList?.get(numberPicker3.value))
            }
        }
    }

    val onDataChangeListener: OnDataChangeListener<U, S, T>?
        get() = mOnDataChangeListener

    fun setOnDataChangeListener(onDataChangeListener: OnDataChangeListener<U, S, T>) {
        mOnDataChangeListener = onDataChangeListener
    }

    private fun setPickerData(numberPicker: NumberPicker, list: List<PickerDataInterface<*>>?) {
        if (list != null && list.isNotEmpty()) {
            numberPicker.displayedValues = null
            numberPicker.maxValue = list.size - 1
            numberPicker.minValue = 0
            numberPicker.displayedValues = list2Datas(list)
        }
    }

    private fun list2Datas(list: List<PickerDataInterface<*>>?): Array<String?>? {
        if (list != null && list.isNotEmpty()) {
            val datas = arrayOfNulls<String>(list.size)
            for (i in list.indices) {
                datas[i] = list[i].pickerDisplayText
            }
            return datas
        } else {
            return null
        }
    }

}
