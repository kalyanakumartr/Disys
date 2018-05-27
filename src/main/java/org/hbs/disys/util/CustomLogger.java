package org.hbs.disys.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class CustomLogger extends Logger {
	public CustomLogger(Class<?> clazz) {
		super(clazz.getName());
	}

	public void debug(Exception exception) {
		super.debug(getExceptionTraceMessage(exception));
	}

	public void error(Exception exception) {
		super.error(getExceptionTraceMessage(exception));
	}

	public void fatal(Exception exception) {
		super.fatal(getExceptionTraceMessage(exception));
	}

	private String getExceptionTraceMessage(Exception exception) {
		StringWriter logMessageWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(logMessageWriter));
		return (logMessageWriter == null ? "No Exception Trace"
				: logMessageWriter.toString());
	}

	public void info(Exception exception) {
		super.info(getExceptionTraceMessage(exception));
	}

	public void trace(Exception exception) {
		super.trace(getExceptionTraceMessage(exception));
	}

	public void warn(Exception exception) {
		super.warn(getExceptionTraceMessage(exception));
	}

}
