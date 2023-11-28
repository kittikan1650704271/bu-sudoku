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
    private String currentMusic; // Variable to store the current music name
    
    public SoundWavePlayer() {
        for(int i = 1; i < 12; i++){
            soundList.add("bgm" + i);
        }
    }
    
    public void playSound(String music, float sound_level) {
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
            float volume = sound_level; // Adjust this value to set the desired volume
            gainControl.setValue(volume);

            // Update the current music
            currentMusic = music;
            

            // Start playing the clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void playLoopSound(String music, float sound_level) {
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
            
            // Update the current music
            currentMusic = music;
            System.out.println(this.getNameMusicPlayToString());
            System.out.println(this.getIndexMusicPlay());

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
        if (clip1 != null && clip1.isRunning()) {
            clip1.stop();
            clip1.close();
        }
    }
    
    public String getNameMusicPlay() {
        return currentMusic;
    }
    
    public int getIndexMusicPlay() {
        if (currentMusic != null && !soundList.isEmpty()) {
            return soundList.indexOf(currentMusic);
        } else {
            return -1; // Return -1 if no music is playing or if the soundList is empty
        }
    }
    
    public String getNameMusicPlayToString() {
        if (currentMusic != null) {
            return "Currently playing: " + currentMusic;
        } else {
            return "No music is currently playing.";
        }
    }
    
    public void shuffleSound(float sound_level) {
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            playSound(randomSound, sound_level);
        }
    }
    
    public void shuffleLoopSound(float sound_level) {
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            System.out.println("Random music int"+randomIndex);
            String randomSound = soundList.get(randomIndex);
            playLoopSound(randomSound, sound_level);
        }
    }
}