package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import proxy.Video;
import proxy.VideoI;

public class App {
    public static void main(String[] args) {
        VideoI proxyInstance = (VideoI) Proxy.newProxyInstance(VideoI.class.getClassLoader(),
                new Class[] { VideoI.class }, new ObjectInvocationHandler("video"));

        System.out.println("--------");
        proxyInstance.play();
        proxyInstance.renameVideo("new_video");

        System.out.println("--------");

        proxyInstance = (VideoI) Proxy.newProxyInstance(VideoI.class.getClassLoader(), new Class[] { VideoI.class },
                (proxy, method, methodArgs) -> {
                    if (!(proxy instanceof Video)) {
                        proxy = new Video("video");
                    }

                    System.out.println("Starting executing method " + method.getName());

                    long start = System.nanoTime();
                    Object result = method.invoke(proxy, methodArgs);
                    long diff = System.nanoTime() - start;

                    System.out.println("Method " + method.getName() + " finished in " + diff + " ns");

                    return result;
                });
        System.out.println("--------");

        proxyInstance.play();
        proxyInstance.renameVideo("new_video");
    }
}

class ObjectInvocationHandler implements InvocationHandler {
    private String videoName;

    ObjectInvocationHandler(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!(proxy instanceof Video)) {
            proxy = new Video(videoName);
        }

        System.out.println("Starting executing method " + method.getName());

        long start = System.nanoTime();
        Object result = method.invoke(proxy, args);
        long diff = System.nanoTime() - start;

        System.out.println("Method " + method.getName() + " finished in " + diff + " ns");

        return result;
    }
}