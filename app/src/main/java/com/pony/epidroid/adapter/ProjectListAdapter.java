package com.pony.epidroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.epidroid.R;
import com.pony.epidroid.api.data.ProjectEntry;

import java.util.List;

public class ProjectListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private List<ProjectEntry> mModules;

    public ProjectListAdapter(List<ProjectEntry> modules, Context context) {
        mModules = modules;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mModules.size();
    }

    @Override
    public Object getItem(int position) {
        return mModules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.project_list_content, null);
            holder.projectName = (TextView) convertView.findViewById(R.id.list_projectName);
            holder.titleModule = (TextView) convertView.findViewById(R.id.list_project_titleModule);
            holder.registered = (ImageView) convertView.findViewById(R.id.list_project_registered);
            holder.position = position;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ProjectEntry currentEntry = mModules.get(position);

        holder.projectName.setText(currentEntry.project);
        holder.titleModule.setText(currentEntry.titleModule);
        if (currentEntry.registered) {
            holder.registered.setImageResource(R.drawable.ic_registered);
        }
        else {
            holder.registered.setImageResource(R.drawable.ic_not_registered);
        }
        return convertView;
    }

    private class ViewHolder {
        int position;

        TextView projectName;
        TextView titleModule;
        ImageView registered;
    }
}
