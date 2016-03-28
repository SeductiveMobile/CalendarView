package com.seductive.tools.calendarview;


import org.joda.time.DateTime;

public final class WeekItem {
    protected static final int TYPE_HEADER = 1;
    protected static final int TYPE_ITEM = 2;

    protected int itemType;
    protected int month;
    protected int year;
    protected DateTime startDate;

    protected WeekItem(int itemType, int month, int year) {
        this.itemType = itemType;
        this.month = month;
        this.year = year;
    }

    protected WeekItem(int itemType, int month, int year, DateTime firstWeekDay) {
        this.itemType = itemType;
        this.month = month;
        this.year = year;
        this.startDate = firstWeekDay;
    }
}
