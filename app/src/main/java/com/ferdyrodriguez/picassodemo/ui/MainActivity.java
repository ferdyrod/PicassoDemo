package com.ferdyrodriguez.picassodemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ferdyrodriguez.picassodemo.R;
import com.ferdyrodriguez.picassodemo.data.DataAdapter;
import com.ferdyrodriguez.picassodemo.model.Character;
import com.ferdyrodriguez.picassodemo.model.Characters;
import com.ferdyrodriguez.picassodemo.model.MarvelResponse;
import com.ferdyrodriguez.picassodemo.service.CharacterService;
import com.ferdyrodriguez.picassodemo.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<MarvelResponse<Characters>> {

    private static final String TAG = "PicassoDemo";
    private ArrayList<Character> characterData;
    private DataAdapter adapter;
    private RecyclerView rv;
    private static final int MARVEL_EVENT = 310;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request.Builder requestBuilder = originalRequest.newBuilder();
                        HttpUrl url = originalRequest.url()
                                .newBuilder()
                                .addQueryParameter("ts", Utils.getTimestamp())
                                .addQueryParameter("apikey", Utils.getApiKey())
                                .addQueryParameter("hash", Utils.getKeyHash())
                                .build();
                        Request request = requestBuilder.url(url).build();
                        Log.d(TAG, "intercept: request "+ request.toString());
                        return chain.proceed(request);
                    }
                }).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_MARVEL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        CharacterService characterService = retrofit.create(CharacterService.class);
        Call<MarvelResponse<Characters>> call = characterService.getCharacters(MARVEL_EVENT);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<MarvelResponse<Characters>> call, Response<MarvelResponse<Characters>> response) {

        if (response.isSuccessful()) {
            MarvelResponse<Characters> resp = response.body();
            Log.d(TAG, "onResponse: resp " + resp.getResponse().getAllCharacters().toString());
            characterData = new ArrayList<>(resp.getResponse().getAllCharacters());
            Log.d(TAG, "onResponse: " + characterData.size());
            adapter = new DataAdapter(getApplicationContext(), characterData);
            rv.setAdapter(adapter);
        } else {
            try {
                Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<MarvelResponse<Characters>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
    }
}
