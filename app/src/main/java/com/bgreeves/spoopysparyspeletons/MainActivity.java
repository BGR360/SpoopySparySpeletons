package com.bgreeves.spoopysparyspeletons;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
{
    private final int MIN_SIZE_OF_DISAPPEARING_BUTTON = 10;
    private final int ANIMATION_TIME_MILLIS = 1000;

    SoundPlayer spoopySparyPlayer;
    boolean canSpoopYet = false;

    // All the GIFs
    GifImageView spoopySparySpeleton, spoopySparyCult, spoopySparyShortAndTall;
    GifImageView spoopySparyDancer, spoopySparyPogo, spoopySparyIntensifies, spoopySparyCrazyShakinSkull;
    GifImageView spoopySparyPumpkin, spoopySparySwallow, /* spoopySparyCrazyShakinSkull */ spoopySparySpeletor;
    GifImageView spoopySparySaunter;
    GifImageView spoopySparyRave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spoopySparyPlayer = new SoundPlayer(this, R.raw.spoopy_spary_speletons, this);

        // Get all the GIFs
        spoopySparySpeleton = (GifImageView)findViewById(R.id.spoopy_spary_speletons);
        spoopySparyCult = (GifImageView)findViewById(R.id.spoopy_spary_cult);
        spoopySparyShortAndTall = (GifImageView)findViewById(R.id.spoopy_spary_short_and_tall);
        spoopySparyDancer = (GifImageView)findViewById(R.id.spoopy_spary_dancer);
        spoopySparyPogo = (GifImageView)findViewById(R.id.spoopy_spary_pogo);
        spoopySparyIntensifies = (GifImageView)findViewById(R.id.spoopy_spary_intensifies);
        spoopySparyCrazyShakinSkull = (GifImageView)findViewById(R.id.spoopy_spary_crazy_shakin_skull);
        spoopySparyPumpkin = (GifImageView)findViewById(R.id.spoopy_spary_pumpkin);
        spoopySparySwallow = (GifImageView)findViewById(R.id.spoopy_spary_swallow);
        spoopySparySpeletor = (GifImageView)findViewById(R.id.spoopy_spary_speletor);
        spoopySparySaunter = (GifImageView)findViewById(R.id.spoopy_spary_sauntering);
        spoopySparyRave = (GifImageView)findViewById(R.id.spoopy_spary_rave);
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
        beginSpoopyAnimation();
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
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        spoopySparyButton.startAnimation(spoopySparyAnimation);
    }

    public void beginSpoopyAnimation()
    {
        GifStarter startSpeleton     = new GifStarter(spoopySparySpeleton, null);
        GifStarter startCult         = new GifStarter(spoopySparyCult, spoopySparySpeleton);
        GifStarter startShortAndTall = new GifStarter(spoopySparyShortAndTall, spoopySparyCult);
        GifStarter startDancer       = new GifStarter(spoopySparyDancer, spoopySparyShortAndTall);
        GifStarter startPogo         = new GifStarter(spoopySparyPogo, spoopySparyDancer);
        GifStarter startIntensifies  = new GifStarter(spoopySparyIntensifies, spoopySparyPogo);
        GifStarter startSkull        = new GifStarter(spoopySparyCrazyShakinSkull, spoopySparyIntensifies);

        // a silence

        GifStarter startPumpkin      = new GifStarter(spoopySparyPumpkin, null);
        GifStarter startSwallow      = new GifStarter(spoopySparySwallow, spoopySparyPumpkin);
        GifStarter startPumpkin2     = new GifStarter(spoopySparyPumpkin, spoopySparySwallow);
        GifStarter startSkull2       = new GifStarter(spoopySparyCrazyShakinSkull, spoopySparyPumpkin);
        GifStarter startPumpkin3     = new GifStarter(spoopySparyPumpkin, spoopySparyCrazyShakinSkull);
        GifStarter startSpeleton2    = new GifStarter(spoopySparySpeleton, spoopySparyPumpkin);
        GifStarter startPumpkin4     = new GifStarter(spoopySparyPumpkin, spoopySparySpeleton);
        GifStarter startSpeletor     = new GifStarter(spoopySparySpeletor, spoopySparyPumpkin);

        GifStarter startPogo2        = new GifStarter(spoopySparyPogo, spoopySparySpeletor);
        GifStarter startSaunter      = new GifStarter(spoopySparySaunter, spoopySparyPogo);
        GifStarter startSpeletor2    = new GifStarter(spoopySparySpeletor, spoopySparySaunter);

        // another build
        GifStarter startShortTall2   = new GifStarter(spoopySparyShortAndTall, spoopySparySpeletor);
        GifStarter startCult2        = new GifStarter(spoopySparyCult, spoopySparyShortAndTall);
        GifStarter startIntensifies2 = new GifStarter(spoopySparyIntensifies, spoopySparyCult);
        GifStarter startSkull3       = new GifStarter(spoopySparyCrazyShakinSkull, spoopySparyIntensifies);
        GifStarter startEmpty        = new GifStarter(null, spoopySparyCrazyShakinSkull);

        // RAAAAVVEEE
        GifStarter startRave         = new GifStarter(spoopySparyRave, null);



        Handler handler = new Handler();

        handler.postDelayed(startSpeleton, 1905);
        handler.postDelayed(startCult, 9390);
        handler.postDelayed(startShortAndTall, 13170);
        handler.postDelayed(startDancer, 16960);
        handler.postDelayed(startPogo, 18710);
        handler.postDelayed(startIntensifies, 20700);
        handler.postDelayed(startSkull, 22600);

        // the silence before the drop
        final GifImageView skull = spoopySparyCrazyShakinSkull;
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                skull.setVisibility(View.INVISIBLE);
            }
        }, 24130);

        // drop 1
        handler.postDelayed(startPumpkin, 24470);
        handler.postDelayed(startSwallow, 27910);
        handler.postDelayed(startPumpkin2, 28210);
        handler.postDelayed(startSkull2, 31493);
        handler.postDelayed(startPumpkin3, 31963);
        handler.postDelayed(startSpeleton2, 35230);
        handler.postDelayed(startPumpkin4, 35705);
        handler.postDelayed(startSpeletor, 37595);

        // drop 1 part 2
        handler.postDelayed(startPogo2, 39470);
        handler.postDelayed(startSaunter, 46960);
        handler.postDelayed(startSpeletor2, 52595);

        // build 2
        handler.postDelayed(startShortTall2, 54500);
        handler.postDelayed(startCult2, 56330);
        handler.postDelayed(startIntensifies2, 58200);
        handler.postDelayed(startSkull3, 59625);
        handler.postDelayed(startEmpty, 60545);

        // Change screen to black
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                RelativeLayout mainLayout = (RelativeLayout)MainActivity.this.findViewById(R.id.main_relative_layout);
                mainLayout.setBackgroundColor(Color.BLACK);
            }
        }, 61035);

        // RAVE
        handler.postDelayed(startRave, 61505);
    }

    private class GifStarter implements Runnable
    {
        GifImageView mGif;
        GifImageView mPrevGif;

        public GifStarter(GifImageView gif, GifImageView prevGif)
        {
            mGif = gif;
            mPrevGif = prevGif;
        }

        @Override
        public void run()
        {
            if (mGif != null)
            {
                mGif.setVisibility(View.VISIBLE);
            }
            if (mPrevGif != null)
            {
                mPrevGif.setVisibility(View.INVISIBLE);
            }
        }
    }
}
