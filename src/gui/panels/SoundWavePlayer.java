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

    public void playSound(String music,float sound_level) {
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
    
    public void shuffleSound(float sound_level) {
        // Initialize the list of sound filenames
        //soundList.add("sbg");
        //soundList.add("ADO_Music");
        soundList.add("bgm1");
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            playSound(randomSound,sound_level);
            System.out.println(randomSound);
        }
    }
    
    public void shuffleLoopSound( int loopCount,float sound_level) {
        // Initialize the list of sound filenames
        //soundList.add("sbg");
        //soundList.add("ADO_Music");
        soundList.add("bgm1");
        // Play a random sound from the list
        if (!soundList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(soundList.size());
            String randomSound = soundList.get(randomIndex);
            for (int i = 0; i < loopCount; i++) {
                
                playSound(randomSound,sound_level);
            }
            System.out.println(randomSound);
        }
    }
    
    public void loopSound(float soundlevel) {
        soundList.add("bgm1");
        Random random = new Random();
        int randomIndex = random.nextInt(soundList.size());
        String randomSound = soundList.get(randomIndex);
        if (!soundList.isEmpty()) {
            
            playSound(randomSound,soundlevel);
            System.out.println(randomSound);
        }
        clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        // When the clip stops, rewind and start playing again for looping
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }
            });
        // Play the specified sound in a loop
        playSound(randomSound,soundlevel);
    }
    
    public void loopSound1(String sound, int loopCount,float sound_level) {
        // Play the specified sound or a random sound in a loop
        for (int i = 0; i < loopCount; i++) {
            playSound(sound, sound_level);
        }
    }
}