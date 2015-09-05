package com.pony.epidroid.activity.fragment;

import android.os.Bundle;

import com.github.machinarius.preferencefragment.PreferenceFragment;
import com.pony.epidroid.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
