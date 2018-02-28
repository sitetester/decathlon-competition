package Service.Score;

import Service.Entity.DecathlonEvent;

import java.util.HashMap;
import java.util.Map;

public class AthleteScoresCalculator {

    /**
     * Calculates event score for each athlete by applying respective formula
     * For verification of generated score, compare with https://www.usatf.org/statistics/calculators/combinedEventsScoring/
     */
    public Map<String, Integer> calculate(Map<String, Map<String, Float>> eventScoresByAthletes) {

        Map<String, Integer> athleteTotalEventsScoreResult = new HashMap<>();

        for (Map.Entry<String, Map<String, Float>> entry : eventScoresByAthletes.entrySet()) {
            int athleteTotalEventsCalculatedScore = 0;

            for (Map.Entry<String, Float> eventScoreEntry : entry.getValue().entrySet()) {
                athleteTotalEventsCalculatedScore += this.calculateEventScore(
                        DecathlonEvent.valueOf(eventScoreEntry.getKey()),
                        eventScoreEntry.getValue()
                );
            }

            athleteTotalEventsScoreResult.put(entry.getKey(), athleteTotalEventsCalculatedScore);
        }

        return athleteTotalEventsScoreResult;
    }

    /**
     * Applies score calculation formula based on event type
     * <p>
     * Formulas are taken from https://en.wikipedia.org/wiki/Decathlon#Points_system
     */
    public int calculateEventScore(DecathlonEvent decathlonEvent, float eventScore) {

        if (this.getEventType(decathlonEvent.name()).equals("TRACK")) {
            return this.calculateTrackEventScore(decathlonEvent, eventScore);
        } else {
            return this.calculateFieldEventScore(decathlonEvent, eventScore);
        }
    }

    /**
     * Either TRACK or FIELD
     */
    private String getEventType(String eventName) {
        return eventName.substring(0, eventName.indexOf("_"));
    }

    /**
     * Points = INT(A(B — P)C) for track events (faster time produces a better score)
     * <p>
     * A, B and C are parameters that vary by discipline, while P is the performance by the athlete,
     * measured in seconds (running), metres (throwing), or centimetres (jumping).
     * <p>
     * Also see - https://en.wikipedia.org/wiki/Decathlon#Benchmarks for unit of measurement for each event type
     *
     * @return int
     */
    private int calculateTrackEventScore(DecathlonEvent decathlonEvent, float eventScore) {

        return (int) (decathlonEvent.getA() * Math.pow((decathlonEvent.getB() - eventScore), decathlonEvent.getC()));
    }

    /**
     * Points = INT(A(P — B)^C) for field events (greater distance or height produces a better score)
     * <p>
     * A, B and C are parameters that vary by discipline, while P is the performance by the athlete,
     * measured in seconds (running), metres (throwing), or centimetres (jumping).
     * <p>
     * Also see - https://en.wikipedia.org/wiki/Decathlon#Benchmarks for unit of measurement for each event type
     *
     * @return int
     */
    private int calculateFieldEventScore(DecathlonEvent decathlonEvent, float eventScore) {

        // Since jumping events needs to be calculated in centimetres
        if (decathlonEvent.name().equals("FIELD_LONG_JUMP")
                || decathlonEvent.name().equals("FIELD_HIGH_JUMP")
                || decathlonEvent.name().equals("FIELD_POLE_VAULT")) {

            eventScore *= 100;
        }

        float difference = (eventScore - decathlonEvent.getB());
        double diffToPower = Math.pow(difference, decathlonEvent.getC());

        return (int) Math.floor(decathlonEvent.getA() * diffToPower);
    }
}
