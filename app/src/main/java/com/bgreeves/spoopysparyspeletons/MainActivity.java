package com.bgreeves.spoopysparyspeletons;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

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
        spoopyFadeAway();
        spoopTheShitOutOfMePls();
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
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        spoopySparyButton.startAnimation(spoopySparyAnimation);
    }
}
