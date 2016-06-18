package com.example.binhbt.rxjavaretrofitdemo.net;


import com.example.binhbt.rxjavaretrofitdemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by binhbt on 6/7/2016.
 */
public class ApiService {
    private static final String BASE_URI = "http://www.android10.org/myapi/";
    private EndPoints mEndPoints;

    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mEndPoints = retrofit.create(EndPoints.class);
    }

    public EndPoints getApi() {

        return mEndPoints;
    }

    public interface EndPoints {
        //http://www.android10.org/myapi/users.json
        @GET("users.json")
        public Call<List<User>>
        userEntityList();

        //http://www.android10.org/myapi/user_1.json
        @GET("user_{userId}.json")
        public Call<User>
        userEntityById(@Path("userId") int userId);

        /*
        //GET for url http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=2de143494c0b295cca9337e1e96b00e0
        @GET("data/2.5/weather")
        Call<Model> getWheatherReport(@Query("q") String query, @Query("appid") String appid);


        @GET("me/users")
        public Call<List<UserEntity>>
        userEntityList(@Header("X-AUTH") String token);

        @GET("me/{userId}")
        public Call<UserEntity>
        userEntityById(@Path("userId") int userId);

        @GET("me/chatSessions")
        public Call<List<ChatSessionEntity>>
        sessionEntityList(@Header("X-AUTH") String token);

        @FormUrlEncoded
        @POST("signup")
        public Call<UserEntity>
        askForSignup(@Field("name") String userName,
                     @Field("email") String userEmail,
                     @Field("password") String userPassword);
        @FormUrlEncoded
        @POST("signin")
        public Call<UserEntity>
        askForSignIn(@Field("name") String userName,
                     @Field("password") String userPassword);

        //@FormUrlEncoded
        @POST("signout")
        public Call<MessageEntity> askForLogout(@Header("X-AUTH") String token);

        @FormUrlEncoded
        @POST("cattle/add")
        public Call<CattleEntity>
        newCattle(@Header("X-AUTH") String token,
                  @Field("name") String userName,
                  @Field("description") String des,
                  @Field("blood") String blood,
                  @Field("weight") int weight,
                  @Field("month_old") int monthOld,
                  @Field("cost") int cost,
                  @Field("buy_date") String date,
                  @Field("user_id") int userId);

        @FormUrlEncoded
        @POST("cost/add")
        public Call<CostEntity>
        newCost(@Header("X-AUTH") String token,
                @Field("name") String name,
                @Field("description") String des,
                @Field("cost") int cost,
                @Field("date") String date,
                @Field("user_id") int userId);

        @FormUrlEncoded
        @POST("event/adds")
        public Call<EventEntity>
        newEvent(@Header("X-AUTH") String token,
                @Field("name") String name,
                @Field("description") String des,
                @Field("cost") int cost,
                @Field("date") String date,
                @Field("user_id") int userId,
                @Field("cattle_ids") String cattles);

        @FormUrlEncoded
        @POST("weight/add")
        public Call<WeightEntity>
        newWeight(@Header("X-AUTH") String token,
                 @Field("name") String name,
                 @Field("description") String des,
                 @Field("weight") int weight,
                 @Field("date") String date,
                 @Field("user_id") int userId,
                 @Field("cattle_id") int cattleId);

        @GET("me/cattles")
        public Call<List<CattleEntity>>
        cattleEntityList(@Header("X-AUTH") String token);
         */
    }
}
