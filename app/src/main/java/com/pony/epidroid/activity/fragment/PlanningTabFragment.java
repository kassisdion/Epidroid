package com.pony.epidroid.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.adapter.PlanningListAdapter;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.data.PlanningEntry;
import com.pony.epidroid.api.listeners.data.PlanningListener;
import com.pony.epidroid.utils.CalendarUtils;
import com.pony.epidroid.utils.CollectionsUtils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by faisant on 21/01/15.
 */
public class PlanningTabFragment extends Fragment {
    public final static String ARG_START = "planning_start";
    public final static String ARG_END = "planning_end";
    public final static String ARG_MODULE_REGISTERED = "planning_module_registered";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ListView listView = (ListView) view.findViewById(R.id.list_planning);

        Bundle args = getArguments();
        String start = args.getString(ARG_START);
        String end = args.getString(ARG_END);
        final boolean moduleRegistered = args.getBoolean(ARG_MODULE_REGISTERED);

        Date startDate;
        Date endDate;

        try {
            startDate = CalendarUtils.parse(start);
            endDate = CalendarUtils.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return; // TODO: Do something more useful.
        }

        Api.getPlanning(startDate, endDate, new PlanningListener() {
            @Override
            public void onPlanning(List<PlanningEntry> planning) {
                planning = CollectionsUtils.filter(planning, new CollectionsUtils.Filter<PlanningEntry>() {
                    @Override
                    public boolean check(PlanningEntry item) {
                        return !moduleRegistered || item.moduleRegistered;
                    }
                });

                Collections.sort(planning, new Comparator<PlanningEntry>() {
                    @Override
                    public int compare(PlanningEntry lhs, PlanningEntry rhs) {
                        return lhs.start.compareTo(rhs.start);
                    }
                });

                PlanningListAdapter adapter = new PlanningListAdapter(planning, getActivity());
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
