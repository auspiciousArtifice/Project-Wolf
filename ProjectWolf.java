import java.util.*;
import java.io.*;

public class ProjectWolf {
    
    private int fettle, robustness, fortification, creed, wealth;
    private String name;
    private ArrayList<String> inventory = new ArrayList<String>();
    private String[] attack = {"attack", "aggrieve", "aggress", "assail", "assault", 
        "antagonize", "beat", "bash", "clobber", "clash with", "charge", "confront",
        "besiege", "harm", "hit", "hurt", "lay siege to", "strike", "rush"};
    private ArrayList<String> strike = (ArrayList<String>) Arrays.asList(attack);
    private String[] defend = {"defend", "contend", "guard", "resist", "safeguard",
        "shield", "bulwark", "cover", "entrench", "fortify", "garrison"};
    private ArrayList<String> fortify = (ArrayList<String>) Arrays.asList(defend);
    private String[] flee = {"run", "flee", "abscond", "retreat", "take off",
        "bolt", "scamper", "scram", "skedaddle", "split", "vamoose"};
    private ArrayList<String> abscond = (ArrayList<String>) Arrays.asList(flee);
    Scanner file = new Scanner(System.in);
    
    public ProjectWolf() {
        fettle = 50;
        robustness = 1;
        fortification = 1;
        creed = 1;
        wealth = 0;
        name = "Saul";
    }
    
    public ProjectWolf(String given) {
        fettle = 50;
        robustness = 1;
        fortification = 1;
        creed = 1;
        wealth = 0;
        name = given;
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    }
    
    public String stringParser(String yourInput) {
        String interpretation = stringLowercase(yourInput);
    }
    
    public String battleParser(String battleInput) {
        String interpretation = stringLowercase(battleInput);
        if(strike.contains(interpretation)) {
            return "attack";
        }
        else if(fortify.contains(interpretation)) {
            return "defend";
        }
        else if(abscond.contains(interpretation)) {
            return "flee";
        }
        return "nomatch";
    }
    
    public String stringLowercase(String text) {
        return text.toLowerCase();
    }
}
