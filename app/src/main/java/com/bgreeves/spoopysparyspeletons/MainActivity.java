package com.bgreeves.spoopysparyspeletons;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
{
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
        Toast.makeText(this, "SPOOPY SPARY SPELETONS", Toast.LENGTH_SHORT).show();
    }
}
