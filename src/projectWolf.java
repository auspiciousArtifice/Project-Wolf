// John D'Angelo

import java.util.*;
import java.io.*;

public class projectWolf
{
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		menu();
	}
	
    static void menu() throws IOException, InterruptedException				//Print main menu and process user selections
    {
    	System.out.println("PROJECT WOLF");				//add ascii art at some point
        System.out.println("Created by Harry Wang & John D'Angelo\n");
        System.out.println("Type an option:\nNew Game\nLoad\nOptions\nCredits\nQuit\n");
    	
        String next = sc.nextLine().trim().toLowerCase();		//Get user input then go to menu selection
        helper.cls();
		
		switch(next){
			case "new game":
				player.newGame();
				break;
			case "load":
				player.loadSave();
				break;
			case "options":
				options();
				break;
			case "credits":
				credits();
				break;
			case "quit":
			case "an option":
				System.exit(0);
			default:
				System.out.println("What was that again?\n");
				menu();
		}
		System.out.println("you managed to fuck up somehow, nice job\n");
		menu();
    }
	
	private static void options() throws IOException, InterruptedException
	{
		System.out.print("Options:\nColors\nSounds\nMisc\nBack\n");		//Print all the options
		
		String temp = sc.nextLine().trim().toLowerCase();
		
		switch (temp){
			case "colors":
			case "color":
				System.out.print("maybe console colors?");
				sc.nextLine();
				helper.cls();
				options();
				break;
			case "sounds":
			case "sound":
				System.out.print("bgm volume?\nmenu sounds?");
				sc.nextLine();
				helper.cls();
				options();
				break;
			case "misc":
				System.out.print("anything else?");
				sc.nextLine();
				helper.cls();
				options();
				break;
			case "back":
				helper.cls();
				menu();
				break;
			default:
				helper.cls();
				System.out.println("What was that again?\n");
				options();
				break;
		}
		menu();
	}
	
	static void credits() throws IOException, InterruptedException
	{
		System.out.println("we is make dis game");
		sc.nextLine();
		helper.cls();
		menu();
	}
}