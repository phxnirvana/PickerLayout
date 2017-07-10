package phxnirvana.pickerlayout.bean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import phxnirvana.library.base.PickerDataInterface;

/**
 * @author phxnirvana
 *         2017/7/10
 */

public class ThreeBean implements PickerDataInterface<ThreeBean> {

    public ThreeBean(String s) {
        mString = s;
    }

    private String mString;
    private List<ThreeBean> mList = new ArrayList<>();

    public List<ThreeBean> getList() {
        return mList;
    }

    public ThreeBean setList(List<ThreeBean> list) {
        mList = list;
        return this;
    }

    public String getString() {
        return mString;
    }

    public ThreeBean setString(String string) {
        mString = string;
        return this;
    }

    @NotNull
    @Override
    public String getPickerDisplayText() {
        return mString;
    }

    @Nullable
    @Override
    public List<ThreeBean> getSecondaryList() {
        return mList;
    }
}
