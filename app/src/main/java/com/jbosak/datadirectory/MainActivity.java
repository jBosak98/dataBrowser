package com.jbosak.datadirectory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataFactory df = new DataFactory();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NameListAdapter adapter = new NameListAdapter();
        recyclerView.setAdapter(adapter);

        for(int i = 0; i < 200; i++){
            adapter.addName(df.getName());
        }

    }

    private class NameListAdapter extends RecyclerView.Adapter<NameListViewHolder>{
        private final ArrayList<String> names;

        public NameListAdapter() {
            names = new ArrayList<>();
        }

        public void addName(String name){
            names.add(name);
            notifyItemInserted(names.size() - 1);
        }

        public void removeName(String name){
            int position = names.indexOf(name);
            if(position == -1){
                return;
            }
            names.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,names.size());

        }

        @Override
        public NameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_list_content, parent, false);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String name = (String) view.getTag();
                    removeName(name);
                }
            });
            return new NameListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NameListViewHolder holder, int position) {
            String name = names.get(position);
            holder.idView.setText(Integer.toString(position + 1));
            holder.NameTextView.setText(name);
            holder.itemView.setTag(name);

        }

        @Override
        public int getItemCount() {
            return names.size();
        }
    }

    private class NameListViewHolder extends RecyclerView.ViewHolder{
        public final TextView NameTextView;
        public final TextView idView;



        public NameListViewHolder(View itemView) {
            super(itemView);
            NameTextView = (TextView) itemView.findViewById(R.id.content);
            idView = (TextView) itemView.findViewById(R.id.id);
        }
    }
}
