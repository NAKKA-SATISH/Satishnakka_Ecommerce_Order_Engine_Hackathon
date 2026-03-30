package ECommerceOrderEngine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditLogger {
    private static final List<String> logs = new ArrayList<>();

    public static void log(String message) {
        logs.add(LocalDateTime.now() + " | " + message);
    }

    public static void showLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}