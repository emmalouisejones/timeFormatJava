public class TimeFormatter {
    public static String convertTime(Integer seconds) {
        return (seconds <= 0) ? "none" : calculateTime(seconds);
    }

    private static String calculateTime(Integer seconds) {
        return seconds.toString() + " seconds";
    }

}
