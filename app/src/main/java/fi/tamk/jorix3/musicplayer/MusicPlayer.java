package fi.tamk.jorix3.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * MusicPlayer
 *
 * @author Jyri Virtaranta jyri.virtaranta@cs.tamk.fi
 * @version 2018.01.25
 * @since 1.8
 */
public class MusicPlayer extends Service {
    private MediaPlayer mediaPlayer;
    private IBinder localBinder;
    private float volume = 1.0f;
    
    public void increaseVolume() {
        if (volume < 1) {
            volume += 0.1f;
            mediaPlayer.setVolume(volume, volume);
        }
        
        Log.d("my_debug", "increase volume" + volume);
    }
    
    public void decreaseVolume() {
        if (volume > 0) {
            volume -= 0.1f;
            mediaPlayer.setVolume(volume, volume);
        }
        
        Log.d("my_debug", "decrease volume" + volume);
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        
        return START_STICKY;
    }
    
    @Override
    public void onCreate() {
        localBinder = new LocalBinder(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.muzak);
        mediaPlayer.setLooping(true);
    }
    
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}
