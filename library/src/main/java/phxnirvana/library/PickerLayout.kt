package phxnirvana.library

import android.app.Activity
import phxnirvana.library.base.PickerDataInterface

/**
 * 滚动选取界面，最多支持三行
 * 适用于3个列表item相同的情况

 * @author phxnirvana
 * *         2017/5/23
 */

class PickerLayout<T : PickerDataInterface<T>>(context: Activity) : PickerLayout3<T, T, T>(context)
