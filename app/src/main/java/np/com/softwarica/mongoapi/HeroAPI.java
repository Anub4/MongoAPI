package np.com.softwarica.mongoapi;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HeroAPI {
    @POST("heroes")
    Call<Void> addHero(@Body Hero hero);

    @GET("heroes")
    Call<ArrayList<Hero>> getHeroes();

    @FormUrlEncoded
    @POST("heroes")
    Call<Void>addHero(@Field("name") String name,@Field("desc")String desc);

    @FormUrlEncoded
    @POST("heroes")
    Call<Void>addHero(@FieldMap Map<String,String> map);

    @Multipart
    @POST("upload")
    Call<ImageResponse>uploadImage(@Part MultipartBody.Part img);
}
