package com.pony.epidroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pony.epidroid.R;
import com.pony.epidroid.api.data.MarkEntry;

import java.util.List;

/**
 * Created by Aude on 03/02/15.
 */
public class MarkListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final List<MarkEntry> mMarks;

    public MarkListAdapter(List<MarkEntry> marks, Context context) {
        this.mMarks = marks;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMarks.size();
    }

    @Override
    public Object getItem(int position) {
        return mMarks.get(position);
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
            convertView = mInflater.inflate(R.layout.marks_list_content, null);
            holder.title = (TextView) convertView.findViewById(R.id.list_marks_name);
            holder.value = (TextView) convertView.findViewById(R.id.list_marks_date);
            holder.position = position;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MarkEntry currentEntry = mMarks.get(position);

        holder.title.setText(currentEntry.title);
        holder.value.setText(currentEntry.finalNote);

        return convertView;
    }

    private class ViewHolder {
        int position;

        TextView title;
        TextView value;
    }
}
