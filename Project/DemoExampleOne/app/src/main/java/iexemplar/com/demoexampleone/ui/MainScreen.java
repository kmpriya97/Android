package iexemplar.com.demoexampleone.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import iexemplar.com.demoexampleone.R;
import iexemplar.com.demoexampleone.app.AppBaseActivity;
import iexemplar.com.demoexampleone.fragments.Configuration;
import iexemplar.com.demoexampleone.fragments.DashBoard;
import iexemplar.com.demoexampleone.fragments.Help;
import iexemplar.com.demoexampleone.fragments.Notification;
import iexemplar.com.demoexampleone.fragments.Settings;

public class MainScreen extends AppBaseActivity  implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_screen );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerBaseActivityReceiver();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (id) {
            case R.id.nav_dashboard:
                fragment = new DashBoard();
                title = "DashBoard";
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                title = "Settings";
                break;
            case R.id.nav_config:
                fragment = new Configuration();
                title = "Configuration";
                break;
            case R.id.nav_notification:
                fragment = new Notification();
                title = "Notification";
                break;
            case R.id.logout:
                finish();
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        if (drawer.isDrawerOpen( GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }
}
