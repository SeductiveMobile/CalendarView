package com.seductive.tools.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.seductive.tools.calendarview.CalendarAdapter;
import com.seductive.tools.calendarview.CalendarUtils;
import com.seductive.tools.calendarview.CalendarView;

import org.joda.time.DateTime;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.DateClickListener {

    private static final int PERIOD_MONTHS = 12;
    private static final String DATE_TO = "date_to";
    private static final String DATE_BACK = "date_back";

    public static Intent createIntent(Context context){
        return createIntent(context, null, null);
    }

    public static Intent createIntent(Context context, DateTime dateTo){
        return createIntent(context, dateTo, null);
    }

    public static Intent createIntent(Context context, DateTime dateTo, DateTime dateBack){
        Intent intent = new Intent(context, CalendarActivity.class);
        if(dateTo != null)
            intent.putExtra(DATE_TO, dateTo);
        if(dateBack != null)
            intent.putExtra(DATE_BACK, dateBack);
        return intent;
    }

    private DateTime dateTo;
    private DateTime dateBack;

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
            if(extras.containsKey(DATE_TO))
                dateTo = (DateTime) extras.getSerializable(DATE_TO);
            if(extras.containsKey(DATE_BACK))
                dateBack = (DateTime) extras.getSerializable(DATE_BACK);
        }
        initUIViews();
    }

    private void initUIViews() {
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_container);
        calendarView.setWeekAnimation(android.R.anim.slide_in_left);
        calendarView.setPeriod(PERIOD_MONTHS);
        calendarView.addOnScrollListener(scrollListener);
        calendarView.setDates(dateTo, dateBack);
        calendarView.scrollTo(dateTo);
        calendarView.setDateClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finishWithResults();
    }

    @Override
    public void onDateClick(DateTime date) {
        if (clickedItem.equals(IntentConstants.DATE_TYPE_TO)) {
            if (!isChecked) {
                setDateTo(date, FINISH);
            } else {
                if (dateTo == null) {
                    if (dateBack == null) {
                        setDateTo(date, NOT_FINISH);
                    } else {
                        if (!CalendarUtils.datesEquals(date, dateBack) && date.compareTo(dateBack) > 0) {
                            dateBack = null;
                            setDateTo(date, NOT_FINISH);
                        } else {
                            setDateTo(date, NOT_FINISH);
                        }
                    }

                } else if (dateBack == null) {
                    if (!CalendarUtils.datesEquals(date, dateTo) && date.compareTo(dateTo) < 0) {
                        dateBack = null;
                        setDateTo(date, NOT_FINISH);
                    } else {
                        setDateBack(date, FINISH);
                    }
                } else {
                    if (!CalendarUtils.datesEquals(date, dateTo) && date.compareTo(dateTo) < 0) {
                        dateBack = null;
                        setDateTo(date, NOT_FINISH);
                    } else {
                        setDateBack(date, FINISH);
                    }
                }
            }
        } else if (clickedItem.equals(IntentConstants.DATE_TYPE_BACK)) {
            if (dateTo == null) {
                setDateTo(date, NOT_FINISH);
            } else {
                if (dateBack == null) {
                    if (!CalendarUtils.datesEquals(date, dateTo) && date.compareTo(dateTo) < 0) {
                        dateBack = null;
                        setDateTo(date, NOT_FINISH);
                    } else {
                        setDateBack(date, FINISH);
                    }
                } else {
                    if (!CalendarUtils.datesEquals(date, dateTo) && date.compareTo(dateTo) < 0) {
                        dateBack = null;
                        setDateTo(date, NOT_FINISH);
                    } else {
                        setDateBack(date, FINISH);
                    }
                }
            }
        }
    }

    private void finishWithResults() {
        Intent intent = new Intent();
        intent.putExtra(DATE_TO, dateTo);
        intent.putExtra(DATE_BACK, dateBack);
        setResult(RESULT_OK, intent);
        finish();
    }
}
