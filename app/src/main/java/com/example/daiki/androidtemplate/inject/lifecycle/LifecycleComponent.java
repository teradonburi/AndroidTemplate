package com.example.daiki.androidtemplate.inject.lifecycle;


import com.example.daiki.androidtemplate.viewmodel.MainActivityViewModel;
import com.example.daiki.androidtemplate.viewmodel.MainFragmentViewModel;

import dagger.Subcomponent;

/**
 * Created by daiki on 2017/04/28.
 */

@Lifecycle
@Subcomponent(modules = {LifecycleModule.class})
public interface LifecycleComponent {
    // ViewModel
    MainActivityViewModel mainActivityViewModel();
    MainFragmentViewModel mainFragmentViewModel();

    // Activity、Fragment追加時はここに書く
}
