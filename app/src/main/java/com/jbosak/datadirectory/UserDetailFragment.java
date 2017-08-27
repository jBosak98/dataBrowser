package com.jbosak.datadirectory;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDetailFragment extends android.support.v4.app.Fragment {
    private String TITLE = "TITLE";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail_fragment, container, false);
        if(getArguments().containsKey(ItemDetailActivity.USER_DETAILS)){
            ((TextView) rootView.findViewById(R.id.item_detail_fragment)).setText(getArguments().getString(ItemDetailActivity.USER_DETAILS));
        }

        return rootView;
    }

    public UserDetailFragment() {
    }


}
