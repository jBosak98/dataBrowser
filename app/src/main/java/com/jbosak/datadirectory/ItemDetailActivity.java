package com.jbosak.datadirectory;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.star_big_on;


public class ItemDetailActivity extends AppCompatActivity {
    public static final String TOOLBAR_TITLE = "TOOLBAR_TITLE";
    public static final String USER_DETAILS = "USER_DETAILS";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        final String title = getIntent().getStringExtra(ItemDetailActivity.TOOLBAR_TITLE);


        Toolbar detailToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        if(appBarLayout != null) {

            if (getIntent().getStringExtra(ItemDetailActivity.TOOLBAR_TITLE) != null) {
                appBarLayout.setTitle(getIntent().getStringExtra(ItemDetailActivity.TOOLBAR_TITLE));
            }


            } if(savedInstanceState != null) {


            if (savedInstanceState.containsKey(ItemDetailActivity.TOOLBAR_TITLE)) {
                appBarLayout.setTitle(savedInstanceState.getString(ItemDetailActivity.TOOLBAR_TITLE));
            }
        }


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setTag(title);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(btn_star_big_off);

                Snackbar.make(view, "Added to favourite" + title, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (savedInstanceState == null) {

            Bundle arguments = getIntent().getExtras();
            arguments.putString(TOOLBAR_TITLE, title);
            arguments.putString(USER_DETAILS, getIntent().getStringExtra(ItemDetailActivity.USER_DETAILS));
            UserDetailFragment fragment = new UserDetailFragment();

            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(ItemDetailActivity.TOOLBAR_TITLE, getIntent().getStringExtra(ItemDetailActivity.TOOLBAR_TITLE));
        super.onSaveInstanceState(savedInstanceState);
    }


}
