// John D'Angelo

import java.util.*;
import java.io.*;

public class equipment
{
	private int defence, attack;
	private String name;
	
	public static void main(String[] args) throws IOException{}
	
	public equipment()
	{
		defence = 0;
		attack = 0;
		name = "Error";
	}
	public equipment(String N)
	{
		defence = 0;
		attack = 0;
		name = N;
	}
	public equipment(int atk, int def, String N){
		attack = atk;
		defence = def;
		name = N;
	}
	
	public String getName(){
		return name;
	}
	public int getAtk(){
		return attack;
	}
	public int getDef(){
		return defence;
	}
	public String toString(){
		return attack + "#" + defence + "#" + name;
	}
}