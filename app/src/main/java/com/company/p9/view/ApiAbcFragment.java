package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.p9.R;
import com.company.p9.model.Item;
import com.company.p9.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ApiAbcFragment extends Fragment {

    private MainViewModel mainViewModel;
    private ItemAdapter itemAdapter;

    public ApiAbcFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_api_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.items);
        recyclerView.setAdapter(itemAdapter = new ItemAdapter());

        mainViewModel.apiItemList.observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                itemAdapter.setList(items);
            }
        });
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
        List<Item> items = new ArrayList<>();

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
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
