package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.p9.R;
import com.company.p9.model.Item;
import com.company.p9.viewmodel.DatabaseViewModel;
import com.company.p9.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


public class DatabaseAbcFragment extends Fragment {

    SearchViewModel searchViewModel;
    DatabaseViewModel databaseViewModel;
    ItemAdapter itemAdapter;

    public DatabaseAbcFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_database_abc, container, false);

        searchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);
        databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        RecyclerView recyclerView = fragment.findViewById(R.id.items);
        itemAdapter = new ItemAdapter(getContext());
        recyclerView.setAdapter(itemAdapter);

        databaseViewModel.getItems("").observe(DatabaseAbcFragment.this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                itemAdapter.setList(items);
            }
        });

        searchViewModel.term.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                databaseViewModel.getItems(s).observe(DatabaseAbcFragment.this, new Observer<List<Item>>() {
                    @Override
                    public void onChanged(List<Item> items) {
                        itemAdapter.setList(items);
                    }
                });
            }
        });

        return fragment;
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
        List<Item> items = new ArrayList<>();
        Context mContext;

        public ItemAdapter(Context context){
            mContext = context;
        }

        @NonNull
        @Override
        public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemAdapter.ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
            Item item = items.get(position);

            holder.name.setText(item.name);
            holder.date.setText(item.date);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setList(List<Item> newList){
            items = newList;
            notifyDataSetChanged();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            TextView name, date;
            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.item_name);
                date = itemView.findViewById(R.id.item_date);
            }
        }
    }
}
