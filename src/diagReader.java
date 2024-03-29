//Shamee Mahmud 
//package wolfFiles;
//what is eclipse

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import audio.AudioController;

import java.util.ArrayList;

public class diagReader
{
	static String currentChoice;
	static String nextDialogue = "";
	static String currentDialogue = "";
	static String filename = "src/wolfFiles/" + player.location[1];
	static String currentInput = "";
	static AudioController ac;
	
	public static void main(String[] args) throws IOException, InterruptedException{
		ac = new AudioController();
	}
	
	public static void dialogue(String DialogueCode) throws IOException, InterruptedException{
		helper.cls();
		Scanner kb = new Scanner(new File(filename));
		String dialogue = "";
		currentDialogue = DialogueCode;
		currentChoice = "";
		nextDialogue = "";
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(DialogueCode)){
				break;
			}
		} //finds the DialogueCode in events.txt
		
		boolean slowText = false;
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(DialogueCode)){
				nextDialogue = line.substring(line.lastIndexOf("%"));
				break;
				//finds the last occurence of DialogueCode, parses the arrow to the next code
			}
			else if(line.startsWith("#")){
				//this is a comment. Nothing happens.
			}
			else{
				String[] arr = line.split("\\s+");
				for(int i = 0; i < arr.length; i++){
					
					if(arr[i].contains("(s)")){
						arr[i] = arr[i].replace("(s)", "");
						slowText = true;
					} //slowText recog
					if(arr[i].contains("(/s)")){
						arr[i] = arr[i].replace("(/s)", "");
						slowText = false;
					} //slowText end recog
					if(arr[i].contains("(pause")){
						pause(helper.asDouble(arr[i+1].substring(0, arr[i+1].indexOf(")"))));
						arr[i] = "";
						arr[i+1] = "";
					} //pause command
					if(arr[i].contains("(get")){
						//some sort of implementation of items being 
						//added to the inventory, as well as a print message.
						arr[i] = "";
						arr[i+1] = "";
					} //get command
					if(arr[i].contains("(stop)")){
						player.sc.nextLine();
						arr[i] = "";
						//waits until the player presses the enter key
					}
					
					if(arr[i].contains("(mTrack")){
						//some sort of implementation of track changing, called to MusicThread.
						String arg = arr[i+1].substring(0, arr[i+1].indexOf(")"));
						ac.changeTrack(arg);
						arr[i] = "";
						arr[i+1] = "";
					} 
					if(arr[i].contains("(mVol")){
						String arg = arr[i+1].substring(0, arr[i+1].indexOf(")"));
						ac.changeVolume(arg);
						arr[i] = "";
						arr[i+1] = "";
					} 
					if(arr[i].contains("(mAction")){
						String arg = arr[i+1].substring(0, arr[i+1].indexOf(")"));
						ac.changeAction(arg);
						arr[i] = "";
						arr[i+1] = "";
					} 
					
					if(slowText){
						slowPrint(arr[i] + " ");
					}
					else{
						System.out.print(arr[i] + " ");
					}
				}
				System.out.println();
			}
		}
		
		System.out.println(dialogue);
		kb.close();
		
		if(nextDialogue.startsWith("%c")){
			ChoiceHandler(nextDialogue);
		}
		else if(nextDialogue.startsWith("%r")){
			player.location[0] = nextDialogue;
			System.out.println(player.location[0]);//work plz
			dialogue(nextDialogue);
			//todo RoomHandler, loads next Room/Text
		}
		else if(nextDialogue.startsWith("%end")){
			player.kill();
			helper.cls();
			projectWolf.menu();
		}
		else if(currentDialogue.equals(nextDialogue)){
			System.out.println("This program is terminated, because someone didn't write the pointer to another dialogue.");
			System.out.println("So to save myself the effort of looping, I'd rather just stare at you blankly.");
		}
		else{
			dialogue(nextDialogue);
		}
	}
	
	public static void MessageHandler(String[] input) throws IOException, InterruptedException{
		for(int i = 0; i < input.length; i++){
			String query = input[0];
			
			switch(query){
			case "hello": currentInput = ""; break;
			case "info": 
				try{
					String subject = input[1]; currentInput = ""; return; //info(subject);
				}
				catch(Exception e){
					System.out.println("Error with your info command. Incorrect input.");
					break;
				}
			case "eat": currentInput = ""; break; 
			//you're supposed to eat something from inventory, but it hasn't been implemented yet.
			case "save": currentInput = ""; player.saveGame(); break;
			case "repeat": helper.cls(); dialogue(currentDialogue); currentInput = ""; return;
			case "clear": currentInput = ""; helper.cls(); return; 
			default: currentInput = ""; break;
			}
		}
		
		for(String s: input){
			currentInput += s + " ";
		}
	} //finds the ChoiceCode in events.txt
	
	public static void ChoiceHandler(String ChoiceCode) throws IOException, InterruptedException{
		Scanner text = new Scanner(new File(filename));
		
		String input = player.sc.nextLine(); //have to set to string, otherwise NPE occurs.		
		MessageHandler(input.split("\\s+"));
		
		currentInput = currentInput.trim();
		
		if(currentInput.equals("")){
			ChoiceHandler(ChoiceCode);
			return;
		}
		
		while(text.hasNextLine()){
			String line = text.nextLine();
			if(line.equals(ChoiceCode)){
				break;
			}
		}
		
		String[] preconvert = currentInput.split("\\s+");
		for(int i = 0; i < preconvert.length; i++){
			preconvert[i] = aliases(preconvert[i]);
		}
		ArrayList<String> converted = helper.asArrayList(preconvert);
		currentInput = "";
		for(String s: converted){
			currentInput += s + " ";
		}
		currentInput = currentInput.trim();
		
		String strChoices = text.nextLine();
		ArrayList<String> choices = helper.asArrayList(strChoices.substring(strChoices.indexOf(" ") + 1).split("/"));
		String strConsequences = text.nextLine();
		ArrayList<String> consequences = helper.asArrayList(strConsequences.substring(strConsequences.indexOf(" ") + 1).split("/"));
		
		for(int i = 0; i < choices.size(); i++){
			if(choices.get(i).contains("-")){
				String hyphenTemp = choices.get(i);
				int indexOfSpace = hyphenTemp.indexOf(" ");
				if(indexOfSpace < 0){
					String[] firstparts = hyphenTemp.substring(0).split("-");
					for(String fHyphens: firstparts){
						choices.add(fHyphens);
						consequences.add(consequences.get(i));
					}
				}
				else{
					String[] firstparts = hyphenTemp.substring(0, hyphenTemp.indexOf(" ")).split("-");
					String[] secondparts = hyphenTemp.substring(hyphenTemp.indexOf(" ") +1).split("-");
					for(String fHyphens: firstparts){
						for(String sHyphens: secondparts){
							choices.add(fHyphens + " " + sHyphens);
							consequences.add(consequences.get(i));
						}
					}
				}
				choices.remove(i);
				consequences.remove(i);
				i--;
			}
		}
		//System.out.println(choices.toString());
		//System.out.println(consequences.toString());
		//System.out.println(currentInput);
		//System.out.println(currentChoice);
		
		for(String i: choices)
		{
			if(i.equals(currentInput))
			{
				int index = choices.indexOf(currentInput);
				currentChoice = consequences.get(index);
			}
		}
		
		if(currentChoice == null){
			System.out.println("Unrecognized command.");
			ChoiceHandler(ChoiceCode);
			return;
		}
		if(currentChoice.equals("")){
			System.out.println("Unrecognized command.");
			ChoiceHandler(ChoiceCode);
			return;
		}

		dialogue(currentChoice);
		text.close();
	}	//finds the ChoiceCode in events.txt
	
	
	public static void slowPrint(String st) throws InterruptedException{
		for(int i = 0; i < st.length(); i++){
			System.out.print(st.substring(i,i+1));
			Thread.sleep(100);
		}
	}
	public static void slowPrint(String st, int speed) throws InterruptedException{
		for(int i = 0; i < st.length(); i++){
			System.out.print(st.substring(i,i+1));
			Thread.sleep(speed);
		}
	}
	public static void pause(double amount) throws InterruptedException{
		Thread.sleep((long)amount * 1000);
	}
	
	public static String aliases(String in) throws FileNotFoundException{
		File alias = new File("src/wolfFiles/alias.txt");
		Scanner fs = new Scanner(alias);
		while(fs.hasNextLine()){
			String line = fs.nextLine();
			if(line.contains(in)){
				return line.substring(0, line.indexOf(":"));
			}
		}
		return in;
	}
	
	
	
	
	
	
}