package com.jbosak.datadirectory;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDetailFragment extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  if (getArguments().containsKey("TITLE")) {
            Log.e(this.getTag(),"KeY, ok!");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getArguments().getString(ItemDetailActivity.TOOLBAR_TITLE));
            }
        //}
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail_fragment, container, false);
        //((TextView)rootView.findViewById(R.id.user_detail)).setText("abc");
        if(getArguments().containsKey(ItemDetailActivity.USER_DETAILS)){
            Log.e(getActivity().getTitle().toString(), getArguments().getString(ItemDetailActivity.USER_DETAILS));
            ((TextView) rootView.findViewById(R.id.item_detail_fragment)).setText(getArguments().getString(ItemDetailActivity.USER_DETAILS));
        }

        return rootView;
    }

    public UserDetailFragment() {
    }
}
