package Service.Score;

import Service.Entity.DecathlonEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AthleteScoresCalculatorTest {

    /**
     * Check values from https://www.usatf.org/statistics/calculators/combinedEventsScoring/
     */
    @Test
    public void calculate() {

        assertEquals(
                397,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.TRACK_100M, 13.43f)
        );

        assertEquals(
                264,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_LONG_JUMP, 4.35f)
        );

        assertEquals(
                404,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_SHOT_PUT, 8.64f)
        );

        assertEquals(
                389,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_HIGH_JUMP, 1.50f)
        );

        assertEquals(
                230,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.TRACK_RUN_400M, 66.06f)
        );

        assertEquals(
                428,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.TRACK_110M_HURDLES, 19.05f)
        );

        assertEquals(
                365,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_DISCUS_THROW, 24.89f)
        );

        assertEquals(
                179,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_POLE_VAULT, 2.20f)
        );

        assertEquals(
                348,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.FIELD_JAVELIN_THROW, 33.48f)
        );

        // input value should be in seconds. i.e. 6.51 to sec = 360 + 51 = 411
        assertEquals(
                95,
                new AthleteScoresCalculator().calculateEventScore(DecathlonEvent.TRACK_RUN_1500M, 411f)
        );
    }
}