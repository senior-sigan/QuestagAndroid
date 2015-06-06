package org.catinthedark.questag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "QuizPic";
    QuestionFlow questionFlow;
    final List<Button> buttons = new ArrayList<>(4);

    private class ThumbImageListener implements View.OnClickListener {
        final ImageView imageView;
        final ImageModel imageModel;

        public ThumbImageListener(final ImageView imageView, final ImageModel imageModel) {
            this.imageView = imageView;
            this.imageModel = imageModel;
        }

        @Override
        public void onClick(View v) {
            zoomImageFromThumb(imageView, imageModel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionFlow = new QuestionFlow();
        buttons.add((Button)findViewById(R.id.button1));
        buttons.add((Button)findViewById(R.id.button2));
        buttons.add((Button)findViewById(R.id.button3));
        buttons.add((Button)findViewById(R.id.button4));

        for (Button button: buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showVariants();
                }
            });
        }

        showVariants();

        final List<ImageModel> images = new ArrayList<>();
        images.add(new ImageModel("https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/s320x320/e15/11251082_996206120391754_220197680_n.jpg", "https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/e15/11251082_996206120391754_220197680_n.jpg"));
        images.add(new ImageModel("https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/s320x320/e15/11258207_939063396145061_878676856_n.jpg", "https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/e15/11258207_939063396145061_878676856_n.jpg"));
        images.add(new ImageModel("https://scontent.cdninstagram.com/hphotos-xap1/t51.2885-15/s320x320/e15/11190001_630711613696116_1765183538_n.jpg", "https://scontent.cdninstagram.com/hphotos-xap1/t51.2885-15/e15/11190001_630711613696116_1765183538_n.jpg"));
        images.add(new ImageModel("https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/s320x320/e15/11375897_1598078640459760_491696510_n.jpg", "https://scontent.cdninstagram.com/hphotos-xfa1/t51.2885-15/e15/11375897_1598078640459760_491696510_n.jpg"));

        final ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        final ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        final ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        final ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        Ion.with(imageView1).load(images.get(0).thumbnailUrl);
        Ion.with(imageView2).load(images.get(1).thumbnailUrl);
        Ion.with(imageView3).load(images.get(2).thumbnailUrl);
        Ion.with(imageView4).load(images.get(3).thumbnailUrl);

        imageView1.setOnClickListener(new ThumbImageListener(imageView1, images.get(0)));
        imageView2.setOnClickListener(new ThumbImageListener(imageView2, images.get(1)));
        imageView3.setOnClickListener(new ThumbImageListener(imageView3, images.get(2)));
        imageView4.setOnClickListener(new ThumbImageListener(imageView4, images.get(3)));
    }

    private void showVariants() {
        Question question = questionFlow.next();
        for (int i = 0; i < buttons.size(); ++i) {
            if (!questionFlow.hasNext()) {
                Toast.makeText(getApplicationContext(), "Game over. Reset", Toast.LENGTH_SHORT).show();
                questionFlow.reset();
            }
            Tag tag = question.getVariants().get(i);
            buttons.get(i).setText(tag.getName());
            //Log.d(TAG, question.getAnswer().getName());
        }
    }

    private void zoomImageFromThumb(final ImageView imageView, final ImageModel model) {
        final ImageView bigView = (ImageView)findViewById(R.id.imageViewBig);
        final LinearLayout layout = (LinearLayout)findViewById(R.id.imagesLayout);
        bigView.setImageDrawable(imageView.getDrawable());
        Ion.with(bigView).load(model.highResolutionUrl);
        bigView.setPivotX(0f);
        bigView.setPivotY(0f);
        bigView.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);

        bigView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigView.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
                bigView.setImageDrawable(null);
            }
        });
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
