package fi.tamk.jorix3.musicplayer;

import android.os.Binder;

/**
 * MusicPlayer
 *
 * @author Jyri Virtaranta jyri.virtaranta@cs.tamk.fi
 * @version 2018.01.25
 * @since 1.8
 */
public class LocalBinder extends Binder {
    private MusicPlayer service;
    
    public LocalBinder(MusicPlayer service) {
        this.service = service;
    }
    
    public MusicPlayer getService() {
        return this.service;
    }
}
