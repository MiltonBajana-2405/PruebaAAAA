package com.example.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.prueba.parteUno.Volumenes;
import java.util.List;

public class VolumenesAdapter extends RecyclerView.Adapter<VolumenesAdapter.ViewHolder> {
    private List<Volumenes> volumenesList;

    public VolumenesAdapter(List<Volumenes> volumenesList) {
        this.volumenesList = volumenesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volumenes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Volumenes volumenes = volumenesList.get(position);
        holder.titleTextView.setText(volumenes.getTitle());
        holder.numberTextView.setText("Volumen " + volumenes.getVolume());
        Glide.with(holder.coverImageView.getContext())
                .load(volumenes.getCover())
                .into(holder.coverImageView);
    }

    @Override
    public int getItemCount() {
        return volumenesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverImageView;
        public TextView titleTextView;
        public TextView numberTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.volume_cover);
            titleTextView = itemView.findViewById(R.id.volume_title);
            numberTextView = itemView.findViewById(R.id.volume_number);
        }
    }
}
