package com.seductive.tools.calendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public final class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private static final int NONE_VALUE = -1;

    private Context context;
    private DateClickListener listener;
    private List<WeekItem> items = new ArrayList<>();

    private CalendarType calendarType = CalendarType.SINGLE_DATE;
    private HeaderStyle headerStyle;
    private WeekStyle weekStyle;
    private WeekDayStyle weekDayStyle;
    private WeekDayStyle todayStyle;
    private WeekDayStyle weekDayOriginStyle;
    private WeekDayStyle weekDayDestinationStyle;
    private int bothDatesBgResource = android.R.color.holo_blue_dark;
    private int rowAnimation = NONE_VALUE;
    private String[] monthNames;
    private int dateOriginPosition = NONE_VALUE;
    private int dateDestinationPosition = NONE_VALUE;
    private DateTime dateOrigin;
    private DateTime dateDestination;
    private TextView todayLabel;
    private String todayLabelText;

    public CalendarAdapter(Context context) {
        this.context = context;
        this.monthNames = context.getResources().getStringArray(R.array.months_names);
    }

    public CalendarAdapter setCalendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
        return this;
    }

    public CalendarAdapter setRowAnimation(int rowAnimation) {
        this.rowAnimation = rowAnimation;
        return this;
    }

    public CalendarAdapter setTodayLabelText(String todayLabelText) {
        this.todayLabelText = todayLabelText;
        return this;
    }

    public CalendarAdapter setBothDatesBgResource(int bothDatesBgResource) {
        this.bothDatesBgResource = bothDatesBgResource;
        return this;
    }

    public CalendarAdapter setTodayStyle(WeekDayStyle todayStyle) {
        this.todayStyle = todayStyle;
        return this;
    }

    public CalendarAdapter setHeaderStyle(HeaderStyle headerStyle) {
        this.headerStyle = headerStyle;
        return this;
    }

    public CalendarAdapter setWeekStyle(WeekStyle weekStyle) {
        this.weekStyle = weekStyle;
        return this;
    }

    public CalendarAdapter setWeekDayStyle(WeekDayStyle weekDayStyle) {
        this.weekDayStyle = weekDayStyle;
        return this;
    }

    public CalendarAdapter setWeekDayOriginStyle(WeekDayStyle weekDayOriginStyle) {
        this.weekDayOriginStyle = weekDayOriginStyle;
        return this;
    }

    public CalendarAdapter setWeekDayDestinationStyle(WeekDayStyle weekDayDestinationStyle) {
        this.weekDayDestinationStyle = weekDayDestinationStyle;
        return this;
    }

    public HeaderStyle getHeaderStyle() {
        return headerStyle;
    }

    public WeekStyle getWeekStyle() {
        return weekStyle;
    }

    public WeekDayStyle getWeekDayStyle() {
        return weekDayStyle;
    }

    public WeekDayStyle getTodayStyle() {
        return todayStyle;
    }

    public WeekDayStyle getWeekDayOriginStyle() {
        return weekDayOriginStyle;
    }

    public WeekDayStyle getWeekDayDestinationStyle() {
        return weekDayDestinationStyle;
    }

    public void addWeekItem(WeekItem weekItem) {
        this.items.add(weekItem);
        notifyItemInserted(items.size() - 1);
    }

    public void setDates(DateTime dateTo, DateTime dateBack) {
        this.dateOrigin = dateTo != null ? new DateTime().withDate(dateTo.toLocalDate()) : null;
        this.dateDestination = dateBack != null ? new DateTime().withDate(dateBack.toLocalDate()) : null;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == WeekItem.TYPE_HEADER) {
            View v = LayoutInflater.from(this.context).inflate(R.layout.calendar_header_view, parent, false);
            return new ViewHolder(v, viewType);
        } else if (viewType == WeekItem.TYPE_ITEM) {
            View v = LayoutInflater.from(this.context).inflate(R.layout.calendar_row_view, parent, false);
            return new ViewHolder(v, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == WeekItem.TYPE_HEADER) {
            String monthNameStr = monthNames[items.get(position).month - 1];
            String fullLabel = monthNameStr + " " + items.get(position).year;
            holder.monthName.setText(fullLabel.toUpperCase());
        } else if (getItemViewType(position) == WeekItem.TYPE_ITEM) {
            DateTime startWeekDate = items.get(position).startDate;
            if (startWeekDate != null) {
                fillByItems(holder, startWeekDate, items.get(position).year,
                        items.get(position).month);
            }
        }
        setRowAnimation(holder.itemView, position);
    }

    protected DateTime getDateOrigin() {
        return dateOrigin;
    }

    protected DateTime getDateDestination() {
        return dateDestination;
    }

    protected CalendarAdapter setListener(DateClickListener listener) {
        this.listener = listener;
        return this;
    }

    protected List<WeekItem> getItems() {
        return items;
    }

    protected void setItems(List<WeekItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private void fillByItems(ViewHolder holder, DateTime firstDayWeek, int year, int month) {

        DateTime today = DateTime.now();
        for (int i = 0; i < 7; i++) {
            clearCellState(holder.parentsList.get(i));
            DateTime timeToSet = firstDayWeek.plusDays(i);
            if (timeToSet.getMonthOfYear() == month && timeToSet.getYear() == year) {
                if (dateOrigin != null && dateDestination != null && CalendarUtils.datesEquals(dateOrigin, dateDestination) && CalendarUtils.datesEquals(timeToSet, dateOrigin)) {
                    setItemBothDates(holder, timeToSet, holder.parentsList.get(i), holder.dayItems.get(i));
                } else if (dateOrigin != null && CalendarUtils.datesEquals(dateOrigin, timeToSet)) {
                    setItemOrigin(holder, timeToSet, holder.parentsList.get(i), holder.dayItems.get(i));
                } else if (dateDestination != null && CalendarUtils.datesEquals(dateDestination, timeToSet)) {
                    setItemDestination(holder, timeToSet, holder.parentsList.get(i), holder.dayItems.get(i));
                } else {
                    if (CalendarUtils.datesEquals(timeToSet, today)) {
                        setItemToday(holder.parentsList.get(i), i, holder, holder.dayItems.get(i), timeToSet.getDayOfMonth());
                    } else {
                        if (timeToSet.compareTo(today) < 0) {
                            setItemInactive(holder, i, timeToSet.getDayOfMonth());
                        } else {
                            setItemActive(holder, timeToSet, i, timeToSet);
                        }
                    }
                }
            } else {
                setItemInvisible(holder.dayItems.get(i));
            }
        }
    }

    private void setItemOrigin(View.OnClickListener listener, DateTime tag, FrameLayout parent, TextView tv) {
        tv.setText(String.valueOf(tag.getDayOfMonth()));
        parent.setBackgroundResource(weekDayOriginStyle.bgResource);
        parent.setTag(tag);
        parent.setOnClickListener(listener);
        applyDayStyle(tv, weekDayOriginStyle, true);
    }

    private void setItemDestination(View.OnClickListener listener, DateTime tag, FrameLayout parent, TextView tv) {
        tv.setText(String.valueOf(tag.getDayOfMonth()));
        parent.setBackgroundResource(weekDayDestinationStyle.bgResource);
        parent.setTag(tag);
        parent.setOnClickListener(listener);
        applyDayStyle(tv, weekDayDestinationStyle, true);
    }

    private void setItemBothDates(View.OnClickListener listener, DateTime tag, FrameLayout parent, TextView tv) {
        parent.setBackgroundResource(bothDatesBgResource);
        parent.setTag(tag);
        parent.setOnClickListener(listener);
    }

    private void setItemToday(FrameLayout parent, int dayPos, ViewHolder holder, TextView tv, int day) {
        if (todayLabel == null) {
            todayLabel = new TextView(context);
            todayLabel.setGravity(Gravity.CENTER);
            todayLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            todayLabel.setText(todayLabelText);
            todayLabel.setTextColor(ContextCompat.getColor(context, R.color.calendar_today_color));
            todayLabel.setId(R.id.label);
            todayLabel.setOnClickListener(null);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            todayLabel.setLayoutParams(params);
        }
        if (todayLabel.getParent() != null)
            ((ViewGroup) todayLabel.getParent()).removeView(todayLabel);
        parent.addView(todayLabel);
        parent.bringChildToFront(todayLabel);

        tv.setText(String.valueOf(day));
        tv.setOnClickListener(null);

        applyDayStyle(tv, todayStyle, false);

        holder.parentsList.get(dayPos).setBackgroundColor(weekDayStyle.bgColorActive);
    }

    private void clearCellState(FrameLayout parent) {
        if (parent.getChildCount() > 1)
            parent.removeView(todayLabel);
        parent.setBackgroundResource(weekDayStyle.bgResource);
        parent.setOnClickListener(null);
    }

    private void setItemInactive(ViewHolder holder, int dayPos, int day) {
        TextView tv = holder.dayItems.get(dayPos);
        tv.setText(String.valueOf(day));
        tv.setOnClickListener(null);

        applyDayStyle(tv, weekDayStyle, false);

        holder.parentsList.get(dayPos).setBackgroundColor(weekDayStyle.bgColorInactive);
    }

    private void setItemActive(ViewHolder holder, DateTime tag, int dayPos, DateTime day) {
        TextView tv = holder.dayItems.get(dayPos);
        tv.setText(String.valueOf(day.getDayOfMonth()));
        tv.setTag(tag);
        tv.setOnClickListener(holder);

        applyDayStyle(tv, weekDayStyle, true);

        if (dateOrigin != null && dateDestination != null
                && day.compareTo(dateOrigin) > 0 && day.compareTo(dateDestination) < 0) {
            holder.parentsList.get(dayPos).setBackgroundColor(weekDayStyle.bgColorInterval);
        } else {
            holder.parentsList.get(dayPos).setBackgroundColor(weekDayStyle.bgColorActive);
        }
    }

    private void applyDayStyle(TextView tv, WeekDayStyle style, boolean active) {
        tv.setGravity(style.textGravity);
        tv.setTypeface(Typeface.defaultFromStyle(style.textTypeface), style.textStyle);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, style.textSize);
        tv.setAllCaps(style.textAllCaps == 1);
        if (active) {
            tv.setTextColor(style.textColorActive);
        } else {
            tv.setTextColor(style.textColorInactive);
        }
    }

    private void applyHeaderStyle(TextView textView, HeaderStyle style) {
        textView.setTextColor(style.dayNameTextColor);
        textView.setGravity(style.dayNameTextGravity);
        textView.setTypeface(Typeface.defaultFromStyle(style.dayNameTextTypeface), style.dayNameTextStyle);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, style.dayNameTextSize);
        textView.setAllCaps(style.dayNameTextAllCaps == 1);
    }

    private void setItemInvisible(TextView tv) {
        tv.setTextColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).itemType;
    }

    @SuppressWarnings("all")
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView monthName;
        TextView monItem, tueItem, wenItem, thuItem, friItem, satItem, sunItem;
        RelativeLayout header;
        ImageView headerBg;
        LinearLayout weekRoot;
        List<TextView> dayItems;
        List<FrameLayout> parentsList;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == WeekItem.TYPE_HEADER) {
                header = (RelativeLayout) itemView;
                headerBg = (ImageView) itemView.findViewById(R.id.bg);
                monthName = (TextView) itemView.findViewById(R.id.month_name);

                monthName.setTextColor(headerStyle.monthNameTextColor);
                monthName.setGravity(headerStyle.monthNameTextGravity);
                monthName.setTypeface(Typeface.defaultFromStyle(headerStyle.monthNameTextTypeface), headerStyle.monthNameTextStyle);
                monthName.setTextSize(TypedValue.COMPLEX_UNIT_SP, headerStyle.monthNameTextSize);

                header.setBackgroundColor(headerStyle.bgColor);
                headerBg.setBackgroundResource(headerStyle.bgResID);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.mon), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.tue), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.wen), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.thu), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.fri), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.sat), headerStyle);

                applyHeaderStyle((TextView) itemView.findViewById(R.id.sun), headerStyle);
            } else if (viewType == WeekItem.TYPE_ITEM) {
                weekRoot = (LinearLayout) itemView;
                dayItems = new ArrayList<>();
                parentsList = new ArrayList<>();

                weekRoot.setBackgroundColor(weekStyle.bgColor);

                monItem = (TextView) itemView.findViewById(R.id.mon);
                applyDayStyle(monItem, weekDayDestinationStyle, true);
                monItem.setOnClickListener(this);
                monItem.setOnLongClickListener(this);
                dayItems.add(monItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.mon_parent));

                tueItem = (TextView) itemView.findViewById(R.id.tue);
                applyDayStyle(tueItem, weekDayDestinationStyle, true);
                tueItem.setOnClickListener(this);
                tueItem.setOnLongClickListener(this);
                dayItems.add(tueItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.tue_parent));

                wenItem = (TextView) itemView.findViewById(R.id.wen);
                applyDayStyle(wenItem, weekDayDestinationStyle, true);
                wenItem.setOnClickListener(this);
                wenItem.setOnLongClickListener(this);
                dayItems.add(wenItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.wen_parent));

                thuItem = (TextView) itemView.findViewById(R.id.thu);
                applyDayStyle(thuItem, weekDayDestinationStyle, true);
                thuItem.setOnClickListener(this);
                thuItem.setOnLongClickListener(this);
                dayItems.add(thuItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.thu_parent));

                friItem = (TextView) itemView.findViewById(R.id.fri);
                applyDayStyle(friItem, weekDayDestinationStyle, true);
                friItem.setOnClickListener(this);
                friItem.setOnLongClickListener(this);
                dayItems.add(friItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.fri_parent));

                satItem = (TextView) itemView.findViewById(R.id.sat);
                applyDayStyle(satItem, weekDayDestinationStyle, true);
                satItem.setOnClickListener(this);
                satItem.setOnLongClickListener(this);
                dayItems.add(satItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.sat_parent));

                sunItem = (TextView) itemView.findViewById(R.id.sun);
                applyDayStyle(sunItem, weekDayDestinationStyle, true);
                sunItem.setOnClickListener(this);
                sunItem.setOnLongClickListener(this);
                dayItems.add(sunItem);
                parentsList.add((FrameLayout) itemView.findViewById(R.id.sun_parent));
                for (FrameLayout parent : parentsList) {
                    parent.setBackgroundColor(weekDayStyle.bgColorActive);
                    parent.setBackgroundResource(weekDayStyle.bgResource);
                }
            }
        }

        @Override
        public void onClick(View view) {
            if (view.getTag() != null && listener != null) {
                DateTime date = (DateTime) view.getTag();
                handleDateClick(date, getAdapterPosition());
                listener.onDateClick(date);
            }
        }

        public void clearAnimation() {
            itemView.clearAnimation();
        }

        @Override
        public boolean onLongClick(View view) {
            if (view.getTag() != null && listener != null) {
                listener.onDateLongClick((DateTime) view.getTag());
                return true;
            }
            return false;
        }
    }

    private void handleDateClick(DateTime date, int position) {
        switch (calendarType) {
            case SINGLE_DATE:
                dateOrigin = date;
                if (dateOriginPosition != NONE_VALUE && dateOriginPosition != position) {
                    notifyItemChanged(dateOriginPosition);
                }
                dateOriginPosition = position;
                notifyItemChanged(position);
                break;
            case PERIOD:
                if (dateOrigin == null) {
                    dateOrigin = date;
                    dateOriginPosition = position;
                    notifyItemChanged(position);
                } else {
                    switch (dateOrigin.compareTo(date)) {
                        case -1:    //Less
                            if (dateDestination == null) {
                                dateDestination = date;
                                dateDestinationPosition = position;
                                notifyItemRangeChanged(dateOriginPosition, dateDestinationPosition);
                            } else {
                                switch (dateDestination.compareTo(date)) {
                                    case -1:
                                        int maxPosition = Math.max(position, dateDestinationPosition);
                                        dateDestination = date;
                                        dateDestinationPosition = position;
                                        notifyItemRangeChanged(dateOriginPosition, maxPosition);
                                        break;
                                    case 1:
                                        dateOrigin = date;
                                        dateDestination = null;
                                        notifyItemRangeChanged(dateOriginPosition, dateDestinationPosition);
                                        dateOriginPosition = position;
                                        dateDestinationPosition = -1;
                                        break;
                                }
                            }
                            break;
                        case 0:     //Equals
                            break;
                        default:    //Greater
                            if (dateOriginPosition != position)
                                notifyItemChanged(dateOriginPosition);
                            dateOrigin = date;
                            dateOriginPosition = position;
                            notifyItemChanged(position);
                            break;
                    }
                }
                break;
        }
    }

    public void removeClickListener() {
        listener = null;
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    private int lastPosition = -1;

    /**
     * Here is the key method to apply the animation
     */
    private void setRowAnimation(View viewToAnimate, int position) {
        if (rowAnimation == NONE_VALUE) {
            return;
        }
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, rowAnimation);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else {
            lastPosition--;
        }
    }

    public interface DateClickListener {
        void onDateClick(DateTime date);

        void onDateLongClick(DateTime date);
    }
}
