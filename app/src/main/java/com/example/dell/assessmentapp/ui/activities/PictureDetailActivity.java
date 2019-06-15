package com.example.dell.assessmentapp.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.dell.assessmentapp.R;

public class PictureDetailActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello: This is testing text");
    }
}
