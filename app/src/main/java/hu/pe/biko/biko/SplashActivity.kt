package hu.pe.biko.biko

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.vk.sdk.util.VKUtil

import hu.pe.biko.biko.Main.MainActivity

/**
 * Created by nikita on 28.05.17.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        val fingerprints = VKUtil.getCertificateFingerprint(this, this.packageName)
        Log.i("finger", fingerprints[0])
    }
}
