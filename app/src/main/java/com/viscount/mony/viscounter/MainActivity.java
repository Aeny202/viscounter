package com.viscount.mony.viscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity {

    private String strStartDate;
    private String strEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatePicker dpStartDate = (DatePicker)findViewById(R.id.datePicker1);
        DatePicker dpEndDate = (DatePicker)findViewById(R.id.datePicker2);
        dpStartDate.init(0, 0, 0, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Toast toast = Toast.makeText(MainActivity.this, view.getDayOfMonth() + "-"+ view.getMonth() + "-" + view.getYear(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    public void OnDateChanged()
    {

    }
}
