import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class ThePioneerTrail {

    private static Scanner scanner = new Scanner(System.in);
    private static int milesTraveled = 0;
    private static int foodSupplies = 250;
    private static int health = 100;
    private static int daysOnTrail = 0;
    private static int favorsDone = 0;
    private static String leaderName;
    private static String partnerName;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-help")) {
            System.out.println("HELP");
            displayHelp();
            System.exit(0);
        }

        slowPrintln("\u001B[31mWelcome to your journey! \u001B[0m");
        slowPrintln("Enter your wagon leader's name: ");
        leaderName = scanner.nextLine();
        slowPrintln("You have one family member aboard your wagon. What is their name?");
        partnerName = scanner.nextLine();

        slowPrintln("Hello, \u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m!");
        slowPrintln("\u001B[31mHere's what you have for this journey: \u001B[0m");
        slowPrintln("Health: " + health);
        slowPrintln("Food: " + foodSupplies + " food");

        slowPrintln("Stay alive for 1000 miles.");

        // introduction to game, takes in user input for two names

        while (milesTraveled < 1000 && health > 0 && foodSupplies > 0) {
            System.out.println("\u001B[36m\n--- Options ---\u001B[0m");
            System.out.println("1. Continue on the trail");
            System.out.println("2. Hunt for food");
            System.out.println("3. Rest for the day");
            System.out.println("4. Barter menu");
            System.out.println("5. Quit the game");
            System.out.println("\u001B[31mChoose an option: \u001B[0m");
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    continueOnTrail();
                    break;
                case 2:
                    huntForFood();
                    break;
                case 3:
                    restForDay();
                    break;
                case 4:
                    barterMenu();
                    break;
                case 5:
                    quitGame();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    //menu for user to choose from, if input is invalid, it will print invalid choice
            }
        }

        if (milesTraveled >= 1000) {
            slowPrintln("\u001B[33mCongratulations! \u001B[34m");
            slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and " + "\u001B[32m" + partnerName + "\u001B[0m successfully reached their destination in " + daysOnTrail + " days.");
        } else {
            slowPrintln("\u001B[31mGame Over!\u001B[0m");
            slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m didn't make it to their destination. Better luck next time!");

        }
        //Message to show that the game is completed when user either makes it or does not

        scanner.close();
    }

    public static void continueOnTrail() {
        boolean randomHunterEventOccurs = Math.random() < 0.2;
        if (randomHunterEventOccurs) {
            randomHunterEvent();
        } else {
            int milesThisDay = (int) (Math.random() * 100) + 45;
            milesTraveled += milesThisDay;

            foodSupplies -= (int) (Math.random() * 21) + 10;
            health -= (int) (Math.random() * 31);
            daysOnTrail++;

            slowPrintln("You traveled " + milesThisDay + " miles.");
            slowPrintln("Total miles traveled: " + milesTraveled);
            slowPrintln("Current supplies: " + foodSupplies + " food ");
            slowPrintln("Health: " + health);
            slowPrintln("Days passed: " + daysOnTrail);

            //calls randomHunterEvent method if user continues trail, chance it can/cannot happen due to it being randomized
        }
    }


    private static void randomHunterEvent() {
        daysOnTrail++;
        
        slowPrintln("\u001B[31mWATCH OUT! THERE'S A GROUP OF HUNTERS! WHAT WILL YOU DO?! \u001B[0m");
        slowPrintln("Current food supplies: " + foodSupplies);
        slowPrintln("Current health: " + health);
        slowPrintln("1. Give up half your food supplies.");
        slowPrintln("2. Fight them by sacrificing half your health.");
        slowPrintln("Choose an option: ");
        int hunterEventChoice = scanner.nextInt();

        switch (hunterEventChoice) {
            case 1:
                giveHuntersFoodChoice();
                break;
            case 2:
                fightHuntersChoice();
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");

                //choices of giving food or fighting is shown if user ever encounters hunters on trail
        }
    }

    private static void giveHuntersFoodChoice() {
        foodSupplies = (int) (foodSupplies / 2);
        slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m gave the hunters half their food supplies.");
        slowPrintln("The hunters might return soon. Be careful.");
        slowPrintln("Remaining food: " + foodSupplies);
        slowPrintln("Days passed: " + daysOnTrail);

        //option when user decides to give food... will lose food from supplies
    }

    private static void fightHuntersChoice() {
        health = (int) (health / 2);
        slowPrintln("You fought the hunters and won!");
        slowPrintln("However, both \u001B[34m" + leaderName + "\u001B[0m, and \u001B[32m" + partnerName + "\u001B[0m are injured.");
        slowPrintln("Remaining Health: " + health);
        slowPrintln("Days passed: " + daysOnTrail);

        //option when user decides to fight... will lose half their health
    }

    public static void huntForFood() {
        boolean ifFoodCaughtToday = Math.random() < 0.7;
        if (ifFoodCaughtToday) {
            int foodCaught = (int) (Math.random() * 51) + 20;
            foodSupplies += foodCaught;
            slowPrintln("You caught " + foodCaught + " pounds of food.");
        } else {
            health -= 5;
            slowPrintln("Unlucky... no food caught today.");
            slowPrintln(leaderName + " and " + partnerName + " got hurt while their food got away!");
            
        }
        daysOnTrail++;
        slowPrintln("Current Supplies: " + foodSupplies + " food");
        slowPrintln("Total miles traveled: " + milesTraveled);
        slowPrintln("Health: " + health);
        slowPrintln("Days passed: " + daysOnTrail);
    }

    public static void restForDay() {
        boolean ifGoodRestToday = Math.random() < 0.8;
        if (ifGoodRestToday) {
            health += 15;
            adjustHealthPoints();
            slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m rested for the day.");
            slowPrintln("Health: " + health);
        } else {
            health -= 20;
            slowPrintln("Horrible rest today.");
            slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m were attacked by bugs.");
            slowPrintln("Current health: " + health);
        }
        daysOnTrail++;
        slowPrintln("Total miles traveled: " + milesTraveled);
        slowPrintln("Current supplies: " + foodSupplies + " food ");
        slowPrintln("Days passed: " + daysOnTrail);
    }

    public static void barterMenu() {
        slowPrintln("You are traveling with dozens of other travelers.");
        slowPrintln("You may barter your supplies here.\n");

        System.out.println("\u001B[36m--- Barter Menu ---\u001B[0m");
        System.out.println("1. Trade food supplies");
        System.out.println("2. Do a favor for someone");
        System.out.println("3. Return to main menu");
        System.out.println("\u001B[31mChoose an option: \u001B[0m");
        int barterChoice = scanner.nextInt();

        switch(barterChoice) {
            case 1:
                tradeFood();
                break;
            case 2:
                doAFavorBarter();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
    }


    private static void tradeFood() {
        slowPrintln("Every 3 food you own can be traded for a health point.");
        slowPrintln("Make sure not to deplete your food supply though!");
        slowPrintln("Please enter how much food you're willing to trade: ");

        int foodToBarter = scanner.nextInt();

        if (foodToBarter <= 0) {
            slowPrintln("Invalid input. Please enter a positive value.");
            return; // Exit the method if the input is invalid
        }

        if (foodToBarter > foodSupplies) {
            slowPrintln("You don't have enough food for that trade.");
            return; // Exit the method if the player doesn't have enough food
        }

        // Calculate the health gained from the food trade
        int healthGained = foodToBarter / 3;

        // Update the food supplies and health
        foodSupplies -= foodToBarter;
        health += healthGained;
        adjustHealthPoints();

        slowPrintln("Traded " + foodToBarter + " food for " + healthGained + " health.");
        slowPrintln("Current food supplies: " + foodSupplies + " food");
        slowPrintln("Health: " + health);
    }


    private static void doAFavorBarter() {
        slowPrintln("Here you can sacrifice 20 health points for a fellow traveler to transport you 100 miles.");
        slowPrintln("You can do this a maximum of 3 times.");
        slowPrintln("Current health: " + health);
        slowPrintln("Do you accept? (Enter '1' for Yes, '2' for No): ");
        int performFavorOrNotChoice = scanner.nextInt();

        switch (performFavorOrNotChoice) {
            case 1:
                if (favorsDone < 3) {
                    slowPrintln("\u001B[34m" + leaderName + "\u001B[0m and \u001B[32m" + partnerName + "\u001B[0m decide to perform a favor.");

                    health -= 20;

                    int milesTraveledForFavor = 100;
                    milesTraveled += milesTraveledForFavor;
                    daysOnTrail++;

                    slowPrintln("The fellow traveler took you both " + milesTraveledForFavor + " miles today.");
                    slowPrintln("Remaining health: " + health);
                    slowPrintln("Total miles traveled: " + milesTraveled);
                    slowPrintln("Days passed: " + daysOnTrail);

                    favorsDone++;
                } else {
                    slowPrintln("You have already done the maximum allowed favors. No more favors available.");
                }
                break;

            case 2:
                slowPrintln("You chose not to perform the favor. Returning to the Main Menu.");
                break;

            default:
                slowPrintln("Invalid choice. Returning to the Barter Menu.");
                break;
            
        }

        // Return to the main menu after performing the favor or not
        return;
    }

    public static void quitGame() {
        slowPrintln("You decided to quit the game. Goodbye!");
        System.exit(0);
    } 


    private static void adjustHealthPoints() {
        if(health > 100) {
            health = 100;
        }
        //makes sure health doesn't exceed 100
    }

    public static void displayHelp() {
        System.out.println("Rules: ");
        System.out.println("Travel 1000 miles before your health or food supplies reaches 0.");
        System.out.println("You have a lot of options to select on your journey.");
        System.out.println("\nHints: ");
        System.out.println("Make sure to keep food supplies high!");
        System.out.println("You can always use food to replenish your health.");
    }


    private static void slowPrintln(String output) {
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}