package com.example.daiki.androidtemplate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daiki.androidtemplate.databinding.FragmentSubBinding;
import com.example.daiki.androidtemplate.event.BaseEvent;
import com.example.daiki.androidtemplate.event.MessageEvent;
import com.example.daiki.androidtemplate.event.Presenter;
import com.example.daiki.androidtemplate.inject.AppComponent;

import javax.inject.Inject;

/**
 * Created by daiki on 2016/11/27.
 */


public class SubFragment extends BaseFragment {

    @Inject
    Context appContext;
    @Inject
    Presenter presenter;

    @Override
    protected void inject(AppComponent component) {
        component.inject(this);
    }

    /////////////// DataBinding ///////////////
    private FragmentSubBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sub, container, false);
        binding.setHandlers(new Handlers());

        return binding.getRoot();
    }


    // Event
    public class Handlers implements BaseHandlers {
        public void onClick(View view) {
            if(view.getId() == R.id.sendButton) {
                presenter.post("Post from SubFragment");
            }
            else if(view.getId() == R.id.backButton){
                presenter.back();
            }
        }

    }

    @Override
    public void onEvent(BaseEvent event) {
        if(event instanceof MessageEvent){
            MessageEvent msgEvent = (MessageEvent)event;
            Toast.makeText(appContext,msgEvent.msg,Toast.LENGTH_LONG).show();
        }
    }


}
