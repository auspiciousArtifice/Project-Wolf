package wolfFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import java.util.ArrayList;

public class DialogueReader {

	static String header, oh;
	static String dialogue = "";

	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println(dialogue("%r1"));
	}

	public static String dialogue(String dc) throws IOException{
		Scanner kb = new Scanner(new File("events.txt"));
		dialogue="";
		String nextDialogue = "";
		
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(dc)){
				header = dc;
				break;
			}
		}
		//kb.nextLine();
		while(kb.hasNextLine()){
			String line = kb.nextLine();
			if(line.startsWith(dc)){
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

	public static void choicesMet(String rc) throws IOException{
		Scanner text = new Scanner(new File("events.txt"));
		Scanner kb=new Scanner(System.in);

		String input="";
		while(text.hasNextLine()){
			String line = text.nextLine();
			if(line.equals(rc)){
				header = rc;
				break;
			}
		}
		String c = text.nextLine();
		String[] temp = c.split("/");
		ArrayList<String> cancer  =  new ArrayList<String>();
		cancer.addAll(Arrays.asList(temp));
		String c2 = text.nextLine();
		String[] choiceid = c2.split("/");
		input = kb.nextLine();
		for(String i: cancer)
		{
			if(i.equals(input))
			{
				int f = cancer.indexOf(input);
				oh = choiceid[f];
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