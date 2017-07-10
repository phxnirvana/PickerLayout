package phxnirvana.library

import android.app.Activity
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TextView
import phxnirvana.library.base.BasePicker
import java.util.*

/**
 * @author phxnirvana
 * *         2017/5/24
 */

class DatePickerLayout(context: Activity) : BasePicker(context) {
    private var cancelTv: TextView
    private var confirmTv: TextView
    private var datePicker: DatePicker
    private var mOnTimeSelectListener: OnTimeSelectListener? = null

    interface OnTimeSelectListener {
        fun onTimeSelected(date: Date)
    }

    init {
        val rootView = LayoutInflater.from(mContext).inflate(R.layout.picker_date_layout,
                contentContainer, true)
        cancelTv = rootView.findViewById(R.id.cancelTv) as TextView
        confirmTv = rootView.findViewById(R.id.confirmTv) as TextView
        datePicker = rootView.findViewById(R.id.datePicker) as DatePicker
        cancelTv.setOnClickListener { dismiss() }
        confirmTv.setOnClickListener {
            if (mOnTimeSelectListener != null) {
                mOnTimeSelectListener!!.onTimeSelected(date)
            }
            dismiss()
        }
    }

    fun setDate(date: Date?): DatePickerLayout {
        val calendar = Calendar.getInstance()
        if (date == null) {
            calendar.timeInMillis = System.currentTimeMillis()
        } else {
            calendar.time = date
        }
        datePicker.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        return this
    }

    private val date: Date
        get() {
            val year = datePicker.year
            val month = datePicker.month
            val dayOfMonth = datePicker.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            return calendar.time
        }

    fun getOnTimeSelectListener(): OnTimeSelectListener? {
        return mOnTimeSelectListener
    }

    fun setOnTimeSelectListener(onTimeSelectListener: OnTimeSelectListener): DatePickerLayout {
        mOnTimeSelectListener = onTimeSelectListener
        return this
    }
}
