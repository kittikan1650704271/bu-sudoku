package gui.panels;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundWavePlayer {
    private Clip clip;
    private Clip clip1;
    private List<String> soundList = new ArrayList<>();
    
    public SoundWavePlayer() {
        // Initialize the list of sound filenames
        soundList.add("bgm1");
        soundList.add("bgm2");
        soundList.add("bgm3");
        soundList.add("bgm4");
        soundList.add("bgm5");
        soundList.add("bgm6");
        // Add more sounds as needed
    }
    
    public void playSound(String music,float sound_level) {
        try {
            System.out.println("ARE YOU PLAYED?");
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
            float volume = sound_level; // Adjust this value to set the desired volume
            gainControl.setValue(volume);

            // Start playing the clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void playLoopSound(String music,float sound_level) {
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
            clip1 = (Clip) AudioSystem.getLine(dataLineInfo);

            // Open the audioInputStream to the clip
            clip1.open(audioInputStream);

            // Create a FloatControl for volume adjustment
            FloatControl gainControl = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);

            // Set the volume level (in decibels)
            float volume = sound_level; // Adjust this value to set the desired volume
            gainControl.setValue(volume);
            
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        // When the clip stops, rewind and start playing again for looping
                        clip1.setFramePosition(1);
                        clip1.start();
                    }
                }
            });
            
            // Start playing the clip
            clip1.start();
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
    
    public void shuffleSound(float sound_level) {
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            playSound(randomSound,sound_level);
            System.out.println(randomSound);
        }
    }
    
    public void shuffleLoopSound(float sound_level) {
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            playLoopSound(randomSound,sound_level);
            System.out.println(randomSound);
        }
    } 
}