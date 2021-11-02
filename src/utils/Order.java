/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public class Order extends Table{
    
    public static int num=000;
    private int id;
    private String ref;
    private Date date;
    private String ref_cus;
    private String ref_pro;
    private int qte_product;
    private int prix;
    private String produit;
    
    public Order() {
    }
    
    public Order(ArrayList<Object> l) {
        this.id=Integer.parseInt(l.get(0).toString());
        this.ref =l.get(1).toString() ;
        this.date =Date.valueOf(l.get(2).toString());
        this.ref_cus = l.get(3).toString() ;
        this.ref_pro = l.get(4).toString() ;
        this.qte_product = Integer.parseInt(l.get(5).toString());
        num++;
    }
    
    public Order(String date, String ref_cus, String ref_pro, int qte_product) {
        num++;
        this.ref = "com"+num;  
        this.date = (date==null)?new Date(System.currentTimeMillis()):Date.valueOf(date);
        this.ref_cus = ref_cus;
        this.ref_pro = ref_pro;
        this.qte_product = qte_product;
      
    }
    public Order(String date, String ref_cus, String ref_pro, int qte_product,int same) {
        num+=(same==1)?0:1;
        this.ref = "com"+num;  
        this.date = (date==null)?new Date(System.currentTimeMillis()):Date.valueOf(date);
        this.ref_cus = ref_cus;
        this.ref_pro = ref_pro;
        this.qte_product = qte_product;     
    }
    
   
    
    @Override
    public String classname(){
        return "order_product";
    }
    
    @Override
    public String toString() {
        return "Order{" + "ref=" + ref + ", date=" + date + ", ref_cus=" + ref_cus + ", ref_pro=" + ref_pro + ", qte_product=" + qte_product + '}';
    }

        /*data formating for db insert instruction*/
    @Override
     public String toInsert() {
        return "(null,'" + ref + "','" + date + "','" + ref_cus + "','" + ref_pro + "'," + qte_product + ")";
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }
    
    public int getId() {
        return this.id;
    }

    public  void setId(int id) {
       this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRef_cus() {
        return ref_cus;
    }

    public void setRef_cus(String ref_cus) {
        this.ref_cus = ref_cus;
    }

    public String getRef_pro() {
        return ref_pro;
    }

    public void setRef_pro(String ref_pro) {
        this.ref_pro = ref_pro;
    }

    public int getQte_product() {
        return qte_product;
    }

    public void setQte_product(int qte_product) {
        this.qte_product = qte_product;
    }

  
    
    
    
}
