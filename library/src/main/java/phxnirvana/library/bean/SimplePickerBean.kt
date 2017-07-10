package phxnirvana.library.bean

import phxnirvana.library.base.PickerDataInterface

/**
 * @author phxnirvana
 * *         2017/5/24
 */

class SimplePickerBean(private var text: String) : PickerDataInterface<SimplePickerBean> {
    override val pickerDisplayText: String
        get() = text

    fun getText(): String {
        return text
    }

    fun setText(text: String): SimplePickerBean {
        this.text = text
        return this
    }

    override val secondaryList: List<SimplePickerBean>?
        get() = null
}
