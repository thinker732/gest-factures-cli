/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public class Product extends Table{
    
    public static int num=000;
    private int id;
    private String ref;
    private String designation;
    private String description;
    private int prix;
    private Date date_ins;

    public Product(){
    }
      public Product(ArrayList<Object> l) {
          
        this.ref = l.get(1).toString();
        this.designation =  l.get(2).toString();
        this.description = l.get(3).toString();
        this.prix = Integer.parseInt( l.get(4).toString());
        this.date_ins =Date.valueOf(l.get(5).toString());
         //l.get(5)
        
    }
      
    public Product(String designation, String description, int prix, String date_ins) {
        this.ref = "pro"+num;
        this.designation = designation;
        this.description = description;
        this.prix = prix;
        
        this.date_ins = (date_ins==null)?new Date(System.currentTimeMillis()):Date.valueOf(date_ins);
        
        num++;
    }
    
     @Override
    public String classname(){
        return "product";
    }
    
    @Override
    public String toString() {
        return "Product{" + "ref=" + ref + ", designation=" + designation + ", description=" + description + ", prix=" + prix + ", date_ins=" + date_ins + '}';
    }

     /*data formating for db insert instruction*/
     public String toInsert() {
        return "(null,'"+ref+"','"+ designation +"','"+ description +"'," + prix + ",'" +  date_ins + "')";
    }
     
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate_ins() {
        return date_ins;
    }

    public void setDate_ins(Date date_ins) {
        this.date_ins = date_ins;
    }
    
    
    
}
