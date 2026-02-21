package org.example.config;

import java.io.*;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ConfigLoader {

    public static AgentConfig load(String path) throws Exception {
        Properties props = new Properties();
        File file = new File(path);

        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
        }

        // === agentId ===
        String agentIdStr = props.getProperty("agent.id");
        int agentId;

        if (agentIdStr == null || agentIdStr.isBlank()) {
            agentId = ThreadLocalRandom.current().nextInt(10000000, 99999999);
            props.setProperty("agent.id", String.valueOf(agentId));

            try (FileOutputStream fos = new FileOutputStream(file)) {
                props.store(fos, "Generated agent.id");
            }
        } else {
            agentId = Integer.parseInt(agentIdStr);
        }

        AgentConfig c = new AgentConfig();
        c.agentId = agentId;
        c.agentName = props.getProperty("agent.Name");
        c.serverIp = props.getProperty("server.ip");
        c.serverPort = Integer.parseInt(props.getProperty("server.port"));
        c.interval = Long.parseLong(props.getProperty("metrics.interval"));

        return c;
    }
}
