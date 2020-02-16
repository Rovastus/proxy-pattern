package proxy;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        User user1 = new User("Janko", false);
        User user2 = new User("Jožko", true);

        VideoI video = new ProxyVideo("xxx.avi", user1);
        video.play();
        video.play();

        System.out.println("--------");

        video.renameVideo("new_xxx.avi");

        System.out.println("--------");

        video = new ProxyVideo("xxx.avi", user2);
        video.renameVideo("new_xxx.avi");
    }
}
