/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ges_facture;
import java.util.ArrayList;
import java.util.List;
 import java.util.Scanner;
/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public class Menu {
    
private static final String OPTION_EXIT="0";
private static final String LEVEL_INDENTATION ="    ";
private List<Options> menuOptions=new ArrayList<>();
private int level=0;

public Menu(){}

public Menu(int level){
    this.level=level;
}

public void add(Options m){
    this.menuOptions.add(m);
}

/**
 * gest of menu
 * 
 */
public void loopUntilExit(){

    String input;
    Scanner sc=new Scanner(System.in);
    
        while (true) {
            System.out.println(this);
            input = sc.next();
            if (input.equals(OPTION_EXIT)) {
                return;
            }
            boolean foundOption = false;
            for (Options m : menuOptions) {
                if (m.getOption().equalsIgnoreCase(input)) {
                    m.doAction();
                    foundOption = true;
                }
            }
            if (!foundOption) {
                System.out.println("choix invalide");
            }
        }
}

@Override
public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("\n\nCHOISISSEZ UNE OPTION OU APPUYER SUR, "+OPTION_EXIT+" POUR SORTIR.\n");
    for(Options m:menuOptions){
        for(int i=0;i<level;i++){
            sb.append(LEVEL_INDENTATION);
        }
        sb.append(m).append("\n");
    }
    return sb.toString();
}


}

