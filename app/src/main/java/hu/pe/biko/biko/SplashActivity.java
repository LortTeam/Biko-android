package hu.pe.biko.biko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vk.sdk.util.VKUtil;

import hu.pe.biko.biko.Main.MainActivity;

/**
 * Created by nikita on 28.05.17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        Log.i("finger", fingerprints[0]);
    }
}
