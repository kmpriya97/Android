package producttracking.iexemplar.com.app;

/*******************************************************************************
 * Created by iExemplar on 11/8/2017.
 * <p>
 * Copyright (c) 2017 iExemplar. All rights reserved.
 *******************************************************************************/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

public abstract class AppBaseActivity extends AppCompatActivity {
    public static final String FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION = "producttracking.iexemplar.com.app.FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION";
    public static final IntentFilter INTENT_FILTER = createIntentFilter();
    private BaseActivityReceiver baseActivityReceiver = new BaseActivityReceiver();

    /************************************************************************************
     * Class      : AppBaseActivity
     * Use        : Method Call for clear intent filter values
     * Created on : 11/8/2017
     * Updated on : 11/8/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    private static IntentFilter createIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        return filter;
    }

    /************************************************************************************
     * Class      : AppBaseActivity
     * Use        : Method Call for  register activity receiver
     * Created on : 11/8/2017
     * Updated on : 11/8/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    protected void registerBaseActivityReceiver() {
        registerReceiver(baseActivityReceiver, INTENT_FILTER);
    }

    /************************************************************************************
     * Class      : AppBaseActivity
     * Use        : Method Call for  un register activity receiver
     * Created on : 11/8/2017
     * Updated on : 11/8/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    protected void unRegisterBaseActivityReceiver() {
        unregisterReceiver(baseActivityReceiver);
    }

    /************************************************************************************
     * Class      : AppBaseActivity
     * Use        : Method Call for closing activity
     * Created on : 11/8/2017
     * Updated on : 11/8/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    protected void closeAllActivities() {
        sendBroadcast(new Intent(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION));
    }

    /************************************************************************************
     * Class      : AppBaseActivity
     * Use        : Method Call for receiving activity
     * Created on : 11/8/2017
     * Updated on : 11/8/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    public class BaseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION)) {
                finish();
            }
        }
    }


}