package me.idarkyy.common.event.handler;

import me.idarkyy.common.event.Event;
import me.idarkyy.common.event.annotations.Asynchronous;
import me.idarkyy.common.event.exception.EventException;
import me.idarkyy.common.event.listener.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerMethod {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static ExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private Method method;
    private Listener listener;

    public HandlerMethod(Method method, Listener listener) {
        this.method = method;
        this.listener = listener;
    }

    public HandlerMethod(Class<?> clazz, String methodName) {
        try {
            this.method = clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Event invoke(Event event) {
        try {
            if (method.isAnnotationPresent(Asynchronous.class)) {
                executorService.submit(() -> invoke(event));
                return event;
            }

            method.invoke(listener, event);
        } catch (InvocationTargetException e) {
            throw new EventException(e.getCause());
        } catch (IllegalAccessException e) {
            throw new EventException("Could not trigger event " + event.getClass().getSimpleName() + ": Method is not accessible");
        }

        return event;
    }
}
