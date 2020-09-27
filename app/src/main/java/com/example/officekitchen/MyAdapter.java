package com.example.officekitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewComp.setText(listItem.getCompany());
        holder.textViewLoc.setText(listItem.getLocation());
        holder.textViewPrice.setText(listItem.getPrice());
        holder.textViewAlert.setText(listItem.getAlert());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewComp;
        public TextView textViewLoc;
        public TextView textViewPrice;
        public TextView textViewAlert;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewComp = (TextView) itemView.findViewById(R.id.companyName);
            textViewLoc = (TextView) itemView.findViewById(R.id.companyLocation);
            textViewPrice = (TextView) itemView.findViewById(R.id.companyPrice);
            textViewAlert = (TextView) itemView.findViewById(R.id.alert);

        }
    }
}
