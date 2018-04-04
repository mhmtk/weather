package com.mobiquityinc.weather.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.mobiquityinc.weather.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container_frame_layout)
    protected FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
