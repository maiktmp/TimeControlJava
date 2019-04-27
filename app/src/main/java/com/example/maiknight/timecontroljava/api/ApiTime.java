package com.example.maiknight.timecontroljava.api;

import android.util.Log;

import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.api.serializers.BooleanDeserializer;
import com.example.maiknight.timecontroljava.api.serializers.BooleanSerializer;
import com.example.maiknight.timecontroljava.api.serializers.DateDeserializaer;
import com.example.maiknight.timecontroljava.api.serializers.DateSerializer;
import com.example.maiknight.timecontroljava.utils.callbacks.CBSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiTime {
    private static final String TAG = "API_CAFE";
    private static ApiTime instance;
    private TimeInterface apiService;

    public static ApiTime getInstance() {
        if (instance == null) {
            instance = new ApiTime();
        }
        return instance;
    }

    private ApiTime() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Prepare http client
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        // Prepare Gson instance
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, new BooleanSerializer())
                .registerTypeAdapter(Boolean.class, new BooleanDeserializer())
                .registerTypeAdapter(boolean.class, new BooleanSerializer())
                .registerTypeAdapter(boolean.class, new BooleanDeserializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializaer())
                .create();

        // Prepare retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.serverPath)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build();

        apiService = retrofit.create(TimeInterface.class);
    }

    public void getGruoups(long userId, CBSuccess<List<Group>> cb) {
        request(
                "Get groups",
                apiService.getUserGroups(userId),
                cb
        );
    }

    @SuppressWarnings("unchecked")
    private void request(final String operation, Call call, final CBSuccess cb) {
        call.enqueue(new Callback() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    cb.onResponse(true, response.body());
                } else {
                    handleUnsuccessful(operation, cb);
                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call call, Throwable t) {
                handleFailure(operation, t, cb);
            }
        });
    }


    @SuppressWarnings("unchecked")
    private void handleUnsuccessful(String operation, CBSuccess callback) {
        Log.w(TAG, operation + " was unsuccessful");
        callback.onResponse(false, null);
    }

    @SuppressWarnings("unchecked")
    private void handleFailure(String operation, Throwable t,
                               CBSuccess callback) {
        Log.e(TAG, operation + " has failed");
        Log.e(TAG, "Message is: " + t.getMessage());

        callback.onResponse(false, null);
    }

}
