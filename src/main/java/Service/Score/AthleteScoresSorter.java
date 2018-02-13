package Service.Score;

import java.util.*;

public class AthleteScoresSorter {

    /**
     * Compares the given map scores & returns sorted scores
     */
    public static Map<String, Integer> sort(Map<String, Integer> athleteEventScoresResult) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(athleteEventScoresResult.entrySet());

        // sorting the list based on values
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // maintaining insertion order with the help of LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
