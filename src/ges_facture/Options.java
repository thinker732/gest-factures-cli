/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ges_facture;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public abstract class Options {
private String option;
private String menuLine;

public Options(String option, String menuLine) {
    this.option = option;
    this.menuLine = menuLine;
}

@Override
public String toString() {
    return this.option+" - "+this.menuLine;
}

public String getOption() {
    return option;
}
public abstract void doAction();

}
