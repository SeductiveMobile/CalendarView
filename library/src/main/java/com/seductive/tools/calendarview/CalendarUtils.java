package com.seductive.tools.calendarview;

import org.joda.time.DateTime;

import java.util.List;

public class CalendarUtils {

    public static void generateWeekItems(List<WeekItem> listToGenerate, int periodInMonth) {
        DateTime now = DateTime.now();
        for (int i = 0; i < periodInMonth; i++) {
            DateTime newDateTime = now.plusMonths(i);
            int month = newDateTime.getMonthOfYear();
            int year = newDateTime.getYear();

            listToGenerate.add(new WeekItem(WeekItem.TYPE_HEADER, month, year));
            DateTime first = new DateTime(year, month, 1, 0, 0);
            DateTime last = first.dayOfMonth().withMaximumValue();

            int firstWeek = first.getWeekOfWeekyear();
            int lastWeek = last.getWeekOfWeekyear();
            if (first.getWeekyear() == last.getWeekyear()) {
                for (int j = firstWeek; j <= lastWeek; j++) {
                    DateTime startWeekDateTime = new DateTime().withYear(year).withMonthOfYear(month)
                            .withWeekOfWeekyear(j).withDayOfWeek(1);
                    listToGenerate.add(new WeekItem(WeekItem.TYPE_ITEM, month, year, startWeekDateTime));
                }
            } else {
                DateTime startWeekDateTime = first.withWeekOfWeekyear(firstWeek).withDayOfWeek(1);
                listToGenerate.add(new WeekItem(WeekItem.TYPE_ITEM, month, year, startWeekDateTime));
                firstWeek = first.plusWeeks(1).getWeekOfWeekyear();
                for (int j = firstWeek; j <= lastWeek; j++) {
                    startWeekDateTime = new DateTime().withYear(year).withMonthOfYear(month).withWeekyear(year)
                            .withWeekOfWeekyear(j).withDayOfWeek(1);
                    listToGenerate.add(new WeekItem(WeekItem.TYPE_ITEM, month, year, startWeekDateTime));
                }
            }
        }
    }

    public static boolean datesEquals(DateTime firstDate, DateTime secondDate) {
        return firstDate.getDayOfMonth() == secondDate.getDayOfMonth()
                && firstDate.getMonthOfYear() == secondDate.getMonthOfYear()
                && firstDate.getYear() == secondDate.getYear();
    }

    public static boolean isDateLessOrEqualsToday(DateTime dateToCompare) {
        DateTime today = new DateTime();
        if (dateToCompare.getYear() < today.getYear()) {
            return true;
        } else if (dateToCompare.getYear() == today.getYear()) {
            if (dateToCompare.getMonthOfYear() < today.getMonthOfYear()) {
                return true;
            } else if (dateToCompare.getMonthOfYear() == today.getMonthOfYear()) {
                if (dateToCompare.getDayOfMonth() <= today.getDayOfMonth()) {
                    return true;
                }
            }
        }
        return false;
    }
}
