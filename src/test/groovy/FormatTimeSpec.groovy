import spock.lang.Specification


class FormatTimeSpec extends Specification {

    def "time of zero returns none"() {

        TimeFormatter timeFormatter = new TimeFormatter();
        String returnedTime = timeFormatter.getTime(0);
        expect: returnedTime == "none";
    }

    /**
     * test('time of seconds returns seconds only', () => {*   expect(formatTime(2)).toBe('2 seconds');
     * });
     */


    def "time of seconds returns seconds only"() {
//TODO
        TimeFormatter timeFormatter = new TimeFormatter();
        String returnedTime = timeFormatter.getTime(0);
        expect: returnedTime == "none";
    }

}