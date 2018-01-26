package fi.tamk.jorix3.musicplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Intent musicPlayerIntent;
    private MusicPlayer musicPlayerService;
    private MusicPlayerConnection musicPlayerConnection;
    private boolean isBound = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicPlayerIntent = new Intent(this, MusicPlayer.class);
        musicPlayerConnection = new MusicPlayerConnection();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        bindIfNotBound();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        unBindIfBound();
    }
    
    public void playMusic(View v) {
        bindIfNotBound();
        startService(musicPlayerIntent);
    }
    
    public void stopMusic(View v) {
        unBindIfBound();
        stopService(musicPlayerIntent);
    }
    
    public void volumeControl(View v) {
        Button button = (Button) v;
        
        if (isBound) {
            if (button.getTag().toString().equals("increase_volume")) {
                musicPlayerService.increaseVolume();
            } else if (button.getTag().toString().equals("decrease_volume")) {
                musicPlayerService.decreaseVolume();
            }
        }
    }
    
    private void bindIfNotBound() {
        if (!isBound) {
            isBound = true;
            bindService(musicPlayerIntent,
                    musicPlayerConnection,
                    Context.BIND_AUTO_CREATE);
        }
    }
    
    private void unBindIfBound() {
        if (isBound) {
            isBound = false;
            unbindService(musicPlayerConnection);
        }
    }
    
    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder iBinder) {
            LocalBinder binder = (LocalBinder) iBinder;
            musicPlayerService = binder.getService();
            isBound = true;
        }
    
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    }
}
