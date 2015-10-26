package com.bgreeves.spoopysparyspeletons;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Ben on 10/8/15.
 *
 * A small wrapper for the Android MediaPlayer class.
 * Takes in the resource id of a sound clip and asynchronously creates an instance of
 * MediaPlayer for the sound clip. User will have to register an OnPreparedListener.
 */
public class SoundPlayer extends MediaPlayer implements MediaPlayer.OnErrorListener
{
    private Context mContext;
    private boolean mIsInitialized;

    public SoundPlayer(Context context, int resId)
    {
        this(context, resId, null);
    }

    public SoundPlayer(Context context, int resId, OnPreparedListener listener)
    {
        mContext = context;
        mIsInitialized = false;
        setOnPreparedListener(listener);
        init(resId);
    }

    public boolean isInitialized()
    {
        return mIsInitialized;
    }

    private void init(int resId)
    {
        try
        {
            Resources res = mContext.getResources();
            AssetFileDescriptor afd = res.openRawResourceFd(resId);
            if (afd == null)
            {
                if (Build.VERSION.SDK_INT >= 17)
                {
                    onError(this, MEDIA_ERROR_UNKNOWN, MEDIA_ERROR_IO);
                }
                else
                {
                    onError(this, MEDIA_ERROR_UNKNOWN, 0);
                }
            }
            else
            {
                setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
            }
            mIsInitialized = true;

            // The call to the asynchronous prepare() method
            prepareAsync();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Many of the "MEDIA_ERROR" codes require API level 17 or above
    @TargetApi(17)
    private void initializationError()
    {
        onError(this, MEDIA_ERROR_UNKNOWN, MEDIA_ERROR_IO);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        Toast toast = new Toast(mContext);
        Resources res = mContext.getResources();

        if (what == MEDIA_ERROR_UNKNOWN)
        {
            switch (extra)
            {
            case MEDIA_ERROR_IO:
                toast.setText(R.string.media_error_io);
                break;

            case MEDIA_ERROR_MALFORMED:
                toast.setText(R.string.media_error_malformed);
                break;

            case MEDIA_ERROR_TIMED_OUT:
                toast.setText(R.string.media_error_timed_out);
                break;

            case MEDIA_ERROR_UNSUPPORTED:
                toast.setText(R.string.media_error_unsupported);
                break;

            default:
                toast.setText(R.string.media_error_unknown);
                break;
            }
        }
        else if (what == MEDIA_ERROR_SERVER_DIED)
        {
            toast.setText(res.getString(R.string.media_error_server_died));

            // Media server died. In this case, the application must release the MediaPlayer
            // object and instantiate a new one.
            release();
        }

        // Show the Toast
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

        return true;
    }
}
