package com.jbosak.datadirectory;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        if(savedInstanceState != null) {
            Log.e("LOL",savedInstanceState.getString("TITLE", "TTT"));


            toolbar.setTitle(savedInstanceState.getString("TITLE", "TITLE"));
            getSupportActionBar().setTitle(savedInstanceState.getString("TITLE", "TITLE"));
        }
        setSupportActionBar(toolbar);

        //avedInstanceState.getString("TITLE",)


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //private void setSupportActionBar(Toolbar toolbar) {
    //}


}
