package com.viscount.mony.viscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;


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

        // Get total amount of days in a month
        int intTotalDaysInStartMonth = getTotalDaysInMonth(intStartYear, intStartMonth);
        int intTotalDaysInEndMonth = getTotalDaysInMonth(intEndYear, intEndMonth);

        int intRemainingYears = intEndYear - intStartYear;

        int intRemainingMonths = intEndMonth - intStartMonth;
        int intRemainingDays = intEndDay - intStartDay;

        /*
        Unroll month into days
        Example: 30/06/2017 - 02/07/2017 = 02-30 = -28 days
        Subtract 1 month of intTempMonth, add 31 days to intTempDays (31-28 = 3)
        */
        if(intRemainingDays < 1)
        {
            intRemainingDays = (getTotalDaysInMonth(intStartYear,intStartMonth)+1) + intRemainingDays;
            intRemainingMonths = intRemainingMonths - 1;
        }

        /*
        Unroll month to a year
        Example: 30/06/2008 - 30/02/2009 = 02-06=-4 months
        Subtract 1 year from intTempYear, add 8 months to intTempMonth (12-4 = 8 months)

        Example: 30/04/2008 - 30/03/2009 = 03-04=-1 month (12-1= 11 months)
        Subtract 1 year from intTempYear, add 11 months to intTempMonth

        Example: 30/05/2009 - 30/06/2009 = 06-05=+1
        Subtract 0 year from intTempYear, add 1 month to intTempMonths
        */
        if(intRemainingMonths < 1)
        {
            intRemainingMonths = 12 + intRemainingMonths;
            intRemainingYears = intRemainingYears - 1;
        }

        TextView txtTimeRemaining = (TextView) findViewById(R.id.timeRemaining);
        txtTimeRemaining.setText("There are "+intRemainingYears+" year(s), "+intRemainingMonths+" month(s) and "+intRemainingDays+" day(s) remaining!");
    }

    // Get the total amount of days for a given month in a given year.
    private int getTotalDaysInMonth(int intYear, int intMonth)
    {
        // Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(intYear, intMonth, 0);

        // Get the number of days in that month
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
