package co.uk.epicguru.logging;

public final class Log {

	private static void print(final String string){
		
		// Default implementation, just print to console.		
		System.out.println(string);
	}
	
	public static void info(final Object text){
		
		if(text == null){
			print("null");
		}else{
			if(text.toString() == null){
				print("ERROR_NULL_TO-STRING");
			}else{
				print(text.toString());
			}
		}
	}
	
}
