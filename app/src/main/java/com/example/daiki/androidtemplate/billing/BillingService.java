package com.example.daiki.androidtemplate.billing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.android.vending.billing.IInAppBillingService;
import com.example.daiki.androidtemplate.inject.lifecycle.Lifecycle;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/05/07.
 */

@Lifecycle
public class BillingService {

    private AppCompatActivity activity;
    private IInAppBillingService mService;
    private ServiceConnection mServiceConn;

    @Inject
    public BillingService(AppCompatActivity activity)
    {
        this.activity = activity;

        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
            }
        };
    }

    public void startService(){
        Intent serviceIntent =
                new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        this.activity.bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    public void stopService(){
        if (mService != null) {
            this.activity.unbindService(mServiceConn);
        }
    }

}
