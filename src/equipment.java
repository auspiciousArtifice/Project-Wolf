// John D'Angelo
// Period 4
import java.util.*;
import java.io.*;

public class equipment
{
	private int defence, attack;
	private String name;
	
	public static void main(String[] args) throws IOException{}
	
	public equipment()
	{
		defence = 1;
		attack = 1;
		name = "Error";
	}
	public equipment(int def, int atk, String N){
		defence = def;
		attack = atk;
		name = N;
	}
	
	public int getDef(){
		return defence;
	}
	public int getAtk(){
		return attack;
	}
}