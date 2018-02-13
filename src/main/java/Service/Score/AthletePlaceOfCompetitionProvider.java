package Service.Score;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AthletePlaceOfCompetitionProvider {

    private Map<Integer, Map<String, Integer>> mapOfCountAndStartingIndexByTotalEventsScore = new HashMap<>();

    /**
     * Takes a collection of athlete=>score & returns athlete=>place collection
     * "place" can be in range (e.g 1-2) in case of first two same scores & so on.
     */
    public Map<String, String> providePlaceOfCompetition(Map<String, Integer> athleteSortedScoreResult) {

        Map<String, String> placeOfCompetition = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : athleteSortedScoreResult.entrySet()) {

            // don't repeat loop for same stored score (athleteTotalEventsScore)
            if (!this.mapOfCountAndStartingIndexByTotalEventsScore.containsKey(entry.getValue())) {
                this.countNumberOfOccurrences(athleteSortedScoreResult, entry.getValue());
            }

            int startingIndex = this.mapOfCountAndStartingIndexByTotalEventsScore.get(entry.getValue()).get("startingIndex");
            int count = this.mapOfCountAndStartingIndexByTotalEventsScore.get(entry.getValue()).get("count");

            int to = startingIndex + (count - 1);

            placeOfCompetition.put(entry.getKey(), count == 1 ? String.valueOf(startingIndex) : startingIndex + "-" + to);
        }

        return placeOfCompetition;
    }

    /**
     * Increments count for multiple occurrences of same score, but stores only first found index
     */
    private void countNumberOfOccurrences(Map<String, Integer> athleteSortedScoreResult, int athleteTotalEventsScore) {

        Map<String, Integer> mapOfCountAndStartingIndex = new HashMap<>();

        int count = 0;
        boolean startingIndexFound = false;
        int sortedPosition = 0;

        for (Map.Entry<String, Integer> entry : athleteSortedScoreResult.entrySet()) {
            sortedPosition++;

            if (athleteTotalEventsScore == entry.getValue()) {
                count += 1;

                if (!startingIndexFound) {
                    mapOfCountAndStartingIndex.put("startingIndex", sortedPosition);
                    startingIndexFound = true;
                }
            }
        }

        mapOfCountAndStartingIndex.put("count", count);

        this.mapOfCountAndStartingIndexByTotalEventsScore.put(athleteTotalEventsScore, mapOfCountAndStartingIndex);
    }
}
