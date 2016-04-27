package com.seductive.tools.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.seductive.tools.calendarview.CalendarAdapter;
import com.seductive.tools.calendarview.CalendarType;
import com.seductive.tools.calendarview.CalendarView;

import org.joda.time.DateTime;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.DateClickListener {

    private static final int PERIOD_MONTHS = 12;
    private static final String DATE_ORIGIN = "date_origin";
    private static final String DATE_DESTINATION = "date_destination";
    private static final String DATE_TYPE = "date_type";

    public static Intent createIntent(Context context, CalendarType type){
        return createIntent(context, null, null, type);
    }

    public static Intent createIntent(Context context, DateTime dateOrigin){
        return createIntent(context, dateOrigin, null, CalendarType.SINGLE_DATE);
    }

    public static Intent createIntent(Context context, DateTime dateOrigin, DateTime dateDestination){
        return createIntent(context, dateOrigin, dateDestination, CalendarType.PERIOD);
    }

    public static Intent createIntent(Context context, DateTime dateOrigin, DateTime dateDestination, CalendarType type){
        Intent intent = new Intent(context, CalendarActivity.class);
        if(dateOrigin != null)
            intent.putExtra(DATE_ORIGIN, dateOrigin);
        if(dateDestination != null)
            intent.putExtra(DATE_DESTINATION, dateDestination);
        intent.putExtra(DATE_TYPE, type.ordinal());
        return intent;
    }

    public static CalendarType readDateType(Intent resultIntent){
        return CalendarType.values()[resultIntent.getIntExtra(DATE_TYPE, CalendarType.SINGLE_DATE.ordinal())];
    }

    public static DateTime readDate(Intent resultIntent){
        if(resultIntent.hasExtra(DATE_ORIGIN)) {
            return (DateTime) resultIntent.getSerializableExtra(DATE_ORIGIN);
        }
        return null;
    }

    public static Pair<DateTime, DateTime> readPeriod(Intent resultIntent){
        DateTime origin = null;
        if(resultIntent.hasExtra(DATE_ORIGIN)) {
            origin = (DateTime) resultIntent.getSerializableExtra(DATE_ORIGIN);
        }
        DateTime destination = null;
        if(resultIntent.hasExtra(DATE_DESTINATION)) {
            destination = (DateTime) resultIntent.getSerializableExtra(DATE_DESTINATION);
        }
        return new Pair<>(origin, destination);
    }

    private DateTime dateOrigin;
    private DateTime dateDestination;
    private CalendarType dateType;
    private CalendarView calendarView;

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.containsKey(DATE_ORIGIN))
                dateOrigin = (DateTime) extras.getSerializable(DATE_ORIGIN);
            if(extras.containsKey(DATE_DESTINATION))
                dateDestination = (DateTime) extras.getSerializable(DATE_DESTINATION);
            dateType = CalendarType.values()[extras.getInt(DATE_TYPE, CalendarType.PERIOD.ordinal())];
        }
        initUIViews();
    }

    private void initUIViews() {
        calendarView = (CalendarView) findViewById(R.id.calendar_container);
//        calendarView.setWeekAnimation(android.R.anim.slide_in_left);
        calendarView.setPeriod(PERIOD_MONTHS);
        calendarView.addOnScrollListener(scrollListener);
        calendarView.setDates(dateOrigin, dateDestination);
        calendarView.scrollTo(dateOrigin);
        calendarView.setDateClickListener(this);
        calendarView.setCalendarType(dateType);
    }

    @Override
    public void onBackPressed() {
        composeResult(RESULT_CANCELED);
    }

    @Override
    public void onDateClick(DateTime date) {
        Toast.makeText(this, "Click: " + date.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateLongClick(DateTime date) {
        Toast.makeText(this, "LongClick: " + date.toString(), Toast.LENGTH_LONG).show();
    }

    private void composeResult(int resultStatus) {
        Intent intent = new Intent();
        dateOrigin = calendarView.getDateOrigin();
        dateDestination = calendarView.getDateDestination();
        if(resultStatus == RESULT_OK) {
            if(dateOrigin != null) {
                intent.putExtra(DATE_ORIGIN, dateOrigin);
            }
            if(dateDestination != null) {
                intent.putExtra(DATE_DESTINATION, dateDestination);
            }
        }
        intent.putExtra(DATE_TYPE, dateType.ordinal());
        setResult(resultStatus, intent);
        finish();
    }
}
