import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        welcome();
        System.out.println("Would you like to Book a night or Exit?");
        System.out.println("[1] Book a night");
        System.out.println("[0] Exit");
        System.out.print("~> ");
        int userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                int inputRoomSelection = getRoomType(input);
                int inputSizeSelection = getSize(inputRoomSelection,input);
                int priceofRoomSize = getPriceRoom(inputRoomSelection,inputSizeSelection);
                System.out.println("PRICE = "+ priceofRoomSize); //TEST
                            
                //izzy
                int nightofStay = getHowManyNights(input);

                int totalPrice = priceofRoomSize * nightofStay;
                
                System.out.println("Total cost: " + totalPrice);
                break;
        
            case 0:
                System.exit(0);
            default:
                break;
        }
    }

    private static void welcome() {
        System.out.println("WELCOME TO HOTEL DE LUNA");
    }
    //ADDED GUEST CHARGE CALCULATION, RETURN TYPE DOUBLE
    private static double guestCharge(double totalBill){ 
        double charge = totalBill * 0.10;
        return charge;
   }
    private static int getRoomType(Scanner userRoomchoice){
        System.out.println("Select your Room Type");
        System.out.print("[1] Standard\n[2] Deluxe\n[3] Suite\n");
        System.out.print("~> ");
        return userRoomchoice.nextInt();
    }
    private static int getSize(int roomType, Scanner input){
        switch (roomType) {
            case 1:
                System.out.println("Select Size");
                System.out.print("[1] Single (1800.00) \n[2] Double (2700.00)\n");
                break;
            case 2:
                System.out.println("Select Size");
                System.out.print("[1] Single (2300.00) \n[2] Double (3200.00)\n");
                break;
            case 3:
                System.out.println("Select Size");
                System.out.print("[1] Single (3000.00) \n[2] Double (4000.00)\n");
                break;
            default:
                break;
        }
        System.out.print("~> ");
        return input.nextInt();
    }
    private static int getPriceRoom(int getRoomType, int userSizeChoice){
        int userChoicePrice = 0;
        int singleOccupancy , doubleOccupancy;
        switch (getRoomType){
            case 1://STANDARD
                singleOccupancy = 1800;
                doubleOccupancy = 2700;
                switch (userSizeChoice) {
                    case 1:
                        userChoicePrice = singleOccupancy;
                        break;
                    case 2:
                        userChoicePrice = doubleOccupancy;
                        break;
                    default:
                        break;
                }
                break;
            case 2://DELUXE
                singleOccupancy = 2300;
                doubleOccupancy = 3200;
                switch (userSizeChoice) {
                    case 1:
                        userChoicePrice = singleOccupancy;
                        break;
                    case 2:
                        userChoicePrice = doubleOccupancy;
                        break;
                    default:
                        break;
            }
                break;
            case 3://SUITE
                singleOccupancy = 3000;
                doubleOccupancy = 4000;
                switch (userSizeChoice) {
                    case 1:
                        userChoicePrice = singleOccupancy;
                        break;
                    case 2:
                        userChoicePrice = doubleOccupancy;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return userChoicePrice;
    }
    
     private static int getHowManyNights(Scanner input) {
        System.out.println("How many nights you will be staying?");
        System.out.print("~> ");
        return input.nextInt();
    }
}