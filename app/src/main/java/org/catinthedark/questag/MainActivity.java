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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;


public class MainActivity extends AppCompatActivity {

    final static String TAG = "QUESTAG";

    @InjectViews({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    List<Button> buttons;
    @InjectViews({R.id.imageView1, R.id.imageView2, R.id.imageView3, R.id.imageView4})
    List<ImageView> imageViews;

    @InjectView(R.id.imageViewBig)
    ImageView bigView;
    @InjectView(R.id.imagesLayout)
    LinearLayout layout;

    final List<ButtonController> buttonControllers = new ArrayList<>(4);
    final List<ImageViewController> imageViewControllers = new ArrayList<>(4);

    QuestionFlow questionFlow;
    PicturesRepository picturesRepository;

    class ButtonListener implements View.OnClickListener {
        private final ButtonController controller;
        private final QuestionFlow flow;

        public ButtonListener(final ButtonController controller, final QuestionFlow flow) {
            this.controller = controller;
            this.flow = flow;
        }

        @Override
        public void onClick(final View v) {
            if (controller.getModel().equals(flow.current().getAnswer())) {
                showVariants();
            } else {
                Toast.makeText(v.getContext(), "Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ThumbImageListener implements View.OnClickListener {
        final ImageViewController data;

        public ThumbImageListener(final ImageViewController data) {
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            if (data.getModel() != null && data.getView() != null) {
                zoomImageFromThumb(data.getView(), data.getModel());
            } else {
                Log.e(TAG, "ImageModel and ImageView are null");
            }
        }

        private void zoomImageFromThumb(final ImageView imageView, final ImageModel model) {
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
                    bigView.setImageResource(0);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        picturesRepository = new InstagramPicturesRepository(getApplicationContext());
        questionFlow = new QuestionFlow();

        for(Button view: buttons) {
            ButtonController controller = new ButtonController(view);
            controller.setOnClickListener(new ButtonListener(controller, questionFlow));
            buttonControllers.add(controller);
        }

        for (ImageView view: imageViews) {
            ImageViewController controller = new ImageViewController(view);
            controller.setOnClickListener(new ThumbImageListener(controller));
            imageViewControllers.add(controller);
        }

        showVariants();
    }

    private void showVariants() {
        for (ImageViewController controller: imageViewControllers) {
            controller.clearView();
        }

        if (!questionFlow.hasNext()) {
            Toast.makeText(getApplicationContext(), "Game over. Reset", Toast.LENGTH_SHORT).show();
            questionFlow.reset();
        }
        Question question = questionFlow.next();

        picturesRepository.getUrlByTag(question.getAnswer().getName(), new PicturesRepository.OnLoaded() {
            @Override
            public void run(final List<ImageModel> models) {
                for (int i = 0; i < imageViewControllers.size(); ++i) {
                    imageViewControllers.get(i).setModel(models.get(i));
                }
            }
        });

        for (int i = 0; i < buttonControllers.size(); ++i) {
            Tag tag = question.getVariants().get(i);
            buttonControllers.get(i).setModel(tag);
        }
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
