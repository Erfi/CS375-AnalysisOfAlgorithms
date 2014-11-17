
public class Timer {
	private static long startTime;
	private static long endTime;
	
	public static void start(){
		startTime = System.nanoTime();
	}
	
	public static void stop(){
		endTime = System.nanoTime();
	}
	
	//gives the runtime is miliseconds
	public static long getRunTime(){
		return (endTime - startTime) / 1000000;
	}
}
