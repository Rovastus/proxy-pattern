package proxy;

public class App {
    public static void main(String[] args) {

        User user1 = new User("Janko", false);
        User user2 = new User("Jožko", true);

        VideoI video = new ProxyVideo("video", user1);
        video.play();
        video.play();

        System.out.println("--------");

        video.renameVideo("new_video");

        System.out.println("--------");

        video = new ProxyVideo("video", user2);
        video.renameVideo("new_video");
    }
}
