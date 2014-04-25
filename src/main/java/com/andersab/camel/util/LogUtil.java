package com.andersab.camel.util;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class LogUtil {
    public static final Logger transactionLogger = Logger.getLogger("TRANX");
    public static final Logger errorLogger = Logger.getLogger("ERROR");
    private static final String LINE_DASH = String.format("%60s", " ").replace(' ','-');
    public static final String errorLogFormat = "%s - %s - %s \n %s \n %s \n %s \n";

    public static void logError(String componentName, Throwable e, String message) {
        StringWriter stackTraceWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stackTraceWriter));
        errorLogger.info(String.format(errorLogFormat, componentName, message, e, LINE_DASH, stackTraceWriter.toString(), LINE_DASH));
    }
}
