package com.seductive.tools.calendarview;

public class WeekDayStyle {
    public static final int NONE_VALUE = -1;
    protected int textColorActive = NONE_VALUE;
    protected int textColorInactive = NONE_VALUE;
    protected int textSize = NONE_VALUE;
    protected int textStyle = NONE_VALUE;
    protected int textGravity = NONE_VALUE;
    protected int textTypeface = NONE_VALUE;
    protected int textAllCaps = NONE_VALUE;

    protected int bgColorActive = NONE_VALUE;
    protected int bgColorInactive = NONE_VALUE;
    protected int bgColorInterval = NONE_VALUE;
    protected int bgResource = NONE_VALUE;

    public WeekDayStyle setBgColorInactive(int bgColorInactive) {
        this.bgColorInactive = bgColorInactive;
        return this;
    }

    public WeekDayStyle setBgColorInterval(int bgColorInterval) {
        this.bgColorInterval = bgColorInterval;
        return this;
    }

    public WeekDayStyle setBgResource(int bgResource) {
        this.bgResource = bgResource;
        return this;
    }

    public WeekDayStyle setBgColorActive(int bgColorActive) {
        this.bgColorActive = bgColorActive;
        return this;
    }

    public WeekDayStyle setTextColorActive(int textColorActive) {
        this.textColorActive = textColorActive;
        return this;
    }

    public WeekDayStyle setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public WeekDayStyle setTextStyle(int textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public WeekDayStyle setTextGravity(int textGravity) {
        this.textGravity = textGravity;
        return this;
    }

    public WeekDayStyle setTextTypeface(int textTypeface) {
        this.textTypeface = textTypeface;
        return this;
    }

    public WeekDayStyle setTextAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps ? 1 : 0;
        return this;
    }

    public WeekDayStyle setTextColorInactive(int textColorInactive) {
        this.textColorInactive = textColorInactive;
        return this;
    }
}
