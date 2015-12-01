package com.ally.pam.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ally.pam.R;
import com.ally.pam.adapter.RecyclerViewAdapter;
import com.ally.pam.fragment.EventFragment;
import com.ally.pam.fragment.HomeFragment;
import com.ally.pam.fragment.NotificationFragment;
import com.ally.pam.fragment.ProfileFragment;
import com.ally.pam.fragment.RequestFragment;
import com.ally.pam.fragment.SupportFragment;

/**
 * Created by Ally on 11/23/2015.
 */
public class HomeActivity extends ActionBarActivity {

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view
    private String NAME = "Akash Bangad";
    private String EMAIL = "akash.bangad@gmail.com";
    private int PROFILE = R.mipmap.user_pic;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    private RecyclerView mRecyclerView;                           // Declaring RecyclerView
    private RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    private RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager

    private DrawerLayout Drawer;                                  // Declaring DrawerLayout

    private CharSequence mTitle;

    private ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    private String navTitles[];
    private TypedArray navIcons;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTitle = getTitle();

        //Let's first set up toolbar
        setupToolbar();

        navTitles = getResources().getStringArray(R.array.navDrawerItems);
        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new RecyclerViewAdapter(navTitles, navIcons, NAME, EMAIL, PROFILE, this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
                getSupportActionBar().setTitle(mTitle);
//                invalidateOptionsMenu();
            }
        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(1);
        }
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    public void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new HomeFragment(this);
                if (mMenu != null) {
                    mMenu.findItem(R.id.edit).setVisible(false);
                }
                break;
            case 2:
                fragment = new RequestFragment();
                mMenu.findItem(R.id.edit).setVisible(false);
                break;
            case 3:
                fragment = new NotificationFragment();
                mMenu.findItem(R.id.edit).setVisible(false);
                break;
            case 4:
                fragment = new EventFragment();
                mMenu.findItem(R.id.edit).setVisible(false);
                break;
            case 5:
                fragment = new ProfileFragment();
                mMenu.findItem(R.id.edit).setVisible(true);
                break;
            case 6:
                fragment = new SupportFragment();
                mMenu.findItem(R.id.edit).setVisible(false);
                break;
            case 7:
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            // update selected item and title, then close the drawer
//            mDrawerList.setItemChecked(position, true);
//            mDrawerList.setSelection(position);
            setTitle(navTitles[position - 1]);
            Drawer.closeDrawer(mRecyclerView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
//            case R.id.add:
//                Intent i = new Intent(this, NewRequestActivity.class);
//                startActivity(i);
//                break;
            case R.id.edit:
                Intent ii = new Intent(this, ProfileDetailActivity.class);
                startActivity(ii);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.add).setVisible(false);
        menu.findItem(R.id.edit).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle(R.string.exit_message)
                .setPositiveButton(R.string.yes_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
								/* User clicked OK so do some stuff */
                                Intent startMain = new Intent(
                                        Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startMain
                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                                moveTaskToBack(false);
                                android.os.Process
                                        .killProcess(android.os.Process.myPid());
                                System.exit(0);
                            }
                        })
                .setNegativeButton(R.string.no_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
								/* User clicked Cancel so do some stuff */
                            }
                        }).create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_HOME:
                android.os.Process.killProcess(android.os.Process.myPid());
                moveTaskToBack(false);
                System.exit(0);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
