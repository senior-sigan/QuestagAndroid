package org.catinthedark.questag;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RemoteTagRepository implements TagRepository {
    private static final String apiUrl = "http://blan4.github.io/questag/api/tags.json";
    private final Context context;

    public RemoteTagRepository(final Context context) {
        this.context = context;
    }

    @Override
    public void getTagCollection(final OnLoaded callback) {
        Ion.with(context).load(apiUrl).as(new TypeToken<TagJson>(){}).setCallback(new FutureCallback<TagJson>() {
            @Override
            public void onCompleted(final Exception e, final TagJson result) {
                if (e == null) {
                    List<Tag> tags = new ArrayList<Tag>();
                    for(String tagName: result.data.tags) {
                        tags.add(new Tag(tagName));
                    }

                    callback.run(new TagCollection(tags));
                } else {
                    Log.e("QUESTAG", e.getLocalizedMessage());
                    callback.run(null);
                }
            }
        });
    }
}
