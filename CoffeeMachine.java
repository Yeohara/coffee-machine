package machine;
import java.util.Scanner;

public class CoffeeMachine {
    static int money = 550;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int disposable_cups = 9;

    static void printState() {
        System.out.println("The coffee machine has:\n"
                        + CoffeeMachine.water + " of water\n"
                        + CoffeeMachine.milk + " of milk\n"
                + CoffeeMachine.beans + " of coffee beans\n"
                + CoffeeMachine.disposable_cups + " of disposable cups\n"
                + CoffeeMachine.money + " of money");
    }
    static void actionBuy (int coffee) {
        int money_received = 0;
        int water_used = 0;
        int beans_used = 0;
        int milk_used = 0;
        String missing_resource = "";
        if (CoffeeMachine.disposable_cups == 0) {
            System.out.println("Sorry, not enough cups!");
            return;
        }
        switch (coffee) {
            case 1:
                if (CoffeeMachine.water / 250 < 1) {
                    missing_resource += " water";
                }
                if (CoffeeMachine.beans / 16 < 1) {
                    missing_resource += " beans";
                }
                money_received = 4;
                water_used = 250;
                beans_used = 16;
                break;
            case 2:
                if (CoffeeMachine.water / 350 < 1) {
                    missing_resource += " water";
                }
                if (CoffeeMachine.milk / 75 < 1) {
                    missing_resource += " milk";
                }
                if (CoffeeMachine.beans / 20 < 1) {
                    missing_resource += " beans";
                }
                money_received = 7;
                water_used = 350;
                milk_used = 75;
                beans_used = 20;
                break;
            case 3:
                if (CoffeeMachine.water / 200 < 1) {
                    missing_resource += " water";
                }
                if (CoffeeMachine.milk / 100 < 1) {
                    missing_resource += " milk";
                }
                if (CoffeeMachine.beans / 12 < 1) {
                    missing_resource += " beans";
                }
                money_received = 6;
                water_used = 200;
                milk_used = 100;
                beans_used = 12;
                break;
        }
        System.out.println(missing_resource);
        if (!missing_resource.equals("")) {
            System.out.println("Sorry, not enough" + missing_resource + "!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            CoffeeMachine.money += money_received;
            CoffeeMachine.water -= water_used;
            CoffeeMachine.milk -= milk_used;
            CoffeeMachine.beans -= beans_used;
            CoffeeMachine.disposable_cups--;
        }
    }

    static void actionFill(int water, int milk, int beans, int cups) {
        CoffeeMachine.water += water;
        CoffeeMachine.milk += milk;
        CoffeeMachine.beans += beans;
        CoffeeMachine.disposable_cups += cups;
    }

    static void actionTake() {
        System.out.println("I gave you $" + CoffeeMachine.money);
        CoffeeMachine.money = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();
            endcase:
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String buyAction;
                    int coffee;
                    do {
                        buyAction = scanner.nextLine();
                        if (buyAction.equals("back")) {
                            break endcase;
                        } else {
                            coffee = Integer.parseInt(buyAction);
                            if (coffee < 4 && coffee > 0) {
                                break;
                            } else {
                                System.out.println("You must choose the number between 1 and 3!");
                            }
                        }
                    } while (scanner.hasNextLine());
                    actionBuy(coffee);
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    int water = scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    int milk = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    int beans = scanner.nextInt();
                    System.out.println("Write how many disposable cups do you want to add:");
                    int cups = scanner.nextInt();
                    actionFill(water, milk, beans, cups);
                    break;
                case "take":
                    actionTake();
                    break;
                case "remaining":
                    printState();
                    break;
                case "exit":
                    return;
            }
        }
    }
}
