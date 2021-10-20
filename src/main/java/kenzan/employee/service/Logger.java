package kenzan.employee.service;

import org.owasp.esapi.ESAPI;
import org.slf4j.helpers.MessageFormatter;

public final class Logger {
	
	private final org.owasp.esapi.Logger esapiLogger;
	
	private Logger(Class c) {
		this.esapiLogger=ESAPI.getLogger(c);
	}
	
	public static Logger in(Class c) {
		return new Logger(c);
	}
	
	public void error(String message, Throwable t) {
		esapiLogger.error(org.owasp.esapi.Logger.EVENT_FAILURE, message, t);
	}
	
	public void error(String message, Object... params) {
		esapiLogger.error(org.owasp.esapi.Logger.EVENT_FAILURE, 
				MessageFormatter.format(message, params).getMessage());
	}
	
	public void info(String message, Object... params) {
		esapiLogger.error(org.owasp.esapi.Logger.EVENT_SUCCESS, 
				MessageFormatter.format(message, params).getMessage());
	}

}
