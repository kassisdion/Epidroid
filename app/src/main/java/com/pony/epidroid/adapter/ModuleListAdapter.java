package com.pony.epidroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pony.epidroid.R;
import com.pony.epidroid.api.data.ModuleEntry;

import java.util.List;

/**
 * Created by Aude on 03/02/15.
 */
public class ModuleListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final List<ModuleEntry> mModules;

    public ModuleListAdapter(List<ModuleEntry> modules, Context context) {
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
            convertView = mInflater.inflate(R.layout.module_list_content, null);
            holder.title = (TextView) convertView.findViewById(R.id.list_module_name);
            //holder.cycle = (TextView) convertView.findViewById();
            holder.grade = (TextView) convertView.findViewById(R.id.list_module_grade);
            //holder.credits = (TextView) convertView.findViewById();
            //holder.semester = (TextView) convertView.findViewById();
            holder.position = position;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ModuleEntry currentEntry = mModules.get(position);

        holder.title.setText(currentEntry.title);
        holder.grade.setText(currentEntry.grade);

        return convertView;
    }

    private class ViewHolder {
        int position;

        TextView title;
        //TextView cycle;
        TextView grade;
        //TextView credits;
        //TextView semester;
    }
}
