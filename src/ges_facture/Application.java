/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ges_facture;

import db.DataBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import utils.*;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 * 
 */

public class Application {

    /**
     * @param args the command line arguments
     */
    public static DataBase bd;
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Vous pouvez changer le port
        bd=new DataBase("3306");
      
        bd.insertData(new Customer("Bidzang Emmanuel","+237 690425754","Douala"));
        bd.insertData(new Customer("Doum Akono","+237 690112233","Douala"));
        bd.insertData(new Customer("Gankam suzanne","+237 699005500","Douala"));
        bd.insertData(new Customer("Juegang Dunelle","+237 656547904","Douala"));
        
        bd.insertData(new Product("sucre"," ", 800, null));
        bd.insertData(new Product("corn_flakes"," ", 1500, null));
        bd.insertData(new Product("nido"," ", 2400, null));
        bd.insertData(new Product("mayor"," ", 1200, null));
        bd.insertData(new Product("alveole oeuf "," ", 1800, null));
       
       
        bd.insertData(new Order(null, "cli0", "pro0", 1)) ;
        bd.insertData(new Order(null, "cli0", "pro1", 2,1));
        bd.insertData(new Order(null, "cli0", "pro2", 1,1)) ;
        bd.insertData(new Order(null, "cli0", "pro3", 1,1)) ;
        bd.insertData(new Order(null, "cli0", "pro4", 1,1)) ;
        
        bd.insertData(new Order(null, "cli1", "pro4", 1)) ;
        bd.insertData(new Order(null, "cli1", "pro3", 1,1)) ;
        
        bd.insertData(new Order(null, "cli3", "pro2", 1)) ;
        
         
       
       
        System.out.println("Clients  \n \n");
       bd.printData(bd.getAllData("customer"));
        System.out.println("produit \n \n");
        bd.printData(bd.getAllData("product"));
        System.out.println("Commandes  \n \n");

        bd.printData(bd.getAllData("order_product"));
        
       loadMenu();
       bd.deleteData("bill");
       bd.deleteData("order_product");
       bd.deleteData("customer");
       bd.deleteData("product");
       bd.Closeflux();   
        
    }
    
    private static void loadMenu() {
   
    Menu mainMenu=new Menu();
   
    
    
    /*********************Menu Principal****************/
    
             /**-----------[[sous menu 1]]]------------------------*/
                 Menu subMenu0 = new Menu(1);
        mainMenu.add(new Options("1","Affichers les Donnes") {
        @Override
        public void doAction() {
            subMenu0.loopUntilExit();
        }});
       
       
        subMenu0.add(new Options("1","Afficher les clients") {
        @Override
        public void doAction() {
                System.out.println("Liste des Clients");
               bd.printData(bd.getAllData("customer"));
              
         }});
        
        subMenu0.add(new Options("2","Afficher les produits") {
        @Override
        public void doAction() {
               System.out.println("Liste des produits");
               bd.printData(bd.getAllData("product"));  
         }});
        
         /**-----------[[sous menu 2]]]------------------------*/
            
        Menu donnees = new Menu(1);
        mainMenu.add(new Options("2","Inserer des Donnes") {
        @Override
        public void doAction() {
            donnees.loopUntilExit();
        }});
        
        /**-----------[[sous menu 3]]]------------------------*/
        
        //Menu search= new Menu(1);
        mainMenu.add(new Options("3","Modifier port") {
        @Override
        public void doAction() {
            Scanner sc=new Scanner(System.in);
            System.out.println("Entrer votre port"); 
            bd.Closeflux();
             bd=new DataBase(sc.next());
        }});
        
        
         /**-----------[[sous menu 4]]]------------------------*/
        
        Menu facture= new Menu(1);
        mainMenu.add(new Options("4","Impression de facture") {
        @Override
        public void doAction() {
            facture.loopUntilExit();
        }});
         Menu facturecli= new Menu(2);
        facture.add(new Options("1","Imprimer la facture d'un client") {
        @Override
        public void doAction() {
                facturecli.loopUntilExit();
         }});
List<Table> coms=bd.getAllData("customer");

                    Customer c;
                    int n=1;
                     for(Table p:coms){
                         c=(Customer)p;
                         String name=c.getName();
                        facturecli.add(new Options(""+n,name) {
                        @Override
                        public void doAction() {
                                Bill b=new Bill();
                                b.setRef_customer(bd.getChamp("ref","nom='"+name+"'","customer"));
                                String r="ref_customer='"+b.getRef_customer()+"'";
                                
                                 b.setRef_order(bd.getChamp("ref",r,"order_product"));
                                 String r1="ref='"+b.getRef_order()+"'";
                                 
                                 List<Table> coms=bd.getData(r1,"order_product");
                                 ArrayList<Order> orders=new ArrayList<Order>();
                                 
                                 Order p1;
                         for(Table p:coms){
                             p1=(Order)p;
                             p1.setProduit(bd.getChamp("designation","ref='"+p1.getRef_pro()+"'","product"));
                                 if(p1.getProduit().length()<8)
                                 p1.setProduit(p1.getProduit().concat("\t"));
                            p1.setPrix(Integer.parseInt(bd.getChamp("prix","ref='"+p1.getRef_pro()+"'","product"))); 
                            orders.add(p1);
                         } 
                                 b.setRef("default");
                             Customer c=(Customer) bd.getByRef(b.getRef_customer(), "customer");
                             b.Afficher(c,orders);
                             
                             bd.insertData(b);
                        }});
                        n++;
                     }
                     
                     
        facture.add(new Options("2","Consulter la derniere facture generée") {
        @Override
        public void doAction() {
                try{
                 Bill b=(Bill)bd.getData("1=1 ORDER BY id DESC LIMIT 1","bill").get(0);
                 String r1="ref='"+b.getRef_order()+"'";
                 
                 List<Table> coms=bd.getData(r1,"order_product");
                 ArrayList<Order> orders=new ArrayList<Order>();
                   //System.out.println(r1);
                    Order p1;
                     for(Table p:coms){
                        p1=(Order)p;
                         p1.setProduit(bd.getChamp("designation","ref='"+p1.getRef_pro()+"'","product"));
                         if(p1.getProduit().length()<8)
                             p1.setProduit(p1.getProduit().concat("\t"));
                         p1.setPrix(Integer.parseInt(bd.getChamp("prix","ref='"+p1.getRef_pro()+"'","product"))); 
                        orders.add(p1);
                     } 
                     b.setRef("default");
                     Customer c=(Customer) bd.getByRef(b.getRef_customer(), "customer");
                     b.Afficher(c,orders);
                }
                catch(Exception e){
                    System.out.println("Echec Affichage facture");
                }
         }});
        
         /**-----------[[sous menu 5]]]--11----------------------*/
        
               
       Menu suppress = new Menu(1);
        mainMenu.add(new Options("5","Vider les tables") {
        @Override
        public void doAction() {
            suppress.loopUntilExit();
        }});
        
        suppress.add(new Options("1","clients") {
        @Override
        public void doAction() {
                System.out.println("Liste des Clients");
               bd.printData(bd.getAllData("customer"));
              
         }});
        
         suppress.add(new Options("1","clients") {
        @Override
        public void doAction() {
           
        bd.deleteData("customer");
       
         }});
         
          suppress.add(new Options("2","produit") {
        @Override
        public void doAction() {
    
        bd.deleteData("product");
              
         }});
          
           suppress.add(new Options("3","commandes") {
        @Override
        public void doAction() {
              
         bd.deleteData("order_product");
              
         }});
        
        suppress.add(new Options("4","factures") {
        @Override
        public void doAction() {
               bd.deleteData("bill");
              
         }});
    /********************end menu principle *********************************/    
        
       
        
        
      
        
        
      mainMenu.loopUntilExit();
}
    
}
