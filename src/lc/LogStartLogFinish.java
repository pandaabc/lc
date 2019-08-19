package lc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Google HF
 * Log start and log finish
 */
public class LogStartLogFinish {

    LinkedHashMap<String, Log> logMap = new LinkedHashMap<>();
    public void start(long timeStamp, String requestId) {
        logMap.put(requestId, new Log(timeStamp));
    }

    public void end (long timeStamp, String requestId) {
        logMap.get(requestId).end = timeStamp;
    }

}

class Log {
    String id;
    long start;
    long end;
    public Log (long start) {
        this.start = start;
    }
}
