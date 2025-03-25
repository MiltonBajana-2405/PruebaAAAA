package com.example.prueba;

import android.os.Bundle;
import android.util.Log;
import com.example.prueba.parteUno.Revistas;
import com.example.prueba.parteDos.ParteRevistas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RevistasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Muestra en vertical
        fetchJournals();
    }

    private void fetchJournals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ParteRevistas service = retrofit.create(ParteRevistas.class);
        Call<List<Revistas>> call = service.getJournals();

        call.enqueue(new Callback<List<Revistas>>() {
            @Override
            public void onResponse(Call<List<Revistas>> call, Response<List<Revistas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Revistas> revistasList = response.body();
                    adapter = new RevistasAdapter(revistasList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("MainActivity", "No respondio correctamente");
                }
            }

            @Override
            public void onFailure(Call<List<Revistas>> call, Throwable t) {
                Log.e("MainActivity", "Error al mostrar las revistas", t);
            }
        });
    }
}