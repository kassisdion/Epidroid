package com.pony.epidroid.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.adapter.MarkListAdapter;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.data.MarkEntry;
import com.pony.epidroid.api.listeners.data.MarksListener;
import com.pony.epidroid.utils.Debug;

import java.util.List;

public class MarksFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_marks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ListView listView = (ListView) view.findViewById(R.id.list_marks);
        Api.getMarks(new MarksListener() {
            @Override
            public void onError(VolleyError error) {
                System.err.println("STACK TRACE");
                error.printStackTrace();
                Debug.showToast(getActivity(), "Can't load");
            }

            @Override
            public void onMarks(List<MarkEntry> marks) {
                MarkListAdapter adapter = new MarkListAdapter(marks, getActivity());
                listView.setAdapter(adapter);

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
