package com.seductive.tools.calendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;

import org.joda.time.DateTime;

public class CalendarView extends RecyclerView {

    private CalendarAdapter mAdapter;

    public CalendarView(Context context) {
        super(context);
        init(context, null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void scrollTo(DateTime date) {
        scrollToPosition(getScrolledPosition(date));
    }

    public void setDates(DateTime dateFrom, DateTime dateTo) {
        mAdapter.setDates(dateFrom, dateTo);
    }

    public void setDateClickListener(CalendarAdapter.DateClickListener listener) {
        mAdapter.setListener(listener);
    }

    public void setPeriod(int months) {
        mAdapter.getItems().clear();
        CalendarUtils.generateWeekItems(mAdapter.getItems(), months);
        mAdapter.notifyDataSetChanged();
    }

    public void setCalendarType(CalendarType calendarType) {
        this.mAdapter.setCalendarType(calendarType);
    }

    public void setWeekAnimation(@AnimRes int animation) {
        mAdapter.setRowAnimation(animation);
    }

    public void setHeaderStyle(HeaderStyle headerStyle) {
        HeaderStyle cachedStyle = mAdapter.getHeaderStyle();
        if (cachedStyle == null) {
            mAdapter.setHeaderStyle(headerStyle);
        } else {
            if (headerStyle.bgColor != HeaderStyle.NONE_VALUE) {
                cachedStyle.bgColor = headerStyle.bgColor;
            }
            if (headerStyle.dayNameTextAllCaps != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextAllCaps = headerStyle.dayNameTextAllCaps;
            }
            if (headerStyle.bgResID != HeaderStyle.NONE_VALUE) {
                cachedStyle.bgResID = headerStyle.bgResID;
            }
            if (headerStyle.dayNameTextColor != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextColor = headerStyle.dayNameTextColor;
            }
            if (headerStyle.dayNameTextGravity != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextGravity = headerStyle.dayNameTextGravity;
            }
            if (headerStyle.dayNameTextSize != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextSize = headerStyle.dayNameTextSize;
            }
            if (headerStyle.dayNameTextStyle != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextStyle = headerStyle.dayNameTextStyle;
            }
            if (headerStyle.dayNameTextTypeface != HeaderStyle.NONE_VALUE) {
                cachedStyle.dayNameTextTypeface = headerStyle.dayNameTextTypeface;
            }
            if (headerStyle.monthNameTextColor != HeaderStyle.NONE_VALUE) {
                cachedStyle.monthNameTextColor = headerStyle.monthNameTextColor;
            }
            if (headerStyle.monthNameTextGravity != HeaderStyle.NONE_VALUE) {
                cachedStyle.monthNameTextGravity = headerStyle.monthNameTextGravity;
            }
            if (headerStyle.monthNameTextSize != HeaderStyle.NONE_VALUE) {
                cachedStyle.monthNameTextSize = headerStyle.monthNameTextSize;
            }
            if (headerStyle.monthNameTextStyle != HeaderStyle.NONE_VALUE) {
                cachedStyle.monthNameTextStyle = headerStyle.monthNameTextStyle;
            }
            if (headerStyle.monthNameTextTypeface != HeaderStyle.NONE_VALUE) {
                cachedStyle.monthNameTextTypeface = headerStyle.monthNameTextTypeface;
            }
        }
    }

    public void setWeekDayStyle(WeekDayStyle style) {
        WeekDayStyle cachedStyle = mAdapter.getWeekDayStyle();
        if (cachedStyle == null) {
            mAdapter.setWeekDayStyle(style);
        } else {
            copyWeekDayStyle(cachedStyle, style);
        }
    }

    private void copyWeekDayStyle(WeekDayStyle target, WeekDayStyle source) {
        if (source.textColorInactive != WeekDayStyle.NONE_VALUE) {
            target.textColorInactive = source.textColorInactive;
        }
        if (source.textAllCaps != WeekDayStyle.NONE_VALUE) {
            target.textAllCaps = source.textAllCaps;
        }
        if (source.bgColorActive != WeekDayStyle.NONE_VALUE) {
            target.bgColorActive = source.bgColorActive;
        }
        if (source.bgColorInactive != WeekDayStyle.NONE_VALUE) {
            target.bgColorInactive = source.bgColorInactive;
        }
        if (source.bgColorInterval != WeekDayStyle.NONE_VALUE) {
            target.bgColorInterval = source.bgColorInterval;
        }
        if (source.bgResource != WeekDayStyle.NONE_VALUE) {
            target.bgResource = source.bgResource;
        }
        if (source.textColorActive != WeekDayStyle.NONE_VALUE) {
            target.textColorActive = source.textColorActive;
        }
        if (source.textGravity != WeekDayStyle.NONE_VALUE) {
            target.textGravity = source.textGravity;
        }
        if (source.textSize != WeekDayStyle.NONE_VALUE) {
            target.textSize = source.textSize;
        }
        if (source.textStyle != WeekDayStyle.NONE_VALUE) {
            target.textStyle = source.textStyle;
        }
        if (source.textTypeface != WeekDayStyle.NONE_VALUE) {
            target.textTypeface = source.textTypeface;
        }
    }

    public void setWeekDayDestinationStyle(WeekDayStyle style) {
        WeekDayStyle cachedStyle = mAdapter.getWeekDayDestinationStyle();
        if (cachedStyle == null) {
            mAdapter.setWeekDayDestinationStyle(style);
        } else {
            copyWeekDayStyle(cachedStyle, style);
        }
    }

    public void setWeekDayOriginStyle(WeekDayStyle style) {
        WeekDayStyle cachedStyle = mAdapter.getWeekDayOriginStyle();
        if (cachedStyle == null) {
            mAdapter.setWeekDayOriginStyle(style);
        } else {
            copyWeekDayStyle(cachedStyle, style);
        }
    }

    public void setWeekStyle(WeekStyle style) {
        WeekStyle cachedStyle = mAdapter.getWeekStyle();
        if (cachedStyle == null) {
            mAdapter.setWeekStyle(style);
        } else {
            if (style.bgColor != WeekStyle.NONE_VALUE) {
                cachedStyle.bgColor = style.bgColor;
            }
        }
    }

    public void setTodayStyle(WeekDayStyle style) {
        WeekDayStyle cachedStyle = mAdapter.getTodayStyle();
        if (cachedStyle == null) {
            mAdapter.setTodayStyle(style);
        } else {
            copyWeekDayStyle(cachedStyle, style);
        }
    }

    public void setBothDatesBgResource(@DrawableRes int resId) {
        mAdapter.setBothDatesBgResource(resId);
    }

    public void setTodayLabelText(@StringRes int resId) {
        setTodayLabelText(getResources().getString(resId));
    }

    public void setTodayLabelText(String text) {
        mAdapter.setTodayLabelText(text);
    }

    private void init(Context context, AttributeSet attributes) {
        setAdapter(mAdapter = new CalendarAdapter(context));
        setLayoutManager(new LinearLayoutManager(context));
        setHasFixedSize(true);
        setVerticalScrollBarEnabled(true);
        HeaderStyle headerStyle = new HeaderStyle();
        WeekStyle weekStyle = new WeekStyle();
        WeekDayStyle weekDayStyle = new WeekDayStyle();
        WeekDayStyle todayStyle = new WeekDayStyle();
        WeekDayStyle weekDayOriginStyle = new WeekDayStyle();
        WeekDayStyle weekDayDestinationStyle = new WeekDayStyle();
        CalendarType calendarType = CalendarType.SingleDate;
        if (attributes != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, R.styleable.CalendarView, 0, 0);

            try {
                if (typedArray.hasValue(R.styleable.CalendarView_type)) {
                    int type = typedArray.getInt(R.styleable.CalendarView_type, CalendarType.SingleDate.ordinal());
                    calendarType = CalendarType.values()[type];
                }

                //Apply HeaderStyle attributes
                headerStyle.setBgColor(typedArray.getColor(R.styleable.CalendarView_Header_bgColor, Color.WHITE));
                headerStyle.setBgResID(typedArray.getResourceId(R.styleable.CalendarView_Header_bgResID, R.drawable.calendar_gradient));

                //Apply header MonthName attributes
                int[] attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity
                };
                int ap = typedArray.getResourceId(R.styleable.CalendarView_Header_monthNameTextAppearance,
                        R.style.CalendarView_MonthNameTextAppearance);
                TypedArray textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                headerStyle.setMonthNameTextColor(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_month_name)));
                                break;
                            case 1:
                                headerStyle.setMonthNameTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.month_text_size)));
                                break;
                            case 2:
                                headerStyle.setMonthNameTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                headerStyle.setMonthNameTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                headerStyle.setMonthNameTextGravity(textAppearance.getInt(i, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }

                //Apply header MonthDayName attributes
                attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity,
                        android.R.attr.textAllCaps
                };
                ap = typedArray.getResourceId(R.styleable.CalendarView_Header_dayNameTextAppearance,
                        R.style.CalendarView_MonthDayNameTextAppearance);
                textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                headerStyle.setDayNameTextColor(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_days_name)));
                                break;
                            case 1:
                                headerStyle.setDayNameTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.week_day_text_size)));
                                break;
                            case 2:
                                headerStyle.setDayNameTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                headerStyle.setDayNameTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                headerStyle.setDayNameTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                                break;
                            case 5:
                                headerStyle.setDayNameTextAllCaps(textAppearance.getBoolean(i, true));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }

                //Apply WeekStyle attributes
                weekStyle.setBgColor(typedArray.getColor(R.styleable.CalendarView_Week_bgColor, Color.WHITE));

                //Apply WeekDay attributes
                weekDayStyle.setBgColorActive(typedArray.getColor(R.styleable.CalendarView_WeekDay_bgColorActive, Color.WHITE));
                weekDayStyle.setBgColorInactive(typedArray.getColor(R.styleable.CalendarView_WeekDay_bgColorInactive, Color.WHITE));
                weekDayStyle.setBgColorInterval(typedArray.getColor(R.styleable.CalendarView_WeekDay_bgColorInterval, ContextCompat.getColor(context, R.color.calendar_background)));
                weekDayStyle.setBgResource(typedArray.getResourceId(R.styleable.CalendarView_WeekDay_bgResource, android.R.color.transparent));
                //Apply WeekDay text appearance
                attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity,
                        android.R.attr.textAllCaps
                };
                ap = typedArray.getResourceId(R.styleable.CalendarView_WeekDay_textAppearance,
                        R.style.CalendarView_WeekDayTextAppearance);
                textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                weekDayStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                                break;
                            case 1:
                                weekDayStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                                break;
                            case 2:
                                weekDayStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                weekDayStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                weekDayStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                                break;
                            case 5:
                                weekDayStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }
                weekDayStyle.setTextColorInactive(typedArray.getColor(R.styleable.CalendarView_WeekDay_textColorInactive, ContextCompat.getColor(context, R.color.calendar_numbers_inactive)));

                //Apply WeekDayOrigin attributes
                weekDayOriginStyle.setBgColorActive(weekDayStyle.bgColorActive);
                weekDayOriginStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
                weekDayOriginStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
                weekDayOriginStyle.setBgResource(typedArray.getResourceId(R.styleable.CalendarView_OriginDay_bgResource, android.R.color.holo_blue_bright));
                //Apply WeekDay text appearance
                attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity,
                        android.R.attr.textAllCaps
                };
                ap = typedArray.getResourceId(R.styleable.CalendarView_WeekDay_textAppearance,
                        R.style.CalendarView_OriginTextAppearance);
                textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                weekDayOriginStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                                break;
                            case 1:
                                weekDayOriginStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                                break;
                            case 2:
                                weekDayOriginStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                weekDayOriginStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                weekDayOriginStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                                break;
                            case 5:
                                weekDayOriginStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }
                weekDayStyle.setTextColorInactive(weekDayStyle.textColorInactive);

                //Apply WeekDayDestination attributes
                weekDayDestinationStyle.setBgColorActive(weekDayStyle.bgColorActive);
                weekDayDestinationStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
                weekDayDestinationStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
                weekDayDestinationStyle.setBgResource(typedArray.getResourceId(R.styleable.CalendarView_OriginDay_bgResource, android.R.color.holo_green_light));
                //Apply WeekDay text appearance
                attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity,
                        android.R.attr.textAllCaps
                };
                ap = typedArray.getResourceId(R.styleable.CalendarView_WeekDay_textAppearance,
                        R.style.CalendarView_DestinationTextAppearance);
                textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                weekDayDestinationStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                                break;
                            case 1:
                                weekDayDestinationStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                                break;
                            case 2:
                                weekDayDestinationStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                weekDayDestinationStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                weekDayDestinationStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                                break;
                            case 5:
                                weekDayDestinationStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }
                weekDayDestinationStyle.setTextColorInactive(weekDayStyle.textColorInactive);

                //Apply Today attributes
                todayStyle.setBgColorActive(weekDayStyle.bgColorActive);
                todayStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
                todayStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
                todayStyle.setBgResource(weekDayStyle.bgResource);
                //Apply WeekDay text appearance
                attrIds = new int[]{
                        android.R.attr.textColor,
                        android.R.attr.textSize,
                        android.R.attr.typeface,
                        android.R.attr.textStyle,
                        android.R.attr.gravity,
                        android.R.attr.textAllCaps
                };
                ap = typedArray.getResourceId(R.styleable.CalendarView_WeekDay_textAppearance,
                        R.style.CalendarView_TodayTextAppearance);
                textAppearance = (ap != -1) ?
                        context.getTheme().obtainStyledAttributes(ap, attrIds) : null;
                if (textAppearance != null) {
                    for (int i = 0; i < attrIds.length; i++) {
                        switch (i) {
                            case 0:
                                todayStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                                break;
                            case 1:
                                todayStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                                break;
                            case 2:
                                todayStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                                break;
                            case 3:
                                todayStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                                break;
                            case 4:
                                todayStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                                break;
                            case 5:
                                todayStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                                break;
                        }
                    }
                    textAppearance.recycle();
                }
                todayStyle.setTextColorInactive(weekDayStyle.textColorInactive);

                mAdapter.setBothDatesBgResource(typedArray.getResourceId(R.styleable.CalendarView_BothDays_bgResource, android.R.color.holo_blue_dark));
                String todayLabel = typedArray.getString(R.styleable.CalendarView_todayLabelText);
                if (todayLabel == null) {
                    todayLabel = getResources().getString(R.string.label_today);
                }
                mAdapter.setTodayLabelText(todayLabel);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                typedArray.recycle();
            }
        } else {
            //Apply HeaderStyle attributes
            headerStyle.setBgColor(Color.WHITE);
            headerStyle.setBgResID(R.drawable.calendar_gradient);

            //Apply header MonthName attributes
            int[] attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity
            };
            TypedArray textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_MonthNameTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        headerStyle.setMonthNameTextColor(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_month_name)));
                        break;
                    case 1:
                        headerStyle.setMonthNameTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.month_text_size)));
                        break;
                    case 2:
                        headerStyle.setMonthNameTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        headerStyle.setMonthNameTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        headerStyle.setMonthNameTextGravity(textAppearance.getInt(i, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL));
                        break;
                }
                textAppearance.recycle();
            }

            //Apply header MonthDayName attributes
            attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity,
                    android.R.attr.textAllCaps
            };
            textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_MonthDayNameTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        headerStyle.setDayNameTextColor(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_days_name)));
                        break;
                    case 1:
                        headerStyle.setDayNameTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.week_day_text_size)));
                        break;
                    case 2:
                        headerStyle.setDayNameTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        headerStyle.setDayNameTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        headerStyle.setDayNameTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                        break;
                    case 5:
                        headerStyle.setDayNameTextAllCaps(textAppearance.getBoolean(i, true));
                        break;
                }
            }
            textAppearance.recycle();

            //Apply WeekStyle attributes
            weekStyle.setBgColor(Color.WHITE);

            //Apply WeekDay attributes
            weekDayStyle.setBgColorActive(Color.WHITE);
            weekDayStyle.setBgColorInactive(Color.WHITE);
            weekDayStyle.setBgColorInterval(ContextCompat.getColor(context, R.color.calendar_background));
            weekDayStyle.setBgResource(android.R.color.transparent);
            //Apply WeekDay text appearance
            attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity,
                    android.R.attr.textAllCaps
            };
            textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_WeekDayTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        weekDayStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                        break;
                    case 1:
                        weekDayStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                        break;
                    case 2:
                        weekDayStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        weekDayStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        weekDayStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                        break;
                    case 5:
                        weekDayStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                        break;
                }
            }
            textAppearance.recycle();
            weekDayStyle.setTextColorInactive(ContextCompat.getColor(context, R.color.calendar_numbers_inactive));

            //Apply WeekDayOrigin attributes
            weekDayOriginStyle.setBgColorActive(weekDayStyle.bgColorActive);
            weekDayOriginStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
            weekDayOriginStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
            weekDayOriginStyle.setBgResource(android.R.color.holo_blue_bright);
            //Apply WeekDay text appearance
            attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity,
                    android.R.attr.textAllCaps
            };
            textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_OriginTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        weekDayOriginStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                        break;
                    case 1:
                        weekDayOriginStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                        break;
                    case 2:
                        weekDayOriginStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        weekDayOriginStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        weekDayOriginStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                        break;
                    case 5:
                        weekDayOriginStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                        break;
                }
            }
            textAppearance.recycle();
            weekDayStyle.setTextColorInactive(weekDayStyle.textColorInactive);

            //Apply WeekDayDestination attributes
            weekDayDestinationStyle.setBgColorActive(weekDayStyle.bgColorActive);
            weekDayDestinationStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
            weekDayDestinationStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
            weekDayDestinationStyle.setBgResource(android.R.color.holo_green_light);
            //Apply WeekDay text appearance
            attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity,
                    android.R.attr.textAllCaps
            };
            textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_DestinationTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        weekDayDestinationStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                        break;
                    case 1:
                        weekDayDestinationStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                        break;
                    case 2:
                        weekDayDestinationStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        weekDayDestinationStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        weekDayDestinationStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                        break;
                    case 5:
                        weekDayDestinationStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                        break;
                }
            }
            textAppearance.recycle();
            weekDayDestinationStyle.setTextColorInactive(weekDayStyle.textColorInactive);

            //Apply Today attributes
            todayStyle.setBgColorActive(weekDayStyle.bgColorActive);
            todayStyle.setBgColorInactive(weekDayStyle.bgColorInactive);
            todayStyle.setBgColorInterval(weekDayStyle.bgColorInterval);
            todayStyle.setBgResource(weekDayStyle.bgResource);
            //Apply WeekDay text appearance
            attrIds = new int[]{
                    android.R.attr.textColor,
                    android.R.attr.textSize,
                    android.R.attr.typeface,
                    android.R.attr.textStyle,
                    android.R.attr.gravity,
                    android.R.attr.textAllCaps
            };
            textAppearance = context.getTheme().obtainStyledAttributes(R.style.CalendarView_TodayTextAppearance, attrIds);
            for (int i = 0; i < attrIds.length; i++) {
                switch (i) {
                    case 0:
                        todayStyle.setTextColorActive(textAppearance.getColor(i, ContextCompat.getColor(context, R.color.calendar_numbers_chosen)));
                        break;
                    case 1:
                        todayStyle.setTextSize(textAppearance.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(R.dimen.date_text_size)));
                        break;
                    case 2:
                        todayStyle.setTextTypeface(textAppearance.getInt(i, Typeface.MONOSPACE.getStyle()));
                        break;
                    case 3:
                        todayStyle.setTextStyle(textAppearance.getInt(i, Typeface.NORMAL));
                        break;
                    case 4:
                        todayStyle.setTextGravity(textAppearance.getInt(i, Gravity.CENTER));
                        break;
                    case 5:
                        todayStyle.setTextAllCaps(textAppearance.getBoolean(i, true));
                        break;
                }
            }
            textAppearance.recycle();
            todayStyle.setTextColorInactive(weekDayStyle.textColorInactive);

            mAdapter.setBothDatesBgResource(android.R.color.holo_blue_dark);
            mAdapter.setTodayLabelText(getResources().getString(R.string.label_today));
        }
        setCalendarType(calendarType);
        setHeaderStyle(headerStyle);
        setWeekDayDestinationStyle(weekDayDestinationStyle);
        setWeekDayOriginStyle(weekDayOriginStyle);
        setWeekDayStyle(weekDayStyle);
        setWeekStyle(weekStyle);
        setTodayStyle(todayStyle);
    }

    private int getScrolledPosition(DateTime date) {
        if (date != null) {
            for (WeekItem item : mAdapter.getItems()) {
                if (item.month == date.getMonthOfYear() && item.year == date.getYear()) {
                    return mAdapter.getItems().indexOf(item);
                }
            }
        }
        return 0;
    }

    public void removeClickListener() {
        mAdapter.removeClickListener();
    }
}
