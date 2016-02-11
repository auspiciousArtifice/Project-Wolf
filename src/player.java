// John D'Angelo

import java.util.*;
import java.io.*;

public class player
{
	static int health,defence,strength,wealth,xpAtk,xpDef;
	static double creed;
	static String saveName,name;
	static String[] location;
	static ArrayList<equipment> inventory;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		loadSave();
	}
    
	public static void newGame() throws IOException, InterruptedException		//Create new save file with given name
	{
		System.out.println("What is the name of this save?");
		saveName = sc.nextLine() + ".sav";
		
		File save = null;
		File f = new File("src/wolfFiles/saves");
		ArrayList<String> files = new ArrayList<>(Arrays.asList(f.list()));
		
		if(files.contains(saveName)){
			helper.cls();
			System.out.println("Error: File name already exists!\n");
			newGame();
		}
		else{
			System.out.println("\nWhat is your player name?");
			name = sc.nextLine();
			
			save = new File("src/wolfFiles/saves" + saveName);
			save.createNewFile();
		}
		
		location = new String[]{"%r5.1", "events.txt", "1"};
		health = 100;                       //static for now
		creed = 1;							//increases by 3/4 of enemy's creed
		wealth = 0;
		defence = 1;                    	//xpDef req to raise = (2^def * 10)/4
		strength = 1;                       //xpAtk req to raise = (2^str * 10)/2
		xpAtk = 0;							//dmgGiven/4 * creed/10(int)	always at least 1
		xpDef = 0;							//dmgTaken/4 * creed/10(int)	always at least 1
		inventory = new ArrayList<equipment>();
		inventory.add(new equipment(0,0,"Empty"));
		FileWriter fw = new FileWriter("src/wolfFiles/saves" + saveName);
		String line = System.lineSeparator();
		
		fw.write("" + name);
		fw.write(line + location[0] + " " + location[1] + " " + location[2]);
		fw.write(line + health);
		fw.write(line + creed);
		fw.write(line + wealth);
		fw.write(line + defence);
		fw.write(line + strength);
		fw.write(line + xpAtk);
		fw.write(line + xpDef);
		fw.write(line);
		for(equipment e:inventory){
			fw.write(e.toString() + line);
		}
		
		fw.flush();
        fw.close();
        helper.cls();
        System.out.println("Welcome to your adventure, adventurer! Prepare yourself for your journey.");
        System.out.println("Available commands are:\nhelp - display this list again\ninfo <item> - displays information about an item");
        System.out.println("eat <food> - consume a food item in your inventory\nrepeat - display your current room's text again\n");
        System.out.println("(Press enter to begin your quest)");
        sc.nextLine();
        helper.cls();
        diagReader.dialogue(location[0]);
	}
	
	public static void loadSave() throws IOException, InterruptedException		//Load from an old .sav file
	{
		File f = new File("src/wolfFiles/saves");
		ArrayList<String> files = new ArrayList<String>(Arrays.asList(f.list()));
		if(files.size() == 0){
			System.out.println("Error: No files in this folder.");
			sc.nextLine();
			helper.cls();
			projectWolf.menu();
		}
		System.out.print("Choose file to load:\n\n");
		System.out.print(files.toString() + "\n\n");
		saveName = sc.nextLine() + ".sav";
		
		if(files.contains(saveName)){
			File save = new File("src/wolfFiles/saves" + saveName);
			Scanner fr = new Scanner(save);
			
			name = fr.nextLine();
			location = fr.nextLine().split(" ");
			health = helper.asInt(fr.nextLine());
			creed = helper.asDouble(fr.nextLine());
			wealth = helper.asInt(fr.nextLine());
			defence = helper.asInt(fr.nextLine());
			strength = helper.asInt(fr.nextLine());
			xpAtk = helper.asInt(fr.nextLine());
			xpDef = helper.asInt(fr.nextLine());
			inventory = new ArrayList<equipment>();
			while(fr.hasNextLine()){
				String[] temp = fr.nextLine().split("#");
				inventory.add(new equipment(helper.asInt(temp[0]),helper.asInt(temp[1]),temp[2]));
			}
			
			diagReader.dialogue(location[0]);
		}
		else{
			helper.cls();
			System.out.print("Error: File not found. Try again.\n");
			loadSave();
		}
	}
	
	public static void saveGame() throws IOException, InterruptedException
	{
		File temp = new File("src/wolfFiles/saves" + saveName);
		temp.delete();
		File save = new File("src/wolfFiles/saves" + saveName);
		save.createNewFile();
		
		FileWriter fw = new FileWriter("src/wolfFiles/saves" + saveName);
		String line = System.lineSeparator();
		
		fw.write("" + name);
		fw.write(line + location[0] + " " + location[1] + " " + location[2]);
		fw.write(line + health);
		fw.write(line + creed);
		fw.write(line + wealth);
		fw.write(line + defence);
		fw.write(line + strength);
		fw.write(line + xpAtk);
		fw.write(line + xpDef);
		fw.write(line);
		for(equipment e:inventory){
			fw.write(e.toString() + line);
		}
		
		fw.flush();
        fw.close();
        System.out.print("File saved successfully, ...maybe\n(Press Enter to continue)\n");
        sc.nextLine();
        projectWolf.menu();
	}
	
	public static void kill(){
		File you = new File("src/wolfFiles/saves" + saveName);
		you.delete();
	}
}