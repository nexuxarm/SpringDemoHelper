package batch.helper;

public class ValidateHelper {
	public static boolean CheckNotNull(String s) {
		return (s != null && s.trim().length() > 0);
	}
	
	public static boolean IsNull(String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	public static boolean CheckArgs(String... args) {
		return (args != null && args.length > 0 && CheckNotNull(args[0]));
	}
}
