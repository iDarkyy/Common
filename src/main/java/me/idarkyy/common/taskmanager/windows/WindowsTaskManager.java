package me.idarkyy.common.taskmanager.windows;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WindowsTaskManager {
    protected static final String WINDIR = System.getenv("windir");

    public static List<WindowsProcess> getRunningTasks() throws IOException {
        Process process = Runtime.getRuntime().exec(WINDIR + File.separator + "system32" + File.separator + "tasklist.exe /fo csv /nh");

        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        List<WindowsProcess> processes = new ArrayList<>();

        String line;
        while ((line = input.readLine()) != null) {
            if (line.startsWith("Image")) {
                continue;
            }

            String[] info = line.replace("\"", "").split(",");
            processes.add(new WindowsProcess(
                    info[0], // name
                    Integer.valueOf(info[1]), // id
                    info[2], // sessionName
                    Integer.valueOf(info[3]), // sessionId
                    Double.valueOf((info[4].endsWith("K") ? info[4] : info[5]).replaceAll("[K ,.]", "")))); // kbUsage
        }

        return processes;
    }

    public static WindowsProcess getTaskByProcessId(int id) throws IOException {
        for (WindowsProcess process : getRunningTasks()) {
            if (process.getProcessId() == id) {
                return process;
            }
        }

        return null;
    }

    public static List<WindowsProcess> getTasksByName(String name) throws IOException {
        List<WindowsProcess> processes = new ArrayList<>();

        for (WindowsProcess process : getRunningTasks()) {
            if (process.getName().startsWith(name)) {
                processes.add(process);
            }
        }

        return processes;
    }
}
