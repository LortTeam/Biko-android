package hu.pe.biko.biko;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Galya Sheremetova on 27.05.2017.
 */

public interface BikoApi {
    @GET("routes")
    Flowable<List<Route>> getRoutes();

    @GET("places")
    Flowable<List<Place>> getPlaces();
}
