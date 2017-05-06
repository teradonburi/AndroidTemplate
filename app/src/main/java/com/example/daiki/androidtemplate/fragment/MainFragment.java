package com.example.daiki.androidtemplate.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daiki.androidtemplate.MainApplication;
import com.example.daiki.androidtemplate.R;
import com.example.daiki.androidtemplate.databinding.FragmentMainBinding;
import com.example.daiki.androidtemplate.entity.User;
import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleComponent;
import com.example.daiki.androidtemplate.inject.lifecycle.LifecycleModule;
import com.example.daiki.androidtemplate.viewmodel.MainFragmentViewModel;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by daiki on 2017/04/28.
 */

@FragmentWithArgs
public class MainFragment extends Fragment {

    private LifecycleComponent lifecycleComponent;
    private FragmentMainBinding binding;
    private MainFragmentViewModel mainFragmentViewModel;
    @Arg(bundler = ParcelerArgsBundler.class)
    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainFragmentBuilder.injectArguments(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        lifecycleComponent = MainApplication.getComponent()
                .lifecycleComponent(new LifecycleModule((AppCompatActivity) getActivity()));
        mainFragmentViewModel = lifecycleComponent.mainFragmentViewModel();
        binding.setViewModel(mainFragmentViewModel);
        mainFragmentViewModel.setUser(user);

        return binding.getRoot();
    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(User user){
       mainFragmentViewModel.setUser(user);
    }

}
