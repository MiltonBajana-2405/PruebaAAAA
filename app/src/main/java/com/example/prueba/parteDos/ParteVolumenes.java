package com.example.prueba.parteDos;

import com.example.prueba.parteUno.Volumenes;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParteVolumenes {
    @GET("ws/issues.php")
    Call<List<Volumenes>> getVolumes(@Query("j_id") String journalId);
}
