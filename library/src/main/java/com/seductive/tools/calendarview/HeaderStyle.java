package com.seductive.tools.calendarview;

public final class HeaderStyle {
    public static final int NONE_VALUE = -1;

    protected int bgResID = NONE_VALUE;
    protected int bgColor = NONE_VALUE;
    protected int monthNameTextColor = NONE_VALUE;
    protected int monthNameTextSize = NONE_VALUE;
    protected int monthNameTextStyle = NONE_VALUE;
    protected int monthNameTextGravity = NONE_VALUE;
    protected int monthNameTextTypeface = NONE_VALUE;

    protected int dayNameTextColor = NONE_VALUE;
    protected int dayNameTextSize = NONE_VALUE;
    protected int dayNameTextStyle = NONE_VALUE;
    protected int dayNameTextGravity = NONE_VALUE;
    protected int dayNameTextTypeface = NONE_VALUE;
    protected boolean dayNameTextAllCaps;

    public HeaderStyle setMonthNameTextTypeface(int monthNameTextTypeface) {
        this.monthNameTextTypeface = monthNameTextTypeface;
        return this;
    }

    public HeaderStyle setMonthNameTextColor(int monthNameTextColor) {
        this.monthNameTextColor = monthNameTextColor;
        return this;
    }

    public HeaderStyle setMonthNameTextSize(int monthNameTextSize) {
        this.monthNameTextSize = monthNameTextSize;
        return this;
    }

    public HeaderStyle setMonthNameTextStyle(int monthNameTextStyle) {
        this.monthNameTextStyle = monthNameTextStyle;
        return this;
    }

    public HeaderStyle setMonthNameTextGravity(int monthNameTextGravity) {
        this.monthNameTextGravity = monthNameTextGravity;
        return this;
    }

    public HeaderStyle setBgResID(int bgResID) {
        this.bgResID = bgResID;
        return this;
    }

    public HeaderStyle setBgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public HeaderStyle setDayNameTextColor(int dayNameTextColor) {
        this.dayNameTextColor = dayNameTextColor;
        return this;
    }

    public HeaderStyle setDayNameTextSize(int dayNameTextSize) {
        this.dayNameTextSize = dayNameTextSize;
        return this;
    }

    public HeaderStyle setDayNameTextStyle(int dayNameTextStyle) {
        this.dayNameTextStyle = dayNameTextStyle;
        return this;
    }

    public HeaderStyle setDayNameTextGravity(int dayNameTextGravity) {
        this.dayNameTextGravity = dayNameTextGravity;
        return this;
    }

    public HeaderStyle setDayNameTextTypeface(int dayNameTextTypeface) {
        this.dayNameTextTypeface = dayNameTextTypeface;
        return this;
    }

    public HeaderStyle setDayNameTextAllCaps(boolean dayNameTextAllCaps) {
        this.dayNameTextAllCaps = dayNameTextAllCaps;
        return this;
    }
}
