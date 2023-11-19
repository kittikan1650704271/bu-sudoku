package gui.panels;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundWavePlayer {

    public static void main() {
        // Specify the path to your sound file (e.g., .wav)
        String soundFilePath = "H:\\wav\\a place i call home.wav";

        // Create a new SoundWavePlayer object and play the sound
        SoundWavePlayer player = new SoundWavePlayer();
        player.playSound(soundFilePath);
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);

            if (!soundFile.exists()) {
                System.out.println("File not found: " + soundFilePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat audioFormat = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Audio line is not supported");
                return;
            }

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();

            int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
            byte[] buffer = new byte[bufferSize];

            int bytesRead;

            while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.close();
            audioInputStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
