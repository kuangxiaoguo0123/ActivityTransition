package com.spriteapp.contactexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by kuangxiaoguo on 2018/2/8.
 */

public class ContactDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_contact_detail);
        setupWindowAnimation();
        initToolbar();
    }

    private void setupWindowAnimation() {
        Slide enterTransition = new Slide(Gravity.BOTTOM);
        enterTransition.setDuration(500);
        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        //设置Activity进入动画
        getWindow().setEnterTransition(enterTransition);

        Slide returnTransition = new Slide(Gravity.BOTTOM);
        enterTransition.setDuration(500);
        returnTransition.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setReturnTransition(returnTransition);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.inflateMenu(R.menu.contact_detail_menu);
    }
}
