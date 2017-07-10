package phxnirvana.pickerlayout.bean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import phxnirvana.library.base.PickerDataInterface;

public class RegionBean implements PickerDataInterface<RegionBean>{
    private String code;
    private List<RegionBean> regions;
    private String name;
    private int id;
    private String type;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RegionBean> getRegions() {
        return this.regions;
    }

    public void setRegions(List<RegionBean> regions) {
        this.regions = regions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull
    @Override
    public String getPickerDisplayText() {
        return name;
    }

    @Nullable
    @Override
    public List<RegionBean> getSecondaryList() {
        return regions;
    }
}
