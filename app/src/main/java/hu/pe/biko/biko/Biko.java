package hu.pe.biko.biko;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class Biko {
    private BikoApi bikoApi = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(BikoApi.class);

    @GET("routes")
    Flowable<List<Route>> getRoutes() {
        return bikoApi.getRoutes().subscribeOn(Schedulers.io());
    }

    @GET("places")
    Flowable<List<Place>> getPlaces() {
        return bikoApi.getPlaces().subscribeOn(Schedulers.io());
    }
}
