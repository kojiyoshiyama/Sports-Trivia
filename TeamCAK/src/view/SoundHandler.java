/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * This class adds LoFi music to the game.
 *
 * @author Calvin Beardemphl, Alex Thompson, Koji Yoshiyama
 * @version 15/12/2023
 */
public class SoundHandler {

    /**
     * This is the method that adds the music to the gameplay
     *
     * @param thePath the path to get the music file needed
     */
    public static void RunMusic(final String thePath) {
        final float volume = -30.0f;
        try {
            final AudioInputStream inputStream =
                    AudioSystem.getAudioInputStream(new File(thePath));
            final Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
            final FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        } catch (final UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
