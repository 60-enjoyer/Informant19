package org.example.models;

public class Models {

    /* ===================== FirstStart ===================== */

    public static class Register {
        public int agentId;        // UUID
        public String agentName;
        public String hostname;       // PC_name

        public String osName;
        public String osVersion;
        public String architecture;

        public String macAddress;

        public long totalRam;         // bytes
        public long totalDisk;        // bytes

        //CPU_Full_Info
        public String vendor;
        public String model;
        public int physicalCores;
        public int logicalCores;
        public long maxFrequency;     // Hz
    }

    /* ====================== EverySecond ===================== */

    public static class Metrics {
        public int agentId;

        public long timestamp;

        // CPU
        public double cpuLoad;          // 0.0 - 1.0

        /* RAM */
        public long availableRam;       // bytes

        // DISK
        public long freeDisk;           // bytes (root / выбранный mount)

        // NETWORK (counters)
        public long bytesSent;          // total bytes
        public long bytesReceived;      // total bytes

        // SYSTEM
        public long systemUptime;       // seconds
    }
}
