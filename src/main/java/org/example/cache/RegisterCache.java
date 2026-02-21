package org.example.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Models;

import java.io.File;
import java.util.Objects;

public class RegisterCache {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Models.Register load(File file) {
        try {
            return mapper.readValue(file, Models.Register.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void save(File file, Models.Register r) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, r);
    }

    public static boolean changed(Models.Register a, Models.Register b) {
        if (a == null || b == null) return true;

        return a.totalRam != b.totalRam
                || a.totalDisk != b.totalDisk
                || a.agentId != b.agentId
                || !Objects.equals(a.agentName, b.agentName)
                || !safeEquals(a.model, b.model)
                || !safeEquals(a.macAddress, b.macAddress);
    }

    private static boolean safeEquals(String a, String b) {
        if (a == null) return b == null;
        return a.equals(b);
    }
}
