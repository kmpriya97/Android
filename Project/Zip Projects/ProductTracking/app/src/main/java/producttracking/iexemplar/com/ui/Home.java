package producttracking.iexemplar.com.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.AppBaseActivity;
import producttracking.iexemplar.com.app.ApplicationContext;
import producttracking.iexemplar.com.fragment.Configuration;
import producttracking.iexemplar.com.fragment.Dashboard;
import producttracking.iexemplar.com.fragment.Indent;
import producttracking.iexemplar.com.fragment.PurchaseOrder;
import producttracking.iexemplar.com.fragment.Reset;
import producttracking.iexemplar.com.fragment.SalesOrderFragment;
import producttracking.iexemplar.com.service.model.NavDrawerItem;
import producttracking.iexemplar.com.slider.NavigationDrawerAdapter;


public class Home extends AppBaseActivity implements ApplicationContext {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private int itemPosition;
    public static int selectedPosition = 0;
    private static String[] arrayNavigationItems = null;
    DrawerLayout mDrawerLayout;
    NavigationDrawerAdapter adapter;
    NavigationDrawerAdapter.NavigationCallBack navigationCallBack;
    ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        adapterClick();
        ButterKnife.bind(this);
        setToolBarConfig();
        getIntentValues();
        registerBaseActivityReceiver();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    private void getIntentValues() {
        int position = getIntent().getIntExtra(INTENT_POSITION, 0);
        selectedPosition = position;
        displayView(position);
    }


    /************************************************************************************
     * Class      : Home
     * Use        : Method Call for configuring toolbar
     * Created on : 11/16/2017
     * Updated on : 11/16/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    private void setToolBarConfig() {
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            mDrawerLayout = findViewById(R.id.drawer_layout);
            mDrawerList = findViewById(R.id.drawerList);
            arrayNavigationItems = getResources().getStringArray(R.array.nav_drawer_labels);
            adapter = new NavigationDrawerAdapter(Home.this, getData(), navigationCallBack);
            mDrawerList.setAdapter(adapter);

            mDrawerToggle = new ActionBarDrawerToggle(Home.this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                    toolbar.setAlpha(1 - slideOffset / 2);
                }
            };

            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mDrawerToggle.syncState();
                }
            });
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu_icon, getTheme());
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.setHomeAsUpIndicator(drawable);
            mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
                }
            });
        } catch (NullPointerException nullPointerException) {
            Log.e(EXCEPTION_NULL_POINTER, nullPointerException.toString());
        } catch (Exception exception) {
            Log.e(EXCEPTION, exception.toString());
        }

    }


    /************************************************************************************
     * Class      : Home
     * Use        : Method Call for callback listview click
     * Created on : 11/16/2017
     * Updated on : 12/02/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    private void adapterClick() {
        navigationCallBack = new NavigationDrawerAdapter.NavigationCallBack() {
            @Override
            public void clickNavigationDetails(int position) {
                mDrawerLayout.closeDrawers();
                displayView(position);
            }
        };
    }


    /************************************************************************************
     * Class      : Home
     * Use        : Method Call for displaying the views
     * Created on : 11/16/2017
     * Updated on : 12/02/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/

    public void displayView(int position) {
        toolbar.setTitle(arrayNavigationItems[position]);
        itemPosition = position;
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Dashboard();

                break;
            case 1:
                fragment = new Configuration();
                break;
            case 2:
                fragment = new Indent();
                break;
            case 3:
                fragment = new PurchaseOrder();
                break;
            case 4:
                fragment = new SalesOrderFragment();
                break;
            case 5:
                fragment = new Reset();
                break;
            case 6:
//                boolean isConnected = connectionDetector.isConnectingToInternet();
//                if (isConnected) {
//                    new APILogout().execute();
//                } else {
//                    Utils.alertDialog(this, getString(R.string.warning), getString(R.string.no_internet_connection));
//                }
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }


    /************************************************************************************
     * Class      : Home
     * Use        : Method Call All for drawer listener
     * Created on : 11/15/2017
     * Updated on : 11/15/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items
        for (int i = 0; i < arrayNavigationItems.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(arrayNavigationItems[i]);
            data.add(navItem);
        }
        return data;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    private void logoutProcess() {
        closeAllActivities();
        Intent intent = new Intent(Home.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }


}