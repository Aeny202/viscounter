package com.viscount.mony.viscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity {

    private String strStartDate;
    private String strEndDate;
    private int intStartYear;
    private int intStartMonth;
    private int intStartDay;
    private int intEndYear;
    private int intEndMonth;
    private int intEndDay;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the current date so we can set it as starting date for the date pickers
        Calendar c = Calendar.getInstance();
        ;

        // Setup initial values and listener for the starting date picker
        DatePicker dpStartDate = (DatePicker)findViewById(R.id.datePicker1);
        dpStartDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener()
        {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                intStartYear = view.getYear();
                intStartMonth = view.getMonth();
                intStartDay = view.getDayOfMonth();

                Toast toast = Toast.makeText(MainActivity.this, intStartDay + "-"+ intStartMonth + "-" + intStartYear, Toast.LENGTH_SHORT);
                toast.show();

                setTimeRemaining(intStartYear, intStartMonth, intStartDay, intEndYear, intEndMonth, intEndDay);
            }
        });

        // prepopulate the variables before the user triggers the onDateChanged listeners.
        intStartYear = dpStartDate.getYear();
        intStartMonth = dpStartDate.getMonth();
        intStartDay = dpStartDate.getDayOfMonth();

        // Setup initial values and listener for the ending date picker
        DatePicker dpEndDate = (DatePicker)findViewById(R.id.datePicker2);
        dpEndDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)+1, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                intEndYear = view.getYear();
                intEndMonth = view.getMonth();
                intEndDay = view.getDayOfMonth();

                Toast toast = Toast.makeText(MainActivity.this, intStartDay + "-"+ intStartMonth + "-" + intStartYear, Toast.LENGTH_SHORT);
                toast.show();

                setTimeRemaining(intStartYear, intStartMonth, intStartDay, intEndYear, intEndMonth, intEndDay);
            }
        });

        // prepopulate the variables before the user triggers the onDateChanged listeners.
        intEndYear = dpEndDate.getYear();
        intEndMonth = dpEndDate.getMonth();
        intEndDay = dpEndDate.getDayOfMonth();

        // Runs this once at onCreate so the field is populated when the app is started (should show 1 day remaning)
        setTimeRemaining(intStartYear, intStartMonth, intStartDay, intEndYear, intEndMonth, intEndDay);
    }

    // This method calculates the difference between the start and end dates selected, then displays it in a TextView on screen.
    private void setTimeRemaining(int intStartYear, int intStartMonth, int intStartDay, int intEndYear, int intEndMonth, int intEndDay)
    {

        TextView txtTimeRemaining = (TextView) findViewById(R.id.timeRemaining);
        int intRemainingYears = intEndYear - intStartYear;
        int intRemaningMonths = intEndMonth - intStartMonth;
        int intRemainingDays = intEndDay - intStartDay;

        txtTimeRemaining.setText("There are "+intRemainingYears+" year(s), "+intRemaningMonths+" month(s) and "+intRemainingDays+" day(s) remaining!");
    }

}
