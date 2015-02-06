package com.pony.epidroid.activity.fragment;

import android.os.Bundle;

import com.pony.epidroid.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by hervie_g on 2/3/15.
 */
public class PlanningFragment extends TabsFragment
{
    private List<Tab> tabs = null;

    private static PlanningTabFragment createTabFragment(Calendar start, Calendar end,
                                                         boolean moduleRegistered) {
        PlanningTabFragment fragment = new PlanningTabFragment();

        Bundle args = new Bundle();
        args.putString(PlanningTabFragment.ARG_START, CalendarUtils.formatDate(start.getTime(), CalendarUtils.IN_FORMAT, Locale.getDefault()));
        args.putString(PlanningTabFragment.ARG_END, CalendarUtils.formatDate(end.getTime(), CalendarUtils.IN_FORMAT, Locale.getDefault()));
        args.putBoolean(PlanningTabFragment.ARG_MODULE_REGISTERED, moduleRegistered);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected List<Tab> getTabs()
    {
        if (this.tabs == null) {
            this.tabs = new ArrayList<>();

            Calendar day = Calendar.getInstance();
            Calendar weekStart = Calendar.getInstance();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Calendar weekEnd = Calendar.getInstance();
            weekEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            this.tabs.add(new Tab("Journée", createTabFragment(day, day, true)));
            this.tabs.add(new Tab("Semaine", createTabFragment(weekStart, weekEnd, true)));
            this.tabs.add(new Tab("Tout", createTabFragment(weekStart, weekEnd, false)));
        }

        return this.tabs;
    }
}
