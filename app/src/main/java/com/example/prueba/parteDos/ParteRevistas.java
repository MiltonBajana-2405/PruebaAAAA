package com.example.prueba.parteDos;

import com.example.prueba.parteUno.Revistas;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ParteRevistas {
    @GET("ws/journals.php")
    public Call<List<Revistas>> getJournals();
}
