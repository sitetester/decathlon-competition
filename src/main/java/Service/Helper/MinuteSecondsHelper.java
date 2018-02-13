package Service.Helper;

public class MinuteSecondsHelper {

    /**
     * Converts min.sec value into sec
     */
    public static float getInSeconds(String value) {

        String[] minSec = value.split("\\.");
        int sec = Integer.parseInt(minSec[0]) * 60 + Integer.parseInt(minSec[1]);

        return (float) sec;
    }
}
