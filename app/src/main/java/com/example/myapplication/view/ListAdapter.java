package com.example.myapplication.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Makeup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Makeup> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            icon = v.findViewById(R.id.icon);
        }
    }

    public ListAdapter(List<Makeup> myDataset) {
        values = myDataset;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Makeup currentMakeup = values.get(position);
        holder.txtHeader.setText(currentMakeup.getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("name", currentMakeup.getName());
                intent.putExtra("price", currentMakeup.getPrice());
                intent.putExtra("brand", currentMakeup.getBrand());
                intent.putExtra("color", currentMakeup.getColor());
                intent.putExtra("imgURL", currentMakeup.getImgURL());
                v.getContext().startActivity(intent);
            }
        });
        holder.txtFooter.setText(currentMakeup.getPrice());
        Picasso.get().load(currentMakeup.getImgURL()).into(holder.icon);
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

}