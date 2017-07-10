package phxnirvana.pickerlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import phxnirvana.library.Constants;
import phxnirvana.library.DatePickerLayout;
import phxnirvana.library.PickerLayout;
import phxnirvana.library.PickerLayout3;
import phxnirvana.library.bean.SimplePickerBean;
import phxnirvana.pickerlayout.bean.CityBean;
import phxnirvana.pickerlayout.bean.RegionBean;

public class MainActivity extends AppCompatActivity {
    PickerLayout<SimplePickerBean> pickerLayout;
    PickerLayout3<CityBean, RegionBean, RegionBean> mPickerLayout3;
    DatePickerLayout mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickerLayout = new PickerLayout<>(this);
        pickerLayout.setCancelable(true);
        mPickerLayout3 = new PickerLayout3<>(this);
        ArrayList<SimplePickerBean> list = new ArrayList<>();
        list.add(new SimplePickerBean("1"));
        list.add(new SimplePickerBean("2"));
        list.add(new SimplePickerBean("3"));
        list.add(new SimplePickerBean("4"));
        List<CityBean> cityList = new Gson().fromJson(Constants.CITY_JSON, new TypeToken<ArrayList<CityBean>>(){}.getType());
        mPickerLayout3.setLinkagePicker(cityList, 3);

        pickerLayout.setLinkagePicker(list, 1);
        findViewById(R.id.simpleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerLayout.show();
            }
        });
        mDatePicker = new DatePickerLayout(this);
        findViewById(R.id.dateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show();
            }
        });

        findViewById(R.id.simple3Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerLayout3.show();
            }
        });
    }


}
