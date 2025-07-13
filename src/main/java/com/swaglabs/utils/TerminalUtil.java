package com.swaglabs.utils;

public class TerminalUtil {

    // String... command is used to accept a variable number of string arguments
    public static void executeCommand(String... command) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // This will inherit the I/O of the current process
            Process process = processBuilder.start();
            process.waitFor(); // Wait for the process to complete
            LogsUtil.info("Command executed successfully: " + String.join(" ", command));
            }
        catch (Exception e) {
             LogsUtil.error("Failed to execute command: " +  e.getMessage());
            }

    }

}
