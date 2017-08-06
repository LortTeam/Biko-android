package hu.pe.biko.biko

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class Biko {
    private val bikoApi = Retrofit.Builder()
            .baseUrl("https://bikotest.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(BikoApi::class.java)

    val routes: Flowable<List<Route>>
        @GET("routes")
        get() = bikoApi.routes.subscribeOn(Schedulers.io())

    val places: Flowable<List<Place>>
        @GET("places")
        get() = bikoApi.places.subscribeOn(Schedulers.io())
}
