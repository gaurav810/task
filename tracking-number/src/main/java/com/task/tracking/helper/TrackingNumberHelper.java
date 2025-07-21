package com.task.tracking.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;

@Component
public class TrackingNumberHelper {

    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generate() {
        String timestampPart = Long.toString(Instant.now().toEpochMilli(), 36).toUpperCase();
        String randomPart = getRandomAlphaNumeric(16 - timestampPart.length());
        return (timestampPart + randomPart).substring(0, 16);
    }

    private String getRandomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            builder.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return builder.toString();
    }
}
