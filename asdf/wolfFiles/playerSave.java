// John D'Angelo
// Period 4
import java.util.*;
import java.io.*;

public class playerSave
{
        private static HashMap<String,HashMap> allFiles = new HashMap<>();
	private static int health,defence,strength,wealth,invSize,xpAtk,xpDef;
	private static double creed;
	private static String saveName,name;
	private static String[] location = new String[3];
	//private equipment[] equipped;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
	    load();
            menu();
	}
        
	private static void load() throws IOException					//Load files in allFiles
	{
		System.out.println("Loading...");
		allFiles.put("battles",read("battles.txt"));
		allFiles.put("areas",read("areas.txt"));
		allFiles.put("events",read("events.txt"));
		System.out.println("Done!\n\nPress enter to continue...");
		//System.out.println(allFiles.get("battles").get("Tutorial"));
		sc.nextLine();
	}
	
	private static HashMap read(String fileName) throws IOException			//Read the given file name into a Hash Map
	{
		HashMap map = new HashMap();
		String name = new String();
		String desc = new String();
		String next = new String();
		Scanner reader = new Scanner(new File("D:\\zPrograms\\javaCode\\src\\allCode\\wolfFiles\\"+fileName));
		
		while(reader.hasNextLine()){
			if(reader.nextLine().equals("%")){			//Find first line of a section
				name = reader.nextLine();
				next = reader.nextLine();
				
				while(!next.equals("%")){			//Get description of the event
					desc += next + "\n";
					next = reader.nextLine();
				}
			}
			map.put(name,desc);					//Add the name & description to the map
			name = desc = "";					//Clear the Strings for next input
		}
		return map;
	}
	
        private static void menu() throws IOException					//Print main menu and process user selections
	{
            System.out.println("big ascii text art");
            System.out.println("Created by Harry Wang & John D'Angelo\n\nType an option:\nNew Game\nLoad\nOptions\nQuit");
            
            String next = sc.nextLine().trim();		    //Get user input then go to menu selection
            if(next.equalsIgnoreCase("New Game")){
                newGame();
            }
            else if(next.equalsIgnoreCase("Load")){
                System.out.println("load");
            }
            else if(next.equalsIgnoreCase("Options")){
                System.out.println("options");
            }
            else if(next.equalsIgnoreCase("Quit")||next.equalsIgnoreCase("an option")){
                System.out.println("quit");
            }
	    else{
		System.out.println("What was that again?\n");
		menu();
	    }
        }
	
	private static void newGame() throws IOException				//Create new save file with given name
	{
		System.out.println("What is the name of this save?");
		saveName = sc.next();
		System.out.println("\nWhat is your player name?");
		name = sc.next();
		
		File folder = new File("D:\\zPrograms\\javaCode\\src\\allCode\\wolfFiles\\saves");
		File save = new File(folder.getPath() + "\\" + saveName + ".sav");
		save.createNewFile();
		
		location = new String[]{"1", "events.txt", "Room 5"};	    //(area number, file of location, actual location)
		health = 100;                           //static for now
		creed = 1;				//increases by 3/4 of enemy's creed
		wealth = 0;
		invSize = 8;                    	//store weapons, armor, and food    -dont think we need this at all
		defence = 1;                    	//xpDef req to raise = (2^def * 10)/4
		strength = 1;                           //xpAtk req to raise = (2^str * 10)/2
		xpAtk = 0;				//dmgGiven/4 * creed/10(int)	always at least 1
		xpDef = 0;				//dmgTaken/4 * creed/10(int)	always at least 1
		
		FileWriter fw = new FileWriter(save);					//write everything to the save file
		String line = System.lineSeparator();
		
		fw.write("" + name);
		fw.write(line + Arrays.toString(location));
		fw.write(line + creed);
		fw.write(line + wealth);
		fw.write(line + invSize);
		fw.write(line + defence);
		fw.write(line + strength);
		fw.write(line + xpAtk);
		fw.write(line + xpDef);
		
		fw.flush();
                fw.close();
		//start/play a new game
	}
}