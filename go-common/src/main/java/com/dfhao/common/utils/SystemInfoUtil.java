package com.dfhao.common.utils;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统信息
 *
 * @author :  dfhao
 * @date :  2021/3/16 22:46
 */
public class SystemInfoUtil {
    private static final int CPUTIME = 500;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;


    //获取内存使用率
    public static String getMemory() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存+虚拟内存
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        double compare = (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
        return "内存已使用:" + (int) compare + "%";
    }

    //获取文件系统使用率
    public static List<String> getDisk() {
        // 操作系统
        List<String> list = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            String dirName = c + ":/";
            File win = new File(dirName);
            if (win.exists()) {
                long total = win.getTotalSpace();
                long free = win.getFreeSpace();
                double compare = (1 - free * 1.0 / total) * 100;
                String str = c + ":盘 已使用 " + (int) compare + "%";
                list.add(str);
            }
        }
        return list;
    }

    //获得cpu使用率
    public static String getCpuRatioForWindows() {
        try {
            String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPUTIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return "CPU使用率:" + Double.valueOf(PERCENT * (busytime) * 1.0 / (busytime + idletime)).intValue() + "%";
            } else {
                return "CPU使用率:" + 0 + "%";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "CPU使用率:" + 0 + "%";
        }
    }

    //读取cpu相关信息
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation
                String caption = substring(line, capidx, cmdidx - 1).trim();
                String cmd = substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.contains("wmic.exe")) {
                    continue;
                }
                String s1 = substring(line, kmtidx, rocidx - 1).trim();
                String s2 = substring(line, umtidx, wocidx - 1).trim();
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    if (s1.length() > 0)
                        idletime += Long.parseLong(s1);
                    if (s2.length() > 0)
                        idletime += Long.parseLong(s2);
                    continue;
                }
                if (s1.length() > 0)
                    kneltime += Long.parseLong(s1);
                if (s2.length() > 0)
                    usertime += Long.parseLong(s2);
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        StringBuilder tgt = new StringBuilder();
        for (int i = start_idx; i <= end_idx; i++) {
            tgt.append((char) b[i]);
        }
        return tgt.toString();
    }
}
