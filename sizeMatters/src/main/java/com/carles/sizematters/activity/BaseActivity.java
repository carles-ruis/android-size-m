package com.carles.sizematters.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.carles.sizematters.SizeMattersApp;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public SizeMattersApp getApp() {
        return (SizeMattersApp) getApplication();
    }

}