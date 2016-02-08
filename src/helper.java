//John D'Angelo

import java.util.*;
import java.io.*;

public class helper
{
	public static void main(String args[]) throws IOException, InterruptedException{}
	
	public static ArrayList<String> asArrayList(String[] arr){
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(Arrays.asList(arr));
		return temp;
	}
	
	static int asInt(String s){
		return Integer.parseInt(s);
	}
	
	static double asDouble(String s) {
		return Double.parseDouble(s);
	}
	
	static void cls() throws IOException, InterruptedException{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}