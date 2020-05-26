import spock.lang.Specification
import spock.lang.Unroll


class FormatTimeSpec extends Specification {

    @Unroll
    def "time of '#timeInSecs' returns '#expectedTime'"() {

        given: TimeFormatter timeFormatter = new TimeFormatter();
        when: String returnedTime = timeFormatter.convertTime(timeInSecs);
        then: returnedTime == expectedTime;
        where:
                timeInSecs  | expectedTime
                0           |"none"
                2           |"2 seconds"
    }




}