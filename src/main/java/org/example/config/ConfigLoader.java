package org.example.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    public static AgentConfig load(String path) throws Exception {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }

        AgentConfig c = new AgentConfig();
        c.agentId = props.getProperty("agent.id");
        c.serverIp = props.getProperty("server.ip");
        c.serverPort = Integer.parseInt(props.getProperty("server.port"));
        c.interval = Long.parseLong(props.getProperty("metrics.interval"));

        return c;
    }
}
