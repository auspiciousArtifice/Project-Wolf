/*spiral6 - Shamee Mahmud
 * 
 */

package wolfFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Helper {

	public static void cls() throws IOException, InterruptedException{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		//This command doesn't work in Eclipse. Makes sense.
	}
	
	public static ArrayList<String> asArrayList(String[] arr){
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(Arrays.asList(arr));
		return temp;
	}
	
	public static double asDouble(String s){
		return Double.parseDouble(s);
	}
	
	public static double asInt(String s){
		return Integer.parseInt(s);
	}
	
	
	
}
