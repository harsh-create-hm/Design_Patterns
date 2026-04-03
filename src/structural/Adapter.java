package structural;

/**
 * Adapter Pattern
 *
 * Intent: Convert the interface of a class into another interface clients expect.
 * Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
 *
 * Use when:
 * - You want to use an existing class, but its interface doesn't match the one you need.
 * - You want to create a reusable class that cooperates with classes that have incompatible interfaces.
 */
public class Adapter {

    // Target interface expected by the client
    interface MediaPlayer {
        void play(String filename);
    }

    // Adaptee – existing class with an incompatible interface
    static class AdvancedMediaPlayer {
        public void playMp4(String filename) {
            System.out.println("Playing MP4 file: " + filename);
        }

        public void playMkv(String filename) {
            System.out.println("Playing MKV file: " + filename);
        }
    }

    // Adapter – wraps the Adaptee and implements the Target interface
    static class MediaAdapter implements MediaPlayer {
        private AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

        @Override
        public void play(String filename) {
            if (filename.endsWith(".mp4")) {
                advancedPlayer.playMp4(filename);
            } else if (filename.endsWith(".mkv")) {
                advancedPlayer.playMkv(filename);
            } else {
                System.out.println("Unsupported format for file: " + filename);
            }
        }
    }

    // Existing basic player that also implements the target interface
    static class AudioPlayer implements MediaPlayer {
        private MediaAdapter adapter = new MediaAdapter();

        @Override
        public void play(String filename) {
            if (filename.endsWith(".mp3")) {
                System.out.println("Playing MP3 file: " + filename);
            } else {
                // Delegate to adapter for other formats
                adapter.play(filename);
            }
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Adapter pattern demo:");

        MediaPlayer player = new AudioPlayer();
        player.play("song.mp3");
        player.play("movie.mp4");
        player.play("video.mkv");
        player.play("clip.avi");
    }
}
