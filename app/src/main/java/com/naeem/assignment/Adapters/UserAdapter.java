package com.naeem.assignment.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naeem.assignment.Interfaces.ItemClick;
import com.naeem.assignment.Models.User;
import com.naeem.assignment.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ShimmerViewHolder> {
    private List<User> userList;
    private Context context;

    public ItemClick getItemClick() {
        return itemClick;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    ItemClick itemClick;


    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShimmerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        ShimmerViewHolder shimmerViewHolder = new ShimmerViewHolder(view);
        return shimmerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShimmerViewHolder shimmerViewHolder, final int i) {
        User data = userList.get(i);
        shimmerViewHolder.textView.setText(data.getOrganizationsUrl());
        shimmerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onclick(null, i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ShimmerViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ShimmerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

    public void setFilter(List<User> newList) {
        userList = new ArrayList<>();
        userList.addAll(newList);
        notifyDataSetChanged();
    }
}
