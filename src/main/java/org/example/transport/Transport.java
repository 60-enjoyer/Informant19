package org.example.transport;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;
import java.net.Socket;

public class Transport {

    private String serverIp;
    private int serverPort;
    private PrintWriter out;

    private static final ObjectMapper mapper = new ObjectMapper();

    public Transport (String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        try {
            Socket socket = new Socket(serverIp, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // вызывается ОДИН раз при старте

    // универсальная отправка JSON
    public void send(Object payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
