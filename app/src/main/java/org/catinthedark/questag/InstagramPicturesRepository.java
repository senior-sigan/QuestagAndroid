package org.catinthedark.questag;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InstagramPicturesRepository implements PicturesRepository {
    private static final String CLIENT_ID = "417c3ee8c9544530b83aa1c24de2abb3";
    private static final String apiUrl = "https://api.instagram.com/v1/tags/%s/media/recent?client_id=%s&count=100";
    private final Context context;
    private final Random random = new Random();

    public InstagramPicturesRepository(final Context context) {
        this.context = context;
    }

    @Override
    public void getUrlByTag(final String tagName, final OnLoaded callback) {
        final String uri = String.format(apiUrl, tagName, CLIENT_ID);
        Ion.with(context)
                .load(uri)
                .as(new TypeToken<InstagramJson>() {
                })
                .setCallback(new FutureCallback<InstagramJson>() {
                    @Override
                    public void onCompleted(final Exception e, final InstagramJson result) {
                        if (e == null) {
                            if (result.data.length == 0) {
                                callback.run(null);
                            }
                            final List<InstagramJson.Media> media = new ArrayList<InstagramJson.Media>(Arrays.asList(result.data));
                            Collections.shuffle(media);
                            final List<InstagramJson.Image> imagesList = new ArrayList<>(4);
                            imagesList.add(media.get(0).images);
                            imagesList.add(media.get(1).images);
                            imagesList.add(media.get(2).images);
                            imagesList.add(media.get(3).images);

                            final List<ImageModel> imageModelList = new ArrayList<>(4);
                            for (InstagramJson.Image image: imagesList) {
                                imageModelList.add(new ImageModel(image.low_resolution.url, image.standard_resolution.url));
                            }

                            callback.run(imageModelList);
                        } else {
                            Log.e("QUESTAG", e.getLocalizedMessage());
                            callback.run(null);
                        }
                    }
                });
    }
}
