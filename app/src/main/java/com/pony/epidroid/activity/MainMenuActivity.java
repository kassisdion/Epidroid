package com.pony.epidroid.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pony.epidroid.R;
import com.pony.epidroid.activity.fragment.HomeFragment;
import com.pony.epidroid.activity.fragment.MarksFragment;
import com.pony.epidroid.activity.fragment.ModuleFragment;
import com.pony.epidroid.activity.fragment.PlanningFragment;
import com.pony.epidroid.activity.fragment.ProjectFragment;
import com.pony.epidroid.activity.fragment.SettingsFragment;
import com.pony.epidroid.api.Api;
import com.pony.epidroid.api.ApiData;
import com.pony.epidroid.utils.ActivityHelper;

public class MainMenuActivity extends FragmentActivity implements ListView.OnItemClickListener {
    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    public static void start(Activity parent, boolean killParent) {
        ActivityHelper.startActivity(parent, MainMenuActivity.class, killParent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerItems = getResources().getStringArray(R.array.drawer_items);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerItems));
        drawerList.setOnItemClickListener(this);

        ActionBar bar = getActionBar();
        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        // Set home as default content.
        selectItem(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: // Home
                fragment = new HomeFragment();
                break;

            case 1: // Planning
                fragment = new PlanningFragment();
                break;

            case 2: // Modules
                fragment = new ModuleFragment();
                break;

            case 3: // Marks
                fragment = new MarksFragment();
                break;

            case 4: // Project
                fragment = new ProjectFragment();
                break;

            case 5: // Parameters
                // fragment = new SettingsFragment();
                this.logout();
                break;
        }

        if (fragment != null) {
            this.setContentFrame(fragment);
        }

        // Set drawer selected item
        drawerList.setItemChecked(position, true);
        setTitle(drawerItems[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    private void setContentFrame(Fragment fragment) {
        Api.cancelAll();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void logout() {
        ApiData.token = null;
        LoginActivity.start(this, true);
    }

}
