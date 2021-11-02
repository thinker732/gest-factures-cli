/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.*;
import utils.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */

public class DataBase{
    

    private final String nameDriver="com.mysql.jdbc.Driver";
    private String host;
    private String port;
    private String DBname;
    private  String url;
    private  String user="root";
    private  String pwd="";
    private Connection con=null;
    private Statement stmt=null;
    private ResultSet rs=null;

    public DataBase( String host, String port, String DBname,String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
        this.host = host;
        this.port = port;
        this.DBname = DBname;
        this.url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.DBname;
    }
      
    public DataBase() {
        this.url="jdbc:mysql://localhost:3306/facture?characterEncoding=utf8";
        this.user="root";
        this.pwd="";
        this.connect();
        this.loadDriver();
    }
    
    public DataBase(String port){
        this.url="jdbc:mysql://localhost:"+port+"/facture?characterEncoding=utf8";
        this.user="root";
        this.pwd="";
        this.connect();
        this.loadDriver();
    }
    
     public void loadDriver(){   
        try{   
            Class.forName(nameDriver);  
            System.out.println("Chargement Etablie");
        }
        catch(Exception e){
           System.out.println("Echec de chargement du Driver");
        }
        
      }
    
     public void connect(){
  
        try {  
            con=DriverManager.getConnection(this.url,this.user,this.pwd);
            System.out.println("Connexion Reussie");
        } catch (SQLException ex) {
            System.out.println("Echec Connexion du a:");
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }   
   
      } 
     
     public void Closeflux(){
                try {
                    
                    if(this.rs!=null)
                     this.con.close();
                     
                    if(this.stmt!=null)
                     this.con.close();
                     
                    if(this.con!=null)
                     this.con.close(); 
                    
                   // System.out.println("All database interaction are close");
                } catch (SQLException ex) {
                    System.out.println("Echec flux close");
            }
     }
     
    /**
     *
     * @param t:table in db
     * @return
     */
    public int insertData(Table t){
       
        String req;
        req = "INSERT INTO "+t.classname()+" VALUES"+t.toInsert();
       System.out.println("\n"+req);
        int nbr=0;
        try {
            stmt=this.con.createStatement();
            nbr=stmt.executeUpdate(req); 
           System.out.println("Insertion reussi");
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(nbr);
        }
        
          return nbr;   
       
    }
    /**
     * 
     * delete all data
     * @param t
     * @return 
     */
     public int deleteData(String t){
       
        String req;
        req =  "DELETE FROM "+t+" WHERE 1 = 1";
       System.out.println("\n"+req);
        int nbr=0;
        try {
            stmt=this.con.createStatement();
            nbr=stmt.executeUpdate(req); 
           System.out.println("Insertion reussi");
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(nbr);
        }
        
          return nbr;   
       
    }
     public List<Table> getAllData(String t){
          String request="SELECT * FROM "+t;
          //ArrayList<ArrayList<Object>> stringArrayList = new ArrayList<>();
          List<Table> listeP=new ArrayList<Table>();
          //Product p=new Product();
          try {           
            stmt=this.con.createStatement();
            rs=stmt.executeQuery(request);
            
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i < col+1; i++) {
                    row.add(rs.getObject(i));
                }
               // stringArrayList.add(row); 
                switch (t) {
                    case "order_product":
                        Order o=new Order(row);
                        listeP.add(o);
                        break;
                    case "customer":
                        Customer c=new Customer(row);
                        listeP.add(c);
                        break;
                    case "product":
                        Product p=new Product(row);
                        listeP.add(p);
                        break;
                    case "bill":
                        Bill b=new Bill(row);
                        listeP.add(b);
                     break;
                    default:
                        break;
                }
                    
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeP;
     }
      public List<Table> getData(String query,String t){
          String request="SELECT * FROM "+t+" where "+query;
          
          // System.out.println(request);
          //ArrayList<ArrayList<Object>> stringArrayList = new ArrayList<>();
          List<Table> listeP=new ArrayList<Table>();
          
          try {           
            stmt=this.con.createStatement();
            rs=stmt.executeQuery(request);
            
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i < col+1; i++) {
                    row.add(rs.getObject(i));
                }
                 
                
                switch (t) {
                    case "order_product":
                        Order o=new Order(row);
                        listeP.add(o);
                        break;
                    case "customer":
                        Customer c=new Customer(row);
                        listeP.add(c);
                        break;
                    case "product":
                        Product p=new Product(row);
                        listeP.add(p);
                        break;
                    case "bill":
                        Bill b=new Bill(row);
                        listeP.add(b);
                     break;
                    default:
                        break;
                }
                    
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeP;
     }
      
       /**
        * 
        * print table data
        * @param liste 
        */
       public void printData(List<Table> liste){
         Iterator<Table> iterator=liste.iterator();
         
          while(iterator.hasNext()){
                 System.out.print(iterator.next().toString()+"\n");   
              }
         }
       
       /**
        * get by the ref of the table
        * @param ref
        * @param t
        * @return 
        */
       public Table getByRef(String ref,String t){
          String request="SELECT * FROM "+t+" WHERE ref='"+ref+"'"; 
          Table T;
         // System.out.println(request);    
          try {           
            stmt=this.con.createStatement();
            rs=stmt.executeQuery(request);
             int col = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 1; i < col+1; i++) {
                    row.add(rs.getObject(i));
                }
                 
                
                switch (t) {
                    case "order_product":
                        Order o=new Order(row);
                        return o;
                    case "customer":
                        Customer c=new Customer(row);
                        return c;
                        
                    case "product":
                        Product p=new Product(row);
                        return p;
                        
                    case "bill":
                        Bill b=new Bill(row);
                        return b;
                        
                        
                    default:
                        break;
                }
            rs.close(); 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
  }

       
   /**
    * get Data Advance
    * @param Champ
    * @param query
    * @param t
    * @return 
    */    
   public String getChamp(String Champ,String query,String t){
          String request="SELECT "+Champ+" FROM "+t+" where "+query;
          String value="none";
         // System.out.println(request);
          try {           
            stmt=this.con.createStatement();
            rs=stmt.executeQuery(request);
            
            while (rs.next()){
                value=rs.getObject(1).toString();     
               break;     
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return value;
     }     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
  }