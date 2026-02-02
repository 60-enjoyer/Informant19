package org.example.transport;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Models;

import java.io.PrintWriter;
import java.net.Socket;

public class Transport {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void send(Object payload) throws Exception {
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(payload);

        System.out.println("SEND TO SERVER:");
        System.out.println(json);
    }
    public static void sendMetrics(Models.Metrics m) {
        try (
                Socket socket = new Socket("127.32.0.1", 11984);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("Timestamp: " + m.timestamp);
            out.println("CPU Load: " + m.cpuLoad);
            out.println("Available RAM: " + m.availableRam);
            out.println("Free Disk: " + m.freeDisk);
            out.println("Bytes Sent: " + m.bytesSent);
            out.println("Bytes Received: " + m.bytesReceived);
            out.println("System Uptime: " + m.systemUptime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
