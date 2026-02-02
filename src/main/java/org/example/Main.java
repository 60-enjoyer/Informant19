package org.example;

import org.example.cache.RegisterCache;
import org.example.config.AgentConfig;
import org.example.config.ConfigLoader;
import org.example.models.Models;
import org.example.service.MetricService;
import org.example.service.RegisterService;
import org.example.transport.Transport;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        AgentConfig cfg = ConfigLoader.load("agent.conf");

        File registerFile = new File("register.json");

        Models.Register newReg = RegisterService.collect(cfg.agentId);
        Models.Register oldReg = RegisterCache.load(registerFile);

        if (RegisterCache.changed(oldReg, newReg)) {
            Transport.send(newReg);
            RegisterCache.save(registerFile, newReg);
        }


        while (true) {
            long start = System.currentTimeMillis();

            Models.Metrics m = MetricService.collect();
            Transport.send(m);
            Transport.sendMetrics(m);

            long sleep = cfg.interval - (System.currentTimeMillis() - start);
            if (sleep > 0) Thread.sleep(sleep);
        }
    }
}

