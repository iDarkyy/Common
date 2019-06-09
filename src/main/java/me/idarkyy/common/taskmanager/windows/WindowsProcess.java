package me.idarkyy.common.taskmanager.windows;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsProcess {
    private static final String TASKKILL_EXECUTABLE = "taskkill.exe $force /PID $pid";

    private final String name;
    private final int processId;
    private final String sessionName;
    private final int session;
    private final double memoryUsage;

    protected WindowsProcess(String name, int processId, String sessionName, int session, double memoryUsage) {
        this.name = name;
        this.processId = processId;
        this.sessionName = sessionName;
        this.session = session;
        this.memoryUsage = memoryUsage;
    }

    public boolean killTask(boolean force) throws IOException {
        String executable = TASKKILL_EXECUTABLE.replace(" $force", force ? " /f" : "").replace("$pid", String.valueOf(getProcessId()));

        Process process = Runtime.getRuntime().exec(WindowsTaskManager.WINDIR + File.separator + "system32" + File.separator + executable);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        return input.readLine().startsWith("SUCCESS");

    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public String getSessionName() {
        return sessionName;
    }

    public int getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

    public int getProcessId() {
        return processId;
    }

    @Override
    public String toString() {
        return "{name:\"" + name + "\",id:" + processId + ",session:" + session + ",session-name:\"" + sessionName + "\",usage:" + memoryUsage + "KB}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WindowsProcess)) {
            return false;
        }

        return obj.toString().equals(toString());
    }
}
