package proxy;

public class ProxyVideo implements VideoI {
    private String name;
    private User user;
    private Video video;

    ProxyVideo(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Override
    public void play() {
        if (video == null) {
            video = new Video(name);
        }
        video.play();
    }

    @Override
    public void renameVideo(String newName) {
        if (video == null) {
            video = new Video(name);
        }

        if (checkAccess()) {
            video.renameVideo(newName);
            this.name = video.getName();
        } else {
            System.out.println("Error: User isn't admin, can't rename video.");
        }
    }

    private boolean checkAccess() {
        return user.isAdmin();
    }
}