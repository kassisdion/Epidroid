package com.pony.epidroid.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.adapter.ProjectListAdapter;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.data.ProjectEntry;
import com.pony.epidroid.api.listeners.data.ProjectsListener;
import com.pony.epidroid.utils.CollectionsUtils;
import com.pony.epidroid.utils.Debug;

import java.util.List;

public class ProjectFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ListView listView = (ListView) view.findViewById(R.id.list_project);
        Api.getProjects(new ProjectsListener() {
            @Override
            public void onProjects(List<ProjectEntry> list) {
                list = CollectionsUtils.filter(list, new CollectionsUtils.Filter<ProjectEntry>() {
                    @Override
                    public boolean check(ProjectEntry item) {
                        return !item.project.equals("null");
                    }
                });
                ProjectListAdapter adapter = new ProjectListAdapter(list, getActivity());
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();
                Debug.showToast(getActivity(), "Can't load");
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}