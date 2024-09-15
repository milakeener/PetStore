/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

public abstract class Pet implements Comparable<Pet>{
    private String name;
    private String sex;
    private String breedOrSpecies;
    protected int age;
    protected double weight;
    private int ID;
    protected double price;

    public Pet(String name, String sex, int age, double weight, int ID, String breedOrSpecies) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.ID = ID;
        this.price = setPrice();
        this.breedOrSpecies = breedOrSpecies;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public int getID() {
        return ID;
    }

    public double getPrice() {
        return this.price;
    }
    
    public String getBreedOrSpecies(){
        return breedOrSpecies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setbreedOrSpecies(String breedOrSpecies){
        this.breedOrSpecies = breedOrSpecies;
    }

    public double setPrice() {
        price = 100.00;
        if(age < 5)
            price += 50;
        if(weight < 10){
            price += 100;
        }
        return price;
    } 

    @Override
    public String toString() {
        return "Pet#" + ID + " Name " + name + " Sex: " + sex + " Age: " + age + " Weight: " + weight + " Price: " + price;
    } 

    
    @Override
    public int compareTo(Pet o) {
        if(getPrice()> o.getPrice()){
            return 1;
        } else if (getPrice() < o.getPrice()){
            return -1;
        } else {
            return 0;
        }
    }
    
}
