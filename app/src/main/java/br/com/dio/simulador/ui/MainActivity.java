package br.com.dio.simulador.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import br.com.dio.simulador.data.MatchesAPI;
import br.com.dio.simulador.databinding.ActivityMainBinding;
import br.com.dio.simulador.domain.Match;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesAPI matchesApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton();
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://avitoriasa.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(MatchesAPI.class);
    }

    private void setupMatchesList(){
            matchesApi.getMatches().enqueue(new Callback<List<Math>>() {
                @Override
                public void onResponse(Call<List<Math>> call, Response<List<Math>> response) {
                    if (response.isSuccessful()){
                        List<Math> matches = response.body();
                        Log.i("SIMULATOR", "Deu tudo certo! Partidas =" + matches.size());
                    } else {
                        showErrorMessage();
                    }
                }

                @Override
                public void onFailure(Call<List<Math>> call, Throwable t) {
                    showErrorMessage();
                }
            });
    }


    private void setupMatchesRefresh(){
        //TODO Listar partidas ao atualizar
    }
    private void setupFloatingActionButton(){
        //TODO Evento de click
    }

    private void showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show();
    }
}
