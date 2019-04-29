package com.example.maiknight.timecontroljava.api;

import com.example.maiknight.timecontroljava.Database.entities.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TimeInterface {
    @GET(APIConstants.wsPath + "user/{userId}/groups")
    Call<List<Group>> getUserGroups(@Path("userId") long userId);

    @GET(APIConstants.wsPath + "user/{userId}/groups/current")
    Call<Group> getCurrentGroup(@Path("userId") long userId);

}
