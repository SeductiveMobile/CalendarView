# CalendarView

CalendarView is a RecycleView based library.

# Import

Currently only the clone setup is available. The gradle build setup is in progress.

# How to use?

You can check the "sample" module to check common usage.

# Features:

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


[wds]: <https://github.com/SeductiveMobile/CalendarView/blob/develop/library/src/main/java/com/seductive/tools/calendarview/WeekDayStyle.java>
