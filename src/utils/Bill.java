/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne;
 */
public class Bill extends Table{

    public static int NUM=001;
    private int id;
    private String ref;
    private Date date;
    private String ref_order;
    private String ref_customer;
    private int Prix_total;
    
    public Bill() {
        this.date =new Date(System.currentTimeMillis());
    }
    
    /**
     * method for database select construction
     * 
     * @param l 
     */
    public Bill(ArrayList<Object> l) {
        this.ref = l.get(1).toString();
        this.date = Date.valueOf(l.get(2).toString());
        this.ref_order = l.get(3).toString();
        this.ref_customer = l.get(4).toString();
        this.Prix_total = Integer.parseInt(l.get(5).toString());
    }
    
    public Bill(String date, String ref_order, String ref_customer) {
        this.ref = "facture n° "+NUM;
        this.date = (date==null)?new Date(System.currentTimeMillis()):Date.valueOf(date);
        this.ref_order = ref_order;
        this.ref_customer = ref_customer;
        this.Prix_total = 0;
    }
    
    /**
     * 
     * calcule of price HTC
     * @param orders:Array list of all the commands of the bill 
     */
    public void calculprix(ArrayList<Order> orders){
            this.Prix_total=0;
            
             for(Order o:orders){
               this.Prix_total+=o.getPrix()*o.getQte_product();
             }
    }
    
    /**
     *  print the bill
     * @param c
     * @param orders
     */
    public void Afficher(Customer c,ArrayList<Order> orders)
    {
        this.calculprix(orders);
        
        List<String> facture=new LinkedList();
        facture.add(String.valueOf(NUM));
        facture.add(String.valueOf(this.date));
        facture.add(c.getName());
        facture.add(c.getAddress());
         facture.add("12345678910");
         facture.add("12345678910");
         facture.add("12345678910");
         facture.add("12345678910");
         facture.add("12345678910");
         facture.add("12345678910");
        facture.add(String.valueOf(c.getTel()) );
        facture.add(String.valueOf(this.Prix_total));
        
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }
        System.out.println("\n| \t\t\t                                            Facture N° "+facture.get(0)+"\t\t\t\t\t                      \t\t\t\t\t\t\t    |");
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }
        System.out.println("\n|\t\t\tNom: "+facture.get(2)+"\t\t\t\t\t\t|"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("|\t\t\taddress:"+facture.get(3)+"\t\t\t\t\t\t\t|Date de saisie: "+facture.get(1)+"\t ");
        System.out.println("|\t\t\tPhone: "+facture.get(4)+"\t\t\t\t\t\t|"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }
        System.out.println("\n|\tDate_commande\t|\tCommande N°\t|\tNom_produit\t\t|\tPrix Unitaire\t|\tQuantite\t|\tCout\t|");
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }
        for(Order o:orders){    
        System.out.println("\n|\t"+o.getDate()+"\t|\t\t"+o.getId()+"\t|\t"+o.getProduit()+"\t\t|\t"+o.getPrix()+" FCFA\t|\t"+o.getQte_product()+"\t\t|\t"+o.getPrix()*o.getQte_product());
        }
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }
         System.out.println("\n| \t\t\t\t                                                                              Prix Toltal "+facture.get(11)+"FCFA\t\t\t\t\t                      \t\t\t\t\t\t\t    |");
        for (int i=0;i<facture.size();i++)
        {
            for(int j=0;j<=facture.get(i).length()+3;j++)
            {
                System.out.print("*");
            }

        }

    }
     
    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", ref=" + ref + ", date=" + date + ", ref_order=" + ref_order + ", ref_customer=" + ref_customer + ", Prix_total=" + Prix_total + '}';
    }
    
    
    @Override
    public String toInsert() {
     return "(null,'"+ ref + "','" + date + "','" + ref_order + "','" + ref_customer + "',"+ Prix_total +")";
     }

    @Override
    public String classname() {
      return "bill";
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
        if (ref.equals("default"))
        this.ref = "facture n° "+NUM;
        else
        this.ref = ref;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRef_order() {
        return ref_order;
    }

    public void setRef_order(String ref_order) {
        this.ref_order = ref_order;
    }

    public String getRef_customer() {
        return ref_customer;
    }

    public void setRef_customer(String ref_customer) {
        this.ref_customer = ref_customer;
    }

    public int getPrix_total() {
        return Prix_total;
    }

    public void setPrix_total(int Prix_total) {
        this.Prix_total = Prix_total;
    }
    
    
}
