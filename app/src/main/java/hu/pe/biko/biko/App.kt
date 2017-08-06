package hu.pe.biko.biko

import android.support.multidex.MultiDexApplication

import com.vk.sdk.VKSdk

/**
 * Created by Galya Sheremetova on 27.05.2017.
 */

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(this)
    }
}
