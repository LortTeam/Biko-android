package hu.pe.biko.biko;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKShareDialogBuilder;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CongratsActivity extends AppCompatActivity {
    Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        Button buttonShare = (Button) findViewById(R.id.buttonShare);
        buttonOk.setOnClickListener(v -> finish());
        buttonShare.setOnClickListener(v -> VKSdk.login(this, VKScope.WALL, VKScope.PHOTOS));
        route = getIntent().getParcelableExtra("route");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Maybe.create((MaybeEmitter<Bitmap> onSubscribe) -> onSubscribe.onSuccess(
                        Glide.with(CongratsActivity.this)
                                .load(route.getImage())
                                .asBitmap()
                                .into(-1, -1)
                                .get()))
                        .map(bitmap -> new VKUploadImage[]{
                                new VKUploadImage(bitmap, VKImageParameters.pngImage())})
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(vkUploadImages -> new VKShareDialogBuilder()
                                .setText("I just finished the route " + route.getName() + "!")
                                .setAttachmentImages(vkUploadImages)
                                .setShareDialogListener(new VKShareDialogBuilder.VKShareDialogListener() {
                                    @Override
                                    public void onVkShareComplete(int postId) {
                                        Log.i("tag", "onVkShareComplete");
                                        finish();
                                    }

                                    @Override
                                    public void onVkShareCancel() {
                                        Log.i("tag", "onVkShareCancel");
                                        finish();
                                    }

                                    @Override
                                    public void onVkShareError(VKError error) {
                                        Log.i("tag", "onVkShareError");
                                        finish();
                                    }
                                }).show(getSupportFragmentManager(), "tag"), Throwable::printStackTrace);
            }

            @Override
            public void onError(VKError error) {

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
