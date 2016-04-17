package com.android.gifts.slidingmenuexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(16);
        menu.setBehindOffset(150);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT); // Use SLIDING_WINDOW to
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new SampleListFragment())
                .commit();
    }
}