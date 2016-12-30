package com.example.daiki.androidtemplate;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.daiki.androidtemplate.event.BaseEvent;
import com.example.daiki.androidtemplate.event.MessageEvent;
import com.example.daiki.androidtemplate.event.Presenter;
import com.example.daiki.androidtemplate.inject.AppComponent;
import com.example.daiki.androidtemplate.model.User;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler;

import javax.inject.Inject;


/**
 * Created by daiki on 2016/11/27.
 */



import com.example.daiki.androidtemplate.databinding.FragmentMainBinding;

@FragmentWithArgs
public class MainFragment extends BaseFragment {

    /////////// Dagger Inject /////////////
    @Inject
    Context appContext;
    @Inject
    Presenter presenter;

    @Override
    protected void inject(AppComponent component) {
        component.inject(this);
    }

    /////////////// Fragment Args Inject ///////////

    @Arg( bundler = ParcelerArgsBundler.class )
    User user;

    /////////////// DataBinding ///////////////
    private FragmentMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this); // read @Arg fields
    }

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false);
        binding.setHandlers(new Handlers());

        // Binding Data
        binding.setUser(user);

        return binding.getRoot();
    }

    void setupView(){
        binding.name.setText(user.getName());
        binding.age.setText(String.format("%d歳",user.getAge()));
    }



    // Event
    public class Handlers implements BaseHandlers {
        public void onClick(View view) {

            if(view.getId() == R.id.sendButton){
                presenter.post("Post from MainFragment");
            }
            else if(view.getId() == R.id.moveButton){
                presenter.move(new SubFragment());
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
