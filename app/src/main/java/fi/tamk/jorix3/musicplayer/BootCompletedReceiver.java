package fi.tamk.jorix3.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * MusicPlayer
 *
 * @author Jyri Virtaranta jyri.virtaranta@cs.tamk.fi
 * @version 2018.01.26
 * @since 1.8
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // For newer versions of API level
        // Intent appIntent = new Intent(context, MainActivity.class);
        // context.startActivity(appIntent);

        // For being an A-hole
        // Intent musicIntent = new Intent(context, MusicPlayer.class);
        // context.startService(musicIntent);
        
        Log.d("boot_debug", "Boot Completed");
    }
}
