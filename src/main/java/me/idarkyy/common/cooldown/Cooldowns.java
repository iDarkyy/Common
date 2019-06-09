package me.idarkyy.common.cooldown;

import me.idarkyy.common.pair.Pair;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Simple cooldown utility
 *
 * @param <T> Key type
 * @author iDarkyy
 */
public class Cooldowns<T> {
    private final HashMap<T, Long> cooldowns = new HashMap<>();
    private final HashMap<T, Long> times = new HashMap<>();
    private final HashMap<T, Timer> actions = new HashMap<>();

    /**
     * Create a cooldown
     *
     * @param key          Cooldown key for further accessing
     * @param time         Duration of the cooldown
     * @param unit         Time unit for the duration
     * @param expireAction Action when the cooldown expires
     */
    public Cooldowns create(T key, long time, TimeUnit unit, Consumer<T> expireAction) {
        if (isOnCooldown(key)) {
            cancelExpireAction(key);
        }

        cooldowns.put(key, System.currentTimeMillis());
        times.put(key, unit.toMillis(time));

        if (expireAction != null) {
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    expireAction.accept(key);
                }
            }, TimeUnit.MILLISECONDS.convert(time, unit));

            actions.put(key, timer);
        }

        return this;
    }

    /**
     * Create a cooldown without a expire action
     *
     * @param key  Cooldown key for further accessing
     * @param time Duration of the cooldown
     * @param unit Time unit for the duration
     */
    public Cooldowns create(T key, long time, TimeUnit unit) {
        return create(key, time, unit, null);
    }

    /**
     * Delete a cooldown
     *
     * @param key The cooldown key
     */
    public Cooldowns delete(T key) {
        cooldowns.remove(key);
        times.remove(key);

        cancelExpireAction(key);

        return this;
    }

    /**
     * Cancel the cooldown expire action
     *
     * @param key The cooldown key
     */
    public Cooldowns cancelExpireAction(T key) {
        if (actions.containsKey(key)) {
            actions.get(key).cancel();
        }

        actions.remove(key);

        return this;
    }

    /**
     * Get the timer which scheduled the action
     *
     * @param key The cooldown key
     * @return Timer that scheduled the action
     */
    public Timer getExpireActionTimer(T key) {
        return actions.get(key);
    }

    /**
     * Is the object (key) on cooldown
     *
     * @param key The cooldown key
     */
    public boolean isOnCooldown(T key) {
        return getTimeLeft(key, TimeUnit.MILLISECONDS) > 0;
    }

    /**
     * Returns how much time there's left for the specified cooldown in the specified unit
     *
     * @param key  The cooldown key
     * @param unit Time unit for the return value
     * @return Time left
     */
    public long getTimeLeft(T key, TimeUnit unit) {
        if (!cooldowns.containsKey(key) || !times.containsKey(key)) {
            return -1;
        }

        return unit.convert(times.get(key) - (System.currentTimeMillis() - cooldowns.get(key)), TimeUnit.MILLISECONDS);
    }

    /**
     * Returns how much time there's left for the specified cooldown
     *
     * @param key The cooldown key
     * @return Time left along with the closest time unit
     */
    public Pair<Long, TimeUnit> getTimeLeft(T key) {
        Pair<Long, TimeUnit> pair = null;

        for (TimeUnit unit : TimeUnit.values()) {
            if (getTimeLeft(key, unit) > 0) {
                pair = Pair.of(getTimeLeft(key, unit), unit);
                break;
            }
        }

        return pair;
    }

    /**
     * Time elpased from when the cooldown was set
     *
     * @param key  The cooldown key
     * @param unit Time unit for the return value
     * @return Time elapsed
     */
    public long getElapsedTime(T key, TimeUnit unit) {
        if (!cooldowns.containsKey(key) || !times.containsKey(key)) {
            return -1;
        }

        return unit.convert(System.currentTimeMillis() - cooldowns.get(key), TimeUnit.MILLISECONDS);
    }

    /**
     * Date (time) on which the cooldown expires
     *
     * @param key The cooldown key
     * @return The expiration date
     */
    public Date getExpirationDate(T key) {
        if (!cooldowns.containsKey(key) || !times.containsKey(key)) {
            return null;
        }

        return Date.from(Instant.ofEpochMilli(cooldowns.get(key) + times.get(key)));
    }
}
