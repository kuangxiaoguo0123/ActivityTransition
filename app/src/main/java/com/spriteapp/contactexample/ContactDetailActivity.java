package com.spriteapp.contactexample;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kuangxiaoguo on 2018/2/8.
 */

public class ContactDetailActivity extends AppCompatActivity {

    private static final String TAG = "ContactDetailActivity";
    public static final String TEXT_TAG = "text_tag";
    private Toolbar mToolbar;
    private ImageView mImageView;
    private TextView mContentTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_contact_detail);
        mImageView = findViewById(R.id.image_view);
        mContentTextView = findViewById(R.id.content_text_view);
        String contentText = getIntent().getStringExtra(TEXT_TAG);
        mContentTextView.setText(contentText);
//        setSlideAnimation();
        setFadeAnimation();
//        setExplodeAnimation();
        initToolbar();
    }

    private void setFadeAnimation() {
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
        getWindow().setReturnTransition(fade);
        fade.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                createRevealAnimation();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    private void createRevealAnimation() {
        mImageView.setVisibility(View.VISIBLE);
        int width = mImageView.getWidth();
        int height = mImageView.getHeight();
        int radius = Math.max(width, height);
        int centerX = width / 2;
        int centerY = height / 2;
        Animator animator = ViewAnimationUtils.createCircularReveal(mImageView, centerX, centerY, 0, radius);
        animator.setDuration(1000);
        animator.start();
    }

    private void setExplodeAnimation() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReturnTransition(explode);
    }

    private void setSlideAnimation() {
        Slide enterTransition = new Slide(Gravity.BOTTOM);
        enterTransition.setDuration(500);
        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        //设置Activity进入动画
        getWindow().setEnterTransition(enterTransition);

        Slide returnTransition = new Slide(Gravity.BOTTOM);
        returnTransition.setDuration(500);
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
