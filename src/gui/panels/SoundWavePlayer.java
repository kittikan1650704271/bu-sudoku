package gui.panels;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundWavePlayer {
    private Clip clip;
    private List<String> soundList = new ArrayList<>();

    public void playSound(String music) {
        try {
            String base = "resources/sound/";
            File wavFile = new File(base + music + ".wav");

            // Set up AudioInputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);

            // Set up AudioFormat
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Set up DataLine.Info
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);

            // Get a Clip
            clip = (Clip) AudioSystem.getLine(dataLineInfo);

            // Open the audioInputStream to the clip
            clip.open(audioInputStream);

            // Create a FloatControl for volume adjustment
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Set the volume level (in decibels)
            float volume = 0.0f; // Adjust this value to set the desired volume
            gainControl.setValue(volume);

            // Start playing the clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
    
    public void shuffleSound() {
        // Initialize the list of sound filenames
        soundList.add("sbg");
        soundList.add("ADO_Music");
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            playSound(randomSound);
            System.out.println(randomSound);
        }
    }
    
    public void loopSound(String sound, int loopCount) {
        // Play the specified sound or a random sound in a loop
        for (int i = 0; i < loopCount; i++) {
            playSound(sound);
        }
    }
}