package com.apppartner.androidtest.animation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.apppartner.androidtest.R;

public class AnimationActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView image;
    private float x, y = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(this.getResources().getString(R.string.animation));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        image = (ImageView) findViewById(R.id.imageView);
        image.setOnTouchListener(this);
    }

    public void onFadeClicked(View view) {
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.animation_alpha);
        image.startAnimation(myFadeInAnimation);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                x = event.getRawX() - image.getWidth();
                if (x < 0f) {
                    x = 0f;
                }
                image.setX(x);

                y = event.getRawY() - image.getHeight();
                if (y < 0f) {
                    y = 0f;
                }
                image.setY(y);
                break;

            default:
                break;
        }
        return true;
    }
}