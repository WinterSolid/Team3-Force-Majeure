import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.List;

public class Audio implements Runnable {
    private static final Audio INSTANCE = new Audio();
    private static final List<String> AUDIO_NAMES = List.of("start", "beach");
    private volatile boolean muted = false;
    private Clip clip;

    private Audio() {}

    public static Audio getInstance() {
        return INSTANCE;
    }

    private void findAndPlay(String name) {
        if (!AUDIO_NAMES.contains(name)) {
            return;
        }
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
        stop();
    }

    public void toggleMute() {
        boolean isMuted = getMuted();
        if (isMuted) {
            unMute();
        } else {
            mute();
        }
    }

    public void unMute() {
        setMuted(false);
    }

    public void stop() {
        if (clip.isActive()) {
            clip.stop();
            clip.close();
        }
    }

    private boolean getMuted() {
        return this.muted;
    }

    private void setMuted(Boolean muted) {
        this.muted = muted;
        // maybe need to close thread?
    }
}