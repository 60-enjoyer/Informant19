package org.example.service;

import org.example.models.Models;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class MetricService {
    private static final SystemInfo si = new SystemInfo();
    private static final HardwareAbstractionLayer hal = si.getHardware();
    private static final OperatingSystem os = si.getOperatingSystem();

    private static long[] prevCpuTicks = hal.getProcessor().getSystemCpuLoadTicks();

    public static Models.Metrics collect() {

        Models.Metrics m = new Models.Metrics();

        m.timestamp = System.currentTimeMillis();

        CentralProcessor cpu = hal.getProcessor();
        m.cpuLoad = cpu.getSystemCpuLoadBetweenTicks(prevCpuTicks);
        prevCpuTicks = cpu.getSystemCpuLoadTicks();

        GlobalMemory mem = hal.getMemory();
        m.availableRam = mem.getAvailable();

        for (OSFileStore store : os.getFileSystem().getFileStores()) {
            if (store.getMount().equals("/") || store.getMount().equals("C:\\")) {
                m.freeDisk = store.getUsableSpace();
                break;
            }
        }

        long sent = 0;
        long recv = 0;
        for (NetworkIF net : hal.getNetworkIFs()) {
            net.updateAttributes();
            sent += net.getBytesSent();
            recv += net.getBytesRecv();
        }

        m.bytesSent = sent;
        m.bytesReceived = recv;

        m.systemUptime = os.getSystemUptime();

        return m;
    }
}
