package scondor;

public class Console {
	
	public static void info(String msg) {
		System.out.println(CMDTool.INFO + msg);
	}
	
	public static void warn(String msg) {
		System.out.println(CMDTool.WARN + msg);
	}
	
	public static void error(String msg) {
		System.out.println(CMDTool.ERROR + msg);
	}
	
}