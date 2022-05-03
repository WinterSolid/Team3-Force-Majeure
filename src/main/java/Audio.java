import javax.sound.sampled.*;
import java.io.InputStream;

public class Audio implements Runnable {
    private static final Audio INSTANCE = new Audio();
    private volatile boolean muted = false;
    public Clip clip;

    private Audio() {}

    public static Audio getInstance() {
        return INSTANCE;
    }

    private void findAndPlay(String name) {
        String fileName = "audio/" + name + ".wav";
        AudioInputStream ais = null;

        try {
            InputStream inputStream = FileResourceUtils.getInputStreamFromResource(fileName);
            ais = AudioSystem.getAudioInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startThread() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        startThread();
    }

    public void play(String fileName) {
        if (!muted) {
            run();
            findAndPlay(fileName);
        }
    }

    public void mute() {
        setMuted(true);
    }

    public void unMute() {
        setMuted(false);
    }

    private void setMuted(Boolean muted) {
        this.muted = muted;
        if (clip.isActive()) {
            clip.stop();
            clip.close();
        }
        // maybe need to close thread?
    }
}