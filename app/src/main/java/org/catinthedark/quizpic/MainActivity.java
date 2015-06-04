package org.catinthedark.quizpic;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "QuizPic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getBaseContext());
        setContentView(R.layout.activity_main);
        final Uri uri = Uri.parse("https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/s320x320/e15/11419025_956716491015049_514263880_n.jpg");
        final SimpleDraweeView draweeView1 = (SimpleDraweeView)findViewById(R.id.imageView1);
        final SimpleDraweeView draweeView2 = (SimpleDraweeView)findViewById(R.id.imageView2);
        final SimpleDraweeView draweeView3 = (SimpleDraweeView)findViewById(R.id.imageView3);
        final SimpleDraweeView draweeView4 = (SimpleDraweeView)findViewById(R.id.imageView4);
        draweeView1.setImageURI(uri);
        draweeView2.setImageURI(uri);
        draweeView3.setImageURI(uri);
        draweeView4.setImageURI(uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tagSelected(final View view) {
        Log.d(TAG, "clicked");
    }
}
