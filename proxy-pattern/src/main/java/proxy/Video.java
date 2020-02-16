package proxy;

public class Video implements VideoI {
    private String name;

    Video(String name) {
        this.name = name;
        this.loadVideo();
    }

    @Override
    public void play() {
        System.out.println("Playing video " + name);
    }

    private void loadVideo() {
        System.out.println("Loading video " + name);
    }

    public void renameVideo(String name) {
        this.name = name;
        System.out.println("Video was renamed to " + name);
    }

    public String getName() {
        return this.name;
    }

    public void encrypt() {
        // ...
    }

    public void decrypt() {
        // ...
    }
}