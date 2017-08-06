package hu.pe.biko.biko

import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Galya Sheremetova on 27.05.2017.
 */

interface BikoApi {
    @get:GET("routes")
    val routes: Flowable<List<Route>>

    @get:GET("places")
    val places: Flowable<List<Place>>
}
