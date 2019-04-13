package me.idarkyy.common.event;

import me.idarkyy.common.event.annotations.EventHandler;
import me.idarkyy.common.event.exception.EventException;
import me.idarkyy.common.event.handler.HandlerList;
import me.idarkyy.common.event.handler.HandlerMethod;
import me.idarkyy.common.event.listener.Listener;

import java.lang.reflect.Method;

public class ListenerManager {
    public void register(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (doesMeetTheRequirements(method)) {

                @SuppressWarnings("all")
                Class<? extends Event> param = (Class<? extends Event>) method.getParameterTypes()[0];

                HandlerList handlerList = HandlerList.fromClass(param);
                HandlerMethod handlerMethod = new HandlerMethod(method, listener);

                if (handlerList == null) {
                    throw new EventException("There is no public static getHandlerList method in event class " + param.getSimpleName());
                }


                handlerList.add(handlerMethod, method.getAnnotation(EventHandler.class).value());
            }
        }
    }

    public void call(Event event) {
        HandlerList handlerList = HandlerList.fromClass(event.getClass());

        if (handlerList == null) {
            throw new EventException("There is no public static getHandlerList method in event class " + event.getClass().getSimpleName());
        }

        handlerList.trigger(event);
    }

    private boolean doesMeetTheRequirements(Method method) {
        if (!method.isAnnotationPresent(EventHandler.class)) {
            return false;
        }

        if (method.getParameterCount() != 1) {
            System.out.println("Warning: Method " + method.getName() + " in listener class " + method.getClass().getSimpleName() + " has to have only 1 parameter (the event)");
            return false;
        }

        if (method.getParameterTypes()[0].getSuperclass() != Event.class) {
            System.out.println("Warning: Method " + method.getName() + " in listener class " + method.getClass().getSimpleName() + ": Invalid event " + method.getParameterTypes()[0].getSimpleName());
            return false;
        }

        return true;
    }
}
