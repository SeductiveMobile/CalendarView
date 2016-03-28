package com.seductive.tools.calendarview;

public final class WeekStyle {
    public static final int NONE_VALUE = -1;

    protected int bgColor = NONE_VALUE;

    public WeekStyle setBgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }
}
