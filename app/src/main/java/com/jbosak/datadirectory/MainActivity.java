package com.jbosak.datadirectory;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    public static final String STATE_USERS = "STATE_USERS";
    private static final int COUNT_OF_USER = 20;
    public ArrayList<User> userList = new ArrayList<>();
    private NameListAdapter adapter = new NameListAdapter();
    private RecyclerView recyclerView;
    private String[] PERMISSIONS = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE};// List of permissions required
    private static final int PERMISSION_ALL = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askPermission();

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            userList = savedInstanceState.getParcelableArrayList(STATE_USERS);

        } else if (savedInstanceState == null) {
            if(isNetworkAvailable())
            addingUser();
        }

    }

    private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;

    }

    private void addingUser() {
        for (int i = 0; i < COUNT_OF_USER; i++) {
            User user = new User(i);
            user.getDetails();
            userList.add(i, user);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(STATE_USERS, userList);
    }


    private class NameListAdapter extends RecyclerView.Adapter<NameListViewHolder> {


        @Override
        public NameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.user_list_content, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    String name = (String) view.getTag();


                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailActivity.TOOLBAR_TITLE, name);
                    intent.putExtra(ItemDetailActivity.USER_DETAILS, userList.get(view.getId()).getDetails());
                    intent.putExtra(ItemDetailActivity.USER_ID, view.getId());

                    context.startActivity(intent);

                }
            });


            return new NameListViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final NameListViewHolder holder, int position) {
            holder.idTextView.setText(Integer.toString(position + 1));

            holder.NameTextView.setText(userList.get(position).getName());
            holder.itemView.setTag(userList.get(position).getName());
            holder.itemView.setId(userList.get(position).getId());
            holder.details = userList.get(position).getDetails();

        }


        @Override
        public int getItemCount() {
            return userList.size();
        }

    }



    private class NameListViewHolder extends RecyclerView.ViewHolder {
        public final TextView NameTextView;
        public TextView idTextView;
        public final View view;
        public String details;
        public long id;
        public boolean isFavourite;


        public NameListViewHolder(View view) {
            super(view);
            this.view = view;
            NameTextView = (TextView) itemView.findViewById(R.id.user_list_content_name);
            isFavourite = false;
            idTextView = (TextView) itemView.findViewById(R.id.user_list_content_id);


        }


    }
    public void askPermission() {
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PERMISSIONS, PERMISSION_ALL);
                    return;
                }
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_ALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Until you grant the permission, we cannot proceed further", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
