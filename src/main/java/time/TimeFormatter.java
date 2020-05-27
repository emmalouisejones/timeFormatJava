package time;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static time.TimeFormatter.UnitOfTime.*;

public class TimeFormatter {

    public enum UnitOfTime {

        second(1), minute(60), hour(3600), day(86400), year(31536000);

        private int secs;

        UnitOfTime(int secs) {
            this.secs = secs;
        }
    }

    public static String convertTime(int secs) {
        return (secs <= 0) ? "none" : calculateTime(secs);
    }

    private static String calculateTime(int secs) {
        Map<String, Integer> timeByUnit = calculateTimeByUnit(secs);
        return getTimeText(timeByUnit);
    }

    private static String getTimeText(Map<String, Integer> timeByUnit) {
        StringBuffer timeText = new StringBuffer();
        int size = timeByUnit.size();
        AtomicInteger count = new AtomicInteger(1);
        timeByUnit.forEach((unit, value) -> {
            String units = (value > 1) ? unit + "s" : unit;
            String separator = isLastSeparator(size, count.get()) ? ", " : " and ";
            if (hasMoreValues(size, count.getAndIncrement())) {
                timeText.append(value).append(" ").append(units).append(separator);
            } else {
                timeText.append(value).append(" ").append(units);
            }
        });
        return timeText.toString();
    }

    private static boolean hasMoreValues(int size, int count) {
        return count != size;
    }

    private static boolean isLastSeparator(int size, int count) {
        return count + 1 != size;
    }

    private static Map calculateTimeByUnit(int secs) {
        LinkedHashMap timeByUnit = new LinkedHashMap<String, Integer>();
        Integer years = getUnitOfTime(secs, year.secs);
        if (years > 0) {
            timeByUnit.put(year.name(),years);
        }
        Integer days = getRemainingDays(secs);
        if (days > 0) {
            timeByUnit.put(day.name(), days);
        }
        Integer hours = getRemainingHours(secs);
        if (hours > 0) {
            timeByUnit.put(hour.name(), hours);
        }
        Integer mins = getRemainingMins(secs);
        if (mins > 0) {
            timeByUnit.put(minute.name(), mins);
        }
        Integer seconds = getRemainingSecs(secs);
        if (seconds > 0) {
            timeByUnit.put(second.name(), seconds);
        }
        return timeByUnit;
    }

    private static Integer getRemainingDays(int secs) {
        return getUnitOfTime(secs, day.secs) - (365 * getUnitOfTime(secs, year.secs));
    }


    private static Integer getRemainingSecs(int secs) {
        return secs % minute.secs;
    }

    private static Integer getRemainingMins(int secs) {
        return getUnitOfTime(secs, minute.secs) - (minute.secs * getUnitOfTime(secs, hour.secs));
    }

    private static Integer getRemainingHours(int secs) {
        return getUnitOfTime(secs, hour.secs) - (24 * getUnitOfTime(secs, day.secs));
    }

    private static Integer getUnitOfTime(int secs, int units) {
        return secs / units;
    }


}
