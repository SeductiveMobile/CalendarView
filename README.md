# CalendarView

CalendarView is a RecycleView based library.

![Simple usage][sc_gif]

# Import

Currently only the clone setup is available. The gradle build setup is in progress.

# How to use?

You can check the "sample" module to check common usage.

# Requirements

SDK:
```sh
    minSdkVersion 15
```
Gradle:
```sh
classpath 'com.android.tools.build:gradle:2.1.0'
```

# Features:

### Single selection mode

```sh
    calendarViewInstance.setCalendarType(CalendarType calendarType);
```


```sh
    app:app:type="single/period"
```

![Single selection][ss_gif]

### Set calendar period in tmonths

```sh
public void setPeriod(int months);
```

### Set period of dates
```sh
public void setDates(DateTime dateFrom, DateTime dateTo);
```

### Each row is a Adapter view which appearance can be animated
```sh
public void setWeekAnimation(@AnimRes int animation);
```

# Custom backgrounds and text colors:

### Programmatically
For each UI element there is a simple class with setters:

[WeekDayStyle.java][wds]
```sh

    public WeekDayStyle setBgColorInactive(int bgColorInactive):

    public WeekDayStyle setBgColorInterval(int bgColorInterval);

    public WeekDayStyle setBgResource(int bgResource);

    public WeekDayStyle setBgColorActive(int bgColorActive);

    public WeekDayStyle setTextColorActive(int textColorActive);

    public WeekDayStyle setTextSize(int textSize);

    public WeekDayStyle setTextStyle(int textStyle);

    public WeekDayStyle setTextGravity(int textGravity);

    public WeekDayStyle setTextTypeface(int textTypeface);

    public WeekDayStyle setTextAllCaps(boolean textAllCaps);

    public WeekDayStyle setTextColorInactive(int textColorInactive);
```
[WeekStyle.java][ws]
```sh
    public WeekStyle setBgColor(int bgColor);
```

[HeaderStyle.java][hs]
```sh
    public HeaderStyle setMonthNameTextTypeface(int monthNameTextTypeface);

    public HeaderStyle setMonthNameTextColor(int monthNameTextColor);

    public HeaderStyle setMonthNameTextSize(int monthNameTextSize);

    public HeaderStyle setMonthNameTextStyle(int monthNameTextStyle);

    public HeaderStyle setMonthNameTextGravity(int monthNameTextGravity);

    public HeaderStyle setBgResID(int bgResID);

    public HeaderStyle setBgColor(int bgColor);

    public HeaderStyle setDayNameTextColor(int dayNameTextColor);

    public HeaderStyle setDayNameTextSize(int dayNameTextSize);

    public HeaderStyle setDayNameTextStyle(int dayNameTextStyle);

    public HeaderStyle setDayNameTextGravity(int dayNameTextGravity);

    public HeaderStyle setDayNameTextTypeface(int dayNameTextTypeface);

    public HeaderStyle setDayNameTextAllCaps(boolean dayNameTextAllCaps);
```

### Using attributes

```sh
        app:BothDays_bgResource="@android:color/holo_blue_dark"
        app:DestinationDay_bgResource="@android:color/holo_green_light"
        app:DestinationDay_textAppearance="@style/CalendarView.DestinationTextAppearance"
        app:Header_bgColor="@android:color/white"

        app:Header_bgResID="@drawable/calendar_gradient"

        app:Header_dayNameTextAppearance="@style/CalendarView.MonthDayNameTextAppearance"
        app:Header_monthNameTextAppearance="@style/CalendarView.MonthNameTextAppearance"
        app:OriginDay_bgResource="@android:color/holo_blue_bright"
        app:OriginDay_textAppearance="@style/CalendarView.OriginTextAppearance"
        app:WeekDay_bgColorActive="@android:color/white"
        app:WeekDay_bgColorInactive="@android:color/white"

        app:WeekDay_bgColorInterval="@color/calendar_background"
        app:WeekDay_bgResource="@android:color/transparent"

        app:WeekDay_textAppearance="@style/CalendarView.WeekDayTextAppearance"
        app:WeekDay_textColorInactive="@color/calendar_numbers_inactive"

        app:Week_bgColor="@android:color/white"

        app:todayLabelText="@string/label_today"
```



[wds]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/library/src/main/java/com/seductive/tools/calendarview/WeekDayStyle.java>
[ws]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/library/src/main/java/com/seductive/tools/calendarview/WeekItemStyle.java>
[hs]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/library/src/main/java/com/seductive/tools/calendarview/HeaderStyle.java>
[sc_gif]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/data/simple_calendar.gif>
[ss_gif]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/data/calendar_single_date.gif>
