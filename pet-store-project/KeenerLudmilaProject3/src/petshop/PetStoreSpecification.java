/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package petshop;

import java.util.ArrayList;

public interface PetStoreSpecification {
    /**
     * update inventory by adding the given pets
     * to the store's current inventory
     * 
     * @param pets
     * @return
     */
    public int adoptionDrive (ArrayList<Pet> pets);
    
    /**
     * calculate and return dollar amount for current inventory of pets
     * that are in stock
     * 
     * @return total in stock value
     */
    public double inventoryValue();
}
