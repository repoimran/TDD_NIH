package util;

public class Misc {
	
	public static String getClassName(Thread curTh) {
		 	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement caller = stackTrace[2]; // The caller is always at index 2
		    String className = caller.getClassName();
		    String methodName = caller.getMethodName();
			return className + "--" + methodName;
	}

}
