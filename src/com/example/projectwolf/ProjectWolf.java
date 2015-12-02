package com.example.projectwolf;

import java.util.*;
import java.io.*;

public class ProjectWolf {

    private int fettle, robustness, fortification, creed, wealth, mapX, mapY;
    private static String name;
    private ArrayList<String> inventory = new ArrayList<String>();
    private String[] attack = {"attack", "aggrieve", "aggress", "assail", "assault",
            "antagonize", "beat", "bash", "clobber", "clash with", "charge", "confront",
            "besiege", "harm", "hit", "hurt", "lay siege to", "strike", "rush"};
    private ArrayList<String> strike = new ArrayList<String>(Arrays.asList(attack));
    private String[] defend = {"defend", "contend", "guard", "resist", "safeguard",
            "shield", "bulwark", "cover", "entrench", "fortify", "garrison"};
    private ArrayList<String> fortify = new ArrayList<String>(Arrays.asList(defend));
    private String[] flee = {"run", "flee", "abscond", "retreat", "take off",
            "bolt", "scamper", "scram", "skedaddle", "split", "vamoose"};
    private ArrayList<String> abscond = new ArrayList<String>(Arrays.asList(flee));
    private String[] move = {"move", "travel", "go", "head", "walk", "run", "proceed", "progress", "advance", "relocate",
            "shift", "migrate"};
    private ArrayList<String> travel = new ArrayList<String>(Arrays.asList(move));

    public ProjectWolf() {
        fettle = 50;
        robustness = 1;
        fortification = 1;
        creed = 1;
        wealth = 0;
        mapX = 0;
        mapY = 0;
        name = "Saul";
    }

    public ProjectWolf(String given) {
        fettle = 50;
        robustness = 1;
        fortification = 1;
        creed = 1;
        wealth = 0;
        mapX = 0;
        mapY = 0;
        name = given;
    }
    public static void main(String[] args) throws Exception {
        Scanner file = new Scanner(System.in);
        System.out.println("Well, hello there. You seem to be a bit confused. Stay calm, though, this is the beginning\n" +
                "of your legend, your legacy, the journey of your life. All I need is your name. If you don't provide\n" +
                "one, I will give you one myself.");
        System.out.println("\nSo, what is your name?");
        String userInput = file.next();
        if(userInput.equals("")) {
            ProjectWolf saulAdvent = new ProjectWolf();
        }
        else {
            ProjectWolf yourAdvent = new ProjectWolf(userInput);
        }
        System.out.println("\nWell then, " + name + ", your grand adventure begins now!");
        Thread.sleep(10000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You awake in a dungeon with nothing but rags on your cold, numb body. How cliché. \n" +
                "You’re shackled to what appears to be a metal stake with a rather short chain. It’s too \n" +
                "dark to see much around you.\n");
        System.out.println("What will you do?");
        userInput = file.next();
    }

    public void stringParser(String yourInput) {
        String parsingThis = stringLowercase(yourInput);
        if(movementCheck(parsingThis).substring(0, 4).equals("move")) {
            if(parsingThis.length() > 4) {
                int[] directionalMove = directionCheck(parsingThis.substring(4, parsingThis.length()));
                mapX += directionalMove[0];
                mapY += directionalMove[1];
                if(directionalMove[0] == 0 && directionalMove[1] == 0) {
                    System.out.println("You had a feeling to move, but you weren't sure which direction. Maybe if you \n" +
                            "were a bit more certain, you could illicit movement.");
                }
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
                if(misspell.substring(i, i+1).equals(propspell.substring(i, i+1))) {
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
