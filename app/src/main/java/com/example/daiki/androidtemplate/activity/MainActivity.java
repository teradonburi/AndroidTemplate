package com.example.daiki.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daiki.androidtemplate.MainApplication;
import com.example.daiki.androidtemplate.R;
import com.example.daiki.androidtemplate.fragment.MainFragment;
import com.example.daiki.androidtemplate.fragment.MainFragmentBuilder;
import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleModule;
import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleComponent;
import com.example.daiki.androidtemplate.viewmodel.MainActivityViewModel;


public class MainActivity extends AppCompatActivity {


    private LifecycleComponent lifecycleComponent;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        lifecycleComponent = MainApplication.getComponent()
                .lifecycleComponent(new LifecycleModule(this));
        mainActivityViewModel = lifecycleComponent.mainActivityViewModel();
        mainActivityViewModel.restoreState(savedInstanceState);

        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            MainFragment mainFragment = new MainFragmentBuilder(mainActivityViewModel.getUser()).build();
            fragmentManager.beginTransaction()
                    .add(R.id.container,mainFragment)
                    .commit();
            fragmentManager.executePendingTransactions();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainActivityViewModel.saveState(outState);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainActivityViewModel.startTimer();
    }
    @Override
    protected void onStop(){
        mainActivityViewModel.stopTimer();
        super.onStop();
    }

}
