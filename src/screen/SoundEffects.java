package screen;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundEffects {

    private Clip clip;

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.HIGH;
    // Constructor to construct each element of the enum with its own sound file.
    public SoundEffects(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getResource(soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Boolean loop) {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
            if(loop)//Loop if loop parameter is true
                clip.loop(0);
        }

    }

    public void stop() //stop playing and rewind to be played again from the beginning
    {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void mute() //don't play sounds(Mute Sound is selected from Options menu)
    {
        volume = Volume.MUTE;
    }

    


}
