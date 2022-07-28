package br.com.dio.simulador.data;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesAPI {

    @GET("matches.json")
    Call<List<Math>> getMatches();
}
