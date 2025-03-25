package com.example.prueba;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prueba.parteUno.Volumenes;
import com.example.prueba.parteDos.ParteVolumenes;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VolumenesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolumenesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumenes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String journalId = getIntent().getStringExtra("journal_id");
        fetchVolumes(journalId);
    }

    private void fetchVolumes(String journalId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ParteVolumenes service = retrofit.create(ParteVolumenes.class);
        Call<List<Volumenes>> call = service.getVolumes(journalId);

        call.enqueue(new Callback<List<Volumenes>>() {
            @Override
            public void onResponse(Call<List<Volumenes>> call, Response<List<Volumenes>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Volumenes> volumenesList = response.body();
                    adapter = new VolumenesAdapter(volumenesList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("VolumenesActivity", "No respondio correctamente");
                }
            }

            @Override
            public void onFailure(Call<List<Volumenes>> call, Throwable t) {
                Log.e("VolumenesActivity", "Error al mostrar los volumenes", t);
            }
        });
    }
}