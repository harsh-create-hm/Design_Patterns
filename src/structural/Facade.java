package structural;

/**
 * Facade Pattern
 *
 * Intent: Provide a unified interface to a set of interfaces in a subsystem.
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 *
 * Use when:
 * - You want to provide a simple interface to a complex subsystem.
 * - There are many dependencies between clients and the implementation classes of an abstraction.
 */
public class Facade {

    // Complex subsystem classes
    static class DVDPlayer {
        public void on()     { System.out.println("DVD Player: ON");     }
        public void off()    { System.out.println("DVD Player: OFF");    }
        public void play()   { System.out.println("DVD Player: Playing movie"); }
        public void stop()   { System.out.println("DVD Player: Stopped"); }
    }

    static class Projector {
        public void on()            { System.out.println("Projector: ON");           }
        public void off()           { System.out.println("Projector: OFF");          }
        public void wideScreenMode(){ System.out.println("Projector: Wide-screen mode"); }
    }

    static class SurroundSoundSystem {
        public void on()          { System.out.println("Sound System: ON");          }
        public void off()         { System.out.println("Sound System: OFF");         }
        public void setVolume(int level) {
            System.out.println("Sound System: Volume set to " + level);
        }
    }

    static class Lights {
        public void dim(int level) {
            System.out.println("Lights: Dimmed to " + level + "%");
        }
        public void on()  { System.out.println("Lights: ON");  }
    }

    // Facade – simplified interface for watching a movie
    static class HomeTheaterFacade {
        private DVDPlayer dvd;
        private Projector projector;
        private SurroundSoundSystem sound;
        private Lights lights;

        HomeTheaterFacade(DVDPlayer dvd, Projector projector,
                          SurroundSoundSystem sound, Lights lights) {
            this.dvd = dvd;
            this.projector = projector;
            this.sound = sound;
            this.lights = lights;
        }

        public void watchMovie() {
            System.out.println("--- Getting ready to watch a movie ---");
            lights.dim(10);
            projector.on();
            projector.wideScreenMode();
            sound.on();
            sound.setVolume(8);
            dvd.on();
            dvd.play();
        }

        public void endMovie() {
            System.out.println("--- Shutting down home theater ---");
            dvd.stop();
            dvd.off();
            sound.off();
            projector.off();
            lights.on();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Facade pattern demo:");
        System.out.println();

        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        SurroundSoundSystem sound = new SurroundSoundSystem();
        Lights lights = new Lights();

        HomeTheaterFacade theater = new HomeTheaterFacade(dvd, projector, sound, lights);

        theater.watchMovie();
        System.out.println();
        theater.endMovie();
    }
}
