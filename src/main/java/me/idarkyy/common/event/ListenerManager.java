package me.idarkyy.common.event;

import me.idarkyy.common.event.annotations.EventHandler;
import me.idarkyy.common.event.listener.Listener;
import me.idarkyy.common.utils.ListHashMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ListenerManager {
    private ListHashMap<Class<? extends Event>, Method> map = new ListHashMap<>();
    private HashMap<Method, Listener> listenerObjects = new HashMap<>();

    // TODO: 04/04/2019 Event Priorities

    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() != 1) {
                    System.out.println("[WARN] Method " + method.getClass().getSimpleName() + "#" + method.getName() + " must have one Event parameter");
                    return;
                }

                Class<?> param = method.getParameterTypes()[0];

                if(param.getSuperclass() != Event.class) {
                    System.out.println("[WARN] Parameter of method " + method.getClass().getSimpleName() + "#" + method.getName() + " isn't a Event");
                    return;
                }

                Class<? extends Event> clazz = (Class<? extends Event>) param;

                map.putIfAbsent(clazz, new ArrayList<>());

                map.addToList(clazz, method);
                listenerObjects.put(method, listener);
            }
        }
    }

    public void call(Event event) {
        for(Method method : map.get(event.getClass())) {
            try {
                method.invoke(listenerObjects.get(method), event);
            } catch(IllegalAccessException e) {
                System.out.println("Could not access method " + method.getClass().getSimpleName() + "#" + method.getName());
            } catch(InvocationTargetException e) {
                System.out.println("Could not invoke method " + method.getClass().getSimpleName() + "#" + method.getName());
                e.printStackTrace();
            }
        }
    }
}
