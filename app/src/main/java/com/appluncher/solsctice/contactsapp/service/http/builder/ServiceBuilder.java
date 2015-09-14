package com.appluncher.solsctice.contactsapp.service.http.builder;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by leandro on 12/09/2015.
 */
public final class ServiceBuilder {

    private static final String BASE_ENDPOINT = "https://solstice.applauncher.com";

    public static <T> T createService(Class<T> clazz ){
        RestAdapter.Builder retrofit = new RestAdapter.Builder()
                //.addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(Gs)
                .setEndpoint(BASE_ENDPOINT)
                .setClient(new OkClient(new OkHttpClient()));

        RestAdapter adapter = retrofit.build();;

        return adapter.create(clazz);
    }
}
