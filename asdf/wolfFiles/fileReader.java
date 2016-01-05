// John D'Angelo
// Period 4
import java.util.*;
import java.io.*;

public class fileReader
{
	private static HashMap allFiles = new HashMap();
	static Scanner sc = new Scanner(System.in);
	
	public static void main( String[] args ) throws IOException
	{
		play();
		//System.out.println(((HashMap)allFiles.get("events")).get("Room 5"));
	}
	
	private static void play() throws IOException
	{
		System.out.println("Loading...");
		load();
		System.out.println("Done!\n\nPress any key to continue...");
		sc.nextLine();
	}
	
	private static void load() throws IOException							//Add the game files to allFiles
	{
		allFiles.put("battles",read("battles.txt"));
		allFiles.put("areas",read("areas.txt"));
		allFiles.put("events",read("events.txt"));
	}
	
	private static HashMap read(String fileName) throws IOException			//Read the given file name into a Hash Map
	{
		HashMap map = new HashMap();
		String name = new String();
		String desc = new String();
		String next = new String();
		Scanner sc = new Scanner(new File("wolfFiles\\"+fileName));
		
		while(sc.hasNextLine()){
			if(sc.nextLine().equals("%")){			//Find first line of a section
				name = sc.nextLine();
				next = sc.nextLine();
				
				while(!next.equals("%")){			//Get description of the event
					desc += next + "\n";
					next = sc.nextLine();
				}
			}
			map.put(name,desc);						//Add the name & description to the map
			name = desc = "";						//Clear the Strings for next input
		}
		return map;
	}
}