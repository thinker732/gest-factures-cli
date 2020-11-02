/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bidzang Atangana Emmanuel;Doum Akono Rudolph;Juengang Dunelle ;Gankam Suzanne
 */
public class Customer extends Table{
    
    
    public static int num=001;
    private String ref;
    private int id;
    private String name;
    private String tel;
    private String address;

    public Customer() {
    }

   
    public Customer(ArrayList<Object> l) {
        
        this.ref = l.get(1).toString();
       this.name = l.get(2).toString();
        this.tel = l.get(3).toString();
       this.address = l.get(4).toString();
       
    }
    
   
    public Customer(String name, String tel, String address) {
        this.ref = "cli"+num;
        this.name = name;
        this.tel = tel;
        this.address = address;
        num++;
    }
    public Customer(String ref,String name, String tel, String address) {
        this.ref = ref;
        this.name = name;
        this.tel = tel;
        this.address = address;
        num++;
    }
   @Override
    public String classname() {
        return "customer";
        }
    
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", ref=" + ref + ", name=" + name + ", tel=" + tel + ", address=" + address + '}';
    }
    
    /*data formating for db insert instruction*/
     public String toInsert() {
        return "(null,'"+ ref + "','" + name + "','" + tel + "','" + address + "')";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
