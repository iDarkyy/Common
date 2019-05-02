package me.idarkyy.common.event.handler;

import me.idarkyy.common.event.Event;
import me.idarkyy.common.event.enums.EventPriority;
import me.idarkyy.common.event.exception.EventException;
import me.idarkyy.common.listhashmap.ListHashMap;

import java.lang.reflect.InvocationTargetException;

public class HandlerList {
    public static final String HANDLER_LIST_METHOD = "getHandlerList";
    public static final String HANDLER_LIST_FIELD = "handlerList";

    private ListHashMap<Integer, HandlerMethod> methods = new ListHashMap<>();

    public static HandlerList fromClass(Class<? extends Event> clazz) {
        try {
            if (clazz.getDeclaredMethod(HANDLER_LIST_METHOD) != null) {
                return (HandlerList) clazz.getDeclaredMethod(HANDLER_LIST_METHOD).invoke(null);
            }

            if (clazz.getDeclaredField(HANDLER_LIST_FIELD) != null) {
                return (HandlerList) clazz.getDeclaredField(HANDLER_LIST_FIELD).get(null);
            }

        } catch (NoSuchMethodException | NoSuchFieldException e) {
            // ignored
        } catch (IllegalAccessException e) {
            throw new EventException("getHandlerList method of class " + clazz.getSimpleName() + " is not public and static");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(HandlerMethod method, EventPriority priority) {
        methods.addToList(priority.getSlot(), method);
    }

    public Event trigger(Event event) {
        for (int i = 0; i < 6; i++) {
            if (methods.containsKey(i)) {
                for (HandlerMethod method : methods.get(i)) {
                    method.invoke(event);
                }
            }
        }

        return event;
    }
}
