package org.example.service;

import org.example.models.Models;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;

public class RegisterService {
    public static Models.Register collect(int agentId, String agentName) {

        Models.Register r = new Models.Register();
        r.agentId = agentId;
        r.agentName = agentName;

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        r.hostname = os.getNetworkParams().getHostName();
        r.osName = os.getFamily();
        r.osVersion = os.getVersionInfo().getVersion();
        r.architecture = System.getProperty("os.arch");

        for (NetworkIF net : hal.getNetworkIFs()) {
            if (!net.getMacaddr().isEmpty()) {
                r.macAddress = net.getMacaddr();
                break;
            }
        }

        r.totalRam = hal.getMemory().getTotal();

        long disk = 0;
        for (HWDiskStore d : hal.getDiskStores()) {
            disk += d.getSize();
        }
        r.totalDisk = disk;

        CentralProcessor cpu = hal.getProcessor();
        r.vendor = cpu.getProcessorIdentifier().getVendor();
        r.model = cpu.getProcessorIdentifier().getName();
        r.physicalCores = cpu.getPhysicalProcessorCount();
        r.logicalCores = cpu.getLogicalProcessorCount();

        long maxFreq = cpu.getMaxFreq();
        r.maxFrequency = maxFreq > 0 ? maxFreq : -1;

        return r;
    }
}
