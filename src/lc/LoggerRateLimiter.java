package lc;

import java.util.*;
import java.util.stream.Collectors;

public class LoggerRateLimiter {


}

class Logger {
    private Map<Integer, Set<String>> messages;
    /** Initialize your data structure here. */
    public Logger() {
        this.messages = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean res = hasPrintIn10(timestamp, message);
        if (!res) {
            this.messages.computeIfAbsent(timestamp, k -> new HashSet<>()).add(message);
        }
        return !res;
    }

    public boolean hasPrintIn10(int timestamp, String message) {
        this.messages = this.messages.keySet().stream()
                .filter(e -> timestamp - e < 10)
                .collect(Collectors.toMap(e -> e, e -> this.messages.get(e)));
        return this.messages.entrySet().stream()
                .anyMatch(e -> e.getValue().contains(message));

    }


}