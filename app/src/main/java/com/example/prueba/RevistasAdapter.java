package com.example.prueba;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.prueba.parteUno.Revistas;
import java.util.List;

public class RevistasAdapter extends RecyclerView.Adapter<RevistasAdapter.ViewHolder> {

    private List<Revistas> revistasList;

    public RevistasAdapter(List<Revistas> revistasList) {
        this.revistasList = revistasList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_revistas, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Revistas revistas = revistasList.get(position);
        holder.textView.setText(revistas.getName());

        // Limpia etiquetas HTML de la descripción
        String cleanDescription = revistas.getDescription().replaceAll("<[^>]*>", "");
        holder.descriptionView.setText(cleanDescription);

        Glide.with(holder.imageView.getContext())
                .load(revistas.getPortada())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VolumenesActivity.class);
            intent.putExtra("journal_id", revistas.getJournal_id());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return revistasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView descriptionView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_text);
            descriptionView = itemView.findViewById(R.id.item_description);
        }
    }
}