package wolfFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import java.util.ArrayList;

public class protoypeDiagRead {

	static String header, currentChoice;
	static String dialogue = "";

	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println(dialogue("%r5"));
		getChoices("%c1");
		System.out.println(dialogue(currentChoice));
	}

	public static String dialogue(String DialogueCode) throws IOException{
		Scanner kb = new Scanner(new File("events.txt"));
		String nextDialogue = "";
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(DialogueCode)){
				header = DialogueCode;
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
		
		
		kb.close();
		return dialogue;
		/*String arr[] = new String[2];
		arr[0] = dialogue;
		arr[1] = nextDialogue;
		return arr;*/
	}

	public static void getChoices(String ChoiceCode) throws IOException{
		Scanner text = new Scanner(new File("events.txt"));
		Scanner kb=new Scanner(System.in);
		
		String input="";
		input = kb.nextLine();
		
		
		while(text.hasNextLine()){
			String line = text.nextLine();
			if(line.equals(ChoiceCode)){
				header = ChoiceCode;
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
		
		
		
	}

	public static void slowText(String st) throws InterruptedException{
		for(int i = 0; i < st.length(); i++){
			System.out.print(st.substring(i,i+1));
			Thread.sleep(100);
		}
	}
	
	
	
	
	
	
	
}