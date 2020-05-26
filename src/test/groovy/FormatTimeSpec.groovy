import spock.lang.Specification
import spock.lang.Unroll


class FormatTimeSpec extends Specification {


    /**

     *
     * test('negative seconds', () => {
     *   expect(formatTime(-1)).toBe('none');
     * });
     * @return
     */
    @Unroll
    def "time of '#timeInSecs' returns '#expectedTime'"() {

        given: TimeFormatter timeFormatter = new TimeFormatter();
        when: String returnedTime = timeFormatter.convertTime(timeInSecs);
        then: returnedTime == expectedTime;
        where:
                timeInSecs  | expectedTime
                0           |"none"
                2           |"2 seconds"
                62          |"1 minute and 2 seconds"
                122         |"2 minutes and 2 seconds"
                120         |"2 minutes"
                3662        |"1 hour, 1 minute and 2 seconds"
                7262        |"2 hours, 1 minute and 2 seconds"
                7260        |"2 hours and 1 minute"
                7201        |"2 hours and 1 second"
                86400       |"1 day"
                86401       |"1 day and 1 second"
                86461       |"1 day, 1 minute and 1 second"
                90061       |"1 day, 1 hour, 1 minute and 1 second"
                31536000    |"1 year"
                62985600    |"1 year and 364 days"
                62985539    |"1 year, 363 days, 23 hours, 58 minutes and 59 seconds"
                -1          |"none"
        //TODO non numeric
    }




}