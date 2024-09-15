/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.util.ArrayList;


public class Member {
    String name;
    int id;
    boolean paid;
    ArrayList<Pet> membersPets;
    double totalAmount;
    

    public Member(String name, int id, boolean paid) {
        this.name = name;
        this.id = id;
        this.paid = paid;
        membersPets = new ArrayList<Pet>();
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public double getAmountSpent(){
        return totalAmount;
    }

    void setAmountSpent(double total) {
       totalAmount += total;
    }

    public ArrayList<Pet> getMembersPets() {
        return membersPets;
    }
    
    void addPet(Pet pet) {
        membersPets.add(pet);
    }
    
}
