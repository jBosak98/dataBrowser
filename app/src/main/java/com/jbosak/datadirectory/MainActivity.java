package com.jbosak.datadirectory;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<User>();
    private static final int COUNT_OF_USER = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NameListAdapter adapter = new NameListAdapter();
        recyclerView.setAdapter(adapter);

        addingUser();

    }


    private void addingUser() {
        for (int i = 0; i < COUNT_OF_USER; i++) {
            User user = new User(i);
            user.getDetails();
            userList.add(i,user);
        }
    }


    private class NameListAdapter extends RecyclerView.Adapter<NameListViewHolder>{

        @Override
        public NameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_list_content, parent, false);

            return new NameListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final NameListViewHolder holder, final int position) {
            holder.idView.setText(String.valueOf(userList.get(position).getId()+1));
            holder.NameTextView.setText(userList.get(position).getName());
            holder.details = userList.get(position).getDetails();

            holder.view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Context context = view.getContext();


                    //String name = (String) view.getTag();
                    //putString("TITLE", userList.get(position).getName());


                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailActivity.TOOLBAR_TITLE, holder.NameTextView.getText());
                    intent.putExtra(ItemDetailActivity.USER_DETAILS,holder.details);
                    context.startActivity(intent);



                }
            });

        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }

    private class NameListViewHolder extends RecyclerView.ViewHolder{
        public final TextView NameTextView;
        public final TextView idView;
        public final View view;
        public String details;


        public NameListViewHolder(View view) {
            super(view);
            this.view = view;
            NameTextView = (TextView) itemView.findViewById(R.id.content);
            idView = (TextView) itemView.findViewById(R.id.id);
        }
    }
}
