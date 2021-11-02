/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public abstract class Table {
    /**
     * format  data of the table pour insertion
     * 
     * @return 
     */
    public abstract String toInsert();
    
    /**
     * 
     * get the database table name
     * @return 
     */
    public abstract String classname();
    
    
}
