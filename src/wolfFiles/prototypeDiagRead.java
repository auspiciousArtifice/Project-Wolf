package wolfFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class prototypeDiagRead {

	static String currentChoice;
	static String nextDialogue = "";
	static String filename = "src/wolfFiles/events.txt";
	static String currentInput = "";

	public static void main(String[] args) throws IOException, InterruptedException{
		dialogue("%r5");
		//ChoiceHandler("%c1");
		
	}
	
	public static void MessageHandler(String[] input) throws FileNotFoundException{
		for(int i = 0; i < input.length; i++){
			String query = input[0];
			//query = aliases(query);
			
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
				default: currentInput = ""; break;
			}
		}
		
		for(String s: input){
			currentInput += s;
		}
		
	} //working on it... need to figure out a way to handle text commands differently from choices

	public static void ChoiceHandler(String ChoiceCode) throws IOException{
		Scanner text = new Scanner(new File(filename));
		Scanner kb=new Scanner(System.in);
		
		MessageHandler(kb.nextLine().split("\\s+"));
		
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
		
		
		String line1 = text.nextLine();
		String[] temp = line1.split("/");
		ArrayList<String> choices  =  new ArrayList<String>();
		choices.addAll(Arrays.asList(temp));
		
		String line2 = text.nextLine();
		String[] temp2 = line2.split("/");
		ArrayList<String> consequences  =  new ArrayList<String>();
		consequences.addAll(Arrays.asList(temp2));
		
		for(int i = 0; i < choices.size(); i++){
			if(choices.get(i).contains("-")){
				String hyphenTemp = choices.get(i);
				String firstpart = hyphenTemp.substring(0, hyphenTemp.indexOf(" ") +1);
				String[] secondparts = hyphenTemp.substring(hyphenTemp.indexOf(" ") +1).split("-");
				for(String hyphens: secondparts){
					choices.add(firstpart + hyphens);
					consequences.add(consequences.get(i));
				}
				choices.remove(i);
				consequences.remove(i);
			}
		}
		System.out.println(choices.toString());
		System.out.println(consequences.toString());
		
		for(String i: choices)
		{
			if(i.equals(currentInput))
			{
				int index = choices.indexOf(currentInput);
				currentChoice = consequences.get(index);
			}
		}
		
		dialogue(currentChoice);
		text.close();
		kb.close();
		
	}
	
	public static void dialogue(String DialogueCode) throws IOException{
		Scanner kb = new Scanner(new File(filename));
		String dialogue = "";
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(DialogueCode)){
				break;
			}
		}
		//kb.nextLine();
		
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(DialogueCode)){
				nextDialogue = line.substring(line.lastIndexOf("%"));
				break;
			}
			else{
				dialogue += line + "\n";
			}
		}
		
		System.out.println(dialogue);
		kb.close();
		
		if(nextDialogue.startsWith("%c")){
			ChoiceHandler(nextDialogue);
		}
		else if(nextDialogue.startsWith("%r")){
			//todo RoomHandler, loads next Room/Text
		}
		else{
			dialogue(nextDialogue);
		}
		
	}

	/*public static void getChoices(String ChoiceCode) throws IOException{
		Scanner text = new Scanner(new File(filename));
		Scanner kb=new Scanner(System.in);
		
		String input="";
		input = kb.nextLine();
		
		
		while(text.hasNextLine()){
			String line = text.nextLine();
			if(line.equals(ChoiceCode)){
				break;
			}
		}
		
		
		String line1 = text.nextLine();
		String[] temp = line1.split("/");
		ArrayList<String> temp2  =  new ArrayList<String>();
		temp2.addAll(Arrays.asList(temp));
		
		String line2 = text.nextLine();
		String[] choiceid = line2.split("/");
		
		for(String i: temp2)
		{
			if(i.equals(input))
			{
				int index = temp2.indexOf(input);
				currentChoice = choiceid[index];
			}
		}
		
		
		
	}*/ //deprecated, replaced by ChoiceHandler

	public static void slowText(String st) throws InterruptedException{
		for(int i = 0; i < st.length(); i++){
			System.out.print(st.substring(i,i+1));
			Thread.sleep(100);
		}
	}
	
	public static void slowText(String st, int speed) throws InterruptedException{
		for(int i = 0; i < st.length(); i++){
			System.out.print(st.substring(i,i+1));
			Thread.sleep(speed);
		}
	}
	
	
	
	
	
	
	
}