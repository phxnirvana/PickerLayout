package phxnirvana.library.base

/**
 * @author phxnirvana
 * *         2017/5/23
 */

interface PickerDataInterface<T> {
    val pickerDisplayText: String
    val secondaryList: List<T>?
}
