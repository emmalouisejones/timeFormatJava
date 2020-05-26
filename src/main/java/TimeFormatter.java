import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        StringBuffer timeText = new StringBuffer();
        int size = timeByUnit.size();
        AtomicInteger count = new AtomicInteger(1);
        timeByUnit.forEach((unit, value) -> {
            String units = (value > 1) ? unit + "s" : unit;
            String separator = isLastSeparator(size, count.get()) ? ", " : " and ";
            if (hasMoreValues(size, count.getAndIncrement())) {
                timeText.append(value);
                timeText.append(" ");
                timeText.append(units);
                timeText.append(separator);
            } else {
                timeText.append(value);
                timeText.append(" ");
                timeText.append(units);
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
        Integer years = getUnitOfTime(secs, UnitOfTime.year.secs);
        if (years > 0) {
            timeByUnit.put(UnitOfTime.year.name(),years);
        }
        Integer days = getRemainingDays(secs);
        if (days > 0) {
            timeByUnit.put(UnitOfTime.day.name(), days);
        }
        Integer hours = getRemainingHours(secs);
        if (hours > 0) {
            timeByUnit.put(UnitOfTime.hour.name(), hours);
        }
        Integer mins = getRemainingMins(secs);
        if (mins > 0) {
            timeByUnit.put(UnitOfTime.minute.name(), mins);
        }
        Integer seconds = getRemainingSecs(secs);
        if (seconds > 0) {
            timeByUnit.put(UnitOfTime.second.name(), seconds);
        }
        return timeByUnit;
    }

    private static Integer getRemainingDays(int secs) {
        return getUnitOfTime(secs, UnitOfTime.day.secs) - (365 * getUnitOfTime(secs, UnitOfTime.year.secs));
    }


    private static Integer getRemainingSecs(int secs) {
        return secs % UnitOfTime.minute.secs;
    }

    private static Integer getRemainingMins(int secs) {
        return getUnitOfTime(secs, UnitOfTime.minute.secs) - (UnitOfTime.minute.secs * getUnitOfTime(secs, UnitOfTime.hour.secs));
    }

    private static Integer getRemainingHours(int secs) {
        return getUnitOfTime(secs, UnitOfTime.hour.secs) - (24 * getUnitOfTime(secs, UnitOfTime.day.secs));
    }

    private static Integer getUnitOfTime(int secs, int units) {
        return secs / units;
    }


}
