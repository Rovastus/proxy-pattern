package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import proxy.Video;
import proxy.VideoI;

public class App {
    public static void main(String[] args) {
        VideoI proxyInstance = (VideoI) Proxy.newProxyInstance(VideoI.class.getClassLoader(),
                new Class[] { VideoI.class }, new ObjectInvocationHandler(new Video("video")));

        System.out.println("--------");

        proxyInstance.play();
        proxyInstance.renameVideo("new_video");
    }
}

class ObjectInvocationHandler implements InvocationHandler {
    private Object target;

    ObjectInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Starting executing method " + method.getName());

        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long diff = System.nanoTime() - start;

        System.out.println("Method " + method.getName() + " finished in " + diff + " ns");

        return result;
    }
}