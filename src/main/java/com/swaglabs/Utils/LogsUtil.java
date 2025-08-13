package com.swaglabs.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtil {

    public static final String LOGS_PATH = "test-outputs/Logs";

    // Private constructor to prevent instantiation
    private LogsUtil() {
    }

    // Method to get a logger instance for the calling class
    public static Logger logger (){

        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());

    }

    // "String... message" allows passing more than one message as separate arguments
    // This will concatenate the messages with a space in between
    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }


}
