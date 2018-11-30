package com.parque.deferias.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.parque.deferias.R;
import com.parque.deferias.utility.ActivityUtils;
import com.parque.deferias.utility.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private RelativeLayout rootLayout;

    // Constants
    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initVariable();
        initView();

    }

    private void initVariable() {
        mContext = getApplicationContext();
        mActivity = SplashActivity.this;
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        rootLayout = (RelativeLayout) findViewById(R.id.splashBody);
    }

    private void initFunctionality() {
        if (AppUtils.isNetworkAvailable(mContext)) {
            rootLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.getInstance().invokeActivity(mActivity, MainActivity.class, true);
                }
            }, SPLASH_DURATION);
        } else {
            AppUtils.noInternetWarning(rootLayout, mContext);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFunctionality();
    }
}

