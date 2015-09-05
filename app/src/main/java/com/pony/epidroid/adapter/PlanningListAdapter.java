package com.pony.epidroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pony.epidroid.R;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.data.PlanningEntry;
import com.pony.epidroid.api.listeners.derived.EmptyListener;
import com.pony.epidroid.utils.DateManager;
import com.pony.epidroid.utils.SimpleDialogFragment;

import java.util.List;

/**
 * Created by dusterherz on 02/02/15.
 */
public class PlanningListAdapter extends BaseAdapter {

    private final List<PlanningEntry> mPlanning;
    private final LayoutInflater mInflater;
    private Context context = null;
    View.OnClickListener listenerInscription = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View parent = (View) v.getParent();
            TextView activityTextView = (TextView) parent.findViewById(R.id.list_planning_activity);

            SimpleDialogFragment dialogFragment = new SimpleDialogFragment();
            dialogFragment.setPositivBtnTxt(context.getString(R.string.yes));
            dialogFragment.setNegativBtnTxt(context.getString(R.string.no));
            dialogFragment.setBoxName((context.getString(R.string.inscription)));
            dialogFragment.setMessage(context.getString(R.string.valid_inscription) + activityTextView.getText());
            dialogFragment.setPositivBtnListener(new SimpleDialogFragment.PositivBtnListener() {
                @Override
                public void onResponse() {
                    //Faire la requete pour s'inscrire
                }
            });
            dialogFragment.show(((FragmentActivity) context).getFragmentManager(), "TAG");
        }
    };
    View.OnClickListener listenerDesincription = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View parent = (View) v.getParent();
            TextView activityTextView = (TextView) parent.findViewById(R.id.list_planning_activity);

            SimpleDialogFragment dialogFragment = new SimpleDialogFragment();
            dialogFragment.setPositivBtnTxt(context.getString(R.string.yes));
            dialogFragment.setNegativBtnTxt(context.getString(R.string.no));
            dialogFragment.setBoxName((context.getString(R.string.desincription)));
            dialogFragment.setMessage(context.getString(R.string.valid_desinscription) + activityTextView.getText());
            dialogFragment.setPositivBtnListener(new SimpleDialogFragment.PositivBtnListener() {
                @Override
                public void onResponse() {
                    //Faire la requete pour ce desinscrire
                }
            });
            dialogFragment.show(((FragmentActivity) context).getFragmentManager(), "TAG");
        }
    };
    View.OnClickListener listenerToken = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolder viewHolder = (ViewHolder) v.getTag();
            final int position = viewHolder.position;

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle(context.getResources().getString(R.string.enter_token));
            alert.setMessage("Veuillez entrer votre token :");

            final EditText input = new EditText(context);
            input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
            input.setGravity(Gravity.CENTER);

            alert.setView(input);
            alert.setPositiveButton(context.getResources().getString(R.string.validate), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    final PlanningEntry currentActivity = mPlanning.get(position);

                    Api.validateToken(currentActivity.scholarYear, currentActivity.codeModule, currentActivity.codeInstance, currentActivity.codeActi, input.getText().toString(), new EmptyListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(context, "Token validé avec succés", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Toast.makeText(context, "Mauvais token", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            alert.show();
        }
    };

    public PlanningListAdapter(List<PlanningEntry> planning, Context context) {
        this.context = context;
        mPlanning = planning;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPlanning.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanning.get(position);
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
            convertView = mInflater.inflate(R.layout.planning_list_content, null);
            holder.activity = (TextView) convertView.findViewById(R.id.list_planning_activity);
            holder.activityDate = (TextView) convertView.findViewById(R.id.list_planning_activityDate);
            holder.activityHour = (TextView) convertView.findViewById(R.id.list_planning_activityHour);
            holder.activityModule = (TextView) convertView.findViewById(R.id.list_planning_activityModule);
            holder.planningIcon = (ImageView) convertView.findViewById(R.id.list_planning_icon);
            holder.button = (Button) convertView.findViewById(R.id.list_planning_btn);
            holder.position = position;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PlanningEntry currentActivity = mPlanning.get(position);

        final DateManager dateStart = new DateManager(currentActivity.start);
        final DateManager dateEnd = new DateManager(currentActivity.end);

        holder.activity.setText(currentActivity.actiTitle);
        holder.activityDate.setText(dateStart.dateToString());
        holder.activityHour.setText(dateStart.getHourString() + ":" + dateStart.getMinutesString() + " - " + dateEnd.getHourString() + ":" + dateEnd.getMinutesString());
        holder.activityModule.setText(currentActivity.titleModule);

        boolean isRegistered = currentActivity.eventRegistered.equals("registered");
        boolean isRegisteredToModule = currentActivity.moduleRegistered;


        if (isRegisteredToModule && currentActivity.allowRegister) {
            holder.planningIcon.setImageResource(R.drawable.ic_registered);
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText(R.string.inscription);
            holder.button.setOnClickListener(listenerInscription);
        }
        else if (isRegistered && currentActivity.allowToken) {
            holder.planningIcon.setImageResource(R.drawable.ic_token);
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText(R.string.enter_token);
            holder.button.setOnClickListener(listenerToken);
        }
        else if (isRegistered) {
            holder.planningIcon.setImageResource(R.drawable.ic_registered);
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setText(R.string.desincription);
            holder.button.setOnClickListener(listenerDesincription);
        }
        else {
            holder.planningIcon.setImageResource(R.drawable.ic_calendar);
            holder.button.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder {
        int position;

        TextView activity;
        TextView activityDate;
        TextView activityHour;
        TextView activityModule;
        ImageView planningIcon;
        Button button;
    }
}