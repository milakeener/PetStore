/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;



public class Store implements PetStoreSpecification {
    ArrayList<Pet> inventory = new ArrayList();
    ArrayList<Member> members = new ArrayList();
    ArrayList<Pet> cart = new ArrayList();
    double total = 0.00;
    Scanner scan = new Scanner(System.in);
    int petID = 1;
    
    public Store(){
     
        System.out.println("******************************************");
        System.out.println("**** Hello! Welcome to the pet store! ****");
        System.out.println("******************************************");
       
        
        // Dog dog1 = new Dog(
        //     "Waffle",
        //     "Female",
        //     12,
        //     85,
        //     getNextPetID(),
        //     "German Shepherd"
        // );
        
        // Dog dog2 = new Dog(
        //         "Poe", 
        //         "Male", 
        //         3, 
        //         35.0, 
        //         getNextPetID() ,
        //         "Corgi");
        
        // Cat cat1 = new Cat("Karma",
        //         "Female",
        //         6,
        //         15,
        //         getNextPetID(),
        //         "Orange Tabby");
        
        // Cat cat2 = new Cat("Kit", 
        //         "Male", 
        //         6, 
        //         18, 
        //         getNextPetID(), 
        //         "Maine Coon");
        
        // ExoticPet ep1 = new ExoticPet("Pancake",
        //         "Male",
        //         6,
        //         0.8,
        //         getNextPetID(),
        //         "Bearded Dragon");
        
        // ExoticPet ep2 = new ExoticPet(
        //         "Noodle",
        //         "Male",
        //         4,
        //         2,
        //         getNextPetID(),
        //         "Ball Python");

        // inventory.add(dog1);
        // inventory.add(dog2);
        // inventory.add(cat1);
        // inventory.add(cat2);
        // inventory.add(ep1);
        // inventory.add(ep2);

        Member member1 = new Member(
                "Jo", 
                generateMemberid(), 
                true);
        
        member1.addPet(
                new Cat("Valjean",
                        "Male",
                        1, 
                        10, 
                        getNextPetID(), 
                        "White tabby")
        );
        members.add(member1);

        PremiumMember member2 = new PremiumMember("Sage", 
                generateMemberid(), 
                false);
        
        members.add(member2);
        
        member2.addPet(
            new ExoticPet("Smaug", 
                    "Male", 
                    5, 
                    1, 
                    getNextPetID(), 
                    "Bearded Dragon")
        );
    }
    
    public double makeAPurchase() {
    try {
        System.out.println("Please enter the product number of the item you would like to buy *Press 0 when you are done*\n");
        printInventory();
        int num = scan.nextInt();
        cycleInventory(num);

        while ((num != 0) && !inventory.isEmpty()) {
            System.out.println("What else would you like to buy? Press 0 to exit\n");
            printInventory();
            num = scan.nextInt();
            cycleInventory(num);
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid product number.");
        // Consume the invalid input to avoid an infinite loop
        scan.nextLine();
        // You may choose to return or throw an exception, or handle the error as appropriate for your application
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace(); // Print the stack trace for debugging purposes
        // You may choose to return or throw an exception, or handle the error as appropriate for your application
    }

    return total;
}
    
    public void addAMember() {
    try {
        System.out.println("What is your name?");
        String name = scan.next();

        System.out.println("Would you like to become a premium member? It's only $4.99 a month!");
        System.out.println("\t 1.Yes\n\t 2.No");
        int choice = scan.nextInt();

        if (choice == 1) {
            PremiumMember member = new PremiumMember(name, generateMemberid(), true);
            member.setPaid(true);
            members.add(member);
            System.out.println("Thank you for being a premium member! Your member id is " + member.getId());
        } else if (choice == 2) {
            Member member = new Member(name, generateMemberid(), false);
            members.add(member);
            System.out.println("Thank you for being a member with us! Your member id is " + member.getId());
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter valid values.");
        // Consume the invalid input to avoid an infinite loop
        scan.nextLine();
        // You may choose to return or throw an exception, or handle the error as appropriate for your application
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace(); // Print the stack trace for debugging purposes
        // You may choose to return or throw an exception, or handle the error as appropriate for your application
    }
}
    
    
    public boolean isAMember(int id){ //returns true if member is found false if not
       for(Member m : members){
           if(m.getId()== id){
               return true;
           }
       }
       return false;
    }
    
    public void printInventory(){ //prints inventory
        for(Pet p : inventory){
            if(p instanceof Dog ){
            System.out.println("**** Dog ****");
            System.out.println("\t" + p.toString());
            }
            if(p instanceof Cat){
                System.out.println("**** Cat ****");
                System.out.println("\t" + p.toString());
            }
            if(p instanceof ExoticPet){
                System.out.println("**** EP:" + ((ExoticPet) p).getBreedOrSpecies()+ " ****");
                System.out.println("\t" + p.toString());
            }
        }
    }
    
    public void cycleInventory(int pNum){//cycles through the inventory to find a product that a user wants and removes it
        ArrayList<Pet> copy = new ArrayList(inventory);
        for(Pet p : copy){
            if(pNum == p.getID()){
                total = total + p.getPrice();
                cart.add(p);
                inventory.remove(p);
            }
        }
        copy = inventory;
    }
  
    public Member findMember(int id){ //returns a member based on the id inputed
        for(Member m : members){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public int generateMemberid(){ //generates a user number from 1 to 50
        int random;
        random = (int)(Math.random() * 50 + 1);
        return random;
    }
    
    public int getNextPetID() {
        petID++;
        return petID - 1;
    }
    
    
    public ArrayList<Pet> registerPet() {
    ArrayList<Pet> donations = new ArrayList();

    try {
        System.out.println("How many pets would you like to donate?");
        int numOfPets = scan.nextInt();

        for (int i = 0; i < numOfPets; i++) {
            System.out.println("What is your pet?");
            System.out.println("\t 1.Dog\n\t 2.Cat\n\t 3.Exotic Animal/Other");
            int num = scan.nextInt();

            System.out.println("What is the pet's name?");
            String name = scan.next();

            System.out.println("What is your pet's sex?");
            String sex = scan.next();

            System.out.println("How old is your pet?");
            int age = scan.nextInt();

            System.out.println("What is your pet's weight?");
            double weight = scan.nextDouble();

            if (num == 1) {
                System.out.println("What Breed is your dog?");
                String breed = scan.next();
                Dog dog = new Dog(name, sex, age, weight, getNextPetID(), breed);
                donations.add(dog);
            } else if (num == 2) {
                System.out.println("What Breed is your cat?");
                String breed = scan.next();
                Cat cat = new Cat(name, sex, age, weight, getNextPetID(), breed);
                donations.add(cat);
            } else if (num == 3) {
                System.out.println("What species is your exotic pet?");
                String species = scan.next();
                ExoticPet exoticPet = new ExoticPet(name, sex, age, weight, getNextPetID(), species);
                donations.add(exoticPet);
            }
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter valid values.");
        scan.nextLine();
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace(); // Print the stack trace for debugging purposes
    }

    return donations;
}
    
    public void printMessage(){
        System.out.println("***************************");
        System.out.println("******* Thank you :) ******");
        System.out.println("***************************");
    }

    /**
     * update inventory by adding the given pets
     * to the store's current inventory
     * 
     * @param pets
     * @return
     */
    @Override
    public int adoptionDrive(ArrayList<Pet> pets) {
        ArrayList<Pet> temp = new ArrayList(pets);
        for(Pet p : pets){
            inventory.add(p);
            temp.remove(p);
        }
        temp = pets;
        return inventory.size();
    }

    @Override
    public double inventoryValue() {
        total = 0;
        for(Pet p : inventory){
            total += p.getPrice();
        }
        return total;
    }
    
    public void loadInventoryFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))){
        while (fileScanner.hasNextLine()) {
            String [] parts = fileScanner.nextLine().split(",");
            
            String name = parts[0];
            String breedOrSpecies = parts[1];
            String sex = parts[2];
            int age = Integer.parseInt(parts[3]);
            double weight = Double.parseDouble(parts[4]);
            String type = parts[5];

            Pet pet;

            switch (type.toLowerCase()) {
                case "dog":
                    pet = new Dog(name, sex, age, weight, getNextPetID(), breedOrSpecies);
                    break;
                case "cat":
                    pet = new Cat(name, sex, age, weight, getNextPetID(), breedOrSpecies);
                    break;
                case "exoticpet":
                    pet = new ExoticPet(name, sex, age, weight, getNextPetID(), breedOrSpecies);
                    break;
                default:
                    // Handle unknown types or log an error
                    System.out.println("Unknown pet type: " + type);
                    continue;  // Skip to the next line
            }

            // Add the created Pet object to the inventory ArrayList
            inventory.add(pet);
            
        }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        }
    
    public void generateEndOfDayReport() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("EndOfDayReport.txt"))) {
        // Include purchased products
        writer.println("Purchased Products:");
        for (Pet pet : cart) {
            writer.println("\t" + pet.getName() + " - $" + pet.getPrice());
        }

        // Include new member registrations
        writer.println("\nNew Member Registrations:");
        for (Member member : members) {
            writer.println("\t" + member.getName() + " (ID: " + member.getId() + ")");
        }

        // Include total sales and revenue
        writer.println("\nTotal Sales and Revenue:");
        writer.println("\tTotal Sales: $" + total);
        writer.println("\tRevenue: $" + calculateRevenue());

        // Add more details as needed

        writer.println("\nEnd of Day Report");
    } catch (IOException e) {
        System.out.println("Error writing end-of-day report: " + e.getMessage());
    }
}
    
     private double calculateRevenue() {
        double revenue = total; // Initialize with total sales

        // Check if there are premium members with discounts
        for (Member member : members) {
            if (member instanceof PremiumMember && ((PremiumMember) member).isPaid()) {
                // Apply a 5% discount for premium members
                revenue -= total * 0.05;
            }
        }

        return revenue;
    }
     
     private String getPetType(Pet pet) {
        if (pet instanceof Dog) {
            return "Dog";
        } else if (pet instanceof Cat) {
            return "Cat";
        } else if (pet instanceof ExoticPet) {
            return "ExoticPet";
        } else {
            // Handle unknown types or log an error
            System.out.println("Unknown pet type: " + pet.getClass().getSimpleName());
            return "Unknown";
        }
    }
     
    public void updateInventoryFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("UpdatedInventory.csv"))) {
            // Write the updated inventory to the new CSV file
            for (Pet pet : inventory) {                       
                String line = String.format("%s,%s,%s,%d,%.2f,%s",
                        pet.getName(), pet.getBreedOrSpecies(), pet.getSex(),
                        pet.getAge(), pet.getWeight(), getPetType(pet));

                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error updating inventory file: " + e.getMessage());
        }
    }
   
}
