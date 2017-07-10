package phxnirvana.pickerlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import phxnirvana.library.DatePickerLayout;
import phxnirvana.library.PickerLayout;
import phxnirvana.library.bean.SimplePickerBean;

public class MainActivity extends AppCompatActivity {
    PickerLayout<SimplePickerBean> pickerLayout;
    DatePickerLayout mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickerLayout = new PickerLayout<>(this);
        pickerLayout.setCancelable(true);
        ArrayList<SimplePickerBean> list = new ArrayList<>();
        list.add(new SimplePickerBean("1"));
        list.add(new SimplePickerBean("2"));
        list.add(new SimplePickerBean("3"));
        list.add(new SimplePickerBean("4"));
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
    }
}
