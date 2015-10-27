package com.bgreeves.spoopysparyspeletons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.InputStream;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
{
    private final int MIN_SIZE_OF_DISAPPEARING_BUTTON = 10;
    private final int ANIMATION_TIME_MILLIS = 1000;

    SoundPlayer spoopySparyPlayer;
    boolean canSpoopYet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spoopySparyPlayer = new SoundPlayer(this, R.raw.spoopy_spary_speletons, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (spoopySparyPlayer.isPlaying())
        {
            spoopySparyPlayer.stop();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {
        canSpoopYet = true;
    }

    public void onSpoopySparyButtonPressed(View v)
    {
        spoopTheShitOutOfMePls();
        spoopyFadeAway();
    }

    public void spoopTheShitOutOfMePls()
    {
        if (canSpoopYet)
        {
            if (spoopySparyPlayer.isPlaying())
            {
                spoopySparyPlayer.stop();
            }
            spoopySparyPlayer.start();
            spoopySparyPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    GifImageView spoopySparySpeleton = (GifImageView)findViewById(R.id.spoopy_spary_speletons);
                    spoopySparySpeleton.setVisibility(View.INVISIBLE);
                    ImageButton spoopySparyButton = (ImageButton)findViewById(R.id.spoopy_spary_bone);
                    spoopySparyButton.setScaleX(1.0f);
                    spoopySparyButton.setScaleY(1.0f);
                    spoopySparyButton.setRotation(0.0f);
                    spoopySparyButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void spoopyFadeAway()
    {
        final ImageButton spoopySparyButton = (ImageButton)findViewById(R.id.spoopy_spary_bone);
        final Animation spoopySparyAnimation = AnimationUtils.loadAnimation(this, R.anim.spoopy_spary_animation);
        spoopySparyAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                spoopySparyButton.setVisibility(View.INVISIBLE);
                GifImageView spoopySparySpeleton = (GifImageView)findViewById(R.id.spoopy_spary_speletons);
                spoopySparySpeleton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        spoopySparyButton.startAnimation(spoopySparyAnimation);
    }
}
