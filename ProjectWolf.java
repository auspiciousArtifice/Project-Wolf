import java.util.*;
import java.io.*;

public class ProjectWolf {
    
    private int fettle, robustness, fortification, creed, wealth, mapX, mapY;
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
    private String[] move = {"move", "travel", "go", "head", "walk", "run", "proceed", "progress", "advance", "relocate",
     "shift", "migrate"};
    private ArrayList<String> travel = (ArrayList<String>) Arrays.asList(move);
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
        String parsingThis = stringLowercase(yourInput);
        if(movementCheck(parsingThis).substring(0, 4).equals("move")) {
            if(parsingThis.length() > 4) {

            }
        }
    }
    
    public String battleParser(String battleInput) {
        String interpretation = stringLowercase(battleInput);
        String attackJudge = spellCheck(interpretation, strike);
        if(strike.contains(interpretation)) {
            return "attack";
        }
        if(attackJudge.equals("misspelled")) {
            return "attackmiss";
        }
        String defendJudge = spellCheck(interpretation, fortify);
        if(fortify.contains(interpretation)) {
            return "defend";
        }
        else if(defendJudge.equals("misspelled")) {
            return "defendmiss";
        }
        String fleeJudge = spellCheck(interpretation, abscond);
        if(abscond.contains(interpretation)) {
            return "flee";
        }
        else if(fleeJudge.equals("misspelled")) {
            return "fleemiss";
        }
        return "nomatch";
    }

    public String movementCheck(String movement) {
        String assign = stringLowercase(movement);
        if(travel.contains(assign)) {
            return "move";
        }
        String judgement = spellCheck(assign, travel);
        if(judgement.equals("misspelled")) {
            return "movemiss";
        }
        return judgement;
    }

    public int[] directionCheck(String cardinal) {
        String temp = stringLowercase(cardinal);
        int[] geaux = {0, 0};
        switch (temp) {
            case "north": geaux[1] = 1;
                          break;
            case "south": geaux[1] = -1;
                          break;
            case "west": geaux[0] = -1;
                         break;
            case "east": geaux[0] = 1;
                         break;
        }
        return geaux;
    }

    public String spellCheck (String misspell, ArrayList<String> proper) {
        for(String propspell : proper) {
            int matches = 0;
            for(int i = 0; i < propspell.length(); i++) {
                if(misspell.substring(i, i+1).equals(propspell.subtring(i, i+1))) {
                    matches++;
                }
            }
            if(matches > propspell.length() - 3) {
                return "misspelled";
            }
        }
        return "nomatch";
    }

    public String stringLowercase(String text) {
        return text.toLowerCase();
    }
}
