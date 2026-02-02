package org.example.util;

import org.example.models.Models;

public class Console {
    public static void printRegister(Models.Register r) {

        System.out.println("===== REGISTER INFO =====");

        System.out.println("Agent ID: " + r.agentId);
        System.out.println("Hostname: " + r.hostname);

        System.out.println("OS: " + r.osName + " " + r.osVersion);
        System.out.println("Architecture: " + r.architecture);

        System.out.println("MAC: " + r.macAddress);

        System.out.println("Total RAM: " + r.totalRam + " bytes");
        System.out.println("Total Disk: " + r.totalDisk + " bytes");

        System.out.println("CPU Vendor: " + r.vendor);
        System.out.println("CPU Model: " + r.model);
        System.out.println("Physical Cores: " + r.physicalCores);
        System.out.println("Logical Cores: " + r.logicalCores);
        System.out.println("Max Frequency: " + r.maxFrequency + " Hz");

        System.out.println("=========================\n");
    }


    public static void printMetrics(Models.Metrics m) {

        System.out.println("===== METRICS =====");

        System.out.println("Timestamp: " + m.timestamp);
        System.out.println("CPU Load: " + String.format("%.2f", m.cpuLoad * 100) + " %");

        System.out.println("Available RAM: " + m.availableRam + " bytes");
        System.out.println("Free Disk: " + m.freeDisk + " bytes");

        System.out.println("Bytes Sent: " + m.bytesSent);
        System.out.println("Bytes Received: " + m.bytesReceived);

        System.out.println("System Uptime: " + m.systemUptime + " sec");

        System.out.println("===================\n");
    }
}
