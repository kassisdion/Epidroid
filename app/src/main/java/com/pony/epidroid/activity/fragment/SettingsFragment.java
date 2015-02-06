package com.pony.epidroid.activity.fragment;

import android.os.Bundle;

import com.github.machinarius.preferencefragment.PreferenceFragment;
import com.pony.epidroid.R;

/**
 * Created by faisant on 21/01/15.
 */
public class SettingsFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
