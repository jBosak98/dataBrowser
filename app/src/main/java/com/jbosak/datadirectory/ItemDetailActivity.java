package com.jbosak.datadirectory;


import android.content.Intent;
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



public class ItemDetailActivity extends AppCompatActivity {
    public static final String TOOLBAR_TITLE = "TOOLBAR_TITLE";
    public static final String USER_DETAILS = "USER_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar detailToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);
        detailToolbar.setTitle("abc");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("abc");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {

            Bundle arguments = getIntent().getExtras();
            arguments.putString(TOOLBAR_TITLE, getIntent().getStringExtra(ItemDetailActivity.TOOLBAR_TITLE));
            arguments.putString(USER_DETAILS, getIntent().getStringExtra(ItemDetailActivity.USER_DETAILS));
            UserDetailFragment fragment = new UserDetailFragment();

            fragment.setArguments(arguments);
            //fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment).commit();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
