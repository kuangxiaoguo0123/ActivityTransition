package com.spriteapp.contactexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.spriteapp.contactexample.adapter.ContactAdapter;
import com.spriteapp.contactexample.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    LinearLayout mOuterLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_contact);
//        setSlideAnimation();
//        setExplodeAnimation();
        initView();
        initTestData();
    }

    private void setExplodeAnimation() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setReenterTransition(explode);
    }

    private void setSlideAnimation() {
        Slide exitTransition = new Slide(Gravity.TOP);
        exitTransition.setDuration(500);
        //设置Activity退出动画
        getWindow().setExitTransition(exitTransition);

        Slide reenterTransition = new Slide(Gravity.TOP);
        reenterTransition.setDuration(500);
        //设置状态栏不执行动画 false为默认执行
        reenterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        //设置Activity再次进入时动画
        getWindow().setReenterTransition(reenterTransition);
    }

    private void initTestData() {
        List<ContactModel> contactModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ContactModel model = new ContactModel();
            model.setName("Mike " + i);
            contactModelList.add(model);
        }
        ContactAdapter contactAdapter = new ContactAdapter(contactModelList);
        mRecyclerView.setAdapter(contactAdapter);
    }

    private void initView() {
        initToolbar();
        mOuterLayout = findViewById(R.id.outer_layout);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("All Contacts");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "Back Click", Toast.LENGTH_SHORT).show();
            }
        });
        mToolbar.inflateMenu(R.menu.contact_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.more:
                        Toast.makeText(ContactActivity.this, "More Click", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}
