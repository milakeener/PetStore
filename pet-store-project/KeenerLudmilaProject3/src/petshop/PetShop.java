
package petshop;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PetShop {

    public static void main(String[] args) {
        Store store = new Store();
        Scanner scan = new Scanner(System.in);
        double total;
        int choice = -1;
        
        store.loadInventoryFromFile("inventory.csv");

        while (choice != 6) {
            try {
                System.out.println("Please select between the following actions \n" +
                        "\t1. Make a purchase \n" +
                        "\t2. Register as a member \n" +
                        "\t3. Search for a member \n" +
                        "\t4. Make a donation \n" +
                        "\t5. Inventory value \n" +
                        "\t6. Close \n");
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        total = 0;
                        total = store.makeAPurchase();

                        System.out.println("Are you a member with us?");
                        System.out.println("\t 1.Yes\n\t 2.no");
                        int num;
                        int id = -1;
                        num = scan.nextInt();

                        while (num != 2 || store.isAMember(id)) {
                            if (num == 1) {
                                System.out.println("What is your member number?");
                                id = scan.nextInt();

                                if (store.findMember(id) instanceof Member) {
                                    if (store.findMember(id) instanceof PremiumMember) {
                                        PremiumMember temp = (PremiumMember) store.findMember(id);
                                        total = total - (total * 0.05);
                                        temp.setAmountSpent(total);
                                        System.out.println("Thank you for being a premium member with us! Your total today is $" + total);
                                        store.printMessage();
                                        break;
                                    }
                                    Member temp = store.findMember(id);
                                    System.out.println("Thank you for being a member with us");
                                    System.out.println("Your total is $" + total);
                                    temp.setAmountSpent(total);
                                    System.out.println("You have spent " + temp.getAmountSpent());
                                    store.printMessage();
                                    break;
                                } else {
                                    System.out.println("Would you like to try again?");
                                    System.out.println("\t 1.Yes\n\t 2.No");
                                    num = scan.nextInt();
                                }
                            }
                        }

                        if (num == 2) {
                            System.out.println("Your total is " + total + " please use the pin pad or pay with cash");
                            store.printMessage();
                            break;
                        }
                        break;

                    case 2:
                        store.addAMember();
                        break;

                    case 3:
                        int num1 = 0;

                        while (true) {
                            System.out.println("What is your member id?");
                            int memberId = scan.nextInt();
                            scan.nextLine();

                            if (store.isAMember(memberId)) {
                                System.out.println("Hello, yes, you are a member with us!");
                                break;
                            } else {
                                System.out.println("You are not a member. Would you like to try again?");
                                System.out.println("\t 1.Yes\n\t 2.no");
                                num1 = scan.nextInt();
                                scan.nextLine();

                                if (num1 == 2) {
                                    break;
                                }
                            }
                        }
                        break;

                    case 4:
                        System.out.println("***Thank you!***We will take great care of them :)");
                        System.out.println("We now have " + store.adoptionDrive(store.registerPet()) + " babies in our care");
                        break;

                    case 5:
                        System.out.println("The value of our inventory right now is " + store.inventoryValue());
                        break;

                    case 6:
                        store.generateEndOfDayReport();
                        store.updateInventoryFile();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch. Please enter a valid number.");
                scan.nextLine(); 
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }
}
